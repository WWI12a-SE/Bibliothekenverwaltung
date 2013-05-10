/**
 * Medien-Modell
 * Einen Datensatz aus der Datenbank lesen oder einen neuen zum Schreiben vorbereiten.
 * 
 * @author ja
 */


package model;
import core.*;

public class Medium {
	
	//CSV-Spalten
	public static final int COL_ID = 0;
	public static final int COL_TITLE = 1;
	public static final int COL_AUTHOR = 2;
	public static final int COL_PUBLISHER = 3;
	public static final int COL_ISBN = 4;
	public static final int COL_YEAR = 5;
	public static final int COL_EDITION = 6;
	public static final int COL_STOCK = 7;
	public static final int COL_COPIES = 8;
	public static final int AMOUNT_COLUMNS = 9;

	private int ID = 0;
	private String sTitle = "";
	private String sAuthor = "";
	private String sPublisher = "";
	private String sIsbn = "";
	private int iYear = 0;
	private int iEdition = 0; // Ausgabe
	private int iOnStock = 0; // Verfügbare Exemplare
	private int iCopies = 0; // Vorhandene Exemplare
	private CsvHandler csvHandler;
	
	public int getID() {
		return this.ID;
	}

	public void setID(int ID) {
		if(this.ID != ID){
			this.ID = ID;
			this.stage();
		}
	}

	public String getTitle() {
		return sTitle;
	}

	public void setTitle(String sTitle) {
		this.sTitle = sTitle;
		if(!this.sTitle.equals(sTitle)){
			this.sTitle = sTitle;
			this.stage();
		}
	}

	public String getAuthor() {
		return sAuthor;
	}

	public void setAuthor(String sAuthor) {
		if(!this.sAuthor.equals(sAuthor)){
			this.sAuthor = sAuthor;
			this.stage();
		}
	}

	public String getPublisher() {
		return sPublisher;
	}

	public void setPublisher(String sPublisher) {
		if(!this.sPublisher.equals(sPublisher)){
			this.sPublisher = sPublisher;
			this.stage();
		}
	}

	public String getIsbn() {
		return sIsbn;
	}

	public void setIsbn(String sIsbn) {
		if(!this.sIsbn.equals(sIsbn)){
			this.sIsbn = sIsbn;
			this.stage();
		}
	}

	public int getYear() {
		return iYear;
	}

	public void setYear(int iYear) {
		if(this.iYear != iYear){
			this.iYear = iYear;
			this.stage();
		}
	}

	public int getEdition() {
		return iEdition;
	}

	public void setEdition(int iEdition) {
		if(this.iEdition != iEdition){
			this.iEdition = iEdition;
			this.stage();
		}
	}

	public int getOnStock() {
		return iOnStock;
	}

	public void setOnStock(int iOnStock) {
		if(this.iOnStock != iOnStock){
			this.iOnStock = iOnStock;
			this.stage();
		}
	}

	public int getCopies() {
		return iCopies;
	}

	public void setCopies(int iCopies) {
		if(this.iCopies != iCopies){
			this.iCopies = iCopies;
			this.stage();
		}
	}
	
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
		this.setYear(Integer.parseInt(values[COL_YEAR]));
		this.setCopies(Integer.parseInt(values[COL_COPIES]));
	}
	
	public String[] getValuesAsStringArray(){
		String[] values = new String[AMOUNT_COLUMNS];
		values[COL_AUTHOR] = this.getAuthor();
		values[COL_COPIES] = String.valueOf(this.getCopies());
		values[COL_EDITION] = String.valueOf(this.getEdition());
		values[COL_ID] = String.valueOf(this.getID());
		values[COL_ISBN] = this.getIsbn();
		values[COL_STOCK] = String.valueOf(this.getOnStock());
		values[COL_TITLE] = this.getTitle();
		values[COL_YEAR] = String.valueOf(this.getYear());
		return values;
	}
	
	public void stage()
	{
		csvHandler.update(this.getValuesAsStringArray());
	}
	
}
