/**
 * CSV-Handler
 * Liest eine CSV-Datei ein und parst sie in einem zweidimensionalen Array.
 * Unterst�tzt das Schreiben von CSV-Dateien.
 * 
 * Standardform:
 * "Zeichenfolge";"1000";"1234.56"
 * -	Alle Werte stehen in Anf�hrungszeichen.
 * -	Werte werden in Anf�hrungszeichen gefasst.
 * 
 * Stundenz�hler: 8,5
 * @author Johannes Ackermann
 * 
 * To do:
 * -	Zeilen mit neuer ID in Map schreiben
 * -	Map in Datei schreiben
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
	private String sObjectType = ""; // Kurzname der CSV-Datei, mit der gearbeitet wird
	private String sFileName = ""; //Name der Datei (mit Pfad und Erweiterung)
	private FileReader oFileReader = null; // FileReader-Objekt
	public int iLines = 0; // Zeilen
	public int iColons = 0; // Spalten
	public String[][] aMap = null; // Komplette CSV-Map
	
	
	
	/**
	 * Constructor
	 * Nimmt Dateinamen ohne Pfad und Erweiterung entgegen und bef�llt daraus globale Variablen und Objekte.
	 * Von Erweiterung ".csv" und festem Pfad "data/" unterhalb von src ausgehen.
	 * 
	 * @param String sFileName
	 * @throws FileNotFoundException 
	 */
	public CsvHandler(String sFileName)
	{
		this.sObjectType = sFileName;
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
		oCR.close(); //Handle schlie�en
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
	 * Lesen: �bergeben
	 * @param null
	 * @return String[][]
	 */
	public String[][] read()
	{
		return this.aMap;
	}
	
	
	
	/**
	 * Zeile als Array zur�ckgeben
	 * Existiert keine Zeile mit dieser ID, wird ein leeres Array zur�ckgegeben
	 * @param String
	 * @return String[]
	 */
	public String[] getLineById(String sId)
	{
		// Erste Spalte durchlaufen
		String[] sOut = new String[this.iColons];
		
		int iCntr = 0;
		while (iCntr < this.iLines){			
			// Hier steht die ID
			if (this.aMap[iCntr][0].equals(sId)){
				
				// Spalten in Array...
				int iCntz = 0;
				while (iCntz < this.iColons){
					sOut[iCntz] = this.aMap[iCntr][iCntz];
					iCntz++;
				}
				break;
			}
			iCntr++;
		}
		
		// Gibt es keine Zeile mit dieser ID, NULL setzen
		if (sOut[0] == null){
			int iCntz = 1;
			while (iCntz < sOut.length){
				sOut[iCntz] = null;
				iCntz++;
			}
			sOut[0] = "0";
			return sOut;
		}
		else{
			return sOut;
		}
	}
	
	
	
	/**
	 * Gibt die Nummer (nicht den Index!) der Zeile mit einer ID zur�ck, beginnt bei 1.
	 * Liefert 0, wenn keine Zeile mit der gefragten ID existiert.
	 * @param sId
	 * @return int
	 */
	public int getLineNumber(String sId){
		int iLineIndex = 0;
		
		// Array durchlaufen und auf Position aArr[i][0] nach genannter ID suchen
		while (iLineIndex < this.iLines){			

			// Durchlaufen
			if (this.aMap[iLineIndex][0].equals(sId)){
				break; // Trifft das zu, raus aus der umgebenden While-Schleife
			}
			iLineIndex++;
		}

		int iOutput = iLineIndex + 1;
		
		// Mehr Zeilen gez�hlt => existiert nicht, 0 setzen.
		if (iOutput > this.iLines){
			return 0;
		}
		else{
			return iOutput;
		}
	}
	
	
	
	/**
	 * Entscheidet, ob ein Datensatz angeh�ngt oder �berschrieben wird, wenn die ID bereits existiert.
	 * @param String[]
	 */
	public void update(String[] aLine)
	{
		// Stimmt die L�nge des Arrays mit der Anzahl der Spalten in der verwendeten CSV-Datei �berein?
		if (aLine.length == this.iColons){
			System.out.println("L�nge des einzuf�genden Arrays: OK.");
			
			// Auf Vorhandensein der ID pr�fen
			if (this.getLineById(aLine[0])[0].equals(aLine[0])){
				System.out.println("Es existiert bereits ein Eintrag mit dieser ID, deshalb wird er aktualisier.");
				this.updateLine(aLine);
			}
			else{
				System.out.println("Es existiert noch kein Eintrag mit dieser ID, er kann aber angelegt werden.");
				this.addLine(aLine);
			}
			
		}
		// Falsche L�nge
		else{
			System.out.println("Der �bergebene Datensatz besteht aus " + aLine.length + " Elementen, " +
					this.iColons + " werden bei Elementen vom Typ " + this.sObjectType + " erwartet.");
		}
		
	}
	
	
	
	/**
	 * �berschreibt eine Zeile mit vorhandener ID in der Map
	 * @param aLine
	 */
	private void updateLine(String[] aLine) {
		
		// In der Map in der ersten Dimension die Zeile benennen und daraus den Index bilden
		int iLineIndex = this.getLineNumber(aLine[0]) - 1;
				
		// Werte in Array schreiben		
		/* Schritt f�r Schritt
		int iCols = 0;
		while (iCols < this.iColons){
			this.aMap[iLineIndex][iCols] = aLine[iCols];
			iCols++;
		}
		*/
		this.aMap[iLineIndex] = aLine;
	}
	
	
	
	/**
	 * Neue Zeile anf�gen, wenn ID nicht existiert.
	 * Kopiert das vorhandene Array this.aMap auf ein Array, das in der ersten Dimension um 1 verl�ngert wurde.
	 * @param aLine
	 */
	private void addLine(String[] aLine) {
		String[][] aTmpMap = new String[this.iLines + 1][this.iColons];
		aTmpMap = this.aMap;
		
		// neue Zeilenl�nge als Index
		aTmpMap[aTmpMap.length - 1] = aLine;		
	}
	
	
	
	/**
	 * Eine Zeile l�schen
	 */
	private void dropLine(String sId)
	{
		//
	}



	/**
	 * Zeile ansehen (Debug/Entwickler)
	 * @param String[]
	 */
	public void viewLineById(String sId)
	{		
		String sOut[] = this.getLineById(sId);
		if (sOut == null){
			System.out.print("Es existiert kein Satz mit dieser ID. (" + sId + ")\n");
		}
		else{
			for(int i=0; i<sOut.length; ++i) {
				System.out.print(sOut[i]+"\t");
			}
		}
	}

	
	
	/**
	 * Schreiben: �bergebenes zweidomensionales Array in Datei schreiben
	 */
	public void write(String[][] aWriteThisMap)
	{
		// mach nichts
		
	}
}
