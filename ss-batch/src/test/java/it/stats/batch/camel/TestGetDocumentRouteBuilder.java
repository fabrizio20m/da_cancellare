/**
 * 
 */
package it.stats.batch.camel;

import it.stats.batch.util.ParserConstants;

import java.util.Map;

import org.junit.Test;

/**
 * @author fabrizio
 *
 */
public class TestGetDocumentRouteBuilder extends TestBaseRouteBuilder
{
	@Override
	protected String getUri() 
	{
		return GetDocumentRouteBuilder.GET_DOCUMENT_URI;
	}
	
	@Override
	protected Object getBody() 
	{
		return ParserConstants.SW_SITE+"/players/1/122";
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
