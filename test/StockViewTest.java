package test;

import javax.swing.*;

import controller.MyAccount;

import model.User;
import view.StockView;

public class StockViewTest {

	public static void main(String[] args) {
		new StockViewTest();
	}
	
	private StockViewTest(){
		JFrame frame = new JFrame("StockViewTest");
//		if(MyAccount.login("admin", "1234")) System.out.println("logged in as Librarian.");
		if(MyAccount.login("aggi", "1111")) System.out.println("logged in as Student.");
		frame.setSize(800, 600);
		JPanel panel = new StockView(MyAccount.getLoggedInUser().getRole());
		frame.add(panel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
