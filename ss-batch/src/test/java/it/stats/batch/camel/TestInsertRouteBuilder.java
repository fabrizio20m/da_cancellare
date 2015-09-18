/**
 * 
 */
package it.stats.batch.camel;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * @author A90C
 *
 */
public class TestInsertRouteBuilder extends TestBaseRouteBuilder 
{
	@Override
	protected String getUri() 
	{
		return "activemq:queue:insertPlayers";
	}

	@Override
	protected Object getBody() 
	{
		Map<String,Object> m = new HashMap<String, Object>();
		m.put("playerId", "222");
		m.put("name", "nn");
		m.put("surname", "ss");
		return m;
	}

	@Override
	protected Map<String, Object> getHeaders() 
	{
		return null;
	}
	
	@Test
	public void testRoute() throws Exception
	{
		super.testRoute();
	}
}
