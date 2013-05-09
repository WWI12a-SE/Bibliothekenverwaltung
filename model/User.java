/**
 * Benutzermodell
 * Stellt eine Instanz von User bereit.
 * 
 * @author ja
 */

package model;
import core.*;

public class User {
	
	//CSV-Spalten
	public static final int COL_LOGINNAME = 0;
	public static final int COL_FIRSTNAME = 1;
	public static final int COL_LASTNAME = 2;
	public static final int COL_ROLE = 3;
	public static final int COL_PASSWORD = 4;
	public static final int COL_EMAIL = 5;
	public static final int AMOUNT_COLUMNS = 6;
	
	//User-Rollen
	public static final int ROLE_STUDENT = 1;
	public static final int ROLE_LECTURER = 2;
	public static final int ROLE_LIBRARIAN = 3;
	
	private static final String S_FILE_NAME = "users";
	private static CsvHandler userHandler = new CsvHandler(S_FILE_NAME);
	private static User[] users;
	
	private String sLoginName = null;
	private String sFirstName = null;
	private String sLastName = null;
	private String sPassword = null; // Kennwort zunächst, unverschlüsselt
	private String sEmail = null;
	private int iRole; // Benutzerrolle, siehe Konstanten
	
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
	
	/**
	 * Schreibt das aktuelle Objekt in die Map zur&uuml;ck.
	 * Die Map jedoch muss ebenfalls gespeichert werden.
	 * 
	 * @return boolean
	 */
	public void save()
	{
		String[] values = new String[AMOUNT_COLUMNS];
		values[COL_ROLE] = Integer.toString(this.iRole);
		values[COL_LOGINNAME] = this.sLoginName;
		values[COL_FIRSTNAME] = this.sFirstName;
		values[COL_LASTNAME] = this.sLastName;
		values[COL_EMAIL] = this.sEmail;
		values[COL_PASSWORD] = this.sPassword;
		userHandler.update(values);
	}
	
	public static void saveAll(){
		for(int i = 0; i < users.length; i++){
			users[i].save();
		}
	}
	
	public static User[] getAllUsers()
	{
		for(int k = 0; k < users.length; k++){
			users[k].save();
		}
		String[][] userMap = userHandler.read();
		users = new User[userMap.length];
		for(int i = 0; i < users.length; i++){
			users[i] = new User(userMap[i]);
		}
		return users;
	}
	
	public static User getUser(String loginName)
	{
		//Durchsuche vorhandene User
		for(int i = 0; i < users.length; i++){
			if(users[i].getLoginName().equals(loginName)){
				return users[i];
			}
		}
		//Erweitere User-Array
		User[] oldUsers = users;
		int newIndex = users.length;
		users = new User[newIndex+1];
		//Kopieren des alten User-Arrays
		for(int i = 0; i < newIndex+1; i++){
			users[i] = oldUsers[i];
		}
		//Neuen User hinzufuegen
		users[newIndex] = new User(userHandler.getLineById(loginName));
		return users[newIndex];
	}
	
	private User(String[] values){
		this.setLoginName(values[COL_LOGINNAME]);
		this.setEmail(values[COL_EMAIL]);
		this.setFirstName(values[COL_FIRSTNAME]);
		this.setLastName(values[COL_LASTNAME]);
		this.setPassword(values[COL_PASSWORD]);
		this.setRole(Integer.parseInt(values[COL_ROLE]));
	}

}
