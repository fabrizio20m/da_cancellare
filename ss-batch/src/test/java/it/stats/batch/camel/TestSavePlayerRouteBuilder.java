/**
 * 
 */
package it.stats.batch.camel;

import it.stats.batch.camel.mondb.MDBSavePlayerRouteBuilder;

import java.util.Map;

import org.junit.Test;

/**
 * @author fabrizio
 *
 */
public class TestSavePlayerRouteBuilder extends TestBaseRouteBuilder
{
	@Override
	protected String getUri() 
	{
		return MDBSavePlayerRouteBuilder.SAVE_PLAYER_URI;
	}
	
	@Override
	protected Object getBody() 
	{
		return "{ _id: 1, name: \"Fabrizio\", surname: \"Mastropaolo\" }";
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
