package controller;

import model.Medium;

public class StockLogic {
	
	public Medium getNewMedium(){
		MediaHandler mediaHandler = MediaHandler.getInstance();
		return mediaHandler.getMedium(mediaHandler.getNewID());
	}
	
	public void reserve(int mediumID){
//		ReservationHandler reservationHandler = ReservationHandler.getInstance();
//		CsvHandler csvHandler = new CsvHandler();
//		reservationHandler.getReservation(csvHandler.);
	}
}
