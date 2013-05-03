/**
 * Benutzerverwaltung (angemeldeter B.)
 * -	Login
 * -	Logout
 * -	Bei Abmeldung speichern
 * 
 * @author ja
 *
 */

package controller;
import model.*;

public class MyAccount {
	
	/**
	 * Private Variablen
	 */
	Utilities oUtilities = null;
	private static User oUser = null;
	
	
	/**
	 * Constructor. Neues User-Objekt erzeugen.
	 * @param oUser
	 */
	public MyAccount(User oUser)
	{
		// Utilities-Variable füllen
		this.oUtilities = new Utilities();
	}
	
	
	
	/**
	 * Anmelden. Sitzung erzeugen.
	 * @param String
	 * @param String
	 */
	public void login(String sLoginName, String sPassword)
	{
		this.oUser = new User();
		this.oUser.setUser(sLoginName); // Benutzer per ID setzen
		this.oUtilities.setUser(oUser); // Das neue Benutzer-Objekt an Utilities übergeben
	}
	
	
	
	/**
	 * Abmelden und dabei speichern.
	 */
	public void logout()
	{
		// Erst speichern
		this.oUtilities.store();
		
		// Dann abmelden:
		// Globales Benutzer-Objekt leeren
		this.oUtilities.setUser(null);
		
		// Hauptfenster schließen und Anmeldedialog anzeigen
		// ...
	}
	
}
