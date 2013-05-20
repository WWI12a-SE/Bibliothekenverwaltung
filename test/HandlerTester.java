package test;

import model.*;
import controller.*;

public class HandlerTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Reservation[] test = ReservationHandler.getInstance().getAllReservations();
		System.out.println("Index\tLoginName\tMediaID\tRückgabeDatum\tVerlängerungen");
		for(int i = 0; i < test.length; i++){
			String output = "";
			String[] values = test[i].getValuesAsStringArray();
			for(int k = 0; k < values.length; k++){
				output += values[k] + "\t";
			}
			System.out.println(output);
		}
		
		
//		UserController userController = new UserController();
//		User[] user = userController.getAllUsers();
//		user[0].setLoginName("Admön");
//		userController.save();
//		for(int i = 0; i < user.length; i++){
//			System.out.println(user[i].getLoginName());
//			System.out.println(user[i].getFirstName());
//			System.out.println(user[i].getLastName());
//			System.out.println(user[i].getEmail());
//		}
		
		
		MediaHandler mediaHandler = MediaHandler.getInstance();
		Medium[] media = mediaHandler.getAllMedia();
		for(int i = 0; i < media.length; i++){
			String output = "";
			String[] values = media[i].getValuesAsStringArray();
			for(int k = 0; k < values.length; k++){
				output += values[k] + "\t";
			}
			System.out.println(output);
		}
		
//		System.out.println(String.valueOf(null));
		
		ReservationHandler reservationHandler = ReservationHandler.getInstance();
		Reservation[] reservation = reservationHandler.getAllReservations();
		for(int i = 0; i < reservation.length; i++){
			System.out.println(reservation[i].getMediaID());
		}
		/*
		 * 
		 * String[][] test;
		Date date = new Date();
		test = new String[][] {{"7","0","0",""+date,"user1"},{"4","0","0",""+date,"user2"}};
		return test;
		 * 
		 */
	}

}
