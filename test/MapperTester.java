package test;

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
			System.out.println(test[i].getiExtensions());
			System.out.println(test[i].getsLoginName());
			System.out.println(test[i].getDateReturnDate());
		}
	}

}
