package controller;
import core.CsvHandler;
import model.*;

public class MediaHandler {
	
	private static final String S_FILE_NAME = "stock";
	private CsvHandler csvHandler;
	private Medium[] media;
	
	public MediaHandler()
	{
		csvHandler = new CsvHandler(S_FILE_NAME);
	}
	
	public Medium[] getAllMedia()
	{
		String[][] mediaMap = csvHandler.read();
		media = new Medium[mediaMap.length];
		for(int i = 0; i < media.length; i++){
			media[i] = new Medium(this.csvHandler, Integer.parseInt(mediaMap[i][Medium.COL_ID]));
		}
		return media;
	}
	
	public Medium getMedium(int ID)
	{
		//Durchsuche vorhandene User
		for(int i = 0; i < media.length; i++){
			if(media[i].getID() == ID){
				return media[i];
			}
		}
		//Erweitere User-Array
		Medium[] oldMedia = media;
		int newIndex = media.length;
		media = new Medium[newIndex+1];
		//Kopieren des alten User-Arrays
		for(int i = 0; i < newIndex+1; i++){
			media[i] = oldMedia[i];
		}
		//Neuen User hinzufuegen
		media[newIndex] = new Medium(this.csvHandler, ID);
		return media[newIndex];
	}
	
	public void save(){
		csvHandler.save();
	}

}
