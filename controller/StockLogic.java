package controller;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import model.*;

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
	
	public void reserve(int mediumID){

//		User user = MyAccount.getLoggedInUser();
		User user = UserHandler.getInstance().getUser("admin");
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
		Date returnDate = new Date();
		
		System.out.println(DateFormat.getInstance().format(returnDate));
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(returnDate);
		calendar.add(Calendar.DATE, RESERVATION_DAYS);
		returnDate = calendar.getTime();
		
		System.out.println(DateFormat.getInstance().format(returnDate));
		
		return returnDate;
	}
}
