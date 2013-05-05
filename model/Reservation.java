/**
 * Reservationsmodell
 * 
 * @author ja
 */

package model;
import java.util.Date;

import core.*;

public class Reservation {
	private int reservationID;
	private String sLoginName;
	private int iMediaID;
	private Date dateReturnDate;
	private int iExtensions;
	
	private static ReservationsMapper oReservationMapper = new ReservationsMapper();
	private static Reservation[] reservation;
	
	public int getReservationID() {
		return reservationID;
	}
	public void setReservationID(int reservationID) {
		this.reservationID = reservationID;
	}
	public String getLoginName() {
		return sLoginName;
	}
	public void setLoginName(String sLoginName) {
		this.sLoginName = sLoginName;
	}
	public int getMediaID() {
		return iMediaID;
	}
	public void setMediaID(int iMediaID) {
		this.iMediaID = iMediaID;
	}
	public Date getReturnDate() {
		return dateReturnDate;
	}
	public void setReturnDate(Date dateReturnDate) {
		this.dateReturnDate = dateReturnDate;
	}
	public int getExtensions() {
		return iExtensions;
	}
	public void setExtensions(int iExtensions) {
		this.iExtensions = iExtensions;
	}
	
	/**
	 * Speichern.
	 * Schreibt das aktuelle Objekt in die Map zur�ck.
	 * 
	 * @return boolean
	 */
	public void save()
	{
		String[] values = new String[ReservationsMapper.AMOUNT_COLUMNS];
		values[ReservationsMapper.COL_RESERVATION_ID] = Integer.toString(this.reservationID);
		values[ReservationsMapper.COL_MEDIA_ID] = Integer.toString(this.iMediaID);
		values[ReservationsMapper.COL_EXTENSIONS] = Integer.toString(this.iExtensions);
		values[ReservationsMapper.COL_RETURNDATE] = this.dateReturnDate.toString();
		values[ReservationsMapper.COL_LOGINNAME] = this.sLoginName;
		oReservationMapper.updateLine(this.reservationID, values);
	}
	
	public static Reservation[] getAllReservations()
	{
		initReservations();
		return Reservation.reservation;
	}
	
	public static Reservation getReservation(int iID)
	{
		initReservations();
		for(int i = 0; i < reservation.length; i++){
			if(reservation[i].getReservationID() == iID){
				return reservation[i];
			}
		}
		return null;
	}
	
	@SuppressWarnings("deprecation")
	private static void initReservations()
	{
		if(reservation == null){
			int lastIndex = oReservationMapper.getLastIndex();
			reservation = new Reservation[lastIndex+1];
			for(int i = 0; i <= lastIndex; i++){
				reservation[i] = new Reservation();
				String[] values = oReservationMapper.readLine(i);
				reservation[i].setReservationID(Integer.parseInt(values[ReservationsMapper.COL_RESERVATION_ID]));
				reservation[i].setMediaID(Integer.parseInt(values[ReservationsMapper.COL_MEDIA_ID]));
				reservation[i].setExtensions(Integer.parseInt(values[ReservationsMapper.COL_EXTENSIONS]));
				reservation[i].setLoginName(values[ReservationsMapper.COL_LOGINNAME]);
//				reservation[i].setReturnDate(new Date(Date.parse(values[ReservationsMapper.COL_RETURNDATE])));
				reservation[i].setReturnDate(new Date());
			}
		}
	}
}
