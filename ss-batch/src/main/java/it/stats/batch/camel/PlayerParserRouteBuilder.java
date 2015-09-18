/**
 * 
 */
package it.stats.batch.camel;

import it.stats.batch.camel.jdbc.bean.PlayerBean;
import it.stats.batch.camel.processor.GetChildElementProcessor;
import it.stats.batch.camel.processor.GetElementsProcessor;
import it.stats.batch.camel.processor.SetFieldsProcessor;
import it.stats.batch.util.FieldParam;
import it.stats.batch.util.ParserConstants;
import it.stats.batch.util.ParserUtil;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Predicate;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author fabrizio
 *
 */
public class PlayerParserRouteBuilder extends RouteBuilder
{
	public static String PARSE_PLAYER_URI = "direct:playerParser";
	
	private Logger logger = LoggerFactory.getLogger(PlayerParserRouteBuilder.class); 
	
	public static FieldParam[] PLAYER_FIELDS = new PlayerFields[] {
		new PlayerFields("PlayerId", "playerId")
		, new PlayerFields("First name", "name")
		, new PlayerFields("Last name", "surname")
		, new PlayerFields("Nationality", "nationality")
		, new PlayerFields("Date of birth", "birthDate")
		, new PlayerFields("Country of birth", "birthCountry")
		, new PlayerFields("Place of birth", "birthPlace")
		, new PlayerFields("Position", "position")
		, new PlayerFields("Height", "height")
		, new PlayerFields("Weight", "weight")
		, new PlayerFields("Foot", "foot")
		, new PlayerFields("Picture URL", "pictureUrl")
		, new PlayerFields("Picture", "picture")
	};
	
	public static String PLAYER = "player";
	
	private static String PLAYER_ELEMENT = "playerElement";
	
	private static class PlayerFields implements FieldParam
	{
		private PlayerFields(String fieldPageKey, String fieldName)
		{
			this.fieldName = fieldName;
			this.fieldPageKey = fieldPageKey;
		}
		
		private String fieldName;
		
		private String fieldPageKey;

		public String getFieldName() 
		{
			return fieldName;
		}

		public String getFieldPageKey() 
		{
			return fieldPageKey;
		}
	}
	
	Predicate playerNotFound = header(GetElementsProcessor.ELEMENTS_NOT_FOUND_KEY).isEqualTo(true);
	
	@Override
	public void configure() throws Exception 
	{
		from(PARSE_PLAYER_URI)
		.process(new Processor() 
		{
			public void process(Exchange exchange) throws Exception 
			{
				String playerId = exchange.getIn().getBody(String.class);
				
				logger.info("Parse player "+playerId);
				
				Map<String, Object> player = new HashMap<String, Object>();
				player.put(PLAYER_FIELDS[0].getFieldName(), playerId);
				exchange.setProperty(PLAYER, player);
				
				exchange.getIn().setBody(ParserConstants.SW_SITE+"/players/1/"+playerId);
			}
		})
		.to(GetDocumentRouteBuilder.GET_DOCUMENT_URI)
		.setHeader(GetElementsProcessor.GET_ELEMENT_KEY, simple(it.stats.batch.camel.processor.GetElementsProcessor.GetElementType.BY_CLASS.name()))
		.setHeader(GetElementsProcessor.CLASSNAME_KEY, simple(ParserConstants.CLASS_PLAYER_PASSPORT))
		.processRef(GetElementsProcessor.REF)
		.choice()
			.when(playerNotFound)
				.to("direct:playerNotFound")
			.otherwise()
				.to("direct:processDataPlayer")
			.end()
		.end();
		
		from("direct:playerNotFound")
		.process(new Processor() 
		{
			public void process(Exchange exchange) throws Exception 
			{
				// TODO
				logger.info("################# direct:playerNotFound... TODO !!!");
			}
		})
		.end();
		
		from("direct:processDataPlayer")
		.process(new Processor() 
		{
			public void process(Exchange exchange) throws Exception 
			{
				Elements playersElements = exchange.getIn().getBody(Elements.class);
				if(playersElements != null
						&& !playersElements.isEmpty())
				{
					exchange.setProperty(PLAYER_ELEMENT, playersElements.get(0));
					exchange.getIn().setBody(playersElements.get(0));
					exchange.getIn().setHeader(GetChildElementProcessor.CHILD_INDEX, "0|0|0|0|0");
				}
			}
		})
		.processRef(GetChildElementProcessor.REF)
		.process(new Processor() 
		{
			public void process(Exchange exchange) throws Exception 
			{
				Element element = exchange.getIn().getBody(Element.class);
				if(element != null
						&& element.children() != null
						&& !element.children().isEmpty())
				{
					exchange.getIn().setBody(element.children());
					exchange.getIn().setHeader(SetFieldsProcessor.FIELDS, PLAYER_FIELDS);
					exchange.getIn().setHeader(SetFieldsProcessor.PROPERTY_KEY, PLAYER);
				}
			}
		})
		.processRef(SetFieldsProcessor.REF)
		
		.process(new Processor() 
		{
			public void process(Exchange exchange) throws Exception 
			{
				Elements playersElements = exchange.getIn().getBody(Elements.class);
				if(playersElements != null
						&& !playersElements.isEmpty())
				{
					Element playerElement = exchange.getProperty(PLAYER_ELEMENT, Element.class);
					exchange.getIn().setBody(playerElement);
					exchange.getIn().setHeader(GetChildElementProcessor.CHILD_INDEX, "0|0|1|0");
				}
			}
		})
		.processRef(GetChildElementProcessor.REF)
		.process(new Processor() 
		{
			public void process(Exchange exchange) throws Exception 
			{
				Element img = exchange.getIn().getBody(Element.class);
				if(img != null)
				{
					@SuppressWarnings("unchecked")
					Map<String,Object> playerMap = exchange.getProperty(PLAYER, Map.class);
					playerMap.put(PLAYER_FIELDS[12].getFieldName(), ParserUtil.getBytesFromImage(img.attr(ParserConstants.SRC)));
//					p.setPictureUrl(img.attr(SWConstants.SRC));
				}
			}
		})
		.process(new Processor() 
		{
			public void process(Exchange exchange) throws Exception 
			{
				exchange.getIn().setBody(exchange.getProperty(PLAYER));
				exchange.getIn().setHeader(UpsertRouteBuilder.BEAN_NAME_KEY, PlayerBean.class.getSimpleName());
				
				logger.info("Parse player EXIT : "+exchange.getProperty(PLAYER));
			}
		})
		.to(UpsertRouteBuilder.UPSERT_URI)
		.end();
	}
}
