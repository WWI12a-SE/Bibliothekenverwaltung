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

		viewReservations();
		viewUsers();
		viewMedia();
		
		
		
		addMedium("Autor");
		addMedium("Jemand");
		
		MediaHandler mediaHandler = MediaHandler.getInstance();
		
		mediaHandler.deleteMedium(2);

		mediaHandler.save();
		
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
//		user[0].setLoginName("Admön");
//		userController.save();
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
}
