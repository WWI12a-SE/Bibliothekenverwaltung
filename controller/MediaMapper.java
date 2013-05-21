package controller;
import model.Medium;
import model.User;
import core.CsvHandler;

public class MediaMapper {
	
	private static final int AMOUNT_COLUMNS = 7;
	private static final int COL_DELFLAG = 6;
	private Object[][] data;
	private CsvHandler csvHandler;
	
	public MediaMapper(CsvHandler csvHandler){
		
		this.data = csvHandler.aMap.clone();
		if(this.data == null){
			System.out.println("No CsvFile found : "+data.toString());
		}
		
	}

	public void setData(int row, int col, Object data){
		this.data[row][col] = String.valueOf(data);
	}
	
	public Object getData(int row, int col){
		return this.data[row][col];
	}
	
	public void storeMap(){
		
		// Update
		String[] stringData;
		for(int i = 0; i < data.length; i++){
			//Delflag gesetzt => loeschen
			if((Integer)this.data[i][COL_DELFLAG] == 1){
				this.csvHandler.dropLine((String)data[i][Medium.COL_ID]);
			}else{
				stringData = (String[]) data[i];
				this.csvHandler.update(stringData);
			}
		}
				
		this.csvHandler.save();
	}
	
	public void deleteRow(int row){
		this.data[row][COL_DELFLAG] = 1;
	}
	
	public void addRow(){
		Object[][] oldData = this.data;
		this.data = new Object[data.length+1][AMOUNT_COLUMNS];
		for(int i = 0; i < oldData.length; i++){
			this.data[i] = oldData[i];
		}
	}
	
}
