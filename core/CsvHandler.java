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
	
	
	
	/**
	 * Constructor
	 * Dateiname OHNE Erweiterung �bergeben.
	 * Von Erweiterunt ".csv" ausgehen.
	 * 
	 * @param String
	 */
	public CsvHandler(String sFileName)
	{
		this.sCsvTargetFile = sFileName + ".csv";
	}
	
	
	
	/**
	 * Test: Gibt die Map aus (grafisch)
	 */
	public String getCsv()
	{
		return getMap();
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
	 * Datei einlesen
	 * @param sFileName
	 */
	private void readFile()
	{
		File oFile = new File(this.sCsvTargetFile);
		
	}
}
