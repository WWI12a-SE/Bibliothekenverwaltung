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
	
	/**
	 * Vorsicht bullshit!
	 * @param iID
	 * @param iColumn
	 * @param valueToWrite
	 */
	public void updateColumn(int iLineIndex,int iColumn, String valueToWrite){
		aUserMap[iLineIndex][iColumn] = valueToWrite;
	}
	
	public void updateLine(int iLineIndex, String[] aLine){
		if(iLineIndex >= aUserMap.length){
			String[][] oldMap = aUserMap;
			aUserMap = new String[oldMap.length+1][AMOUNT_COLUMNS];
			for(int i = 0; i < aUserMap.length-2; i++){
				for(int k = 0; k < AMOUNT_COLUMNS; k++){
					aUserMap[i][k] = oldMap[i][k];
				}
			}
			for(int k = 0; k < AMOUNT_COLUMNS; k++){
				aUserMap[aUserMap.length-1][k] = aLine[k];
			}
		}
	}
	
	public String[] readLine(int iLineIndex){
		return aUserMap[iLineIndex];
	}
	
	public void save(){
		userHandler.write(aUserMap);
	}
	
	public int getNewID(){
		return aUserMap.length;
	}
	
	public int getLastIndex(){
		return aUserMap.length-1;
	}

}
