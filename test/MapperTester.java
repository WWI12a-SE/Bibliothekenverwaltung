package test;

import java.util.Date;
import model.Reservation;

public class MapperTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Reservation[] test = Reservation.getAllReservations();
		for(int i = 0; i < test.length; i++){
			System.out.println(test[i].getReservationID());
			System.out.println(test[i].getExtensions());
			System.out.println(test[i].getLoginName());
			System.out.println(test[i].getReturnDate());
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
