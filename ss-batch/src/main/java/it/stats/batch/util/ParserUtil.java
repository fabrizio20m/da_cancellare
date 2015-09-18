/**
 * 
 */
package it.stats.batch.util;

import java.io.ByteArrayOutputStream;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 * @author A90C
 *
 */
public class ParserUtil 
{
	public static byte[] getBytesFromImage(String url) throws Exception
	{
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    ImageIO.write(ImageIO.read(new URL(url)), "png", baos);
	    return baos.toByteArray();
	}
}
