/**
 * Reservationsmodell
 * 
 * @author ja
 */

package model;
import java.util.Date;

import core.*;

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
	
	public int getReservationID() {
		return reservationID;
	}
	public void setReservationID(int reservationID) {
		if(this.reservationID != reservationID){
			this.reservationID = reservationID;
			this.stage();
		}
		
	}
	public String getLoginName() {
		return sLoginName;
	}
	public void setLoginName(String sLoginName) {
		if(!this.sLoginName.equals(sLoginName)){
			this.sLoginName = sLoginName;
			this.stage();
		}
	}
	public int getMediaID() {
		return iMediaID;
	}
	public void setMediaID(int iMediaID) {
		if(this.iMediaID != iMediaID){
			this.iMediaID = iMediaID;
			this.stage();
		}
	}
	public Date getReturnDate() {
		return dateReturnDate;
	}
	//TODO fix Date
	public void setReturnDate(Date dateReturnDate) {
		this.dateReturnDate = dateReturnDate;
	}
	public int getExtensions() {
		return iExtensions;
	}
	public void setExtensions(int iExtensions) {
		if(this.iExtensions != iExtensions){
			this.iExtensions = iExtensions;
			this.stage();
		}
	}
	
	public Reservation(CsvHandler csvHandler, int ID){
		this.csvHandler = csvHandler;
		String[] values = csvHandler.getLineById(String.valueOf(ID));
		this.setMediaID(ID);
		this.setExtensions(Integer.parseInt(values[COL_EXTENSIONS]));
		this.setReservationID(Integer.parseInt(values[COL_RESERVATION_ID]));
		//TODO fix Date
//		this.setIsbn(values[COL_ISBN]);
		this.setLoginName(values[COL_LOGINNAME]);
	}
	
	public String[] getValuesAsStringArray(){
		String[] values = new String[AMOUNT_COLUMNS];
		values[COL_EXTENSIONS] = String.valueOf(this.getExtensions());
		values[COL_LOGINNAME] = this.getLoginName();
		values[COL_MEDIA_ID] = String.valueOf(this.getMediaID());
		values[COL_RESERVATION_ID] = String.valueOf(this.getReservationID());
		//TODO Date
//		values[COL_RETURNDATE] = this.getIsbn();
		return values;
	}
	
	public void stage()
	{
		csvHandler.update(this.getValuesAsStringArray());
	}
}
