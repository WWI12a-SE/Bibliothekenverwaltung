package test;

import javax.swing.*;

import model.User;
import view.UserTable;

public class UserTableViewTest {

	public static void main(String[] args) {
		new UserTableViewTest();
	}
	
	private UserTableViewTest(){
		JFrame frame = new JFrame("UserTableViewTest");
		frame.setSize(800, 600);
		JPanel panel = new UserTable(User.ROLE_LIBRARIAN);
		frame.add(panel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

