/**
 * Main-Klasse, um CSV-Handler zu testen
 * @author ja
 *
 */

package test;

import java.io.IOException;

import core.*;

public class TestdriveCsvHandler {

	public static void main(String[] args) throws IOException{
		
		/**
		 * Beispiel: Einen Benutzer abfragen
		 */
		System.out.println("Beispiele: Benutzerinformationen abrufen\n");
		
		CsvHandler oTheUser = new CsvHandler("users");
		System.out.println("Es gibt derzeit *" + oTheUser.iLines + "* Benutzer in der Datebank.");
		System.out.println("Die Benutzertabelle hat *" + oTheUser.iColons + "* Spalten.");
		System.out.println("\nAlle Daten des Benutzers mit der ID aggi: (verwende hierfür Methode read())");
		oTheUser.viewLineById("aggi");
		
		System.out.println("\nDas Kennwort für aggi lautet:\n" + oTheUser.getLineById("aggi")[4] + ".");
	}

}
