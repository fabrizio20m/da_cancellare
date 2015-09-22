/**
 * 
 */
package it.stats.batch.camel;

import it.stats.batch.util.ConfigurationConstants;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

/**
 * @author A90C
 *
 */
public class SaveErrorsRouteBuilder extends RouteBuilder 
{
	public static String UPSERT_ERRORS_URI = "seda:saveErrors";
	
	@Override
	public void configure() throws Exception 
	{
		from(UPSERT_ERRORS_URI)
		.routeId(SaveErrorsRouteBuilder.class.getSimpleName())
		.log(LoggingLevel.ERROR, UPSERT_ERRORS_URI+"UPSERT Body\t${body}")
		.log(LoggingLevel.ERROR, UPSERT_ERRORS_URI+"UPSERT Exception\t${exception}")
		.to("file:"+ConfigurationConstants.UPSERT_ERROR_FOLDER+"?readLock=fileLock")
		.end();
	}
}
