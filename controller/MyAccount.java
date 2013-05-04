/**
 * Benutzerverwaltung (angemeldeter B.)
 * -	Login
 * -	Logout
 * -	Bei Abmeldung speichern
 * 
 * @author Sandra Lang
 *
 */

package controller;
import model.*;

public class MyAccount {
	
	/**
	 * Private Variablen
	 */
	Utilities oUtilities = null;
	private static User oUser = null;	// Logindaten User von Backend
	
	
	/**
	 * Constructor. Neues User-Objekt erzeugen.
	 * @param oUser
	 */
		
	
	
	
	
	// Vergleich von Anmeldenamen und Datenbanknamen
	public void login(String sLoginName, String sPassword)
	{
		User loginUser = User.getUser(sLoginName);	// Suche mir den User mit diesem Namen
		String password = loginUser.getsPassword();//Suche mit den User mit diesem Password
		
		//pr�fung
		if ((loginUser.getsLoginName().equals(sLoginName))&(loginUser.getsPassword().equals(sPassword)) ){
			//TODO
		}else{
			//TODO
		}
		
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
		
		// Hauptfenster schlie�en und Anmeldedialog anzeigen
		// ...
	}
	
}
