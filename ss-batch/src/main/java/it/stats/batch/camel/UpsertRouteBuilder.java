/**
 * 
 */
package it.stats.batch.camel;

import it.stats.batch.camel.mondb.MDBSavePlayerRouteBuilder;

import org.apache.camel.builder.RouteBuilder;

/**
 * @author A90C
 *
 */
public class UpsertRouteBuilder extends RouteBuilder 
{
	public static String UPSERT_URI = "seda:upsert";
	
//	private Logger logger = LoggerFactory.getLogger(UpsertRouteBuilder.class); 
	
	@Override
	public void configure() throws Exception 
	{
		errorHandler(deadLetterChannel(UpsertErrorsRouteBuilder.UPSERT_ERRORS_URI));
		
		from(UPSERT_URI)
		.routeId(UpsertRouteBuilder.class.getSimpleName())
		.to(MDBSavePlayerRouteBuilder.SAVE_PLAYER_URI)
		.end();
	}

}
