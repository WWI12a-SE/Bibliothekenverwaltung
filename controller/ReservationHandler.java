/**
 * Alle Reservationen
 * Benutzern nur ihre eigenen Reservationen zeigen.
 * @author ja
 *
 */

package controller;
import model.*;

import java.util.Date;
import core.CsvHandler;

/**
 * <p>
 * Die Klasse ReservationHandler verwaltet alle Reservierungs-Objekte.
 * Sie dient der Benutzeroberfläche als Schnittstelle um einzelne oder alle Reservierungen abzufragen,
 * d.h. um Referenzen auf Reservierungs-Objekte übergeben zu bekommen.
 * </p><p>
 * Der Konstruktor dieser Klasse ist, um eine mehrmalige Instanzierung zu verhindern, privatisiert.
 * Um ein Objekt des ReservationHandlers zu erhalten muss die statische getInstance-Methode verwendet werden.
 * </p>
 * @author weisseth
 * @package controller
 */
public class ReservationHandler {
	
	private static final String S_FILE_NAME = "reservations";
	private static ReservationHandler reservationHandler;
	
	private CsvHandler csvHandler;
	private Reservation[] reservations;
	
	/**
	 * Der Konstruktor dieser Klasse ist, um eine mehrmalige Instanzierung zu verhindern, privatisiert.
	 * Um ein Objekt des ReservationHandlers zu erhalten muss die statische getInstance-Methode verwendet werden.
	 */
	private ReservationHandler()
	{
		csvHandler = new CsvHandler(S_FILE_NAME);
	}
	
	/**
	 * <p>
	 * Die statische getInstance-Methode dient als Fabrikmethode:
	 * </p><p>
	 * Sie initialisiert einmalig ein ReservationHandler-Objekt.
	 * Jeder weitere Methodenaufruf übergibt eine Referenz auf das gleiche ReservationHandler-Objekt.
	 * </p>
	 * @return reservationHandler : ReservationHandler -- Objekt des ReservationHandlers
	 */
	public static ReservationHandler getInstance(){
		if(reservationHandler == null){
			reservationHandler = new ReservationHandler();
		}
		return reservationHandler;
	}
	
	/**
	 * <p>
	 * Die Methode getAllReservations() holt sich alle Reservierungs-Daten aus der entsprechenden CSV-Datei.
	 * Fuer jede Reservierung wird ein neues Reservierungs-Objekt instanziiert und in einem Array zurueck gegeben.
	 * </p>
	 * @return allReservations : Reservation[] -- Array aller Reservierungs-Objekte
	 */
	public Reservation[] getAllReservations()
	{
		String[][] reservationMap = csvHandler.read();
		reservations = new Reservation[reservationMap.length];
		for(int i = 0; i < reservations.length; i++){
			reservations[i] = new Reservation(this.csvHandler, Integer.parseInt(reservationMap[i][Reservation.COL_RESERVATION_ID]));
		}
		return reservations;
	}
	
	/**
	 * <p>
	 * Die Methode getReservation gibt ein Objekt der gesuchten Reservierung zurueck.
	 * Die Suche muss ueber die ID spezifiziert werden.
	 * </p>
	 * <p>
	 * Sollte die ID nicht vergeben sein wird ein neues Reservierungs-Objekt erstellt und uebergeben.
	 * Hierbei ist zu beachten dass die Nicht-ID-Attribute der neu erstellten Reservierung auf "null" gesetzt sind.
	 * </p>
	 * @param ID : Integer -- Die ID der gesuchten Reservierung
	 * @return reservation : Reservation -- Objekt der gesuchten Reservierung
	 */
	public Reservation getReservation(int ID)
	{		
		int newIndex;
		
		//Es gibt geladene Reservierungen
		if(reservations != null){
			
			//Durchsuche vorhandene Reservierungen
			for(int i = 0; i < reservations.length; i++){
				if(reservations[i].getReservationID() == ID){
					return reservations[i]; //Medium gefunden
				}
			}
			
			//Init neue Reservierung
			newIndex = reservations.length;
			//Merke schon initialisierte Reservierungen
			Reservation[] oldReservations = reservations;
			//Erweitere Reservierung-Array
			reservations = new Reservation[newIndex+1];
			//Kopieren des alten Reservierung-Arrays
			for(int i = 0; i < newIndex; i++){
				if(oldReservations[i] != null){
					reservations[i] = oldReservations[i];
				}
			}
		}else{
			newIndex = 0;
			//Erweitere Reservierung-Array
			reservations = new Reservation[1];
		}
		
		//Neue Reservierung hinzufuegen
		reservations[newIndex] = new Reservation(this.csvHandler, ID);
		return reservations[newIndex];
	}
	
	/**
	 * <p>
	 * Uebergibt die nächste freie ID um getReservation() eine neue Reservierung zu instanziieren.
	 * </p>
	 * @return id : Integer
	 */
	public int getNewID(){
		String[] ids = csvHandler.getAllIDs();
		int newID = Integer.parseInt(ids[ids.length-1])+1;
		return newID;
	}
	
	/**
	 * Entfernt eine Reservierung aus der Laufzeitumgebung (staged delete)
	 * @param id : Integer -- Die ID der zu loeschenden Reservierung
	 */
	public void deleteReservation(int id){
		csvHandler.dropLine(""+id);
	}
	
	/**
	 * Die Methode save() weist den CsvHandler des REservationHandlers an die "staged"-ten
	 * (zum speichern bereiten) Aenderungen in die CSV-Datei zu uebertragen. Alle Aenderungen
	 * an Reservierungs-Objekten werden automatisch ge-"staged".
	 */
	public void save(){
		csvHandler.save();
	}
}
