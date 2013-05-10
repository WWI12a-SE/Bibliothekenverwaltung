/**
 * Alle Reservationen
 * Benutzern nur ihre eigenen Reservationen zeigen.
 * @author ja
 *
 */

package controller;
import model.*;

import java.util.Date;

import core.CsvHandler;
import core.ReservationsMapper;


public class ReservationHandler {
	
	private static final String S_FILE_NAME = "reservations";
	private static ReservationHandler reservationHandler;
	
	private CsvHandler csvHandler;
	private Reservation[] reservations;
	
	private ReservationHandler()
	{
		csvHandler = new CsvHandler(S_FILE_NAME);
	}
	
	public static ReservationHandler getInstance(){
		if(reservationHandler == null){
			reservationHandler = new ReservationHandler();
		}
		return reservationHandler;
	}
	
	public Reservation[] getAllReservations()
	{
		String[][] reservationMap = csvHandler.read();
		reservations = new Reservation[reservationMap.length];
		for(int i = 0; i < reservations.length; i++){
			reservations[i] = new Reservation(this.csvHandler, Integer.parseInt(reservationMap[i][Reservation.COL_RESERVATION_ID]));
		}
		return reservations;
	}
	
	public Reservation getReservation(int ID)
	{
		//Durchsuche vorhandene Reservierungen
		for(int i = 0; i < reservations.length; i++){
			if(reservations[i].getReservationID() == ID){
				return reservations[i];
			}
		}
		//Erweitere Reservierungs-Array
		Reservation[] oldMedia = reservations;
		int newIndex = reservations.length;
		reservations = new Reservation[newIndex+1];
		//Kopieren des alten User-Arrays
		for(int i = 0; i < newIndex+1; i++){
			reservations[i] = oldMedia[i];
		}
		//Neue Reservierung hinzufuegen
		reservations[newIndex] = new Reservation(this.csvHandler, ID);
		return reservations[newIndex];
	}
	
	public void save(){
		csvHandler.save();
	}
}
