/**
 * 
 */
package it.stats.batch.camel;

import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.thoughtworks.xstream.XStream;

/**
 * @author A90C
 *
 */
public abstract class TestBaseRouteBuilder extends CamelSpringTestSupport
{
	protected Logger logger = LoggerFactory.getLogger(this.getClass()); 
	
	protected XStream xs = new XStream();
	
	@Override
	protected AbstractApplicationContext createApplicationContext() 
	{
		return new ClassPathXmlApplicationContext("META-INF/spring/camel-context.xml");
	}
	
	protected abstract String getUri();
	
	protected abstract Object getBody();
	
	protected abstract Map<String, Object> getHeaders();
	
    public void testRoute() throws Exception 
    {
    	ProducerTemplate pt = null;
    	CamelContext ctx = null;
    	Exchange exchange = null;
    	
		try
		{
			ctx = context();
			pt = template();
			
			exchange = createExchangeWithBody(getBody());
			
			Map<String, Object> headers = getHeaders();
			if(headers != null)
			{
				exchange.getIn().setHeaders(headers);
			}
			logger.info(">>> ---------------   SEND  --------------- >>>");
			logger.info(">>> SEND - Exchange headers "+exchange.getIn().getHeaders());
			logger.info(">>> SEND - Exchange body "+exchange.getIn().getBody());
			
			exchange = pt.send(getUri(), exchange);

			logger.info("<<< --------------- RECEIVE --------------- <<<");
			logger.info("<<< RECEIVE - Exchange body "+xs.toXML(exchange.getIn().getBody()));
			logger.info(">>> RECEIVE - Exchange properties "+exchange.getProperties());
			if(exchange.getException() != null)
			{
				logger.error("<<< RECEIVE - Exchange exception "+ExceptionUtils.getStackTrace(exchange.getException()));
			}
		}
		catch (Exception e)
		{
			logger.error("=== testRoute - Exception "+e);
		}
		finally
		{
			ctx.stop();
		}
    }
}
