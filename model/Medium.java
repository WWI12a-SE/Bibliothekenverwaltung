package model;
import controller.MediaMapper;
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
	
	private static MediaMapper mediaMapper;

	private int row;
	
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
	public Medium(MediaMapper mediaMapper, int row){
		
		this.row = row;
		Medium.mediaMapper = mediaMapper;
		
	}

	/**
	 * Getter der ID
	 * @return ID : Integer
	 */
	public int getID() {
		return Integer.parseInt((String)mediaMapper.getIntegerData(row, COL_ID));
//		System.out.println(mediaMapper.getData(row, COL_ID));
//		return 444;
	}

	/**
	 * Setter der ID
	 * @param ID : Integer
	 */
	public void setID(int ID) {
		mediaMapper.setData(row, COL_ID, ID);
	}

	/**
	 * Getter des Titels
	 * @return sTitle : String
	 */
	public String getTitle() {
		return String.valueOf(mediaMapper.getStringData(row, COL_TITLE));
	}

	/**
	 * Setter des Titels
	 * @param sTitle : String
	 */
	public void setTitle(String sTitle) {
		mediaMapper.setData(row, COL_TITLE, sTitle);
	}

	/**
	 * Getter des Autors
	 * @return sAuthor : String
	 */
	public String getAuthor() {
		return (String)mediaMapper.getStringData(row, COL_AUTHOR);
	}

	/**
	 * Setter des Autors
	 * @param sAuthor : String
	 */
	public void setAuthor(String sAuthor) {
		mediaMapper.setData(row, COL_AUTHOR, sAuthor);
	}

	/**
	 * Getter des Verlags
	 * @return sPublisher : String
	 */
	public String getPublisher() {
		return (String)mediaMapper.getStringData(row, COL_PUBLISHER);
	}

	/**
	 * Setter des Verlags
	 * @param sPublisher : String
	 */
	public void setPublisher(String sPublisher) {
		mediaMapper.setData(row, COL_PUBLISHER, sPublisher);
	}

	/**
	 * Getter der ISBN
	 * @return sIsbn : String
	 */
	public String getIsbn() {
		return (String)mediaMapper.getStringData(row, COL_ISBN);
	}

	/**
	 * Setter der ISBN
	 * @param sIsbn : String
	 */
	public void setIsbn(String sIsbn) {
		mediaMapper.setData(row, COL_ISBN, sIsbn);
	}

	/**
	 * Getter der Stichworte
	 * @return sKeywords : String
	 */
	public String getKeywords() {
		return (String)mediaMapper.getStringData(row, COL_KEYWORDS);
	}

	/**
	 * Setter der Stichworte
	 * @param sKeywords : String
	 */
	public void setKeywords(String sKeywords) {
		mediaMapper.setData(row, COL_KEYWORDS, sKeywords);
	}

	/**
	 * Getter der Auflage
	 * @return iEdition : Integer
	 */
	public int getEdition() {
		return Integer.parseInt(String.valueOf(mediaMapper.getIntegerData(row, COL_EDITION)));
	}

	/**
	 * Setter der Auflage
	 * @param iEdition : Integer
	 */
	public void setEdition(int iEdition) {
		mediaMapper.setData(row, COL_EDITION, iEdition);
	}

	/**
	 * Getter der Anzahl Exemplare im Bestand (nicht verliehen)
	 * @return iOnStock : Integer
	 */
	public int getOnStock() {
		return Integer.parseInt(String.valueOf(mediaMapper.getIntegerData(row, COL_STOCK)));
	}

	/**
	 * Setter der Anzahl Exemplare im Bestand (nicht verliehen)
	 * @param iOnStock : Integer
	 */
	public void setOnStock(int iOnStock) {
		mediaMapper.setData(row, COL_STOCK, iOnStock);
	}

	/**
	 * Getter der Anzahl Exemplare im System (unabhaenging des Verleih-Status)
	 * @return iCopies : Integer
	 */
	public int getCopies() {
		return Integer.parseInt(String.valueOf(mediaMapper.getIntegerData(row, COL_COPIES)));
	}

	/**
	 * Setter der Anzahl Exemplare im System (unabhaenging des Verleih-Status)
	 * @param iCopies : Integer
	 */
	public void setCopies(int iCopies) {
		mediaMapper.setData(row, COL_COPIES, iCopies);
	}
	
	/**
	 * Liest alle Attribute des Mediums aus und gibt diese als String-Array zurueck.
	 * Die Indiziers entsprechen den CSV-Spalten, welche den Medium-Konstanten zu entnehmen sind.
	 * @return values : String[]
	 */
	public String[] getValuesAsStringArray(){
		String[] values = new String[AMOUNT_COLUMNS];
		values[COL_AUTHOR] = this.getAuthor();
		values[COL_PUBLISHER] = this.getPublisher();
		values[COL_COPIES] = String.valueOf(this.getCopies());
		values[COL_EDITION] = String.valueOf(this.getEdition());
		values[COL_ID] = String.valueOf(this.getID());
		values[COL_ISBN] = this.getIsbn();
		values[COL_STOCK] = String.valueOf(this.getOnStock());
		values[COL_TITLE] = this.getTitle();
		values[COL_KEYWORDS] = String.valueOf(this.getKeywords());
		return values;
	}
	
}
