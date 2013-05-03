/**
 * Benutzermodell
 * Stellt eine Instanz von User bereit.
 * 
 * @author ja
 */

package model;
import core.*;

public class User {
	
	/**
	 * Private Variablen
	 */
	private String sLoginName = null;
	private String sFirstName = null;
	private String sLastName = null;
	private int iRole = 0; // Benutzerrolle. 1 = Student, 2 = Dozent, 3 = Bibliothekar
	private String sPassword = null; // Kennwort zun�chst, unverschl�sselt
	private String sEmail = null;	
	
	/**
	 * R�ckgabeobjekt
	 */
	private User oUser = null;
	
	
	
	/**
	 * Aus bestehemdem Datensatz neues Benutzer-Objekt erzeugen, indem ID �bergeben wird
	 * SONST davon ausgehen, dass ein neuer Benutzer angelegt wird.
	 * @param String
	 * @return boolean
	 */
	public boolean setUser(String sLoginName)
	{
		// Auf jeden Fall die ID setzen, sie ist ja bereits bekannt.
		this.sLoginName = sLoginName;
		
		// Wenn Datensatz mit dieser ID existiert, neues Objekt erzeugen und in this.oUser speichern.
		// Hierf�r alle Setter durchlaufen.
		
		// Existiert der Datensatz noch nicht, soll er neu angelegt werden. Ebenfalls setzen.
		
		// Mit TRUE abschlie�en => Erfolg.
		return true;		
	}
	
	
	
	/**
	 * Gibt ein Objekt vom Typ User zur�ck. Muss zuvor instanziert werden.
	 * @return Object
	 */
	public User getUser()
	{
		return this.oUser;
	}
	
	
	
	/**
	 * Vorname setzen
	 * @param String
	 * @return 
	 */
	public void setFirstName(String sFirstName)
	{
		this.sFirstName = sFirstName;
	}
	
	
	
	/**
	 * Vorname zur�ckgeben
	 * @return String
	 */
	public String getFirstName()
	{
		return this.sFirstName;
	}
	
	
	
	/**
	 * Nachname setzen
	 * @param String
	 * @return 
	 */
	public void setLastName(String sLastName)
	{
		this.sLastName = sLastName;
	}
	
	
	
	/**
	 * Nachname zur�ckgeben
	 * @return String
	 */
	public String getLastName()
	{
		return this.sLastName;
	}
}
