package model;
import java.util.Date;

import controller.Mapper;

public class Reservation {
	
	//CSV-Spalten
	public static final int COL_RESERVATION_ID = 0;
	public static final int COL_LOGINNAME = 1;
	public static final int COL_MEDIA_ID = 2;
	public static final int COL_RETURNDATE = 3;
	public static final int COL_EXTENSIONS = 4;
	public static final int AMOUNT_COLUMNS = 5;
	
	private static Mapper reservationMapper;
	
	private int row;
	
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
	public Reservation(Mapper reservationMapper, int row){
		
		this.row = row;
		Reservation.reservationMapper = reservationMapper;
	}
	
	/**
	 * Getter der ID
	 * @return ID : Integer
	 */
	public int getReservationID() {
		return reservationMapper.getIntegerData(row, COL_RESERVATION_ID);
	}
	
	/**
	 * Setter der ID
	 * @param ID : Integer
	 */
	public void setReservationID(int reservationID) {
		reservationMapper.setData(this.row, COL_RESERVATION_ID, reservationID);
	}
	
	/**
	 * Getter des LoginNamens, der ID des Users
	 * @return loginName : String
	 */
	public String getLoginName() {
		return String.valueOf(reservationMapper.getStringData(row, COL_LOGINNAME));
	}
	
	/**
	 * Setter des LoginNamens, der ID des Users
	 * @param loginName : String
	 */
	public void setLoginName(String sLoginName) {
		reservationMapper.setData(row, COL_LOGINNAME, sLoginName);
	}
	
	/**
	 * Getter der MediaID
	 * @return ID : Integer
	 */
	public int getMediaID() {
		return reservationMapper.getIntegerData(row, COL_MEDIA_ID);
	}
	
	/**
	 * Setter der MediaID
	 * @return ID : Integer
	 */
	public void setMediaID(int iMediaID) {
		reservationMapper.setData(this.row, COL_MEDIA_ID, iMediaID);
	}
	
	/**
	 * Getter des Rueckgabe-Datums.
	 * @return dateReturnDate : Date
	 */
	public Date getReturnDate() {
		try{
			Date date = (Date)reservationMapper.getData(this.row, COL_RETURNDATE);
			return date;
		}catch(Exception e){
			return new Date();
		}
	}
	
	/**
	 * Setter des Rueckgabe-Datums.
	 * @param dateReturnDate : Date
	 */
	public void setReturnDate(Date dateReturnDate) {
		reservationMapper.setData(row, COL_RETURNDATE, dateReturnDate);
	}
	
	/**
	 * Getter der Ueberziehungen.
	 * @return iExtensions : Integer
	 */
	public int getExtensions() {
		return reservationMapper.getIntegerData(row, COL_EXTENSIONS);
	}
	
	/**
	 * Setter der Ueberziehungen.
	 * @param iExtensions : Integer
	 */
	public void setExtensions(int iExtensions) {
		reservationMapper.setData(this.row, COL_EXTENSIONS, iExtensions);
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

}
