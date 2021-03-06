/**
 * 
 */
package it.stats.batch.camel.processor;

import it.stats.batch.util.FieldParam;

import java.util.Iterator;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mongodb.DBObject;

/**
 * @author A90C
 *
 */
public class SetFieldsProcessor implements Processor 
{
//	private Logger logger = LoggerFactory.getLogger(SetFieldsProcessor.class); 
	
	public static String REF = SetFieldsProcessor.class.getSimpleName();
	
	public static String FIELDS = "fields";
	public static String PROPERTY_KEY = "propertyKey";
	
	public void process(Exchange exchange) throws Exception 
	{
		Elements elements = exchange.getIn().getBody(Elements.class);
		FieldParam[] fieldsParam = exchange.getIn().getHeader(SetFieldsProcessor.FIELDS, FieldParam[].class);
		String propertyKey = exchange.getIn().getHeader(PROPERTY_KEY, String.class);
		DBObject obj = exchange.getProperty(propertyKey, DBObject.class);
		
		if(elements != null
				&& !elements.isEmpty()
				&& ArrayUtils.isNotEmpty(fieldsParam))
		{
			for(FieldParam f : fieldsParam)
			{
				Iterator<Element> it = elements.iterator();
				while(it.hasNext())
				{
					Element value = null;
					Element key = it.next();
					
					if(f.getFieldPageKey().equals(StringUtils.trim(key.text())))
					{
						value = it.next();
						if(value != null
								&& StringUtils.isNotBlank(value.text()))
						{
							obj.put(f.getFieldName(), value.text());
						}
					}
				}
			}
		}
	}

}
