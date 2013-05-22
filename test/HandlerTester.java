package test;

import model.*;
import controller.*;
import core.CsvHandler;

public class HandlerTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		CsvHandler csvHandler = new CsvHandler("stock");
		csvHandler.viewMap();
		System.out.println("++++++++++++++++++++++++");
//		csvHandler.viewLineById("0");
		/*
		 * Reservierungen
		 */
//		Reservation[] test = ReservationHandler.getInstance().getAllReservations();
//		System.out.println("Index\tLoginName\tMediaID\tRückgabeDatum\tVerlängerungen");
//		for(int i = 0; i < test.length; i++){
//			String output = "";
//			String[] values = test[i].getValuesAsStringArray();
//			for(int k = 0; k < values.length; k++){
//				output += values[k] + "\t";
//			}
//			System.out.println(output);
//		}
//		System.out.println("-----------------------------------------");
//		/*
//		 * User
//		 */
//		UserHandler userHandler = UserHandler.getInstance();
//		User user1 = userHandler.getUser("admin");
//		user1.setPassword("1234");
//		User[] users = userHandler.getAllUsers();
////		user[0].setLoginName("Admön");
////		userController.save();
//		for(int i = 0; i < users.length; i++){
//			String output = "";
//			String[] values = users[i].getValuesAsStringArray();
//			for(int k = 0; k < values.length; k++){
//				output += values[k] + "\t";
//			}
//			System.out.println(output);
//		}
//		System.out.println("-----------------------------------------");
		
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

//		System.out.println("-----------------------------------------");
//		Medium[] newMedia = mediaHandler.getAllMedia();
//		for(int i = 0; i < newMedia.length; i++){
//			String output = "";
//			String[] values = newMedia[i].getValuesAsStringArray();
//			for(int k = 0; k < values.length; k++){
//				output += values[k] + "\t";
//			}
//			System.out.println(output);
//		}
		
//		newMedia[0].setAuthor("XXXX");
//		System.out.println(media[0].getAuthor().equals(newMedia[0].getAuthor()));
		
		int newID = mediaHandler.getNewID();
		Medium med = mediaHandler.getMedium(newID);
		med.setAuthor("Frank");
		
		newID = mediaHandler.getNewID();
		Medium med2 = mediaHandler.getMedium(newID);
		med2.setAuthor("Alex");
		
		mediaHandler.deleteMedium(2);
		
//		int newID2 = mediaHandler.getNewID();
//		Medium med2 = mediaHandler.getMedium(newID2);
//		med2.setAuthor("Max");
		mediaHandler.save();
		
		System.out.println("++++++++++++++++++++++++");
		csvHandler.viewMap();
		System.out.println("++++++++++++++++++++++++");
		
//		newMedia[0].setEdition(4);
//		System.out.println(media[0].getEdition() == newMedia[0].getEdition());
		
		media = mediaHandler.getAllMedia();
		System.out.println("-----------------------------------------");
		for(int i = 0; i < media.length; i++){
			String output = "";
			String[] values = media[i].getValuesAsStringArray();
			for(int k = 0; k < values.length; k++){
				output += values[k] + "\t";
			}
			System.out.println(output);
		}

	}

}
