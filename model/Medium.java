package model;
import core.CsvHandler;

/**
 * <p>
 * Die Klasse Medium repraesentiert ein echtes Medium der Bibliothek und stellt die in der CSV-Datei 
 * gespeicherten Daten zur Verfuegung.
 * </p><p>
 * Um einen Medium zu instanziieren muss ueber ein MediaHandler-Objekt die Methode 
 * getMedium() oder getAllMedia() verwendet werden um die Konsistenz der Daten zu gewaehrleisten.
 * </p>
 * @author weisseth
 * @package model
 */
public class Medium {
	
	//CSV-Spalten
	public static final int COL_ID = 0;
	public static final int COL_TITLE = 1;
	public static final int COL_AUTHOR = 2;
	public static final int COL_PUBLISHER = 3;
	public static final int COL_ISBN = 4;
	public static final int COL_EDITION = 5;
	public static final int COL_COPIES =6;
	public static final int COL_STOCK = 7;
	public static final int COL_KEYWORDS = 8;
	public static final int AMOUNT_COLUMNS = 9;

	private int ID = 0;
	private String sTitle = "";
	private String sAuthor = "";
	private String sPublisher = "";
	private String sIsbn = "";
	private String sKeywords = "";
	private int iEdition = 0; // Ausgabe
	private int iOnStock = 0; // Verfügbare Exemplare
	private int iCopies = 0; // Vorhandene Exemplare
	
	private CsvHandler csvHandler;
	
	/**
	 * <p>
	 * Konstruktor des Mediums. Stellt die in der CSV-Datei 
	 * gespeicherten Daten zur Verfuegung.
	 * </p><p>
	 * Um ein Medium zu instanziieren muss ueber ein MediaHandler-Objekt die Methode
	 * getMedium() oder getAllMedia() verwendet werden um die Konsistenz der Daten zu gewaehrleisten.
	 * </p>
	 * @param csvHandler : CsvHandler
	 * @param ID : Integer
	 */
	public Medium(CsvHandler csvHandler, int ID){
		this.csvHandler = csvHandler;
		String[] values = csvHandler.getLineById(String.valueOf(ID));
		this.setID(ID);
		this.setAuthor(values[COL_AUTHOR]);
		this.setEdition(Integer.parseInt(values[COL_EDITION]));
		this.setIsbn(values[COL_ISBN]);
		this.setOnStock(Integer.parseInt(values[COL_STOCK]));
		this.setPublisher(values[COL_PUBLISHER]);
		this.setTitle(values[COL_TITLE]);
		this.setKeywords(values[COL_KEYWORDS]);
		this.setCopies(Integer.parseInt(values[COL_COPIES]));
	}

	/**
	 * Getter der ID
	 * @return ID : Integer
	 */
	public int getID() {
		return this.ID;
	}

	/**
	 * Setter der ID
	 * @param ID : Integer
	 */
	public void setID(int ID) {
		if(this.ID != ID){
			this.ID = ID;
			this.stage();
		}
	}

	/**
	 * Getter des Titels
	 * @return sTitle : String
	 */
	public String getTitle() {
		return sTitle;
	}

	/**
	 * Setter des Titels
	 * @param sTitle : String
	 */
	public void setTitle(String sTitle) {
		if(!this.sTitle.equals(sTitle)){
			this.sTitle = sTitle;
			this.stage();
		}
	}

	/**
	 * Getter des Autors
	 * @return sAuthor : String
	 */
	public String getAuthor() {
		return sAuthor;
	}

	/**
	 * Setter des Autors
	 * @param sAuthor : String
	 */
	public void setAuthor(String sAuthor) {
		if(!this.sAuthor.equals(sAuthor)){
			this.sAuthor = sAuthor;
			this.stage();
		}
	}

	/**
	 * Getter des Verlags
	 * @return sPublisher : String
	 */
	public String getPublisher() {
		return sPublisher;
	}

	/**
	 * Setter des Verlags
	 * @param sPublisher : String
	 */
	public void setPublisher(String sPublisher) {
		if(!this.sPublisher.equals(sPublisher)){
			this.sPublisher = sPublisher;
			this.stage();
		}
	}

	/**
	 * Getter der ISBN
	 * @return sIsbn : String
	 */
	public String getIsbn() {
		return sIsbn;
	}

	/**
	 * Setter der ISBN
	 * @param sIsbn : String
	 */
	public void setIsbn(String sIsbn) {
		if(!this.sIsbn.equals(sIsbn)){
			this.sIsbn = sIsbn;
			this.stage();
		}
	}

	/**
	 * Getter der Stichworte
	 * @return sKeywords : String
	 */
	public String getKeywords() {
		return this.sKeywords;
	}

	/**
	 * Setter der Stichworte
	 * @param sKeywords : String
	 */
	public void setKeywords(String sKeywords) {
		if(!this.sKeywords.equals(sKeywords)){
			this.sKeywords = sKeywords;
			this.stage();
		}
	}

	/**
	 * Getter der Auflage
	 * @return iEdition : Integer
	 */
	public int getEdition() {
		return iEdition;
	}

	/**
	 * Setter der Auflage
	 * @param iEdition : Integer
	 */
	public void setEdition(int iEdition) {
		if(this.iEdition != iEdition){
			this.iEdition = iEdition;
			this.stage();
		}
	}

	/**
	 * Getter der Anzahl Exemplare im Bestand (nicht verliehen)
	 * @return iOnStock : Integer
	 */
	public int getOnStock() {
		return iOnStock;
	}

	/**
	 * Setter der Anzahl Exemplare im Bestand (nicht verliehen)
	 * @param iOnStock : Integer
	 */
	public void setOnStock(int iOnStock) {
		if(this.iOnStock != iOnStock){
			this.iOnStock = iOnStock;
			this.stage();
		}
	}

	/**
	 * Getter der Anzahl Exemplare im System (unabhaenging des Verleih-Status)
	 * @return iCopies : Integer
	 */
	public int getCopies() {
		return iCopies;
	}

	/**
	 * Setter der Anzahl Exemplare im System (unabhaenging des Verleih-Status)
	 * @param iCopies : Integer
	 */
	public void setCopies(int iCopies) {
		if(this.iCopies != iCopies){
			this.iCopies = iCopies;
			this.stage();
		}
	}
	
	/**
	 * Liest alle Attribute des Mediums aus und gibt diese als String-Array zurueck.
	 * Die Indiziers entsprechen den CSV-Spalten, welche den Medium-Konstanten zu entnehmen sind.
	 * @return values : String[]
	 */
	public String[] getValuesAsStringArray(){
		String[] values = new String[AMOUNT_COLUMNS];
		values[COL_AUTHOR] = this.getAuthor();
		values[COL_COPIES] = String.valueOf(this.getCopies());
		values[COL_EDITION] = String.valueOf(this.getEdition());
		values[COL_ID] = String.valueOf(this.getID());
		values[COL_ISBN] = this.getIsbn();
		values[COL_STOCK] = String.valueOf(this.getOnStock());
		values[COL_TITLE] = this.getTitle();
		values[COL_KEYWORDS] = String.valueOf(this.getKeywords());
		return values;
	}
	
	/**
	 * Die Attribute des Mediums werden im CsvHandler zwischengespeichert und somit 
	 * zum speichern in der CSV-Datei bereit gestellt ("gestaged").
	 */
	private void stage()
	{
		csvHandler.update(this.getValuesAsStringArray());
	}
	
}
