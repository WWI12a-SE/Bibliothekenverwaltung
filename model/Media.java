/**
 * Medien-Modell
 * Einen Datensatz aus der Datenbank lesen oder einen neuen zum Schreiben vorbereiten.
 * 
 * @author ja
 */


package model;
import core.*;

public class Media {
	/**
	 * Private Variablen
	 */
	private int ID = 0;
	
	private static Media[] media;
	
	private static void initMedia(){
		if(media == null){
			int lastIndex = oMediaMapper.getLastIndex();
			media = new Media[lastIndex];
			for(int i = 0; i <= lastIndex; i++){
				media[i] = new Media();
				String[] values = oMediaMapper.readLine(i);
				media[i].setID(Integer.parseInt(values[MediaMapper.COL_ID]));
				media[i].setiCopies(Integer.parseInt(values[MediaMapper.COL_COPIES]));
				media[i].setiEdition(Integer.parseInt(values[MediaMapper.COL_EDITION]));
				media[i].setiOnStock(Integer.parseInt(values[MediaMapper.COL_STOCK]));
				media[i].setiYear(Integer.parseInt(values[MediaMapper.COL_YEAR]));
				media[i].setsAuthor(values[MediaMapper.COL_AUTHOR]);
				media[i].setsIsbn(values[MediaMapper.COL_ISBN]);
				media[i].setsTitle(values[MediaMapper.COL_TITLE]);
				media[i].setsPublisher(values[MediaMapper.COL_PUBLISHER]);
			}
		}
	}
	
	public int getID() {
		return this.ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public String getsTitle() {
		return sTitle;
	}

	public void setsTitle(String sTitle) {
		this.sTitle = sTitle;
	}

	public String getsAuthor() {
		return sAuthor;
	}

	public void setsAuthor(String sAuthor) {
		this.sAuthor = sAuthor;
	}

	public String getsPublisher() {
		return sPublisher;
	}

	public void setsPublisher(String sPublisher) {
		this.sPublisher = sPublisher;
	}

	public String getsIsbn() {
		return sIsbn;
	}

	public void setsIsbn(String sIsbn) {
		this.sIsbn = sIsbn;
	}

	public int getiYear() {
		return iYear;
	}

	public void setiYear(int iYear) {
		this.iYear = iYear;
	}

	public int getiEdition() {
		return iEdition;
	}

	public void setiEdition(int iEdition) {
		this.iEdition = iEdition;
	}

	public int getiOnStock() {
		return iOnStock;
	}

	public void setiOnStock(int iOnStock) {
		this.iOnStock = iOnStock;
	}

	public int getiCopies() {
		return iCopies;
	}

	public void setiCopies(int iCopies) {
		this.iCopies = iCopies;
	}

	private String sTitle = null;
	private String sAuthor = null;
	private String sPublisher = null;
	private String sIsbn = null;
	private int iYear = 0;
	private int iEdition = 0; // Ausgabe
	private int iOnStock = 0; // Verfügbare Exemplare
	private int iCopies = 0; // Vorhandene Exemplare
	private static MediaMapper oMediaMapper = new MediaMapper(); // Passender Mapper
	
	/*
	 * Aus bestehemdem Datensatz neues Medien-Objekt erzeugen, indem ID übergeben wird
	 * SONST davon ausgehen, dass ein neues Medium angelegt wird.
	 * @param int
	 * @return boolean
	 *
	public boolean setMedium(int iID)
	{
		// Auf jeden Fall die ID setzen, sie ist ja bereits bekannt.
		this.ID = iID;
		
		// Wenn Datensatz mit dieser ID existiert, neues Objekt erzeugen und in this.oUser speichern.
		// Hierfür alle Setter durchlaufen.
		
		// Existiert der Datensatz noch nicht, soll er neu angelegt werden. Ebenfalls setzen.
		
		return true;
	}*/
	
	
	/**
	 * Speichern.
	 * Schreibt das aktuelle Objekt in die Map zurück.
	 * 
	 * @return boolean
	 */
	public void save()
	{
		String[] values = new String[MediaMapper.AMOUNT_COLUMNS];
		values[MediaMapper.COL_ID] = Integer.toString(this.ID);
		values[MediaMapper.COL_COPIES] = Integer.toString(this.iCopies);
		values[MediaMapper.COL_EDITION] = Integer.toString(this.iEdition);
		values[MediaMapper.COL_YEAR] = Integer.toString(this.iYear);
		values[MediaMapper.COL_STOCK] = Integer.toString(this.iOnStock);
		values[MediaMapper.COL_TITLE] = this.sTitle;
		values[MediaMapper.COL_PUBLISHER] = this.sPublisher;
		values[MediaMapper.COL_ISBN] = this.sIsbn;
		values[MediaMapper.COL_AUTHOR] = this.sAuthor;
		oMediaMapper.writeLine(values);	
	}
	
	/**
	 * Alle Medien ausgeben
	 */
	public static Media[] getAllMedia()
	{
		initMedia();
		return Media.media;
	}
	
	public static Media getMedia(int iID){
		initMedia();
		for(int i = 0; i < media.length; i++){
			if(media[i].getID() == iID){
				return media[i];
			}
		}
		return new Media();
	}
	
}
