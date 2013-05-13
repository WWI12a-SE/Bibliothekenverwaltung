package controller;
import core.CsvHandler;
import model.User;

/**
 * <p>
 * Die Klasse UserHandler verwaltet alle User-Objekte.
 * Sie dient der Benutzeroberfläche als Schnittstelle um einzelne oder alle User abzufragen,
 * d.h. um Referenzen auf User-Objekte übergeben zu bekommen.
 * </p><p>
 * Der Konstruktor dieser Klasse ist, um eine mehrmalige Instanzierung zu verhindern, privatisiert.
 * Um ein Objekt des UserHandlers zu erhalten muss die statische getInstance-Methode verwendet werden.
 * </p>
 * @author weisseth
 * @package controller
 */
public class UserHandler {

	private static final String S_FILE_NAME = "users";
	private static UserHandler userController;
	private CsvHandler csvHandler;
	private User[] users; // Alle Benutzer als Objekt in dieses Array
	
	/**
	 * Der Konstruktor dieser Klasse ist, um eine mehrmalige Instanzierung zu verhindern, privatisiert.
	 * Um ein Objekt des UserHandlers zu erhalten muss die statische getInstance-Methode verwendet werden.
	 */
	private UserHandler()
	{
		csvHandler = new CsvHandler(S_FILE_NAME);
	}
	
	/**
	 * <p>
	 * Die statische getInstance-Methode dient als Fabrikmethode:
	 * </p><p>
	 * Sie initialisiert einmalig ein UserHandler-Objekt.
	 * Jeder weitere Methodenaufruf übergibt eine Referenz auf das gleiche UserHandler-Objekt.
	 * </p>
	 * @return userHandlerObject : UserHandler -- Objekt des UserHandlers
	 */
	public static UserHandler getInstance(){
		if(userController == null){
			userController = new UserHandler();
		}
		return userController;
	}
	
	/**
	 * <p>
	 * Die Methode getAllUsers() holt sich alle User-Daten aus der entsprechenden CSV-Datei.
	 * Fuer jeden User wird ein neues User-Objekt instanziiert und als Array zurueck gegeben.
	 * </p><p>
	 * Zuvor initialisierte User werden ebenfalls neu geladen.
	 * Aenderungen welche nicht explizit ueber den CSV-Handler gespeichert wurden gehen hierbei verloren.
	 * </p>
	 * @return allUsers : User[] -- Array aller User-Objekte
	 */
	public User[] getAllUsers()
	{
		String[][] userMap = csvHandler.read();
		users = new User[userMap.length];
		for(int i = 0; i < users.length; i++){
			users[i] = new User(this.csvHandler, userMap[i][User.COL_LOGINNAME]);
		}
		return users;
	}
	
	/**
	 * <p>
	 * Die Methode getUser gibt ein Objekt des gesuchten Users zurueck.
	 * Die Suche muss ueber den LoginNamen als eindeutige ID spezifiziert werden.
	 * </p><p>
	 * Sollte der LoginName nicht vergeben sein wird ein neues User-Objekt erstellt und uebergeben.
	 * Hierbei ist zu beachten dass die String-Attribute des neu erstellten Users auf "null" gesetzt sind
	 * (mit Aussnahme des LoginNamens).
	 * </p>
	 * @param loginName : String -- Die ID des gesuchten Users
	 * @return user : User -- Objekt des gesuchten Users
	 */
	public User getUser(String loginName)
	{
		int newIndex;
		
		//Durchsuche vorhandene User
		if(users != null){
			for(int i = 0; i < users.length; i++){
				if(users[i].getLoginName().equals(loginName)){
					return users[i]; //User-Objekt gefunden
				}
			}
			newIndex = users.length;
			//Merke schon initialisierte User
			User[] oldUsers = users;
			//Erweitere User-Array
			users = new User[newIndex+1];
			//Kopieren des alten User-Arrays	
			for(int i = 0; i < newIndex; i++){
				if(oldUsers[i] != null){
					users[i] = oldUsers[i];
				}
			}
		}else{
			newIndex = 0;
			//Erweitere User-Array
			users = new User[1];
		}
		
		//Neuen User hinzufuegen
		users[newIndex] = new User(this.csvHandler, loginName);
		return users[newIndex];
	}
	
	/**
	 * Die Methode save() weist den CsvHandler des UserHandlers an die "staged"-ten
	 * (zum speichern bereiten) Aenderungen in die CSV-Datei zu uebertragen. Alle Aenderungen
	 * an User-Objekten werden automatisch ge-"staged".
	 */
	public void save(){
		csvHandler.save();
	}
			
}
