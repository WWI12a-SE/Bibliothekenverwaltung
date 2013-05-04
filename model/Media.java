/**
 * Medien-Modell
 * Einen Datensatz aus der Datenbank lesen oder einen neuen zum Schreiben vorbereiten.
 * 
 * @author ja
 */


package model;
import core.*;
// Hallo Sandra ich bin da
public class Media {
	/**
	 * Private Variablen
	 */
	private int ID = 0;
	private String sTitle = null;
	private String sAuthor = null;
	private String sPublisher = null;
	private String sIsbn = null;
	private int iYear = 0;
	private int iEdition = 0; // Ausgabe
	private int iOnStock = 0; // Verfügbare Exemplare
	private int iCopies = 0; // Vorhandene Exemplare
	private StockMapper oStock = null; // Passender Mapper
	
	/**
	 * Rückgabeobjekt
	 */
	private Media oMedium = null;
	
	
	
	/**
	 * Verbindung mit CSV-Datei herstellen
	 */
	
	
	
	/**
	 * Aus bestehemdem Datensatz neues Medien-Objekt erzeugen, indem ID übergeben wird
	 * SONST davon ausgehen, dass ein neues Medium angelegt wird.
	 * @param int
	 * @return boolean
	 */
	public boolean setMedium(int iID)
	{
		// Auf jeden Fall die ID setzen, sie ist ja bereits bekannt.
		this.ID = iID;
		
		// Wenn Datensatz mit dieser ID existiert, neues Objekt erzeugen und in this.oUser speichern.
		// Hierfür alle Setter durchlaufen.
		
		// Existiert der Datensatz noch nicht, soll er neu angelegt werden. Ebenfalls setzen.
		
		return true;
	}
	
	/**
	 * ...
	 */
	
	
	
	/**
	 * Alle Medien ausgeben
	 */
	public void getAllMedia()
	{
		//return aAllMedia[][];
	}
	
	
	
	/**
	 * Speichern.
	 * Schreibt das aktuelle Objekt in die Map zurück.
	 * 
	 * @return boolean
	 */
	public boolean save()
	{
		
		return false;		
	}

	
}
