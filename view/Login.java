package view;

import java.awt.*;

import javax.swing.*;

/**
 * Stellt ein Loginformular bereit. 
 * @author Sandra Lang
 *
 */
public class Login extends JFrame{
	
	//Attribute
	private JButton loginbutton;
	private JTextField usernamefield;
	private JPasswordField passwordfield;
	private JLabel userlabel;
	private JLabel passwordlabel;
	private JPanel loginpanel;
		
	
	//constructor
	public Login(){
		super();
		GridLayout loginLayout = new GridLayout();
		setTitle("Bibliothekenverwaltung: Anmelden");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		// Design
				loginpanel = new JPanel();
				userlabel = new JLabel("Benutzername");
				usernamefield = new JTextField(8);
				passwordlabel = new JLabel("Kennwort");
				passwordfield = new JPasswordField(8);
				loginbutton = new JButton("Anmelden");
				
				loginpanel.add(usernamefield);
				loginpanel.add(userlabel);
				loginpanel.add(passwordlabel);
				loginpanel.add(passwordfield);
				loginpanel.add(loginbutton);
				
				this.add(loginpanel);		// Panel zum Frame hinzufügen
				
	}
}
