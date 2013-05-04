/**
 * StockMapper
 * Lädt Inhalte aus einer CSV-Datei über CsvHandler
 * 
 * @author ja
 */

package core;

import model.Media;

public class MediaMapper {
	
	/**
	 * Private Variablen
	 */
	private String aMediaMap[][] = null; // CSV-Map als String
	private static final String S_FILE_NAME = "stock";
	private CsvHandler mediaHandler;
	
	public static final int COL_ID = 0;
	public static final int COL_TITLE = 1;
	public static final int COL_AUTHOR = 2;
	public static final int COL_PUBLISHER = 3;
	public static final int COL_ISBN = 4;
	public static final int COL_YEAR = 5;
	public static final int COL_EDITION = 6;
	public static final int COL_STOCK = 7;
	public static final int COL_COPIES = 8;
	public static final int AMOUNT_COLUMNS = 9;
	
	public MediaMapper(){
		mediaHandler = new CsvHandler(S_FILE_NAME); 
		aMediaMap = mediaHandler.read();	
	}
	
	public void updateColumn(int iID,int iColumn, String valueToWrite){
		aMediaMap[iID][iColumn] = valueToWrite;
	}
	
	public void updateLine(int iID, String[] aLine){
		if(iID >= aMediaMap.length){
			String[][] oldMap = aMediaMap;
			aMediaMap = new String[oldMap.length+1][AMOUNT_COLUMNS];
			for(int i = 0; i < aMediaMap.length-2; i++){
				for(int k = 0; k < AMOUNT_COLUMNS; k++){
					aMediaMap[i][k] = oldMap[i][k];
				}
			}
			for(int k = 0; k < AMOUNT_COLUMNS; k++){
				aMediaMap[aMediaMap.length-1][k] = aLine[k];
			}
		}
	}
	
	public String[] readLine(int iID){
		return aMediaMap[iID];
	}
	
	public void save(){
		mediaHandler.write(aMediaMap);
	}
	
	public int getNewID(){
		return aMediaMap.length;
	}
	
	public int getLastIndex(){
		return aMediaMap.length-1;
	}
}
