package controller;
import core.CsvHandler;
import model.*;

public class MediaHandler {
	
	private static final String S_FILE_NAME = "stock";
	private static MediaHandler mediaHandler;
	
	private CsvHandler csvHandler;
	private Medium[] media;
	
	private MediaHandler()
	{
		csvHandler = new CsvHandler(S_FILE_NAME);
	}
	
	public static MediaHandler getInstance(){
		if(mediaHandler == null){
			mediaHandler = new MediaHandler();
		}
		return mediaHandler;
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
		//Durchsuche vorhandene Medien
		for(int i = 0; i < media.length; i++){
			if(media[i].getID() == ID){
				return media[i];
			}
		}
		//Erweitere Medien-Array
		Medium[] oldMedia = media;
		int newIndex = media.length;
		media = new Medium[newIndex+1];
		//Kopieren des alten User-Arrays
		for(int i = 0; i < newIndex+1; i++){
			media[i] = oldMedia[i];
		}
		//Neues Medium hinzufuegen
		media[newIndex] = new Medium(this.csvHandler, ID);
		return media[newIndex];
	}
	
	public void save(){
		csvHandler.save();
	}

}
