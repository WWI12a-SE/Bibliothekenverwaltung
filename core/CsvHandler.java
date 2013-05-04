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
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CsvHandler {

	/**
	 * private Variablen
	 */
	private String sCsvTargetFile = null;
	private String sDataDir = "data";
	
	
	
	/**
	 * Constructor
	 * Dateiname OHNE Erweiterung �bergeben.
	 * Von Erweiterunt ".csv" ausgehen.
	 * 
	 * @param String
	 */
	public CsvHandler(String sFileName)
	{
		this.sCsvTargetFile = "./src/" + sDataDir + "/" + sFileName + ".csv"; // Name der Datei
	}
	
	
	
	/**
	 * Dateiname mit Pfad ausgeben
	 * @return String
	 */
	public String getFullFileName()
	{
		return this.sCsvTargetFile;
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
	 * @throws IOException 
	 */
	private String readFile()
	{
		String sReturnLines = "";
		
		try{
			FileReader oF = new FileReader(this.sCsvTargetFile);
		    BufferedReader oR = new BufferedReader(oF);

		    String sLines = "";

		    while((sLines = oR.readLine()) != null )
		    {
		    	sReturnLines = sReturnLines + sLines + "\n";
		    }

		    oR.close();
		}
		catch(FileNotFoundException d)
		{
			System.out.println("Fehler beim Einlesen der Datei " + this.sCsvTargetFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	    return sReturnLines;
		
	}
	
	
	
	/**
	 * Datei schreiben
	 * Schreibt die (ge�nderte) Map in die Ursprungsdatei zur�ck
	 */
	private void writeFile(String aMap){
		// aMap nach this.cSvTargetFile schreiben
		
	}
	
	/**
	 * <-- Dateibehandlung
	 */
	
	
	
	/**
	 * Reicht die Ausgabe von getMap() nach au�en.
	 * @return String
	 * @throws IOException 
	 */
	public String read(){
		//return getMap();
		return readFile();
		
	}
	
	
	
	/**
	 * Nimmt ein zweidimensionales Array entgegen.
	 * @param String[][]
	 */
	public void write(String aMap)
	{
		this.writeFile(aMap);
	}
}
