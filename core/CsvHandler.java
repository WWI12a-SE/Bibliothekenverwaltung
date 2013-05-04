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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.commons.io.*;

public class CsvHandler {

	/**
	 * private Variablen
	 */
	private String sCsvTargetFile = null;
	private String sDataDir = "data"; // Name des Datenverzeichnisses, sollte "data" sein. Wird zunächst nicht geändert.
	private FileInputStream oFileStream = null;
	private BufferedReader oBufferedRdr = null;
	private File oFile = null;
	
	
	/**
	 * Constructor
	 * Dateiname OHNE Erweiterung übergeben.
	 * Von Erweiterunt ".csv" ausgehen.
	 * 
	 * @param String sFileName
	 * @throws FileNotFoundException 
	 */
	public CsvHandler(String sFileName)
	{
		this.sCsvTargetFile = "./src/" + sDataDir + "/" + sFileName + ".csv"; // Name der Datei

		// Filestream
		try {
			this.oFileStream = new FileInputStream(this.sCsvTargetFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		this.oBufferedRdr = new BufferedReader(new InputStreamReader(oFileStream)); // Obj. von Buffered Reader
		this.oFile = new File(this.sCsvTargetFile); // Dateiobjekt
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
	public String getMap()
	{
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
			
			
			try {
				String sZeile = FileUtils.readLines(this.oFile).get(iCntr);
				System.out.print(sZeile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
					
			//return this.oBufferedRdr.readLine();
			
			System.out.print("\n");
			iCntr++;
		}
		
		
		//Für jede Zeile 
		
		return "Ende Map";
	}
	
	
	
	/**
	 * Datei schreiben
	 * Schreibt die (geänderte) Map in die Ursprungsdatei zurück
	 */
	private void writeFile(String aMap){
		// aMap nach this.cSvTargetFile schreiben
		
	}
	
	
	
	/**
	 * Reicht die Ausgabe von getMap() nach außen.
	 * @return String
	 * @throws IOException 
	 */
	public String[][] read(){
		return null;
		//return getMap();
	}
	
	
	
	/**
	 * Nimmt ein zweidimensionales Array entgegen.
	 * @param String[][]
	 */
	public void write(String aMap[])
	{
		//this.writeFile(aMap[]);
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
	        while ((readChars = this.oFileStream.read(c)) != -1) {
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
