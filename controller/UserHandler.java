package controller;
import core.CsvHandler;
import model.Reservation;
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
	
	private static UserHandler userHandler;
	private CsvHandler csvHandler;
	private static Mapper userMapper;
	
	private User[] users; // Alle Benutzer als Objekt in dieses Array
	
	/**
	 * Der Konstruktor dieser Klasse ist, um eine mehrmalige Instanzierung zu verhindern, privatisiert.
	 * Um ein Objekt des UserHandlers zu erhalten muss die statische getInstance-Methode verwendet werden.
	 */
	private UserHandler()
	{
		csvHandler = new CsvHandler(S_FILE_NAME);
		userMapper = new Mapper(csvHandler);
		users = new User[csvHandler.getAllIDs().length];
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
		if(userHandler == null){
			userHandler = new UserHandler();
		}
		return userHandler;
	}
	
	/**
	 * <p>
	 * Die Methode getAllUsers() holt sich alle User-Daten aus der entsprechenden CSV-Datei.
	 * Fuer jeden User wird ein neues User-Objekt instanziiert und in einem Array zurueck gegeben.
	 * </p>
	 * @return allUsers : User[] -- Array aller User-Objekte
	 */
	public User[] getAllUsers()
	{
		for(int i = 0; i < users.length; i++){
			if(users[i] == null){
				users[i] = new User(userMapper, i);
			}
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
		//Schon vorhanden
		for(int i = 0; i < users.length; i++){
			if(users[i] == null){
				users[i] = new User(userMapper, i);
			}
			if(users[i].getLoginName().equals(loginName)){
				return users[i];
			}
		}
		
		//Neu
		User[] oldUsers = users;
		users = new User[oldUsers.length+1];
		
		for(int i = 0; i < oldUsers.length; i++){
			users[i] = oldUsers[i];
		}
		
		userMapper.addRow();
		users[oldUsers.length] = new User(userMapper, oldUsers.length);
		users[oldUsers.length].setLoginName(loginName);
		
		return users[oldUsers.length];
	}
	
	/**
	 * Entfernt einen User aus der Laufzeitumgebung (staged delete)
	 * @param loginName : String -- Die ID des zu loeschenden User
	 */
	public void deleteUser(String loginName){
		userMapper.deleteRow(loginName);
	}
	
	/**
	 * Die Methode save() weist den CsvHandler des UserHandlers an die "staged"-ten
	 * (zum speichern bereiten) Aenderungen in die CSV-Datei zu uebertragen. Alle Aenderungen
	 * an User-Objekten werden automatisch ge-"staged".
	 */
	public void save(){
		userMapper.storeMap();
	}
	
	public void viewTable(){
		System.out.println("----User-Table im UserHandler---------");
		User[] users = this.getAllUsers();
		for(int i = 0; i < users.length; i++){
			String[] stringArray = users[i].getValuesAsStringArray();
			for(int k = 0; k < stringArray.length; k++){
				System.out.print(stringArray[k]+"\t");
			}
			System.out.println("");
		}
		System.out.println("-----------------------------");
	}
			
}
