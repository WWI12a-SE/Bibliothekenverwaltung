/**
 * Reservationen anzeigen
 * 
 * @author ja (阿快)
 */

package test;

import javax.swing.*;

import controller.MyAccount;
import controller.UserHandler;

import view.*;

public class TestdriveReservationsView {

	public static void main(String[] args){
		JFrame frame = new JFrame("Reservationen");
		String loginName = "admin";
		String pw = "1234";
		
		MyAccount.login(loginName, pw);
		System.out.println("Hallo, "+UserHandler.getInstance().getUser(loginName).getRoleAsString());
		frame.setSize(800, 600);
		JPanel panel = new ReservationsView();
		frame.add(panel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
