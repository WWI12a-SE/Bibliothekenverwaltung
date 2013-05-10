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
		 * Einen Satz ändern
		 */
		CsvHandler oCUsers = new CsvHandler("users");
		System.out.println("Benutzer-Map im jetzigen Zustand:\n");
		oCUsers.viewMap();
		/*
		System.out.println("Vorhandene Zeile ändern, Map ausgeben\n");
		String[] aAktualisierterSatz = {"aggi","Joh","Acki","1","1111","acki@ackivität.de"};
		oCUsers.update(aAktualisierterSatz);
		oCUsers.viewMap();
		*/
		
		
		/**
		 * Einen Satz anfügen (ID existiert nicht)
		 */
		String[] aNeuerSatz = {"neuersatz","Ijon","Tichy","2","4444","ijon@raumstation.all"};
		oCUsers.update(aNeuerSatz);
		
		String[] aNeuerSatz2 = {"neuersatz2","2Ijon","2Tichy","2","4444","ijon@raumstation.all"};
		oCUsers.update(aNeuerSatz2);
		
		oCUsers.viewMap();
		
		/**
		 * Einen Satz löschen
		 */
		oCUsers.dropLine("groti");
		oCUsers.viewMap();
		
		/**
		 * Beispiele für Verwendung in Abstraktionsschichten
		 */
		/*
		System.out.println("\n\nBeispiele: Benutzerinformationen abrufen\n");
		
		// Neues Objekt erzeugen
		CsvHandler oTheUser = new CsvHandler("users");
		
		// Ein paar Details
		System.out.println("Es gibt derzeit *" + oTheUser.iLines + "* Benutzer in der Datebank.");
		System.out.println("Die Benutzertabelle hat *" + oTheUser.iColons + "* Spalten.");
		
		// Alle Details zu einem bestimmten Benutzer anzeigen
		System.out.println("\nAlle Daten des Benutzers mit der ID aggi: (verwende hierfür Methode read())");
		oTheUser.viewLineById("aggi");
		
		// Bestimmte Details für einen Benutzer anzeigen:
		System.out.println("\nDas Kennwort für aggi lautet:\n" + oTheUser.getLineById("aggi")[4] + ".");
		
		// Alle Ausgeben
		oTheUser.viewMap();
		*/
	}

}
