package test;

import javax.swing.*;

import model.User;
import view.StockView;

public class StockViewTest {

	public static void main(String[] args) {
		new StockViewTest();
	}
	
	private StockViewTest(){
		JFrame frame = new JFrame("StockViewTest");
		frame.setSize(800, 600);
		JPanel panel = new StockView(User.ROLE_LIBRARIAN);
		frame.add(panel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
