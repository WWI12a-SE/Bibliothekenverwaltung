/**
 * Benutzermodell
 * Stellt eine Instanz von User bereit.
 * 
 * @author ja
 */

package model;
import core.*;;

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
	
	private String sLoginName = "";
	private String sFirstName = "";
	private String sLastName = "";
	private String sPassword = ""; // Kennwort zunächst, unverschlüsselt
	private String sEmail = "";
	private int iRole = 0; // Benutzerrolle, siehe Konstanten
	private CsvHandler csvHandler;
	
	public String getLoginName() {
		return sLoginName;
	}
	public void setLoginName(String sLoginName) {
		if(!this.sLoginName.equals(sLoginName)){
			this.sLoginName = sLoginName;
			this.stage();
		}
	}
	public String getFirstName() {
		return sFirstName;
	}
	public void setFirstName(String sFirstName) {
		if(!this.sFirstName.equals(sFirstName)){
			this.sFirstName = sFirstName;
			this.stage();
		}
	}
	public String getLastName() {
		return sLastName;
	}
	public void setLastName(String sLastName) {
		if(!this.sLastName.equals(sLastName)){
			this.sLastName = sLastName;
			this.stage();
		}
	}
	public int getRole() {
		return iRole;
	}
	public void setRole(int iRole) {
		if(this.iRole != iRole){
			this.iRole = iRole;
			this.stage();
		}
	}
	public String getPassword() {
		return sPassword;
	}
	public void setPassword(String sPassword) {
		if(!this.sPassword.equals(sPassword)){
			this.sPassword = sPassword;
			this.stage();
		}
	}
	public String getEmail() {
		return sEmail;
	}
	public void setEmail(String sEmail) {
		if(!this.sEmail.equals(sEmail)){
			this.sEmail = sEmail;
			this.stage();
		}
	}

	public User(CsvHandler csvHandler, String loginName){
		this.csvHandler = csvHandler;
		String[] values = csvHandler.getLineById(loginName);
		this.setLoginName(loginName);
		this.setEmail(values[COL_EMAIL]);
		this.setFirstName(values[COL_FIRSTNAME]);
		this.setLastName(values[COL_LASTNAME]);
		this.setPassword(values[COL_PASSWORD]);
		this.setRole(Integer.parseInt(values[COL_ROLE]));
	}
	
	public String[] getValuesAsStringArray(){
		String[] values = new String[AMOUNT_COLUMNS];
		values[COL_ROLE] = Integer.toString(this.iRole);
		values[COL_LOGINNAME] = this.sLoginName;
		values[COL_FIRSTNAME] = this.sFirstName;
		values[COL_LASTNAME] = this.sLastName;
		values[COL_EMAIL] = this.sEmail;
		values[COL_PASSWORD] = this.sPassword;
		return values;
	}
	
	public void stage()
	{
		csvHandler.update(this.getValuesAsStringArray());
	}

}
