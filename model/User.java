package model;
import core.CsvHandler;

/**
 * <p>
 * Die Klasse User repraesentiert einen Benutzer und stellt die in der CSV-Datei 
 * gespeicherten Daten zur Verfuegung.
 * </p><p>
 * Um einen User zu instanziieren muss ueber ein UserHandler-Objekt die Methode 
 * getUser() oder getAllUser() verwendet werden um die Konsistenz der Daten zu gewaehrleisten.
 * </p>
 * @author weisseth
 * @package model
 */
public class User {
	
	//CSV-Spalten
	public static final int COL_LOGINNAME = 0;
	public static final int COL_LASTNAME = 1;
	public static final int COL_FIRSTNAME = 2;
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
	
	/**
	 * Getter des LoginNamens, der ID des Users
	 * @return loginName : String
	 */
	public String getLoginName() {
		return sLoginName;
	}
	
	/**
	 * Setter des LoginNamens, der ID des Users
	 * @param loginName : String
	 */
	public void setLoginName(String loginName) {
		if(!this.sLoginName.equals(loginName)){
			this.sLoginName = loginName;
			this.stage();
		}
	}
	
	/**
	 * Getter des Vornamens
	 * @return sFirstName : String
	 */
	public String getFirstName() {
		return sFirstName;
	}
	
	/**
	 * Setter des Vornamens
	 * @param sFirstName : String
	 */
	public void setFirstName(String sFirstName) {
		if(!this.sFirstName.equals(sFirstName)){
			this.sFirstName = sFirstName;
			this.stage();
		}
	}
	
	/**
	 * Getter des Nachnamens
	 * @return sLastName : String
	 */
	public String getLastName() {
		return sLastName;
	}
	
	/**
	 * Setter des Nachnamens
	 * @param sLastName : String
	 */
	public void setLastName(String sLastName) {
		if(!this.sLastName.equals(sLastName)){
			this.sLastName = sLastName;
			this.stage();
		}
	}
	
	/**
	 * Getter der BenutzerRolle.
	 * Die Bedeutung des Wertes ist den User-Konstanten zu entnehmen.
	 * @return iRole : Integer
	 */
	public int getRole() {
		return iRole;
	}
	
	/**
	 * Setter der BenutzerRolle.
	 * Die Bedeutung des Wertes ist den User-Konstanten zu entnehmen.
	 * @param iRole : Integer
	 */
	public void setRole(int iRole) {
		if(this.iRole != iRole){
			this.iRole = iRole;
			this.stage();
		}
	}
	
	/**
	 * Getter des Passworts
	 * @return sPassword : String
	 */
	public String getPassword() {
		return sPassword;
	}
	
	/**
	 * Setter des Passworts
	 * @param sPassword : String
	 */
	public void setPassword(String sPassword) {
		if(!this.sPassword.equals(sPassword)){
			this.sPassword = sPassword;
			this.stage();
		}
	}
	
	/**
	 * Getter der EMail
	 * @return sEmail : String
	 */
	public String getEmail() {
		return sEmail;
	}
	
	/**
	 * Setter der EMail
	 * @param sEmail : String
	 */
	public void setEmail(String sEmail) {
		if(!this.sEmail.equals(sEmail)){
			this.sEmail = sEmail;
			this.stage();
		}
	}

	/**
	 * <p>
	 * Konstruktor des Users. Stellt die in der CSV-Datei 
	 * gespeicherten Daten zur Verfuegung.
	 * </p><p>
	 * Um einen User zu instanziieren muss ueber ein UserHandler-Objekt die Methode 
	 * getUser() oder getAllUser() verwendet werden um die Konsistenz der Daten zu gewaehrleisten.
	 * </p>
	 * @param csvHandler : CsvHandler
	 * @param loginName : String
	 */
	public User(CsvHandler csvHandler, String loginName){
		this.csvHandler = csvHandler;
		String[] values = csvHandler.getLineById(loginName);
		this.setLoginName(loginName);
		this.setEmail(values[COL_EMAIL]);
		this.setFirstName(values[COL_FIRSTNAME]);
		this.setLastName(values[COL_LASTNAME]);
		this.setPassword(values[COL_PASSWORD]);
		try{
			this.setRole(Integer.parseInt(values[COL_ROLE]));
		}catch(Exception e){
			this.setRole(User.ROLE_STUDENT);
		}
		
		//Schreibe values fuer den fall dass das Objekt neu ist und der CSV-Handler 
		//noch keinen Datensatz angelegt hat.
		this.stage();
	}
	
	/**
	 * Liest alle Attribute des Users aus und gibt diese als String-Array zurueck.
	 * Die Indiziers entsprechen den CSV-Spalten, welche den User-Konstanten zu entnehmen sind.
	 * @return values : String[]
	 */
	private String[] getValuesAsStringArray(){
		String[] values = new String[AMOUNT_COLUMNS];
		values[COL_ROLE] = Integer.toString(this.iRole);
		values[COL_LOGINNAME] = this.sLoginName;
		values[COL_FIRSTNAME] = this.sFirstName;
		values[COL_LASTNAME] = this.sLastName;
		values[COL_EMAIL] = this.sEmail;
		values[COL_PASSWORD] = this.sPassword;
		return values;
	}
	
	/**
	 * Die Attribute des Users werden im CsvHandler zwischengespeichert und somit 
	 * zum speichern in der CSV-Datei bereit gestellt ("gestaged").
	 */
	private void stage()
	{
		csvHandler.update(this.getValuesAsStringArray());
	}

}
