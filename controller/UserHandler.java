/**
 * Benutzerverwaltung für Administrator
 * Ruft alle Benutzer aus dem Speicher ab und beliefert ein Formular bereit, um diese zu bearbeiten.
 * 
 * @author ja
 */

package controller;
import core.CsvHandler;
import model.*;

public class UserHandler {

	private static final String S_FILE_NAME = "users";
	private static UserHandler userController;
	private CsvHandler csvHandler;
	private User[] users; // Alle Benutzer als Objekt in dieses Array
	
	private UserHandler()
	{
		csvHandler = new CsvHandler(S_FILE_NAME);
	}
	
	public static UserHandler getInstance(){
		if(userController == null){
			userController = new UserHandler();
		}
		return userController;
	}
	
	public User[] getAllUsers()
	{
		String[][] userMap = csvHandler.read();
		users = new User[userMap.length];
		for(int i = 0; i < users.length; i++){
			users[i] = new User(this.csvHandler, userMap[i][User.COL_LOGINNAME]);
		}
		return users;
	}
	
	public User getUser(String loginName)
	{
		int newIndex;
		User[] oldUsers;
		
		//Durchsuche vorhandene User
		if(users != null){
			for(int i = 0; i < users.length; i++){
				if(users[i].getLoginName().equals(loginName)){
					return users[i];
				}
			}
			newIndex = users.length;
			//Merke schon initialisierte User
			oldUsers = users;
			//Erweitere User-Array
			users = new User[newIndex+1];
			//Kopieren des alten User-Arrays	
			for(int i = 0; i < newIndex+1; i++){
				if(oldUsers[i] != null){
					users[i] = oldUsers[i];
				}
			}
		}else{
			newIndex = 0;
			//Erweitere User-Array
			users = new User[newIndex+1];
		}
		
		//Neuen User hinzufuegen
		users[newIndex] = new User(this.csvHandler, loginName);
		return users[newIndex];
	}
	
	public void save(){
		csvHandler.save();
	}
			
}
