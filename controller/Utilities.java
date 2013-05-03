/**
 * Utilities
 * Hilfsklassen f�r allgemeine Aufgaben
 * -	wie zum Speichern der �nderungen in die CSV-Dateien
 * -	h�lt Sitzungsdaten des angemeldeten Benutzers bereit
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
	 * Bei Anmeldung das Benutzerobjekt in oCurrentUser laden (wird von MyAccount ausgef�hrt)
	 * Dieses ist gloabl verf�gbar und kann ab jetzt genutzt werden, um darauf die Methoden von User auszuf�hren.
	 * @param User
	 */
	public void setUser(User oUser)
	{
		this.oCurrentUser = oUser.getUser();
	}
	
	
	
	/**
	 * Benutzer zur�ckgeben
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
