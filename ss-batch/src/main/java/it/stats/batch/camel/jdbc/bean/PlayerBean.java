/**
 * 
 */
package it.stats.batch.camel.jdbc.bean;

import java.util.Map;

/**
 * @author A90C
 *
 */
public class PlayerBean 
{
	private static String UPSERT_PLAYER = "SELECT sw.upsert_player(";
	
	public String upsert(Map<String, Object> map)
	{
		StringBuilder sb = new StringBuilder(UPSERT_PLAYER);
		sb.append(map.get("playerId"));
		sb.append(", '"+map.get("name")+"'");
		sb.append(", '"+map.get("surname")+"')");
		return sb.toString();
	}
}
