package controller;

import java.util.Calendar;
import java.util.Date;
import model.*;

/**
 * Stellt die fuer die View vereinfachten Bibliotheks-Funktionen zu Verfuegung.
 * Alle Aktionen beziehen sich, falls benoetigt, auf den aktuell angemeldeten User.
 * @author weisseth
 *
 */
public class StockLogic {
	
	public static final int RESERVATION_DAYS = 14;
	public static final int EXTENSIONS_MAX = 1;
	
	private static StockLogic stockLogic;
	
	/**
	 * Gibt das Objekt der StockLogic zurueck.
	 * @return stockLogicObject : StockLogic
	 */
	public static StockLogic getInstance(){
		if(stockLogic == null){
			stockLogic = new StockLogic();
		}
		return stockLogic;
	}
	
	/**
	 * Privatisierter Konstruktor, Objekt ueber statische getInstance()-Funktion
	 */
	private StockLogic(){}
	
	/**
	 * Gibt ein neu erstelltes Medium zurueck.
	 * @return medium : Medium
	 */
	public Medium getNewMedium(){
		MediaHandler mediaHandler = MediaHandler.getInstance();
		return mediaHandler.getMedium(mediaHandler.getNewID());
	}
	
	/**
	 * Gibt das ueber die Parameter definierte Medium fuer den spezifizierten User zurueck ins System.<br><br>
	 * Hierzu muss:<ul>
	 * <li>Das Medium vorhanden sein</li>
	 * <li>Das Medium zurueckgebbar sein (siehe isReturnable())</li>
	 * </ul>
	 * <br>
	 * Der Rueckgabewert liefert true fuer "erfolgreich zurueckgegeben", andernfalls false.<br>
	 * <br>
	 * Bei Erfolg:<ul>
	 * <li>Reservierungs-Datensatz wird geloescht</li>
	 * <li>Der Bestand (die Anzahl der vorraetigen Exemplare) wird um eins inkrementiert</li>
	 * </ul>
	 * @param user : User
	 * @param mediaID : Integer
	 * @return success : Boolean
	 * @see #isReturnable
	 */
	public boolean returnMedium(User user, int mediaID){
		
		/*
		 * Reservierung suchen und loeschen
		 */
		Reservation[] reservations = ReservationHandler.getInstance().getAllReservations();
		String loginName = user.getLoginName();
		
		if(isReturnable(user, mediaID)){//Darf generell zurueckgegeben werden
			for(int i = 0; i < reservations.length; i++){
				boolean equalMediaID = reservations[i].getMediaID() == mediaID;
				boolean equalUser = reservations[i].getLoginName().equals(loginName);
				boolean recordIsActive = !reservations[i].isDeleted();
				if(equalMediaID && equalUser && recordIsActive){
					ReservationHandler.getInstance().deleteReservation(reservations[i].getReservationID());
					Medium medium = MediaHandler.getInstance().getMedium(mediaID);
					medium.setOnStock(medium.getOnStock() + 1);
					return true;
				}
			}
		}

		return false;
	}
	
	/**
	 * Kernfunktion fuer die Verlaengerung eines ueber die MediaID spezifizierten Mediums fuer den uebergebenen User.
	 * Voraussetztung ist die Verlaengerbarkeit des Mediums (siehe isExtendable(user, mediaID)).<br>
	 * Erfolg oder Fehlschlag wird ueber den Rueckgabewert mitgeteilt.
	 * @param user : User
	 * @param mediaID : Integer
	 * @return success : Boolean
	 * @see #isExtendable(User, int)
	 */
	public boolean extendMedium(User user, int mediaID){
		
		/*
		 * Reservierung suchen und loeschen
		 */
		Reservation[] reservations = ReservationHandler.getInstance().getAllReservations();
		String loginName = user.getLoginName();
		
		if(isExtendable(user, mediaID)){//Darf generell zurueckgegeben werden
			for(int i = 0; i < reservations.length; i++){
				boolean equalMediaID = reservations[i].getMediaID() == mediaID;
				boolean equalUser = reservations[i].getLoginName().equals(loginName);
				boolean recordIsActive = !reservations[i].isDeleted();
				boolean moreAllowed = (reservations[i].getExtensions() == 0);
				if(equalMediaID && equalUser && recordIsActive && moreAllowed){
					Reservation reserv = ReservationHandler.getInstance().getReservation(reservations[i].getReservationID());
					reserv.setExtensions(reserv.getExtensions() + 1);
					return true;
				}
			}
		}

		return false;
	}
	
	/**
	 * Das Gegenteil von hasLeasedSpecificMedium.
	 * @param user
	 * @param mediaID
	 * @return isReturnable : Boolean
	 * @see User#hasLeasedSpecificMedium
	 */
	public boolean isReturnable(User user, int mediaID){
		if(!user.hasLeasedSpecificMedium(mediaID)){
			return false;
		}
		return true;
	}
	
	/**
	 * Entleiht das ueber die Parameter definierte Medium fuer den spezifizierten User.<br><br>
	 * Hierzu muss das Medium entleihbar sein (siehe isLeaseable()).
	 * <br>
	 * Der Rueckgabewert liefert true fuer "erfolgreich entliehen", andernfalls false.<br>
	 * <br>
	 * Bei Erfolg:<ul>
	 * <li>Reservierungs-Datensatz wird erstellt</li>
	 * <li>Der Bestand (die Anzahl der vorraetigen Exemplare) wird um eins dekrementiert</li>
	 * </ul>
	 * @param user : User
	 * @param mediaID : Integer
	 * @return success : Boolean
	 * @see #isLeaseable(User, int)
	 */
	public boolean leaseMedium(User user, int mediaID){
		
		if(isLeaseable(user, mediaID)){
			String loginName = user.getLoginName();
			
			//Erstelle neue Reservierung
			ReservationHandler reservationHandler = ReservationHandler.getInstance();
			int reservationID = reservationHandler.getNewID();
			Reservation reservation = reservationHandler.getReservation(reservationID);
			reservation.setLoginName(loginName);
			reservation.setExtensions(0);
			reservation.setMediaID(mediaID);
			reservation.setReturnDate(this.getNewReturnDate(RESERVATION_DAYS));
			
			//Verringere Bestand
			Medium medium = MediaHandler.getInstance().getMedium(mediaID);
			medium.setOnStock(medium.getOnStock() - 1);
			return true;
		}
		return false;
	}
	
	/**
	 * Gibt zurueck ob ein Medium fuer den User entleihbar ist.<br>
	 * Voraussetzungen:
	 * <ul>
	 * <li>Der User hat das Recht Medien zu entleihen</li>
	 * <li>Es sind noch Exemplare des Mediums im Bestand</li>
	 * <li>Der User hat noch kein Exemplar dieses Mediums entliehen</li>
	 * </ul>
	 * @param user : User
	 * @param mediaID : Integer
	 * @return isLeasable : Boolean
	 */
	public boolean isLeaseable(User user, int mediaID){

		/*
		 * User hat das erforderliche Recht
		 */
		if(user.getRole() != User.ROLE_LECTURER && user.getRole() != User.ROLE_STUDENT){
			return false;
		}
		/*
		 * Anzahl Exemplare im Bestand > 0
		 */
		MediaHandler mediaHandler = MediaHandler.getInstance();
		Medium medium = mediaHandler.getMedium(mediaID);
		if(medium.getOnStock() <= 0){
			return false;
		}
		
		/*
		 * Der aktuell angemeldete User hat noch kein Exemplar dieses Mediums entliehen
		 */
		ReservationHandler reservationHandler = ReservationHandler.getInstance();
		Reservation[] reservations = reservationHandler.getAllReservations();
		String loginName = user.getLoginName();
		for(int i = 0; i < reservations.length; i++){
			if(reservations[i].getMediaID() == mediaID && reservations[i].getLoginName().equals(loginName)){
				if(!reservations[i].isDeleted()){
					return false;
				}
			}
		}
		
		return true;
		
	}
	
	/**
	 * Gibt das neue Rueckgabedatum zurueck, welches daysUntilReturn-Tage in der Zukunft liegt.
	 * @param daysUntilReturn : Integer
	 * @return returnDate : Date
	 */
	private Date getNewReturnDate(int daysUntilReturn){
		Date returnDate = new Date();
		
//		System.out.println(DateFormat.getInstance().format(returnDate));
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(returnDate);
		calendar.add(Calendar.DATE, daysUntilReturn);
		returnDate = calendar.getTime();
		
//		System.out.println(DateFormat.getInstance().format(returnDate));
		
		return returnDate;
	}
	
	/**
	 * Gibt zurueck ob ein Medium fuer den User verlaengerbar ist.<br>
	 * Voraussetzungen:
	 * <ul>
	 * <li>Der User hat die Rolle "Dozent"</li>
	 * <li>Der User hat das Medium entliehen</li>
	 * <li>Der User hat die Entleihgrenze fuer das Medium noch nicht ueberschritten</li>
	 * </ul>
	 * @param user : User
	 * @param mediaID : Integer
	 * @return isExtendable : Boolean
	 */
	public boolean isExtendable(User user, int mediaID){
		
		/*
		 * User hat das erforderliche Recht
		 */
		if(user.getRole() != User.ROLE_LECTURER){
			return false;
		}
		/*
		 * User hat entleihgrenze noch nicht erreicht
		 */
		ReservationHandler reservationHandler = ReservationHandler.getInstance();
		Reservation[] reservations = reservationHandler.getAllReservations();
		for(int i = 0; i < reservations.length; i++){
			boolean equalUserID = (reservations[i].getLoginName().equals(user.getLoginName()));
			boolean equalMediaID = (reservations[i].getMediaID() == mediaID);
			boolean isActive = !reservations[i].isDeleted();
			if(equalUserID && equalMediaID && isActive){
				if(reservations[i].getExtensions() >= EXTENSIONS_MAX){
					return false;
				}else{
					return true;
				}
			}
		}
		return false;
		
	}

}
