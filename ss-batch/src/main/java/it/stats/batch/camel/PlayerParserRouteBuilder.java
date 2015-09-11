/**
 * 
 */
package it.stats.batch.camel;

import it.stats.batch.camel.processor.GetChildElementProcessor;
import it.stats.batch.camel.processor.GetElementsProcessor;
import it.stats.batch.camel.processor.SetFieldsProcessor;
import it.stats.batch.util.FieldParam;
import it.stats.batch.util.ParserConstants;
import it.stats.dto.Player;

import java.math.BigInteger;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author fabrizio
 *
 */
public class PlayerParserRouteBuilder extends RouteBuilder
{
	public static String PARSE_PLAYER_URI = "direct:parsePlayer";
	
	private static FieldParam[] playerFields = new PlayerFields[] {
		new PlayerFields("First name", "name")
		, new PlayerFields("Last name", "surname")
	};
	
	public static String PLAYER = "player";
	public static String PLAYER_ID = "playerId";
	
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
	
	@Override
	public void configure() throws Exception 
	{
		from(PARSE_PLAYER_URI)
		.to(GetDocumentRouteBuilder.GET_DOCUMENT_URI)
		.process(new Processor() 
		{
			public void process(Exchange exchange) throws Exception 
			{
				Player player = new Player();
				exchange.setProperty(PLAYER, player);
				player.setPlayerId(exchange.getIn().getHeader(PLAYER_ID, BigInteger.class));
			}
		})
		.setHeader(GetElementsProcessor.GET_ELEMENT_KEY, simple(it.stats.batch.camel.processor.GetElementsProcessor.GetElementType.BY_CLASS.name()))
		.setHeader(GetElementsProcessor.CLASSNAME_KEY, simple(ParserConstants.CLASS_PLAYER_PASSPORT))
		.processRef(GetElementsProcessor.REF)
		.process(new Processor() 
		{
			public void process(Exchange exchange) throws Exception 
			{
				Elements playersElements = exchange.getIn().getBody(Elements.class);
				if(playersElements != null
						&& !playersElements.isEmpty())
				{
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
					exchange.getIn().setHeader(SetFieldsProcessor.FIELDS, playerFields);
					exchange.getIn().setHeader(SetFieldsProcessor.PROPERTY_KEY, PLAYER);
				}
			}
		})
		.processRef(SetFieldsProcessor.REF)
		
		.process(new Processor() 
		{
			public void process(Exchange exchange) throws Exception 
			{
				exchange.getIn().setBody(exchange.getProperty(PLAYER));
			}
		})
		.end();
	}
}
