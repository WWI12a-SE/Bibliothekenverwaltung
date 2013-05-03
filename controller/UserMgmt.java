/**
 * Benutzerverwaltung für Administrator
 * Ruft alle Benutzer aus dem Speicher ab und beliefert ein Formular bereit, um diese zu bearbeiten.
 * 
 * @author ja
 */

package controller;
import model.*;

public class UserMgmt {
	/**
	 * Private Variablen
	 */
	private User[] aUsers = null; // Alle Benutzer als Objekt in dieses Array
	
	

	/**
	 * Constructor
	 */
	public UserMgmt()
	{
		this.aUsers = new User().getAllUsers();
	}
			
}
