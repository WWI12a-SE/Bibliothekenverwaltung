package controller;
import model.Medium;
import model.User;
import core.CsvHandler;

public class MediaMapper {
	
	private static final int AMOUNT_COLUMNS = 10;
	private static final int COL_DELFLAG = 9;
	private Object[][] data;
	private CsvHandler csvHandler;
	
	public MediaMapper(CsvHandler csvHandler){
		
		this.csvHandler = csvHandler;
		
		String[][] readArray = this.csvHandler.read();//csvHandler.aMap.clone();
		this.data = new Object[readArray.length][AMOUNT_COLUMNS];
		
		for(int i = 0; i < readArray.length; i++){
			for(int c = 0; c < readArray[0].length; c++){
				this.data[i][c] = readArray[i][c];
			}
		}
		
		if(this.data == null){
			System.out.println("No CsvFile found : "+data.toString());
		}else{
			//Delflag bei neuen Dateien auf 0
			Object[][] newData = new Object[data.length][AMOUNT_COLUMNS];
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
		this.data[row][col] = String.valueOf(data);
	}
	
//	public Object getData(int row, int col){
//		return (this.data[row][col] == null)? new Object() : this.data[row][col];
//	}
	
	public Object getIntegerData(int row, int col){
		if(this.data[row][col] == null){
			return 0;
		}
		if(this.data[row][col].equals("null")){
			return 0;
		}
		return this.data[row][col];
	}
	
	public Object getStringData(int row, int col){
		return (this.data[row][col] == null)? (String)null : (String)this.data[row][col];
	}
	
	public void storeMap(){
		
//		String[][] string= new String[data.length][AMOUNT_COLUMNS-1];
//		for(int i = 0; i < data.length; i++){
//			if(this.data[i][COL_DELFLAG] != null){
//				if(Integer.parseInt(String.valueOf(this.getIntegerData(i, COL_DELFLAG))) == 1){
//					for(int k = 0; k < AMOUNT_COLUMNS-1; k++){
//						string[i][k] = (String)data[i][k];
//					}
////					
//				}
//			}
//		}
//		csvHandler.write(string);
		
		// Update
		String[] stringData = new String[Medium.AMOUNT_COLUMNS];
		for(int i = 0; i < data.length; i++){
			//Delflag gesetzt => loeschen
			if(this.data[i][COL_DELFLAG] != null){
				if(Integer.parseInt(String.valueOf(this.getIntegerData(i, COL_DELFLAG))) == 1){
					System.out.println("drop1--------------------------------");
					this.csvHandler.dropLine((String)data[i][Medium.COL_ID]);
				}else{
					//Auch Speichern
					for(int c = 0; c < stringData.length; c++){
						stringData[c] = (String)getStringData(i, c);//
//						System.out.println("update: "+i+" : "+c+" : "+stringData[c]);
					}
					if(stringData[0] == null){
						System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
					}else{
						System.out.println(stringData[0]);
					}
					this.csvHandler.update(stringData);//stringData);
				}
			}else{
				//Speichern
				for(int c = 0; c < stringData.length; c++){
					stringData[c] = (String)getStringData(i, c);
//					System.out.println("update: "+i+" : "+c+" : "+stringData[c]);
				}
				if(stringData[0] == null){
					System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				}else{
					System.out.println(stringData[0]);
				}
				this.csvHandler.update(stringData);//stringData);
			}
		}
				
		this.csvHandler.save();
	}
	
	public void deleteRow(int id){
		for(int i = 0; i < data.length; i++){
			if(Integer.parseInt((String)this.data[i][Medium.COL_ID]) == id){
				this.setData(i, COL_DELFLAG, 1);
				break;
			}
		}
	}
	
	public void addRow(){
		Object[][] oldData = this.data;
		this.data = new Object[data.length+1][AMOUNT_COLUMNS];
		for(int i = 0; i < oldData.length; i++){
			this.data[i] = oldData[i];
		}
		this.data[oldData.length] = new Object[AMOUNT_COLUMNS];
	}
	
//	public String viewMap(){
////		for(int i = 0; i < data.length; i++){
////			for(int k = )
////		}
//	}
	
}
