package model;
import controller.Mapper;

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
	public static final int COL_LASTNAME = 2;
	public static final int COL_FIRSTNAME = 1;
	public static final int COL_ROLE = 3;
	public static final int COL_PASSWORD = 4;
	public static final int COL_EMAIL = 5;
	public static final int AMOUNT_COLUMNS = 6;
	
	//User-Rollen
	public static final int ROLE_STUDENT = 1;
	public static final int ROLE_LECTURER = 2;
	public static final int ROLE_LIBRARIAN = 3;
	
	private static Mapper userMapper;
	
	private int row;
	
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
	public User(Mapper userMapper, int row){
		
		this.row = row;
		User.userMapper = userMapper;
		
	}
	
	/**
	 * Getter des LoginNamens, der ID des Users
	 * @return loginName : String
	 */
	public String getLoginName() {
		return String.valueOf(userMapper.getStringData(row, COL_LOGINNAME));
	}
	
	/**
	 * Setter des LoginNamens, der ID des Users
	 * @param loginName : String
	 */
	public void setLoginName(String sLoginName) {
		userMapper.setData(row, COL_LOGINNAME, sLoginName);
	}
	
	/**
	 * Getter des Vornamens
	 * @return sFirstName : String
	 */
	public String getFirstName() {
		return String.valueOf(userMapper.getStringData(row, COL_FIRSTNAME));
	}
	
	/**
	 * Setter des Vornamens
	 * @param sFirstName : String
	 */
	public void setFirstName(String sFirstName) {
		userMapper.setData(row, COL_FIRSTNAME, sFirstName);
	}
	
	/**
	 * Getter des Nachnamens
	 * @return sLastName : String
	 */
	public String getLastName() {
		return String.valueOf(userMapper.getStringData(row, COL_LASTNAME));
	}
	
	/**
	 * Setter des Nachnamens
	 * @param sLastName : String
	 */
	public void setLastName(String sLastName) {
		userMapper.setData(row, COL_LASTNAME, sLastName);
	}
	
	/**
	 * Getter der BenutzerRolle.
	 * Die Bedeutung des Wertes ist den User-Konstanten zu entnehmen.
	 * @return iRole : Integer
	 */
	public int getRole() {
		return userMapper.getIntegerData(row, COL_ROLE);
	}
	
	/**
	 * Setter der BenutzerRolle.
	 * Die Bedeutung des Wertes ist den User-Konstanten zu entnehmen.
	 * @param iRole : Integer
	 */
	public void setRole(int iRole) {
		userMapper.setData(this.row, COL_ROLE, iRole);
	}
	
	/**
	 * Getter des Passworts
	 * @return sPassword : String
	 */
	public String getPassword() {
		return String.valueOf(userMapper.getStringData(row, COL_PASSWORD));
	}
	
	/**
	 * Setter des Passworts
	 * @param sPassword : String
	 */
	public void setPassword(String sPassword) {
		userMapper.setData(row, COL_PASSWORD, sPassword);
	}
	
	/**
	 * Getter der EMail
	 * @return sEmail : String
	 */
	public String getEmail() {
		return String.valueOf(userMapper.getStringData(row, COL_EMAIL));
	}
	
	/**
	 * Setter der EMail
	 * @param sEmail : String
	 */
	public void setEmail(String sEmail) {
		userMapper.setData(row, COL_EMAIL, sEmail);
	}
	
	/**
	 * Liest alle Attribute des Users aus und gibt diese als String-Array zurueck.
	 * Die Indiziers entsprechen den CSV-Spalten, welche den User-Konstanten zu entnehmen sind.
	 * @return values : String[]
	 */
	public String[] getValuesAsStringArray(){
		String[] values = new String[AMOUNT_COLUMNS];
		values[COL_ROLE] = Integer.toString(this.getRole());
		values[COL_LOGINNAME] = this.getLoginName();
		values[COL_FIRSTNAME] = this.getFirstName();
		values[COL_LASTNAME] = this.getLastName();
		values[COL_EMAIL] = this.getEmail();
		values[COL_PASSWORD] = this.getPassword();
		return values;
	}

}
