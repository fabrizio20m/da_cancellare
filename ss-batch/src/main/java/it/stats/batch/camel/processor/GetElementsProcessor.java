/**
 * 
 */
package it.stats.batch.camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


/**
 * @author fabrizio
 *
 */
public class GetElementsProcessor implements Processor 
{
	public static String REF = GetElementsProcessor.class.getSimpleName();
	
	public static String GET_ELEMENT_KEY = "getElementKey";
	public static String CLASSNAME_KEY = "classname";
	public static String ELEMENTS_NOT_FOUND_KEY = "elementsNotFound";
	
	public enum GetElementType
	{
		BY_CLASS
	}
	
	public void process(Exchange exchange) throws Exception 
	{
		String classname = exchange.getIn().getHeader(CLASSNAME_KEY, String.class);
		Document document = exchange.getIn().getBody(Document.class);
		GetElementType type = exchange.getIn().getHeader(GET_ELEMENT_KEY, GetElementType.class);
		if(GetElementType.BY_CLASS == type)
		{
			Elements elements = document.getElementsByClass(classname);
			exchange.getIn().setBody(elements);
			exchange.getIn().setHeader(ELEMENTS_NOT_FOUND_KEY, (elements == null || elements.isEmpty()));
		}
	}

}
