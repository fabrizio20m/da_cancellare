package it.stats.batch.camel;
import it.stats.batch.camel.processor.JsoupConnectProcessor;

import org.apache.camel.builder.RouteBuilder;

/**
 * 
 */

/**
 * @author fabrizio
 *
 */
public class GetDocumentRouteBuilder extends RouteBuilder
{
	public static String GET_DOCUMENT_URI = "direct:getDocument";
	
	@Override
	public void configure() throws Exception 
	{
		from(GET_DOCUMENT_URI)
//		.processRef(JsoupConnectProcessor.REF)
		.process(new JsoupConnectProcessor())
		.end();
	}

}
