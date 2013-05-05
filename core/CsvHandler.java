/**
 * CSV-Handler
 * Liest eine CSV-Datei ein und parst sie in einem zweidimensionalen Array.
 * Unterstützt das Schreiben von CSV-Dateien.
 * 
 * Standardform:
 * "Zeichenfolge";"1000";"1234.56"
 * -	Alle Werte stehen in Anführungszeichen.
 * -	Werte werden in Anführungszeichen gefasst.
 * 
 * Stunden verschwendet: 6,5
 * 
 * To do:
 * Arrays empfangen und in Datei schreiben
 */

package core;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import core.opencsv.*;

public class CsvHandler {

	/**
	 * private Variablen
	 */
	private String sFileName = "";
	private FileReader oFileReader = null;
	public int iLines = 0;
	public int iColons = 0;
	public String[][] aMap = null;
	
	
	
	/**
	 * Constructor
	 * Nimmt Dateinamen ohne Pfad und Erweiterung entgegen und befüllt daraus globale Variablen und Objekte.
	 * Von Erweiterung ".csv" und festem Pfad "data/" unterhalb von src ausgehen.
	 * 
	 * @param String sFileName
	 * @throws FileNotFoundException 
	 */
	public CsvHandler(String sFileName)
	{
		this.sFileName = "./src/data/" + sFileName + ".csv";
		
		// Reader
		try {
			this.oFileReader = new FileReader(this.sFileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// Dimensionen
		try {
			this.fetchDimensions();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * Fragt Dimensionen der CSV-Datei ab und speichert sie in statischen Variablen.
	 * @return String
	 * @throws IOException 
	 */
	private void fetchDimensions() throws IOException{
		CSVReader oCR = new CSVReader(this.oFileReader,';','"');
		List<String[]> content = oCR.readAll();
		
		String[] sColons = null;
		
		int iLines = 0;
		
		for (Object oTmpObject : content) {
		    sColons = (String[]) oTmpObject;
		    
		    // Anzahl der Spalten erfassen
		    int iColons = sColons.length;
		    this.iColons = iColons;
			iLines++;
		}
		
		// Anzahl der Zeilen erfassen
		this.iLines = iLines;
		//System.out.println("Zeilen: " + CsvHandler.iLines + ", Spalten: " + CsvHandler.iColons);
		
		// Neues Array mit passenden Dimensionen erstellen
		String aMap[][] = new String[this.iLines][this.iColons];
		
		// Dateihandle durchlaufen und in Array stecken		
		int iTheLineCounter = 0;
		
		for (Object oCobj : content) {
		    sColons = (String[]) oCobj;
				
		    int iColCntr = 0;
		    while (iColCntr <= sColons.length - 1){
		    	aMap[iTheLineCounter][iColCntr] = sColons[iColCntr];
		    	iColCntr++;
		    }		
		    
			iTheLineCounter++;
		}
		
		this.aMap = aMap; // Map in statischer Variable speichern
		oCR.close(); //Handle schließen
	}
	
	
	
	/**
	 * Sichtbar machen (Debug/Entwickler)
	 */
	public void viewMap()
	{
		for (int k = 0; k < this.aMap.length; ++k) {
		     for (int l = 0; l < this.aMap[k].length; ++l) {
		        System.out.print(this.aMap[k][l] + "\t");
		     }
		     System.out.println("");
		}
	}
	
	
	
	/**
	 * Lesen: Übergeben
	 * @param null
	 * @return String[][]
	 */
	public String[][] read()
	{
		return this.aMap;
	}
	
	
	/**
	 * Schreiben: Übergebenes zweidomensionales Array in Datei schreiben
	 */
	public void write(String[][] aWriteThisMap)
	{
		// mach nichts
		
	}
}
