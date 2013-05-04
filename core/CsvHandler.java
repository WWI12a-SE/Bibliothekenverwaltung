/**
 * CSV-Mapper
 * Liest eine CSV-Datei ein und parst sie in einem zweidimensionalen Array.
 * Unterst�tzt das Schreiben von CSV-Dateien.
 * 
 * Standardform:
 * "Zeichenfolge";"1000";"1234.56"
 * -	Alle Werte stehen in Anf�hrungszeichen.
 * -	Werte werden in Anf�hrungszeichen gefasst.
 */

package core;
import java.io.File;

public class CsvHandler {

	/**
	 * private Variablen
	 */
	private String sCsvTargetFile = null;
	File oCsvFile = null;
	
	
	
	/**
	 * Constructor
	 * Dateiname OHNE Erweiterung �bergeben.
	 * Von Erweiterunt ".csv" ausgehen.
	 * 
	 * @param String
	 */
	public CsvHandler(String sFileName)
	{
		this.sCsvTargetFile = sFileName + ".csv"; // Name der Datei
		this.oCsvFile = new File(this.sCsvTargetFile); // Datei als globales Objekt
	}
	
	
	
	/**
	 * Map-Getter
	 * Gibt den Inhalt einer CSV-Datei als zweidimensionales Array zur�ck.
	 * 
	 * @return Array
	 */
	private String getMap()
	{
		String aCsv = null;
		return aCsv;
	}
	
	
	
	/**
	 * Map-Schreiber
	 * Schreibt eine Map
	 */
	
	
	
	/**
	 * Dateibehandlung --->
	 */
	
	/**
	 * Datei einlesen
	 */
	private void readFile()
	{
		// lesen von this.oCsvFile
	}
	
	
	
	/**
	 * Datei schreiben
	 */
	{
		// schreibe Datei this.oCsvFile
	}
	
	/**
	 * <-- Dateibehandlung
	 */
	
	
	
	/**
	 * Reicht die Ausgabe von getMap() nach au�en.
	 * @return String
	 */
	public String read(){
		return getMap();		
	}
	
	
	
	/**
	 * Nimmt ein zweidimensionales Array entgegen.
	 * @param String[][]
	 */
	public void write()
	{
		//
	}
}
