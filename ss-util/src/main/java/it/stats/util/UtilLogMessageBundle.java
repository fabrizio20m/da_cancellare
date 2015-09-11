/**
 * 
 */
package it.stats.util;

/**
 * @author A90C
 *
 */
public enum UtilLogMessageBundle 
{
	UTIL00001E("Error marshalling object {}. {}"),
	UTIL00002E("Error unmarshalling string {}. {}"),
	;
	
	private UtilLogMessageBundle(String descrizione)
	{
		this.descrizione = descrizione;
	}
	
	private static String SEPARATOR = " - ";
	
	private String descrizione;

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public String toString()
	{
		return this.name()+SEPARATOR+getDescrizione();
	}
}
