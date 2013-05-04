/**
 * CSV-Mapper
 * Liest eine CSV-Datei ein und parst sie in einem zweidimensionalen Array.
 * Unterstützt das Schreiben von CSV-Dateien.
 * 
 * Standardform:
 * "Zeichenfolge";"1000";"1234.56"
 * -	Alle Werte stehen in Anführungszeichen.
 * -	Werte werden in Anführungszeichen gefasst.
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
	 * Dateiname OHNE Erweiterung übergeben.
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
	 * Gibt den Inhalt einer CSV-Datei als zweidimensionales Array zurück.
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
	 * Reicht die Ausgabe von getMap() nach außen.
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
