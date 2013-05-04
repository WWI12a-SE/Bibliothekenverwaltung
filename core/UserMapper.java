/**
 * User-Mapper
 * Holt Benutzer aus der CSV-Datei und legt sie in einer Map ab.
 * 
 * @author ja
 */

package core;

public class UserMapper {
	
	public static final int COL_LOGINNAME = 0;
	public static final int COL_FIRSTNAME = 1;
	public static final int COL_LASTNAME = 2;
	public static final int COL_ROLE = 3;
	public static final int COL_PASSWORD = 4;
	public static final int COL_EMAIL = 5;
	public static final int AMOUNT_COLUMNS = 6;
	
	private String aUserMap[][];
	private static final String S_FILE_NAME = "users";
	private CsvHandler userHandler;
	
	public UserMapper(){
		userHandler = new CsvHandler(S_FILE_NAME); 
		aUserMap = userHandler.read();
	}

}
