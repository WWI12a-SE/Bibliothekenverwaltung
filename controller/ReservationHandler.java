package controller;
import core.CsvHandler;
import model.Medium;
import model.Reservation;;

/**
 * <p>
 * Die Klasse ReservationHandler verwaltet alle Reservierungs-Objekte.
 * Sie dient der Benutzeroberfläche als Schnittstelle um einzelne oder alle Reservierungen abzufragen,
 * d.h. um Referenzen auf Reservierungs-Objekte uebergeben zu bekommen.
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
	private static CsvHandler csvHandler;
	private static Mapper reservationMapper;
	
	private Reservation[] reservations;
	
	/**
	 * Der Konstruktor dieser Klasse ist, um eine mehrmalige Instanzierung zu verhindern, privatisiert.
	 * Um ein Objekt des ReservationHandlers zu erhalten muss die statische getInstance-Methode verwendet werden.
	 */
	private ReservationHandler()
	{
		csvHandler = new CsvHandler(S_FILE_NAME);
		reservationMapper = new Mapper(csvHandler);
		reservations = new Reservation[csvHandler.getAllIDs().length];
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
		for(int i = 0; i < reservations.length; i++){
			if(reservations[i] == null){
				reservations[i] = new Reservation(reservationMapper, i);
			}
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
	 * Hierbei ist zu beachten dass die String-Attribute der neu erstellten Reservierung auf "null" gesetzt sind.
	 * </p>
	 * @param ID : Integer -- Die ID der gesuchten Reservierung
	 * @return reservation : Reservation -- Objekt der gesuchten Reservierung
	 */
	public Reservation getReservation(int ID)
	{		
		//Schon vorhanden
		for(int i = 0; i < reservations.length; i++){
			if(reservations[i] == null){
				reservations[i] = new Reservation(reservationMapper, i);
			}
			if(reservations[i].getReservationID() == ID){
				return reservations[i];
			}
		}
		
		//Neu
		Reservation[] oldReservations = reservations;
		reservations = new Reservation[oldReservations.length+1];
		
		for(int i = 0; i < oldReservations.length; i++){
			reservations[i] = oldReservations[i];
		}
		
		reservationMapper.addRow();
		reservations[oldReservations.length] = new Reservation(reservationMapper, oldReservations.length);
		reservations[oldReservations.length].setReservationID(this.getNewID());
		
		return reservations[oldReservations.length];
	}
	
	/**
	 * <p>
	 * Uebergibt die nächste freie ID um getReservation() eine neue Reservierung zu instanziieren.
	 * </p>
	 * @return id : Integer
	 */
	public int getNewID(){
		int newID = 0;
		for(int i = 0; i < reservations.length; i++){
			if(reservations[i] != null){
				if(reservations[i].getReservationID() >= newID){
					newID = reservations[i].getReservationID()+1;
				}
			}
		}
		return newID;
	}
	
	/**
	 * Entfernt eine Reservierung aus der Laufzeitumgebung (staged delete)
	 * @param id : Integer -- Die ID der zu loeschenden Reservierung
	 */
	public boolean deleteReservation(int id){
		return reservationMapper.deleteRow(id);
	}
	
	/**
	 * Die Methode save() weist den CsvHandler des ReservationHandlers an die "staged"-ten
	 * (zum speichern bereiten) Aenderungen in die CSV-Datei zu uebertragen. Alle Aenderungen
	 * an Reservierungs-Objekten werden automatisch ge-"staged".
	 */
	public void save(){
		reservationMapper.storeMap();
		reservationHandler = null;
	}
	
	/**
	 * Verwirft alle zum speichern bereitgestellten Aenderungen
	 * und erzwingt eine Neuinstanziierung der ReservationHandler-Objekts ueber getInstance()
	 * @see UserHandler#getInstance
	 */
	public static void reset(){
		reservationHandler = null;
	}
	
	/**
	 * Gibt alle Reservierungs-Daten inklusive der zum speichern bereitgestellten Aenderungen auf der Konsole aus.
	 * @develop nur debug
	 */
	public void viewTable(){
		System.out.println("----Reservation-Table im ReservationHandler---------");
		Reservation[] reservations = this.getAllReservations();
		for(int i = 0; i < reservations.length; i++){
			String[] stringArray = reservations[i].getValuesAsStringArray();
			for(int k = 0; k < stringArray.length; k++){
				System.out.print(stringArray[k]+"\t");
			}
			System.out.println("delflagged: "+reservations[i].isDeleted());
		}
		System.out.println("-----------------------------");
	}
}
