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
	private static CsvHandler csvHandler;
	private static Mapper mediaMapper;
	
	private static Medium[] media;
	
	/**
	 * Der Konstruktor dieser Klasse ist, um eine mehrmalige Instanzierung zu verhindern, privatisiert.
	 * Um ein Objekt des MediaHandlers zu erhalten muss die statische getInstance-Methode verwendet werden.
	 */
	private MediaHandler()
	{
		csvHandler = new CsvHandler(S_FILE_NAME);
		mediaMapper = new Mapper(csvHandler);
		media = new Medium[csvHandler.getAllIDs().length];
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
		for(int i = 0; i < media.length; i++){
			if(media[i] == null){
				media[i] = new Medium(mediaMapper, i);
			}
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
		//Schon vorhanden
		for(int i = 0; i < media.length; i++){
			if(media[i] == null){
				media[i] = new Medium(mediaMapper, i);
			}
			if(media[i].getID() == ID){
				return media[i];
			}
		}
		
		//Neu
		Medium[] oldMedia = media;
		media = new Medium[oldMedia.length+1];
		
		for(int i = 0; i < oldMedia.length; i++){
			media[i] = oldMedia[i];
		}
		
		mediaMapper.addRow();
		media[oldMedia.length] = new Medium(mediaMapper, oldMedia.length);
		media[oldMedia.length].setID(this.getNewID());
		
		return media[oldMedia.length];

	}
	
	/**
	 * <p>
	 * Uebergibt die nächste freie ID um getMedium(newID) ein neues Medium zu instanziieren.
	 * </p>
	 * @return id : Integer
	 */
	public int getNewID(){
		int newID = 0;
		for(int i = 0; i < media.length; i++){
			if(media[i] != null){
				if(media[i].getID() >= newID){
					newID = media[i].getID()+1;
				}
			}
		}
		return newID;
	}
	
	/**
	 * Entfernt ein Medium aus der Laufzeitumgebung (staged delete)
	 * @param id : Integer -- Die ID des zu loeschenden Mediums
	 */
	public void deleteMedium(int id){
		mediaMapper.deleteRow(id);
	}
	
	/**
	 * Die Methode save() weist den CsvHandler des MediaHandlers an die "staged"-ten
	 * (zum speichern bereiten) Aenderungen in die CSV-Datei zu uebertragen. Alle Aenderungen
	 * an Medium-Objekten werden automatisch ge-"staged".
	 */
	public void save(){
		mediaMapper.storeMap();
		mediaHandler = null;
	}
	
	public static void reset(){
		mediaHandler = null;
	}
	
	public void viewTable(){
		System.out.println("----Media-Table im MediaHandler---------");
		Medium[] media = this.getAllMedia();
		for(int i = 0; i < media.length; i++){
			String[] stringArray = media[i].getValuesAsStringArray();
			for(int k = 0; k < stringArray.length; k++){
				System.out.print(stringArray[k]+"\t");
			}
			System.out.println("");
		}
		System.out.println("-----------------------------");
	}

}
