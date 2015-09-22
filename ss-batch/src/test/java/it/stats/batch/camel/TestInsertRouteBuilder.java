/**
 * 
 */
package it.stats.batch.camel;

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
		return "direct:monInsert";
	}

	@Override
	protected Object getBody() 
	{
		return "{ \"name\": \"Fabrizio\" }";
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