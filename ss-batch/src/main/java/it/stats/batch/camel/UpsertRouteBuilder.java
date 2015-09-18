/**
 * 
 */
package it.stats.batch.camel;

import org.apache.camel.builder.RouteBuilder;

/**
 * @author A90C
 *
 */
public class UpsertRouteBuilder extends RouteBuilder 
{
	public static String UPSERT_URI = "seda:upsert";
	
	public static String BEAN_NAME_KEY = "beanName";
	
	@Override
	public void configure() throws Exception 
	{
		errorHandler(deadLetterChannel(UpsertErrorsRouteBuilder.UPSERT_ERRORS_URI));
		
		from(UPSERT_URI)
		.transacted()
		.to("bean:${header.beanName}?method=upsert")
		.to("jdbc:swDatasource")
		.end();
	}

}
