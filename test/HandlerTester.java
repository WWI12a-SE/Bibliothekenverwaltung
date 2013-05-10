package test;

import java.util.Date;
import model.*;
import controller.*;

public class HandlerTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Reservation[] test = Reservation.getAllReservations();
//		for(int i = 0; i < test.length; i++){
//			System.out.println(test[i].getReservationID());
//			System.out.println(test[i].getExtensions());
//			System.out.println(test[i].getLoginName());
//			System.out.println(test[i].getReturnDate());
//		}
		
		
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
		media[Medium.COL_AUTHOR].setAuthor("Admön");
		mediaHandler.save();
		for(int i = 0; i < media.length; i++){
			System.out.println(media[i].getAuthor());
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
