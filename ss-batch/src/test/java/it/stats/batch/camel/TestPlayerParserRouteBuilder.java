/**
 * 
 */
package it.stats.batch.camel;

import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author fabrizio
 *
 */
public class TestPlayerParserRouteBuilder extends TestBaseRouteBuilder
{
	private Logger logger = LoggerFactory.getLogger(TestPlayerParserRouteBuilder.class); 
	
	private String playerId = "126";

	@Override
	protected Object getBody() 
	{
		return playerId;
	}

	@Override
	protected Map<String, Object> getHeaders() 
	{
		return null;
	}

	@Override
	protected String getUri() 
	{
		return PlayerParserRouteBuilder.PARSE_PLAYER_URI;
	}
	
    @Test
    public void testRoute() throws Exception 
    {
    	super.testRoute();
    }
}
