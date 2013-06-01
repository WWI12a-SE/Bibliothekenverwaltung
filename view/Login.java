package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import controller.MyAccount;

/**
 * Stellt ein Loginformular bereit. 
 * @author Sandra Lang
 *
 */

public class Login extends JFrame{
	
	//Attribute zu Darstellung
	private JButton loginbutton;
	private JTextField usernamefield;
	private JPasswordField passwordfield;
	private JLabel userlabel;
	private JLabel passwordlabel;
	private JPanel userpanel;
	private JPanel passwordpanel;
	private JPanel buttonpanel;
		
	//constructor
	public Login(){
		super();
		this.setLayout(new GridLayout(3,1));
		setTitle("Bibliothekenverwaltung: Anmelden");
		setSize(400,150);
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		// Design
			userpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));	
			passwordpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			buttonpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		//1.Zeile
			userlabel = new JLabel("Benutzername: ");
			usernamefield = new JTextField(15);
			userpanel.add(userlabel);
			userpanel.add(usernamefield);
				
		//2.Zeile
			passwordlabel = new JLabel("Kennwort:          ");
			passwordfield = new JPasswordField(15);
			passwordpanel.add(passwordlabel);
			passwordpanel.add(passwordfield);
			
		//3.Zeile
			loginbutton = new JButton("Anmelden");
			loginbutton.addActionListener(new LoginListener());
			buttonpanel.add(loginbutton);
		
		// Panels zum Frame hinzuf�gen
			this.add(userpanel);		// Panel zum Frame hinzuf�gen
			this.add(passwordpanel);
			this.add(buttonpanel);	
				
		//Position des Fenster auf dem Bildschirm in der Mitte
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			int x = (screenSize.width - this.getWidth())/2;
			int y = (screenSize.height - this.getHeight())/2;
			this.setLocation(x,y);
	}
		// Aktion des Buttons
	class LoginListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			//eingegebene Logindaten holen
			String username = usernamefield.getText();
			char [] password = passwordfield.getPassword();	
			String sPassword = String.valueOf(password);// Pharse password in String
			
			//Logindaten weiterleiten an MyAccount
			boolean loginOK = MyAccount.login(username, sPassword); //hole daten
						
			if (loginOK == true){
				// Welcher User ist drin
				// Sitzung erstellen
				Login.this.setVisible(false);
				GUI gui = new GUI();
				gui.setVisible(true);
			}else{
				JOptionPane.showMessageDialog(null, "Das Kennwort oder der Benutzername ist falsch" +
						", bitte versuchen Sie es erneut!", "Achtung", JOptionPane.OK_CANCEL_OPTION);
			}
		}	
	}
}
