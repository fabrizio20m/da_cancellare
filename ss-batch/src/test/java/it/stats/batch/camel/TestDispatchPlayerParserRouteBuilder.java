package it.stats.batch.camel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class TestDispatchPlayerParserRouteBuilder extends TestBaseRouteBuilder
{

	@Override
	protected String getUri() 
	{
		return DispatchPlayerParserRouteBuilder.DISPATCH_PLAYER_PARSER_URI;
	}

	@Override
	protected Object getBody() 
	{
		return null;
	}

	@Override
	protected Map<String, Object> getHeaders() 
	{
		Map<String, Object> h = new HashMap<String, Object>();
		List<Integer> playerIds = new ArrayList<Integer>();
		for(int i=290; i<295; i++)
		{
			playerIds.add(i+1);
		}
		
		h.put("playerIds", playerIds);
		
		return h;
	}
	
    @Test
    public void testRoute() throws Exception 
    {
    	super.testRoute();
    }
}
