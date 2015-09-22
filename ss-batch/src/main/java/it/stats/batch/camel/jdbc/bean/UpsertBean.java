/**
 * 
 */
package it.stats.batch.camel.jdbc.bean;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author A90C
 *
 */
public class UpsertBean 
{
//	private static String UPSERT_PLAYER = "insert into sw.player(player_id, name, surname) values (";
	private static String UPSERT_PLAYER = "SELECT sw.upsert_player(";
	
	public static String BEAN_NAME = UpsertBean.class.getSimpleName();
	
	public static String META_BEAN_NAME_KEY = "metaBeanName";
	
	private static String COMMA = ",";
	private static String APEX = "'";
	
	private Logger logger = LoggerFactory.getLogger(UpsertBean.class); 
	
	public String upsert(Map<String, Object> map, BeanType beanType)
	{
		switch (beanType) 
		{
			case PLAYER:
				return upsertPlayer(map);
			default:
				break;
		}
		return null;
	}
	
	private String upsertPlayer(Map<String, Object> map)
	{
		StringBuilder sb = new StringBuilder(UPSERT_PLAYER);
		sb.append(map.get(PlayerBean.PLAYER_FIELDS[0].getFieldName()));
		sb.append(COMMA+APEX+map.get(PlayerBean.PLAYER_FIELDS[1].getFieldName())+APEX);
		sb.append(COMMA+APEX+map.get(PlayerBean.PLAYER_FIELDS[2].getFieldName())+APEX);
		sb.append(COMMA+APEX+map.get(PlayerBean.PLAYER_FIELDS[3].getFieldName())+APEX);
		sb.append(COMMA+APEX+map.get(PlayerBean.PLAYER_FIELDS[4].getFieldName())+APEX);
		sb.append(COMMA+APEX+map.get(PlayerBean.PLAYER_FIELDS[5].getFieldName())+APEX);
		sb.append(COMMA+APEX+map.get(PlayerBean.PLAYER_FIELDS[6].getFieldName())+APEX);
		sb.append(COMMA+APEX+map.get(PlayerBean.PLAYER_FIELDS[7].getFieldName())+APEX);
		sb.append(COMMA+APEX+map.get(PlayerBean.PLAYER_FIELDS[8].getFieldName())+APEX);
		sb.append(COMMA+APEX+map.get(PlayerBean.PLAYER_FIELDS[9].getFieldName())+APEX);
		sb.append(COMMA+APEX+map.get(PlayerBean.PLAYER_FIELDS[10].getFieldName())+APEX);
		sb.append(COMMA+APEX+map.get(PlayerBean.PLAYER_FIELDS[11].getFieldName())+APEX+")");
		return sb.toString();
	}
}
