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
 * Stundenzähler: 10,5
 * @author Johannes Ackermann
 */

package core;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import core.opencsv.*;

public class CsvHandler {

	/**
	 * private Variablen
	 */
	private String sObjectType = ""; // Kurzname der CSV-Datei, mit der gearbeitet wird
	private String sFileName = ""; // Name der Datei (mit Pfad und Erweiterung)
	private File oFile = null; // File-Objekt
	private FileReader oFileReader = null; // FileReader-Objekt
	private FileWriter oFileWriter = null;
	public int iLines = 0; // Zeilen
	public int iColons = 0; // Spalten
	public String[][] aMap = null; // Komplette CSV-Map
	
	
	
	/**
	 * Constructor
	 * Nimmt Dateinamen ohne Pfad und Erweiterung entgegen und befüllt daraus globale Variablen und Objekte.
	 * Von Erweiterung ".csv" und festem Pfad "data/" unterhalb von src ausgehen.
	 * 
	 * @author ja
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
	 * @author ja
	 * 
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
	 * @author ja
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
	 * @author ja
	 * 
	 * @param null
	 * @return String[][]
	 */
	public String[][] read()
	{
		return this.aMap;
	}
	
	
	
	/**
	 * Alle IDs zurückgeben
	 * Gibt ein Array mit allen IDs dieser Instanzu zurück.
	 * @author ja
	 * 
	 * @return String[]
	 */
	public String[] getAllIDs()
	{
		String[] sRtn = new String[this.iLines];
		int iCntr = 0;
		while(iCntr < this.iLines){
			sRtn[iCntr] = this.aMap[iCntr][0];
			iCntr++;
		}
		
		return sRtn;
	}
	
	
	
	/**
	 * Zeile als Array zurückgeben
	 * Existiert keine Zeile mit dieser ID, wird ein leeres Array zurückgegeben
	 * @author ja
	 * 
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
	 * Gibt die Nummer (nicht den Index!) der Zeile mit einer ID zurück, beginnt bei 1.
	 * Liefert 0, wenn keine Zeile mit der gefragten ID existiert.
	 * @author ja
	 * 
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
		
		// Mehr Zeilen gezählt => existiert nicht, 0 setzen.
		if (iOutput > this.iLines){
			return 0;
		}
		else{
			return iOutput;
		}
	}
	
	
	
	/**
	 * Entscheidet, ob ein Datensatz angehängt oder überschrieben wird, wenn die ID bereits existiert.@author ja
	 * @author ja
	 * 
	 * @param String[]
	 */
	public void update(String[] aLine)
	{
		// Stimmt die Länge des Arrays mit der Anzahl der Spalten in der verwendeten CSV-Datei überein?
		if (aLine.length == this.iColons){
			// System.out.println("Länge des einzufügenden Arrays: OK.");
			
			// Auf Vorhandensein der ID prüfen
			if (this.getLineById(aLine[0])[0].equals(aLine[0])){
				// System.out.println("Es existiert bereits ein Eintrag mit dieser ID, deshalb wird er aktualisier.");
				this.updateLine(aLine);
			}
			else{
				// System.out.println("Es existiert noch kein Eintrag mit dieser ID, er kann aber angelegt werden.");
				this.addLine(aLine);
			}
			
		}
		// Falsche Länge
		else{
			System.out.println("Der übergebene Datensatz besteht aus " + aLine.length + " Elementen, " +
					this.iColons + " werden bei Elementen vom Typ " + this.sObjectType + " erwartet.");
		}
		
	}
	
	
	
	/**
	 * Überschreibt eine Zeile mit vorhandener ID in der Map
	 * @author ja
	 * 
	 * @param aLine
	 */
	private void updateLine(String[] aLine) {		
		// In der Map in der ersten Dimension die Zeile benennen und daraus den Index bilden
		int iLineIndex = this.getLineNumber(aLine[0]) - 1;
		this.aMap[iLineIndex] = aLine;
	}
	
	
	
	/**
	 * Neue Zeile anfügen, wenn ID nicht existiert.
	 * Kopiert das vorhandene Array this.aMap auf ein Array, das in der ersten Dimension um 1 verlängert wurde.
	 * @author ja
	 * 
	 * @param aLine
	 */
	private void addLine(String[] aLine) {
		// Neues, verlängertes Array erzeugen
		String[][] aTmpMap = new String[this.aMap.length + 1][this.iColons];
		
		// Auf temporäre, verlängerte Map kopieren
		System.arraycopy(this.aMap,0,aTmpMap,0,this.aMap.length);
		
		// Die neuen Inhalte in die neue Zeile einfügen
		aTmpMap[this.aMap.length] = aLine;
		
		// Neue Map auf this.aMap kopieren
		this.aMap = aTmpMap;
		
		// globaler Wert der Zeilen steigt jetzt um 1
		this.iLines = this.iLines + 1;
	}
	
	
	
	/**
	 * Eine Zeile löschen
	 * Löscht eine Zeile, deren ID angegeben wurde
	 * Unternimmt nichts, wenn diese Zeile nicht existiert.
	 * @author ja
	 * 
	 * @param String
	 */
	public void dropLine(String sId)
	{
		// Existiert überhaupt eine Zeile mit dieser ID?
		if (this.getLineById(sId)[0].equals("0")){ // Wenn "0", dann Meldung
			//System.out.println("Es existiert kein Datensatz mit dieser ID. Nichts gelöscht.");
		}
		// Sonst...
		else{
			// Verkürztes Array vorbereiten
			String[][] aTmpMap = new String[this.aMap.length - 1][this.iColons];
			
			int iDropIndex = this.getLineNumber(sId) - 1;

			int iCntNew = 0; // Zähler für verkürztes Array
			int iCntOld = 0; // Zähler für altes Array
			
			while (iCntNew < aTmpMap.length){
				
				// Bei Erreichen des zu löschenden Index den Zähler für das alte Array um 1 erhöhen
				if (iCntOld == iDropIndex){			
					iCntOld++;
				}
				
				// Altes Array auf neues kopieren und zu löschendes übergehen
				aTmpMap[iCntNew] = this.aMap[iCntOld];
				
				iCntNew++;
				iCntOld++;
			}
			
			// Neue Map auf this.aMap kopieren
			this.aMap = aTmpMap;
			
			// globaler Wert der Zeilen fällt jetzt um 1
			this.iLines = this.iLines - 1;
		}
	}



	/**
	 * Zeile ansehen (Debug/Entwickler)
	 * @author ja
	 * 
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
	 * Schreiben: Übergebenes zweidomensionales Array in Datei schreiben
	 * Wird nicht mehr benötigt, da sich CsvHandler selbst um die Verwaltung kümmert.
	 * @author ja
	 * 
	 * @deprecated
	 */
	public void write(String[][] aWriteThisMap)
	{
		// mach nichts
		
	}
	
	
	
	/**
	 * Datei speichern
	 * Schreibt die Map CSV-formatiert in die Datei
	 */
	public void save()
	{
		// Dateiinhalt als String vorbereiten		
		String sOutput = "";
		
		for (int k = 0; k < this.aMap.length; ++k) {
		     for (int l = 0; l < this.aMap[k].length; ++l) {
		    	 sOutput += "\"";
		    	 sOutput += this.aMap[k][l];
		    	 sOutput += "\"";
		    	 
		    	 if (l != this.aMap[k].length - 1){
			    	 sOutput += ";";
		    	 }
		     }
		     //sOutput += "\n";
		     sOutput += System.getProperty("line.separator");
		}
		
		//Speichern
		this.oFile = new File(this.sFileName);
		try{
			this.oFileWriter = new FileWriter(this.oFile,false);
			this.oFileWriter.write(sOutput); // schreiben
			this.oFileWriter.flush(); // Durchspülen
			this.oFileWriter.close(); // Handle schließen
		}
		catch (IOException e){
			e.printStackTrace();
		}
		
		//System.out.println(sOutput);
		
	}
}
