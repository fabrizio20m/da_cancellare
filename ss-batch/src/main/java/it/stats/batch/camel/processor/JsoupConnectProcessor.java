/**
 * 
 */
package it.stats.batch.camel.processor;

import it.stats.batch.util.ConfigutationConstants;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author fabrizio
 *
 */
public class JsoupConnectProcessor implements Processor 
{
	public static String REF = "JsoupConnectProcessor";
	
	private Logger logger = LoggerFactory.getLogger(JsoupConnectProcessor.class);
	
	public void process(Exchange exchange) throws Exception 
	{
		String url = exchange.getIn().getBody(String.class);
		Document document = null;
		int i=0;
		do
		{
			document = Jsoup.connect(url).timeout(0).get();
			i++;
		}
		while(document == null && i<ConfigutationConstants.MAX_CONNECT_ATTEMPTS);
		exchange.getIn().setBody(document);
		
		logger.info("----> ENTRO "+document);
	}

}
