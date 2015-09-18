/**
 * 
 */
package it.stats.batch.camel;

import java.net.ConnectException;

import org.apache.camel.builder.RouteBuilder;

/**
 * @author A90C
 *
 */
public class JDBCInterceptorRouteBuilder extends RouteBuilder
{
	@Override
	public void configure() throws Exception 
	{
		interceptSendToEndpoint("jdbc:*")
		.choice()
		.when(header("JMSRedelivered").isEqualTo("false"))
		.throwException(new ConnectException("Cannot connect to the database"))
		.end();
	}
}
