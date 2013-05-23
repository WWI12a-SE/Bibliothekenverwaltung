package view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.User;

import controller.MyAccount;

import core.Exception.InvalidFormatException;
import core.Exception.UnequalPasswordsException;

public class ModifyUserDataPanel extends JPanel {
	private JTextField firstNameTextField;
	private JTextField secondNameTextField;
	private JTextField emailTextField;
	private JPasswordField passwordTextField;
	private JPasswordField repeatPasswordTextField;

	//neues Panel mit Grid-Layout anlegen
	public ModifyUserDataPanel() {	
		this.setLayout(new GridLayout(6, 0));
		
		//dem Panel die erste Zeile "Vorname" zuweisen
		JPanel firstName = new JPanel();
		firstName.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JLabel firstNameLabel = new JLabel("Vorname ändern");
		firstName.add(firstNameLabel);
		firstNameTextField = new JTextField(20);
		firstName.add(firstNameTextField);
		this.add(firstName);
				
		//dem Panel die zweite Zeile "Nachname" zuweisen
		JPanel secondName = new JPanel();
		secondName.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JLabel secondNameLabel = new JLabel("Nachname ändern");
		secondName.add(secondNameLabel); 
		secondNameTextField = new JTextField(20);
		secondName.add(secondNameTextField);
		this.add(secondName);
		
		//dem Panel die dritte Zeile "E-mail" zuweisen
		JPanel email = new JPanel();
		email.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JLabel emailLabel = new JLabel("neue E-mail-Adresse");
		email.add(emailLabel); 
		emailTextField = new JTextField(20);
		email.add(emailTextField);
		this.add(email);
		
		//dem Panel die vierte Zeil "Passwort" zuweisen
		JPanel password = new JPanel();
		password.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JLabel passwordLabel = new JLabel("neues Passwort");
		password.add(passwordLabel); 
		passwordTextField = new JPasswordField(20);
		password.add(passwordTextField);
		this.add(password);
		
		//dem Panel die fünfte Zeile "Passwort wiederholen" zuweisen
		JPanel repeatPassword = new JPanel();
		repeatPassword.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JLabel repeatPasswordLabel = new JLabel("Passwort wiederholen");
		repeatPassword.add(repeatPasswordLabel);
		repeatPasswordTextField = new JPasswordField(20);
		repeatPassword.add(repeatPasswordTextField);
		this.add(repeatPassword);
		
		//dem Panel den Speicher-Button zuweisen
		JPanel save = new JPanel();
		JButton saveButton = new JButton("Speichern");
		//der Speicher-Button bekommt einen ActionListener, der entweder speichert oder Exceptions fängt
		//1.Exception: Passwort und wiederholtes Passwort stimmen nicht überein
		//2. Exception: in der E-mail-Adresse ist kein @ enthalten
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {		
					save();	
				} catch(UnequalPasswordsException e) {
					JOptionPane.showMessageDialog(ModifyUserDataPanel.this, "Bitte überprüfen Sie, ob Ihre Passwörter übereinstimmen!!");
				} catch(InvalidFormatException e){
					JOptionPane.showMessageDialog(ModifyUserDataPanel.this, "Ihre E-mail-Adresse muss ein @ enthalten.");
				}
			}
		});
		save.add(saveButton);
		this.add(save);
		
		this.setVisible(true);
	}
	//die save-Methode, die die geänderten Daten speichert 
	public void save() throws UnequalPasswordsException, InvalidFormatException {
		String firstName = firstNameTextField.getText();
		String secondName = secondNameTextField.getText();
		String email = emailTextField.getText();
		String password = String.valueOf(passwordTextField.getPassword());	
		String password2 = String.valueOf(repeatPasswordTextField.getPassword());
		
		//es wird zugleich auf fehlerhafte Eingaben geprüft
		//gegebenenfalls werden Exceptions "gethrowt", die im ActionListener gefangen werden
		if(!password.equals(password2)) {
			passwordTextField.setText("");
			repeatPasswordTextField.setText("");
			throw new UnequalPasswordsException();
		}
		if(!email.contains("@"))  {
			emailTextField.setText("");
			throw new InvalidFormatException();
		}
		//der aktuell eingeloggte User wird geholt
		//seine Daten werden mit den von ihm geänderten Daten überschrieben
		User loggedInUser = MyAccount.getLoggedInUser();
		loggedInUser.setFirstName(firstName);
		loggedInUser.setLastName(secondName);
		loggedInUser.setEmail(email);
		loggedInUser.setPassword(password);
		
		//
		
	}
	//main-Methode, die nur vorübergehend da ist, wird später gelöscht
	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.add(new ModifyUserDataPanel());
		ModifyUserDataPanel modify1 = new ModifyUserDataPanel();
		frame.setVisible(true);
		frame.setSize(450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}