package controller;

import java.util.Date;
import model.*;

public class StockLogic {
	
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
	
	public void reserve(int mediumID){

		User user = MyAccount.getLoggedInUser();
		String loginName = user.getLoginName();
		
		ReservationHandler reservationHandler = ReservationHandler.getInstance();
		int reservationID = reservationHandler.getNewID();
		Reservation reservation = reservationHandler.getReservation(reservationID);
		reservation.setLoginName(loginName);
		reservation.setExtensions(0);
		reservation.setMediaID(mediumID);
		reservation.setReturnDate(this.getNewReturnDate());
	}
	
	private Date getNewReturnDate(){
		return new Date();
	}
}
