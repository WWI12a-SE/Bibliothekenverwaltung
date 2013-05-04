package core;

public class ReservationsMapper {
	
	public static final int COL_RESERVATION_ID = 0;
	public static final int COL_MEDIA_ID = 1;
	public static final int COL_EXTENSIONS = 2;
	public static final int COL_RETURNDATE = 3;
	public static final int COL_LOGINNAME = 4;
	public static final int AMOUNT_COLUMNS = 5;
	
	private String[][] aReservationMap;
	private static final String S_FILE_NAME = "reservations.csv";
	private CsvHandler reservationHandler;
	
	public ReservationsMapper(){
		reservationHandler = new CsvHandler(S_FILE_NAME); 
		aReservationMap = reservationHandler.read();
	}
	
	public void updateColumn(int iID,int iColumn, String valueToWrite){
		aReservationMap[iID][iColumn] = valueToWrite;
	}
	
	public void updateLine(int iID, String[] aLine){
		if(iID >= aReservationMap.length){
			String[][] oldMap = aReservationMap;
			aReservationMap = new String[oldMap.length+1][AMOUNT_COLUMNS];
			for(int i = 0; i < aReservationMap.length-2; i++){
				for(int k = 0; k < AMOUNT_COLUMNS; k++){
					aReservationMap[i][k] = oldMap[i][k];
				}
			}
			for(int k = 0; k < AMOUNT_COLUMNS; k++){
				aReservationMap[aReservationMap.length-1][k] = aLine[k];
			}
		}
	}
	
	public String[] readLine(int iID){
		return aReservationMap[iID];
	}
	
	public void save(){
		reservationHandler.write(aReservationMap);
	}
	
	public int getNewID(){
		return aReservationMap.length;
	}
	
	public int getLastIndex(){
		return aReservationMap.length-1;
	}
}
