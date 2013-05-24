package controller;
import model.*;
import core.CsvHandler;

public class Mapper {
	
	private static final int AMOUNT_COLUMNS_MAX = 10;
	public static final int COL_DELFLAG = 9;
	private Object[][] data;
	private CsvHandler csvHandler;
	
	public Mapper(CsvHandler csvHandler){
		
		this.csvHandler = csvHandler;
		
		String[][] readArray = this.csvHandler.read().clone();
		this.data = new Object[readArray.length][AMOUNT_COLUMNS_MAX];
		
		for(int i = 0; i < readArray.length; i++){
			for(int c = 0; c < readArray[0].length; c++){
				this.data[i][c] = readArray[i][c];
			}
		}
		
		if(this.data == null){
			System.out.println("No CsvFile found : "+data.toString());
		}else{
			//Delflag bei neuen Dateien auf 0
			Object[][] newData = new Object[data.length][AMOUNT_COLUMNS_MAX];
			for(int i = 0; i < data.length; i++){
				for(int k = 0; k < data[0].length; k++){
					newData[i][k] = data[i][k];
				}
				newData[i][COL_DELFLAG] = 0;
			}
			data = newData;
		}
		
	}

	public void setData(int row, int col, Object data){
		String string = String.valueOf(data);
		this.data[row][col] = new String(string);
	}
	
	public Object getData(int row, int col){
		return this.data[row][col];
	}
	
	public int getIntegerData(int row, int col){
		if(this.data[row][col] == null){
			return 0;
		}
		if(this.data[row][col].equals("null")){
			return 0;
		}
		
		return Integer.parseInt(String.valueOf(this.data[row][col]));
	}
	
	public Object getStringData(int row, int col){
		return new String(String.valueOf(this.data[row][col]));
	}
	
	public void storeMap(){

		// Update
		String[] stringData = new String[this.csvHandler.read()[0].length];//CsvHandler.iColons];
		for(int i = 0; i < data.length; i++){
			//Delflag gesetzt => loeschen
			if(this.getIntegerData(i, COL_DELFLAG) == 1){
				this.csvHandler.dropLine((String)this.getStringData(i, 0));
			}else{
				//Speichern
				for(int c = 0; c < stringData.length; c++){
					stringData[c] = (String)getStringData(i, c);
				}
				this.csvHandler.update(stringData.clone());
			}
		}
				
		this.csvHandler.save();
	}
	
	public void deleteRow(int key){
		for(int i = 0; i < data.length; i++){
			if(getIntegerData(i, 0) == key && this.getIntegerData(i, COL_DELFLAG) == 0){
				this.setData(i, COL_DELFLAG, 1);
				break;
			}
		}
	}
	
	public void deleteRow(String key){
		for(int i = 0; i < data.length; i++){
			String colID = (String)this.data[i][User.COL_LOGINNAME];
			if(colID.equals(key) && this.getIntegerData(i, COL_DELFLAG) == 0){
				this.setData(i, COL_DELFLAG, 1);
				break;
			}
		}
	}
	
	public void addRow(){
		Object[][] oldData = this.data;
		this.data = new Object[data.length+1][AMOUNT_COLUMNS_MAX];
		for(int i = 0; i < oldData.length; i++){
			this.data[i] = oldData[i];
		}
		this.data[oldData.length] = new Object[AMOUNT_COLUMNS_MAX];
	}
	
}
