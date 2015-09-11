/**
 * 
 */
package it.stats.batch.camel;

import it.stats.batch.camel.processor.GetElementsProcessor;
import it.stats.batch.util.ParserConstants;

import org.apache.camel.builder.RouteBuilder;

/**
 * @author fabrizio
 *
 */
public class PlayerParserRouteBuilder extends RouteBuilder
{
	public static final String PARSE_PLAYER_URI = "direct:parsePlayer";
	
	@Override
	public void configure() throws Exception 
	{
		from(PARSE_PLAYER_URI)
		.to(GetDocumentRouteBuilder.GET_DOCUMENT_URI)
		.setHeader(GetElementsProcessor.GET_ELEMENT_KEY, simple("${type:it.stats.batch.camel.processor.GetElementsProcessor.GetElementType.BY_CLASS}"))
		.setHeader(GetElementsProcessor.CLASSNAME_KEY, simple(ParserConstants.CLASS_PLAYER_PASSPORT))
//		.processRef(GetElementsProcessor.REF)
		.process(new GetElementsProcessor())
		.end();
	}

	public static void main(String[] args)
	{
		
	}
}
