/**
 * 
 */
package it.stats.util.serialization;

import it.stats.util.UtilLogMessageBundle;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author A90C
 *
 */
public class SerializationUtil 
{
	private static Logger logger = LoggerFactory.getLogger(SerializationUtil.class);
	
	protected static ConcurrentHashMap<Class<?>, Marshaller> mCache = new ConcurrentHashMap<Class<?>, Marshaller>();
	
	protected static ConcurrentHashMap<Class<?>, Unmarshaller> uCache = new ConcurrentHashMap<Class<?>, Unmarshaller>();
	
	private static String NULL = "null";
	
	public static String marshallXML(Object obj) throws Exception 
	{
		try 
		{
			Class<?> clazz = obj.getClass();
			Marshaller m = null;
			if ((m = mCache.get(clazz)) == null) 
			{
				m = JAXBContext.newInstance(clazz).createMarshaller();
				mCache.putIfAbsent(clazz, m);
			}
			
			ByteArrayOutputStream o = new ByteArrayOutputStream();
			m.marshal(obj, o);
			return new String(o.toByteArray());
		} 
		catch (Exception e) 
		{
			logger.error(UtilLogMessageBundle.UTIL00001E.toString(), 
					(obj != null ? obj.getClass().getCanonicalName() : NULL), 
					e.toString());
			throw e;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T>T unmarshallXML(String s, Class<?> clazz) throws Exception
	{
		try
		{
			Unmarshaller u = null;
			if ((u = uCache.get(clazz)) == null) 
			{
				u = JAXBContext.newInstance(clazz).createUnmarshaller();
				uCache.putIfAbsent(clazz, u);
			}
			
			ByteArrayInputStream i = new ByteArrayInputStream(s.getBytes());
			return (T) u.unmarshal(i);
		}
		catch (Exception e) 
		{
			logger.error(UtilLogMessageBundle.UTIL00002E.toString(), 
					s, 
					e.toString());
			throw e;
		}
	}
}
