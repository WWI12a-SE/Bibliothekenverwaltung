package controller;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import model.*;

/**
 * Stellt die fuer die View vereinfachten Bibliotheks-Funktionen zu Verfuegung.
 * Alle Aktionen beziehen sich, falls benoetigt, auf den aktuell angemeldeten User.
 * @author weisseth
 *
 */
public class StockLogic {
	
	public static final int RESERVATION_DAYS = 14;
	public static final int EXTENSIONS_MAX = 1;
	
	private static StockLogic stockLogic;
	
	public static StockLogic getInstance(){
		if(stockLogic == null){
			stockLogic = new StockLogic();
		}
		return stockLogic;
	}
	
	private StockLogic(){
		
	}
	
	public Medium getNewMedium(){
		MediaHandler mediaHandler = MediaHandler.getInstance();
		return mediaHandler.getMedium(mediaHandler.getNewID());
	}
	
	public boolean returnMedium(User user, int mediaID){
		
		/*
		 * Reservierung suchen und loeschen
		 */
		Reservation[] reservations = ReservationHandler.getInstance().getAllReservations();
		String loginName = user.getLoginName();
		
		if(isReturnable(user, mediaID)){//Darf generell zurueckgegeben werden
			for(int i = 0; i < reservations.length; i++){
				boolean equalMediaID = reservations[i].getMediaID() == mediaID;
				boolean equalUser = reservations[i].getLoginName().equals(loginName);
				boolean recordIsActive = !reservations[i].isDeleted();
				if(equalMediaID && equalUser && recordIsActive){
//					System.out.println(ReservationHandler.getInstance().deleteReservation(reservations[i].getReservationID()));
					Medium medium = MediaHandler.getInstance().getMedium(mediaID);
					medium.setOnStock(medium.getOnStock() + 1);
					return true;
				}
			}
		}

		return false;
	}
	
	/**
	 * Das Gegenteil von hasReservation.
	 * @param user
	 * @param mediaID
	 * @return
	 */
	public boolean isReturnable(User user, int mediaID){
		if(!hasReservation(user, mediaID)){
			return false;
		}
		return true;
	}
	
	public boolean leaseMedium(User user, int mediaID){
		
		if(isLeaseable(user, mediaID)){
			String loginName = user.getLoginName();
			
			//Erstelle neue Reservierung
			ReservationHandler reservationHandler = ReservationHandler.getInstance();
			int reservationID = reservationHandler.getNewID();
			Reservation reservation = reservationHandler.getReservation(reservationID);
			reservation.setLoginName(loginName);
			reservation.setExtensions(0);
			reservation.setMediaID(mediaID);
			reservation.setReturnDate(this.getNewReturnDate(RESERVATION_DAYS));
			
			//Verringere Bestand
			Medium medium = MediaHandler.getInstance().getMedium(mediaID);
			medium.setOnStock(medium.getOnStock() - 1);
			return true;
		}
		return false;
	}
	
	public boolean isLeaseable(User user, int mediaID){

		/*
		 * User hat das erforderliche Recht
		 */
		if(user.getRole() != User.ROLE_LECTURER && user.getRole() != User.ROLE_STUDENT){
			return false;
		}
		/*
		 * Anzahl Exemplare im Bestand > 0
		 */
		MediaHandler mediaHandler = MediaHandler.getInstance();
		Medium medium = mediaHandler.getMedium(mediaID);
		if(medium.getOnStock() <= 0){
			return false;
		}
		
		/*
		 * Der aktuell angemeldete User hat noch kein Exemplar dieses Mediums entliehen
		 */
		ReservationHandler reservationHandler = ReservationHandler.getInstance();
		Reservation[] reservations = reservationHandler.getAllReservations();
		String loginName = user.getLoginName();
		for(int i = 0; i < reservations.length; i++){
			if(reservations[i].getMediaID() == mediaID && reservations[i].getLoginName().equals(loginName)){
				if(!reservations[i].isDeleted()){
					return false;
				}
			}
		}
		
		return true;
		
	}
	
	private Date getNewReturnDate(int daysUntilReturn){
		Date returnDate = new Date();
		
//		System.out.println(DateFormat.getInstance().format(returnDate));
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(returnDate);
		calendar.add(Calendar.DATE, daysUntilReturn);
		returnDate = calendar.getTime();
		
//		System.out.println(DateFormat.getInstance().format(returnDate));
		
		return returnDate;
	}
	
public boolean isExtendable(User user, int mediaID){
		
		/*
		 * User hat das erforderliche Recht
		 */
		if(user.getRole() != User.ROLE_LECTURER){
			return false;
		}
		/*
		 * User hat entleihgrenze noch nicht erreicht
		 */
		ReservationHandler reservationHandler = ReservationHandler.getInstance();
		Reservation reservation = reservationHandler.getReservation(mediaID);
		if(reservation.getExtensions() >= EXTENSIONS_MAX){
			return false;
		}
		
		/*
		 * User hat ein Exemplar dieses Mediums entliehen
		 */
		if(!hasReservation(MyAccount.getLoggedInUser(), mediaID)){
			return false;
		}
		
		return true;
		
	}

	public boolean hasReservation(User user, int mediaID){
		/*
		 * Der User hat noch kein Exemplar dieses Mediums entliehen
		 */
		Reservation[] reservations = ReservationHandler.getInstance().getAllReservations();
		String loginName = user.getLoginName();
		for(int i = 0; i < reservations.length; i++){
			if(reservations[i].getMediaID() == mediaID && reservations[i].getLoginName().equals(loginName)){
				if(!reservations[i].isDeleted()){
					return true;
				}
			}
		}
		return false;
	}
}
