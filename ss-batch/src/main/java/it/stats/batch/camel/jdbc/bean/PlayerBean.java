/**
 * 
 */
package it.stats.batch.camel.jdbc.bean;

import it.stats.batch.util.FieldParam;

/**
 * @author A90C
 *
 */
public class PlayerBean 
{
	public static FieldParam[] PLAYER_FIELDS = new PlayerFields[] {
		new PlayerFields("Player Id", "_id")
		, new PlayerFields("First name", "name")
		, new PlayerFields("Last name", "surname")
		, new PlayerFields("Nationality", "nationality")
		, new PlayerFields("Date of birth", "birth_date")
		, new PlayerFields("Country of birth", "birth_country")
		, new PlayerFields("Place of birth", "birth_place")
		, new PlayerFields("Position", "position")
		, new PlayerFields("Height", "height")
		, new PlayerFields("Weight", "weight")
		, new PlayerFields("Foot", "foot")
		, new PlayerFields("Picture URL", "picture_url")
//		, new PlayerFields("Picture", "picture")
	};
	
	private static class PlayerFields implements FieldParam
	{
		private PlayerFields(String fieldPageKey, String fieldName)
		{
			this.fieldName = fieldName;
			this.fieldPageKey = fieldPageKey;
		}
		
		private String fieldName;
		
		private String fieldPageKey;

		public String getFieldName() 
		{
			return fieldName;
		}

		public String getFieldPageKey() 
		{
			return fieldPageKey;
		}
	}
}
