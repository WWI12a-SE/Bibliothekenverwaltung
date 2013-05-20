package model;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import core.CsvHandler;

public class Reservation {
	
	//CSV-Spalten
	public static final int COL_RESERVATION_ID = 0;
	public static final int COL_LOGINNAME = 1;
	public static final int COL_MEDIA_ID = 2;
	public static final int COL_RETURNDATE = 3;
	public static final int COL_EXTENSIONS = 4;
	public static final int AMOUNT_COLUMNS = 5;
	
	private int reservationID;
	private String sLoginName;
	private int iMediaID;
	private Date dateReturnDate;
	private int iExtensions;
	
	private CsvHandler csvHandler;
	
	/**
	 * <p>
	 * Konstruktor einer Reservierung. Stellt die in der CSV-Datei 
	 * gespeicherten Daten zur Verfuegung.
	 * </p><p>
	 * Um eine Reservierung zu instanziieren muss ueber ein ReservationHandler-Objekt die Methode
	 * getReservation() oder getAllReservations() verwendet werden um die Konsistenz der Daten zu gewaehrleisten.
	 * </p>
	 * @param csvHandler : CsvHandler
	 * @param ID : Integer
	 */
	public Reservation(CsvHandler csvHandler, int ID){
		
		this.csvHandler = csvHandler;
		String[] values = csvHandler.getLineById(String.valueOf(ID));
		
		this.setReservationID(ID);
		
		if(values[COL_MEDIA_ID] == null){
			this.iMediaID = 0;
		}else{
			this.iMediaID = Integer.parseInt(values[COL_MEDIA_ID]);
		}
		
		if(values[COL_EXTENSIONS] == null){
			this.iExtensions = 0;
		}else{
			this.iExtensions = Integer.parseInt(values[COL_EXTENSIONS]);
		}
		
		if(values[COL_RETURNDATE] == null){
			this.dateReturnDate = new Date();
		}else{
			try {
				DateFormat dateFormat = DateFormat.getInstance();
				this.dateReturnDate = dateFormat.parse(values[COL_RETURNDATE]);
			} catch (ParseException e) {
//				TODO Fehlerausgabe Date-ParseException
				System.out.println("Date-Parse-Fehler Reservation-Konstruktor");
//				e.printStackTrace();
			}
		}

		if(values[COL_LOGINNAME] == null){
			this.sLoginName = "";
		}else{
			this.sLoginName = values[COL_LOGINNAME];
		}
	}
	
	/**
	 * Getter der ID
	 * @return ID : Integer
	 */
	public int getReservationID() {
		return reservationID;
	}
	
	/**
	 * Setter der ID
	 * @param ID : Integer
	 */
	public void setReservationID(int reservationID) {
		if(this.reservationID != reservationID){
			this.reservationID = reservationID;
			this.stage();
		}
		
	}
	
	/**
	 * Getter des LoginNamens, der ID des Users
	 * @return loginName : String
	 */
	public String getLoginName() {
		return sLoginName;
	}
	
	/**
	 * Setter des LoginNamens, der ID des Users
	 * @param loginName : String
	 */
	public void setLoginName(String sLoginName) {
		if(!this.sLoginName.equals(sLoginName)){
			this.sLoginName = sLoginName;
			this.stage();
		}
	}
	
	/**
	 * Getter der MediaID
	 * @return ID : Integer
	 */
	public int getMediaID() {
		return iMediaID;
	}
	
	/**
	 * Setter der MediaID
	 * @return ID : Integer
	 */
	public void setMediaID(int iMediaID) {
		if(this.iMediaID != iMediaID){
			this.iMediaID = iMediaID;
			this.stage();
		}
	}
	
	/**
	 * Getter des Rueckgabe-Datums.
	 * @return dateReturnDate : Date
	 */
	public Date getReturnDate() {
		return dateReturnDate;
	}
	
	/**
	 * Setter des Rueckgabe-Datums.
	 * @param dateReturnDate : Date
	 */
	public void setReturnDate(Date dateReturnDate) {
		this.dateReturnDate = dateReturnDate;
		this.stage();
	}
	
	/**
	 * Getter der Ueberziehungen.
	 * @return iExtensions : Integer
	 */
	public int getExtensions() {
		return iExtensions;
	}
	
	/**
	 * Setter der Ueberziehungen.
	 * @param iExtensions : Integer
	 */
	public void setExtensions(int iExtensions) {
		if(this.iExtensions != iExtensions){
			this.iExtensions = iExtensions;
			this.stage();
		}
	}
	
	/**
	 * Liest alle Attribute der Reservierung aus und gibt diese als String-Array zurueck.
	 * Die Indiziers entsprechen den CSV-Spalten, welche den Reservation-Konstanten zu entnehmen sind.
	 * @return values : String[]
	 */
	public String[] getValuesAsStringArray(){
		String[] values = new String[AMOUNT_COLUMNS];
		values[COL_EXTENSIONS] = String.valueOf(this.getExtensions());
		values[COL_LOGINNAME] = String.valueOf(this.getLoginName());
		values[COL_MEDIA_ID] = String.valueOf(this.getMediaID());
		values[COL_RESERVATION_ID] = String.valueOf(this.getReservationID());
		if(this.getReturnDate() != null){
			values[COL_RETURNDATE] = this.getReturnDate().toString();//DateFormat.getInstance().format(
		}else{
			values[COL_RETURNDATE] = "";
		}
		return values;
	}
	
	/**
	 * Die Attribute de Reservierung werden im CsvHandler zwischengespeichert und somit 
	 * zum speichern in der CSV-Datei bereit gestellt ("gestaged").
	 */
	private void stage()
	{
		csvHandler.update(this.getValuesAsStringArray());
	}
}
