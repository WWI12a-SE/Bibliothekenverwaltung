/**
 * Utilities
 * Hilfsklassen für allgemeine Aufgaben
 * -	wie zum Speichern der Änderungen in die CSV-Dateien
 * -	hält Sitzungsdaten des angemeldeten Benutzers bereit
 * 
 * @author ja
 */

package controller;
import core.*;
import model.*;

public class Utilities {
	private static User oCurrentUser = null;
	
	
	/**
	 * Benutzer --->
	 */
	
	
	/**
	 * Bei Anmeldung das Benutzerobjekt in oCurrentUser laden (wird von MyAccount ausgeführt)
	 * Dieses ist gloabl verfügbar und kann ab jetzt genutzt werden, um darauf die Methoden von User auszuführen.
	 * @param User
	 */
	public void setUser(User oUser)
	{
		this.oCurrentUser = oUser.getUser();
	}
	
	
	
	/**
	 * Benutzer zurückgeben
	 */
	public User getUser()
	{
		return this.oCurrentUser;
	}
	
	
	/**
	 * <--- ENDE Benutzer
	 * Speichern beim Beenden --->
	 */
	
	
	
	/**
	 * Speichern beim Verlassen
	 */
	public void store()
	{
		// 
	}
	
	
	
}
