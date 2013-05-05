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
	
	public String getLoginName() {
		return sLoginName;
	}
	public void setLoginName(String sLoginName) {
		this.sLoginName = sLoginName;
	}
	public String getFirstName() {
		return sFirstName;
	}
	public void setFirstName(String sFirstName) {
		this.sFirstName = sFirstName;
	}
	public String getLastName() {
		return sLastName;
	}
	public void setLastName(String sLastName) {
		this.sLastName = sLastName;
	}
	public int getRole() {
		return iRole;
	}
	public void setRole(int iRole) {
		this.iRole = iRole;
	}
	public String getPassword() {
		return sPassword;
	}
	public void setPassword(String sPassword) {
		this.sPassword = sPassword;
	}
	public String getEmail() {
		return sEmail;
	}
	public void setEmail(String sEmail) {
		this.sEmail = sEmail;
	}
	private String sLoginName = null;
	private String sFirstName = null;
	private String sLastName = null;
	private int iRole; // Benutzerrolle, siehe Konstanten
	private String sPassword = null; // Kennwort zunÃ¤chst, unverschlÃ¼sselt
	private String sEmail = null;	
	
	private static UserMapper oUserMapper = new UserMapper();
	private static User[] user;
	
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
	
	public static User[] getAllUsers()
	{
		initUsers();
		return User.user;
	}
	
	public static User getUser(String sLoginName)
	{
		initUsers();
		for(int i = 0; i < user.length; i++){
			if(user[i].getLoginName().equals(sLoginName)){
				return user[i];
			}
		}
		return null;
	}
	
	@SuppressWarnings("deprecation")
	private static void initUsers()
	{
		if(user == null){
			int lastIndex = oUserMapper.getLastIndex();
			user = new User[lastIndex+1];
			for(int i = 0; i <= lastIndex; i++){
				user[i] = new User();
				String[] values = oUserMapper.readLine(i);
				user[i].setLoginName(values[UserMapper.COL_LOGINNAME]);
				user[i].setEmail(values[UserMapper.COL_EMAIL]);
				user[i].setFirstName(values[UserMapper.COL_FIRSTNAME]);
				user[i].setLastName(values[UserMapper.COL_LASTNAME]);
				user[i].setPassword(values[UserMapper.COL_PASSWORD]);
				user[i].setRole(Integer.parseInt(values[UserMapper.COL_ROLE]));
			}
		}
	}

	

}
