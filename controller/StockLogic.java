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
	
	public boolean reserve(int mediaID){
		if(isReservable(mediaID)){
			
//			TODO MyAccount getUser()
//			User user = MyAccount.getLoggedInUser();
			User user = UserHandler.getInstance().getUser("admin");//TODO
			String loginName = user.getLoginName();
			
			//Erstelle neue Reservierung
			ReservationHandler reservationHandler = ReservationHandler.getInstance();
			int reservationID = reservationHandler.getNewID();
			Reservation reservation = reservationHandler.getReservation(reservationID);
			reservation.setLoginName(loginName);
			reservation.setExtensions(0);
			reservation.setMediaID(mediaID);
			reservation.setReturnDate(this.getNewReturnDate());
			
			//Verringere Bestand
			Medium medium = MediaHandler.getInstance().getMedium(mediaID);
			medium.setOnStock(medium.getOnStock() - 1);
			return true;
		}
		return false;
	}
	
	public boolean isReservable(int mediaID){
		
		boolean isReservable = true;
		/*
		 * Anzahl Exemplare im Bestand > 0
		 */
		MediaHandler mediaHandler = MediaHandler.getInstance();
		Medium medium = mediaHandler.getMedium(mediaID);
		if(medium.getOnStock() <= 0){
			isReservable = false;
		}
		
		/*
		 * Der aktuell angemeldete User hat noch kein Exemplar entliehen
		 */
		ReservationHandler reservationHandler = ReservationHandler.getInstance();
		Reservation[] reservations = reservationHandler.getAllReservations();
//		TODO MyAccount getUser() entklammern sobald abgesprochen
//		User user = MyAccount.getLoggedInUser();
		User user = UserHandler.getInstance().getUser("admin");//TODO
		String loginName = user.getLoginName();
		for(int i = 0; i < reservations.length; i++){
			if(reservations[i].getMediaID() == mediaID && reservations[i].getLoginName().equals(loginName)){
				isReservable = false;
				break;
			}
		}
		
		return isReservable;
		
	}
	
	private Date getNewReturnDate(){
		Date returnDate = new Date();
		
//		System.out.println(DateFormat.getInstance().format(returnDate));
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(returnDate);
		calendar.add(Calendar.DATE, RESERVATION_DAYS);
		returnDate = calendar.getTime();
		
//		System.out.println(DateFormat.getInstance().format(returnDate));
		
		return returnDate;
	}
}
