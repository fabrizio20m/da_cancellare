/**
 * 
 */
package it.stats.batch.camel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.camel.builder.RouteBuilder;

/**
 * @author A90C
 *
 */
public class DispatchPlayerParserRouteBuilder extends RouteBuilder
{
	public static String DISPATCH_PLAYER_PARSER_URI = "direct:dispatchPlayerParser";
	
	private ExecutorService executor = Executors.newFixedThreadPool(16);
	
	@Override
	public void configure() throws Exception 
	{
		from(DISPATCH_PLAYER_PARSER_URI)
		.split(simple("${in.header.playerIds}"))
		.streaming()
		.parallelProcessing()
		.executorService(executor)
		.to(PlayerParserRouteBuilder.PARSE_PLAYER_URI)
		.end();
	}
}
