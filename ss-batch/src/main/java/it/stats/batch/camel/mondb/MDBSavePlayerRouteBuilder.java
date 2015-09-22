/**
 * 
 */
package it.stats.batch.camel.mondb;

import it.stats.batch.camel.SaveErrorsRouteBuilder;

import org.apache.camel.builder.RouteBuilder;

/**
 * @author A90C
 *
 */
public class MDBSavePlayerRouteBuilder extends RouteBuilder 
{
	public static String SAVE_PLAYER_URI = "direct:savePlayer";
	
	@Override
	public void configure() throws Exception 
	{
		errorHandler(deadLetterChannel(SaveErrorsRouteBuilder.UPSERT_ERRORS_URI));
		
		from(SAVE_PLAYER_URI)
		.to("mongodb:mongoBean?database="+MDBConfiguration.DATABASE_NAME
				+"&collection="+MDBConfiguration.PLAYERS+"&operation=save")
		.end();		
	}

}
