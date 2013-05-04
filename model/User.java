/**
 * Benutzermodell
 * Stellt eine Instanz von User bereit.
 * 
 * @author ja
 */

package model;
import java.util.Date;

import core.*;

public class User {
	
	public String getsLoginName() {
		return sLoginName;
	}
	public void setsLoginName(String sLoginName) {
		this.sLoginName = sLoginName;
	}
	public String getsFirstName() {
		return sFirstName;
	}
	public void setsFirstName(String sFirstName) {
		this.sFirstName = sFirstName;
	}
	public String getsLastName() {
		return sLastName;
	}
	public void setsLastName(String sLastName) {
		this.sLastName = sLastName;
	}
	public int getiRole() {
		return iRole;
	}
	public void setiRole(int iRole) {
		this.iRole = iRole;
	}
	public String getsPassword() {
		return sPassword;
	}
	public void setsPassword(String sPassword) {
		this.sPassword = sPassword;
	}
	public String getsEmail() {
		return sEmail;
	}
	public void setsEmail(String sEmail) {
		this.sEmail = sEmail;
	}
	private String sLoginName = null;
	private String sFirstName = null;
	private String sLastName = null;
	private int iRole; // Benutzerrolle, siehe Konstanten
	private String sPassword = null; // Kennwort zunächst, unverschlüsselt
	private String sEmail = null;	
	
	private static UserMapper oUserMapper = new UserMapper();
	
	public static final int ROLE_STUDENT = 1;
	public static final int ROLE_LECTURER = 2;
	public static final int ROLE_LIBRARIAN = 3;
	
	/*
	 * Aus bestehemdem Datensatz neues Benutzer-Objekt erzeugen, indem ID übergeben wird
	 * SONST davon ausgehen, dass ein neuer Benutzer angelegt wird.
	 * @param String
	 * @return boolean
	 *
	public boolean setUser(String sLoginName)
	{
		// Auf jeden Fall die ID setzen, sie ist ja bereits bekannt.
		this.sLoginName = sLoginName;
		
		// Wenn Datensatz mit dieser ID existiert, neues Objekt erzeugen und in this.oUser speichern.
		// Hierfür alle Setter durchlaufen.
		
		// Existiert der Datensatz noch nicht, soll er neu angelegt werden. Ebenfalls setzen.
		
		// Mit TRUE abschließen => Erfolg.
		return true;		
	}*/
	
	/**
	 * Speichern.
	 * Schreibt das aktuelle Objekt in die Map zurück.
	 * 
	 * @return boolean
	 */
	public void save()
	{
		String[] values = new String[UserMapper.AMOUNT_COLUMNS];
		values[UserMapper.COL_ROLE] = Integer.toString(this.iRole);
		values[UserMapper.COL_LOGINNAME] = this.sLoginName;
		values[UserMapper.COL_FIRSTNAME] = this.sFirstName;
		values[UserMapper.COL_LASTNAME] = this.sLastName;
		values[UserMapper.COL_EMAIL] = this.sEmail;
		values[UserMapper.COL_PASSWORD] = this.sPassword;
		oUserMapper.updateLine(this.sLoginName, values);
	}
	
	public static Reservation[] getAllReservations()
	{
		initReservations();
		return Reservation.reservation;
	}
	
	public static Reservation getReservation(int iID)
	{
		initReservations();
		for(int i = 0; i < reservation.length; i++){
			if(reservation[i].getReservationID() == iID){
				return reservation[i];
			}
		}
		return null;
	}
	
	@SuppressWarnings("deprecation")
	private static void initReservations()
	{
		if(reservation == null){
			int lastIndex = oReservationMapper.getLastIndex();
			reservation = new Reservation[lastIndex+1];
			for(int i = 0; i <= lastIndex; i++){
				reservation[i] = new Reservation();
				String[] values = oReservationMapper.readLine(i);
				reservation[i].setReservationID(Integer.parseInt(values[ReservationsMapper.COL_RESERVATION_ID]));
				reservation[i].setiMediaID(Integer.parseInt(values[ReservationsMapper.COL_MEDIA_ID]));
				reservation[i].setiExtensions(Integer.parseInt(values[ReservationsMapper.COL_EXTENSIONS]));
				reservation[i].setsLoginName(values[ReservationsMapper.COL_LOGINNAME]);
//				reservation[i].setDateReturnDate(new Date(Date.parse(values[ReservationsMapper.COL_RETURNDATE])));
				reservation[i].setDateReturnDate(new Date());
			}
		}
	}

	

}
