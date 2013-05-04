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
		
	
	
	/**
	 * Anmelden. Sitzung erzeugen.
	 * @param String
	 * @param String
	 */
	// Hole User aus Datenbank
	public static getUser(){
	}
	
	// Vergleich von Anmeldenamen und Datenbanknamen
	public void login(String sLoginName, String sPassword)
	{
		User loginname = User.getUser(sLoginName);	// Suche mir den User mit diesem Passwort
		User password = User.getPassword(sPassword);//Suche mit den User mit diesem LoginNamen
		
		//prüfung
		if (loginname.equals(sLoginName) ){
			//TODO
		}else if(password.equals(sPassword)){
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
		
		// Hauptfenster schließen und Anmeldedialog anzeigen
		// ...
	}
	
}
