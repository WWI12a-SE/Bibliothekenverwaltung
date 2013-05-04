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
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.StringTokenizer;

public class CsvHandler {

	/**
	 * private Variablen
	 */
	private String sCsvTargetFile = null;
	private String sDataDir = "data"; // Name des Datenverzeichnisses, sollte "data" sein. Wird zunächst nicht geändert.
	private InputStream oInputStream = null; // Dateistrom
	
	
	
	/**
	 * Constructor
	 * Dateiname OHNE Erweiterung übergeben.
	 * Von Erweiterunt ".csv" ausgehen.
	 * 
	 * @param String sFileName
	 */
	public CsvHandler(String sFileName)
	{
		this.sCsvTargetFile = "./src/" + sDataDir + "/" + sFileName + ".csv"; // Name der Datei
		try {
			this.oInputStream = new BufferedInputStream(new FileInputStream(this.sCsvTargetFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
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
	 * Gibt den Inhalt einer CSV-Datei als zweidimensionales Array zurück.
	 * 
	 * @return Array
	 * @throws IOException 
	 */
	private String getMap()
	{
		// Explode je Zeile
		//StringTokenizer oToken = new StringTokenizer(this.readFile(),";");
		
		int iLinecount = 0;
		
		// Zeilen zählen
		try {
			iLinecount = this.countLines();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Array der ersten Dimension
		String aLines[] = new String[iLinecount];
		
		// Je Zeile der Datei
		int iCntr = 0;
		while(iCntr <= iLinecount){
			//aLines[iCntr] = "A";
			System.out.print("Zeile " + iCntr + ": ");
			
			System.out.print("asd");

			

			
			System.out.print("\n");
			iCntr++;
		}
		
		
		//Für jede Zeile 
		
		return "Ende Map";
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
		    	//sReturnLines = sReturnLines + sLines + "\n";
		    	sReturnLines = sLines;
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
	 * Schreibt die (geänderte) Map in die Ursprungsdatei zurück
	 */
	private void writeFile(String aMap){
		// aMap nach this.cSvTargetFile schreiben
		
	}
	
	/**
	 * <-- Dateibehandlung
	 */
	
	
	
	/**
	 * Reicht die Ausgabe von getMap() nach außen.
	 * @return String
	 * @throws IOException 
	 */
	public String read(){
		return getMap();
	}
	
	
	
	/**
	 * Nimmt ein zweidimensionales Array entgegen.
	 * @param String[][]
	 */
	public void write(String aMap)
	{
		this.writeFile(aMap);
	}
	
	
	
	/**
	 * Anzahl der Zeilen einer Datei
	 * 
	 * @author martinus
	 * @url http://stackoverflow.com/questions/453018/number-of-lines-in-a-file-in-java
	 * 
	 * @param String sFileName
	 * @return integer
	 * @throws IOException
	 */
	private int countLines() throws IOException {		
	    try {
	        byte[] c = new byte[1024];
	        int count = 0;
	        int readChars = 0;
	        boolean empty = true;
	        while ((readChars = this.oInputStream.read(c)) != -1) {
	            empty = false;
	            for (int i = 0; i < readChars; ++i) {
	                if (c[i] == '\n') {
	                    ++count;
	                }
	            }
	        }
	        return (count == 0 && !empty) ? 1 : count;
	    }
	    finally{
	    	// this.oInputStream.close();
	    }
	}
}
