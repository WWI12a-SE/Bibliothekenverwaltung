package controller;
import core.CsvHandler;
import model.*;

/**
 * <p>
 * Die Klasse MediaHandler verwaltet alle Medium-Objekte.
 * Sie dient der Benutzeroberfläche als Schnittstelle um einzelne oder alle Medien abzufragen,
 * d.h. um Referenzen auf Medium-Objekte übergeben zu bekommen.
 * </p><p>
 * Der Konstruktor dieser Klasse ist, um eine mehrmalige Instanzierung zu verhindern, privatisiert.
 * Um ein Objekt des MediaHandlers zu erhalten muss die statische getInstance-Methode verwendet werden.
 * </p>
 * @author weisseth
 * @package controller
 */
public class MediaHandler {
	
	private static final String S_FILE_NAME = "stock";
	private static MediaHandler mediaHandler;
	
	private CsvHandler csvHandler;
	private Medium[] media;
	
	/**
	 * Der Konstruktor dieser Klasse ist, um eine mehrmalige Instanzierung zu verhindern, privatisiert.
	 * Um ein Objekt des MediaHandlers zu erhalten muss die statische getInstance-Methode verwendet werden.
	 */
	private MediaHandler()
	{
		csvHandler = new CsvHandler(S_FILE_NAME);
	}
	
	/**
	 * <p>
	 * Die statische getInstance-Methode dient als Fabrikmethode:
	 * </p><p>
	 * Sie initialisiert einmalig ein MediaHandler-Objekt.
	 * Jeder weitere Methodenaufruf übergibt eine Referenz auf das gleiche MediaHandler-Objekt.
	 * </p>
	 * @return mediaHandlerObject : MediaHandler -- Objekt des MediaHandlers
	 */
	public static MediaHandler getInstance(){
		if(mediaHandler == null){
			mediaHandler = new MediaHandler();
		}
		return mediaHandler;
	}
	
	/**
	 * <p>
	 * Die Methode getAllMedia() holt sich alle Medien-Daten aus der entsprechenden CSV-Datei.
	 * Fuer jedes Medium wird ein neues Medium-Objekt instanziiert und in einem Array zurueck gegeben.
	 * </p>
	 * @return allMedia : Medium[] -- Array aller Medien-Objekte
	 */
	public Medium[] getAllMedia()
	{
		//Alle Medien werden neu geladen, gestagedte Aenderungen bleiben erhalten
		String[][] mediaMap = csvHandler.read();
		media = new Medium[mediaMap.length];
		for(int i = 0; i < media.length; i++){
			media[i] = new Medium(this.csvHandler, Integer.parseInt(mediaMap[i][Medium.COL_ID]));
		}
		return media;
	}
	
	/**
	 * <p>
	 * Die Methode getMedium gibt ein Objekt des gesuchten Mediums zurueck.
	 * Die Suche muss ueber die ID spezifiziert werden.
	 * </p>
	 * <p>
	 * Sollte die ID nicht vergeben sein wird ein neues Reservierungs-Objekt erstellt und uebergeben.
	 * Hierbei ist zu beachten dass die String-Attribute der neu erstellten Reservierung auf "null" gesetzt sind.
	 * </p>
	 * @param ID : Integer -- Die ID des gesuchten Mediums
	 * @return medium : Medium -- Objekt des gesuchten Mediums
	 */
	public Medium getMedium(int ID)
	{
		int newIndex;
		
		//Es gibt geladene Medien
		if(media != null){
			
			//Durchsuche vorhandene Medien
			for(int i = 0; i < media.length; i++){
				if(media[i].getID() == ID){
					return media[i]; //Medium gefunden
				}
			}
			
			//Init neues Medium
			newIndex = media.length;
			//Merke schon initialisierte Medien
			Medium[] oldMedia = media;
			//Erweitere Medien-Array
			media = new Medium[newIndex+1];
			//Kopieren des alten Medien-Arrays
			for(int i = 0; i < newIndex; i++){
				if(oldMedia[i] != null){
					media[i] = oldMedia[i];
				}
			}
		}else{
			newIndex = 0;
			//Erweitere Medien-Array
			media = new Medium[1];
		}
		
		//Neues Medium hinzufuegen
		media[newIndex] = new Medium(this.csvHandler, ID);
		return media[newIndex];
	}
	
	/**
	 * <p>
	 * Uebergibt die nächste freie ID um getMedium(newID) ein neues Medium zu instanziieren.
	 * </p>
	 * @return id : Integer
	 */
	public int getNewID(){
		String[] ids = csvHandler.getAllIDs();
		int newID = Integer.parseInt(ids[ids.length-1])+1;
		return newID;
	}
	
	/**
	 * Entfernt ein Medium aus der Laufzeitumgebung (staged delete)
	 * @param id : Integer -- Die ID des zu loeschenden Mediums
	 */
	public void deleteMedium(int id){
		csvHandler.dropLine(""+id);
	}
	
	/**
	 * Die Methode save() weist den CsvHandler des MediaHandlers an die "staged"-ten
	 * (zum speichern bereiten) Aenderungen in die CSV-Datei zu uebertragen. Alle Aenderungen
	 * an Medium-Objekten werden automatisch ge-"staged".
	 */
	public void save(){
		csvHandler.save();
	}

}
