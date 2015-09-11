/**
 * 
 */
package it.stats.batch.camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;

/**
 * @author A90C
 *
 */
public class GetChildElementProcessor implements Processor 
{
	public static String REF = GetChildElementProcessor.class.getSimpleName();
	
	public static String CHILD_INDEX = "childIndex";
	
	public void process(Exchange exchange) throws Exception 
	{
		String childIndex = exchange.getIn().getHeader(CHILD_INDEX, String.class);
		Element element = exchange.getIn().getBody(Element.class);
		
		String[] idxTmp = StringUtils.split(childIndex, "|");
		if(ArrayUtils.isNotEmpty(idxTmp))
		{
			for(String i : idxTmp)
			{
				element = element.child(Integer.parseInt(i));
			}
		}
		exchange.getIn().setBody(element);
	}
}
