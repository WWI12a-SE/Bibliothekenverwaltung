/**
 * Benutzerverwaltung (angemeldeter B.)
 * -	Login (Sandra Lang)
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
	 * Private Variablen:
	 */
	//Utilities oUtilities = null;	
	private static User oUser = null;	// Logindaten User von Backend
	private static User loggedInUser; // Der aktuell angemeldete User	// @Thorsten
	
	/**
	 * Login Pr�fungsfunktion
	 * @param oUser
	 */
	// Login: Vergleich von Anmeldenamen und Datenbanknamen
	public static boolean login(String sLoginName, String sPassword)
	{
		UserHandler userController = UserHandler.getInstance();
		User loginUser = userController.getUser(sLoginName);	// Suche mir den User mit diesem Namen
				
		//pruefung
		if ((loginUser.getPassword()!=null&&(loginUser.getLoginName().equals(sLoginName))&(loginUser.getPassword().equals(sPassword)))){
			loggedInUser = loginUser;	// @Thorsten
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Logout-Funktion:
	 * Abmelden und dabei speichern.
	 */
	public void logout()
	{
		// Erst speichern
		//this.oUtilities.store();
		
		// Dann abmelden:
		// Globales Benutzer-Objekt leeren
		//this.oUtilities.setUser(null);
		
		// Hauptfenster schliessen und Anmeldedialog anzeigen
		// ...
		
		loggedInUser = null;	// @Thorsten
		MediaHandler.reset();
		UserHandler.reset();
		ReservationHandler.reset();
	}
	
	/**
	 * Gibt den aktuell angemeldeten User zurueck.
	 * Sollte kein User angemeldet sein wir null zurueck gegeben.
	 * @return loggedInUser : User | null
	 */
	public static User getLoggedInUser(){
		return loggedInUser;
	}
}
