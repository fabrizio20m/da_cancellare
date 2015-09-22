/**
 * 
 */
package it.stats.batch.camel;

import it.stats.batch.camel.bean.PlayerBean;
import it.stats.batch.camel.processor.GetChildElementProcessor;
import it.stats.batch.camel.processor.GetElementsProcessor;
import it.stats.batch.camel.processor.SetFieldsProcessor;
import it.stats.batch.util.ParserConstants;

import org.apache.camel.Exchange;
import org.apache.camel.Predicate;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * @author fabrizio
 *
 */
public class PlayerParserRouteBuilder extends RouteBuilder
{
	public static String PARSE_PLAYER_URI = "direct:playerParser";
	
	private Logger logger = LoggerFactory.getLogger(PlayerParserRouteBuilder.class); 
	
	public static String PLAYER = "player";
	private static String PLAYER_ELEMENT = "playerElement";
	
	Predicate playerNotFound = header(GetElementsProcessor.ELEMENTS_NOT_FOUND_KEY).isEqualTo(true);
	
	@Override
	public void configure() throws Exception 
	{
		from(PARSE_PLAYER_URI)
		.routeId(PlayerParserRouteBuilder.class.getSimpleName())
		.process(new Processor() 
		{
			public void process(Exchange exchange) throws Exception 
			{
				String playerId = exchange.getIn().getBody(String.class);
				
				logger.info("Parse player "+playerId);
				
//				Map<String, Object> player = new HashMap<String, Object>();
//				JSONObject player = new JSONObject();
				DBObject player = new BasicDBObject();
				player.put(PlayerBean.PLAYER_FIELDS[0].getFieldName(), playerId);
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
					exchange.getIn().setHeader(SetFieldsProcessor.FIELDS, PlayerBean.PLAYER_FIELDS);
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
//					@SuppressWarnings("unchecked")
//					DBObject player = exchange.getProperty(PLAYER, DBObject.class);
					// TODO Save picture on file
//					playerMap.put(PlayerBean.PLAYER_FIELDS[12].getFieldName(), ParserUtil.getBytesFromImage(img.attr(ParserConstants.SRC)));
//					p.setPictureUrl(img.attr(SWConstants.SRC));
				}
			}
		})
		.process(new Processor() 
		{
			public void process(Exchange exchange) throws Exception 
			{
				exchange.getIn().setBody(exchange.getProperty(PLAYER));
			}
		})
		.to(UpsertRouteBuilder.UPSERT_URI)
		.end();
	}
}
