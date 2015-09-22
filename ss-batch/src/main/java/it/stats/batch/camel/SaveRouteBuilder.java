/**
 * 
 */
package it.stats.batch.camel;

import org.apache.camel.builder.RouteBuilder;

/**
 * @author A90C
 *
 */
public class SaveRouteBuilder extends RouteBuilder 
{
	public static String UPSERT_URI = "seda:save";
	
	public static String SAVE_ENDPOINT_KEY = "saveEndpointKey";
	
//	private Logger logger = LoggerFactory.getLogger(UpsertRouteBuilder.class); 
	
	@Override
	public void configure() throws Exception 
	{
		errorHandler(deadLetterChannel(SaveErrorsRouteBuilder.UPSERT_ERRORS_URI));
		
		from(UPSERT_URI)
		.routeId(SaveRouteBuilder.class.getSimpleName())
//		.to(MDBSavePlayerRouteBuilder.SAVE_PLAYER_URI)
		.recipientList(simple("${header.saveEndpointKey}"))
		.end();
	}

}
