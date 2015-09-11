/**
 * 
 */
package it.stats.batch.camel;

import it.stats.batch.util.ParserConstants;

import java.math.BigInteger;
import java.util.HashMap;
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
	
	private BigInteger playerId = BigInteger.valueOf(122);

	@Override
	protected Object getBody() 
	{
		return ParserConstants.SW_SITE+"/players/1/"+playerId;
	}

	@Override
	protected Map<String, Object> getHeaders() 
	{
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put(PlayerParserRouteBuilder.PLAYER_ID, playerId);
		return headers;
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
