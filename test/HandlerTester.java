package test;

import model.*;
import controller.*;
import core.CsvHandler;

public class HandlerTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("++CSV-Media++++++++++++++++++++++");
		CsvHandler csvHandler = new CsvHandler("stock");
		csvHandler.viewMap();
		System.out.println("++++++++++++++++++++++++");

		//Mapper
		viewReservations();
		viewUsers();
		viewMedia();
		
		//Reservation
		addReservation("Nutzer");
		addReservation("Nutzer2");
		ReservationHandler.getInstance().deleteReservation(12);
		
		//Media
		addMedium("Autor");
		addMedium("Autor2");
		MediaHandler mediaHandler = MediaHandler.getInstance();
		mediaHandler.deleteMedium(2);
		
		//Reservation
		addUser("User1", "markus");
		addUser("User2", "manuel");
		UserHandler.getInstance().deleteUser("User1");

		//Save
		UserHandler.getInstance().save();
		mediaHandler.save();
		ReservationHandler.getInstance().save();
		
		//Mapper
		viewReservations();
		viewUsers();
		viewMedia();
		
//		System.out.println("++CSV-Media++++++++++++++++++++++");
//		csvHandler.viewMap();
//		System.out.println("++++++++++++++++++++++++");

	}
	
	private static void viewReservations(){
		System.out.println("---RESERVIERUNGEN-------------------------");
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
		System.out.println("-----------------------------------------");
	}

	private static void viewUsers(){
		
		System.out.println("---USERS--------------------------------------");
		UserHandler userHandler = UserHandler.getInstance();
		User user1 = userHandler.getUser("admin");
		user1.setPassword("1234");
		User[] users = userHandler.getAllUsers();
		for(int i = 0; i < users.length; i++){
			String output = "";
			String[] values = users[i].getValuesAsStringArray();
			for(int k = 0; k < values.length; k++){
				output += values[k] + "\t";
			}
			System.out.println(output);
		}
		System.out.println("-----------------------------------------");
	}
	
	private static void viewMedia(){
		System.out.println("----MEDIA-------------------------------------");
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
		System.out.println("-----------------------------------------");
	}
	
	private static void addMedium(String author){
		MediaHandler mediaHandler = MediaHandler.getInstance();
		int newID = mediaHandler.getNewID();
		Medium med = mediaHandler.getMedium(newID);
		med.setAuthor(author);
	}
	
	private static void addReservation(String sLoginName){
		ReservationHandler reservationHandler = ReservationHandler.getInstance();
		int newID = reservationHandler.getNewID();
		Reservation reservation = reservationHandler.getReservation(newID);
		reservation.setLoginName(sLoginName);
	}
	
	private static void addUser(String loginName, String firstname){
		UserHandler userHandler = UserHandler.getInstance();
		User user = userHandler.getUser(loginName);
		user.setLoginName(loginName);
		user.setFirstName(firstname);
	}
}
