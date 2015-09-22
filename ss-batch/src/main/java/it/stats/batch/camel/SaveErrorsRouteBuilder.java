/**
 * 
 */
package it.stats.batch.camel;

import it.stats.batch.util.ConfigurationConstants;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import com.mongodb.DBObject;

/**
 * @author A90C
 *
 */
public class SaveErrorsRouteBuilder extends RouteBuilder 
{
	public static String SAVE_ERRORS_URI = "seda:saveErrors";
	
	@Override
	public void configure() throws Exception 
	{
		from(SAVE_ERRORS_URI)
		.routeId(SaveErrorsRouteBuilder.class.getSimpleName())
		.log(LoggingLevel.ERROR, SAVE_ERRORS_URI+" - UPSERT Headers\t${headers}")
		.log(LoggingLevel.ERROR, SAVE_ERRORS_URI+" - UPSERT Body\t${body}")
		.log(LoggingLevel.ERROR, SAVE_ERRORS_URI+" - UPSERT Exception\t${exception}")
		.process(new Processor() 
		{
			public void process(Exchange exchange) throws Exception 
			{
				StringBuilder sb = new StringBuilder();
				String endpoint = exchange.getIn().getHeader(SaveRouteBuilder.SAVE_ENDPOINT_KEY, String.class);
				DBObject dbo = exchange.getIn().getBody(DBObject.class);
				
				sb.append("ENDPOINT="+endpoint+"\n");
				sb.append("BODY="+dbo.toString());
				exchange.getIn().setBody(sb.toString());
			}
		})
		.to("file:"+ConfigurationConstants.SAVE_ERROR_FOLDER+"?readLock=fileLock")
		.end();
	}
}
