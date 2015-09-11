/**
 * 
 */
package it.stats.batch.camel.processor;

import it.stats.batch.util.ConfigutationConstants;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * @author fabrizio
 *
 */
public class JsoupConnectProcessor implements Processor 
{
	public static String REF = JsoupConnectProcessor.class.getSimpleName();
	
	public void process(Exchange exchange) throws Exception 
	{
		String url = exchange.getIn().getBody(String.class);
		Document document = null;
		int i=0;
		do
		{
			Connection connection = Jsoup.connect(url).timeout(0);
			Connection.Response response = connection.execute();
			if(response.statusCode() == 200)
			{
				document = connection.get();
			}
			i++;
		}
		while(document == null && i < ConfigutationConstants.MAX_CONNECT_ATTEMPTS);
		exchange.getIn().setBody(document);
	}

}
