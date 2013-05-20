package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Fragt aktiven Benutzer ab,
 * zeigt davon abh�ngig Register an.
 * @author Tina Lindemann
 * 
 * Register:
 * - PanelStock (B�cher)
 * - PanelReservations (Meine R. (Nutzer)/Alle R. (Bibliothekar))
 * 
 * 
 * http://docs.oracle.com/javase/tutorial/uiswing/components/tabbedpane.html
 *
 */


public class GUI extends JFrame {
	
	public GUI() 
	{
		super();
		
		//das Frame gestalten
		//---------------------------------------
		this.setTitle("Bibliotheksverwaltung");
		this.setSize(600,600);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		//oberen JPanel anlegen und ihm wieder zwei Panels hinzufügen
		JPanel top = new JPanel(new BorderLayout());
		this.add(top, BorderLayout.NORTH);
		JPanel topNorth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel topSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		top.add(topNorth, BorderLayout.NORTH);
		top.add(topSouth, BorderLayout.SOUTH);
		
		
		//Abmelden/Speichern-Button dem topNorth hinzufuegen
		//Button einen ActionListener geben, der zum Login-Fenster zurückführt
		JButton quitSaveButton = new JButton("Abmelden/Speichern");
		topNorth.add(quitSaveButton);
		quitSaveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//aktuelles Fenster unsichtbar
				setVisible(false);
				//neues Login-Fenster aufbauen
				Login login = new Login();
				login.setVisible(true);
				
			}});
				
		//Such-TextField und Such-Button anlegen und sie dem topSouth hinzufügen
		//ActionListener fuer Such-Button 
		JButton searchButton = new JButton("Suchen");
		final JTextField searchField = new JTextField();
		searchField.setPreferredSize(new Dimension (88, 26));
		topSouth.add(searchField);
		topSouth.add(searchButton);
		searchButton.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				searchField.getText();
								
			}});
		
		
		
		//Tableiste anlegen - kommt in den unteren Bereich des Frames
		//Panels f�r die tabbedPane anlegen
		JTabbedPane tabPane = new JTabbedPane(JTabbedPane.TOP,JTabbedPane.SCROLL_TAB_LAYOUT);
		tabPane.setSize(200, 200);
		this.add(tabPane, BorderLayout.CENTER);
		JPanel tabStore = new JPanel();
		JPanel tabReservations = new JPanel();
		JPanel tabMyAccount = new JPanel();
		tabStore.setSize(200, 200);
		tabReservations.setSize(200, 200);
		tabMyAccount.setSize(200, 200);
		
		//die Panels der Tableiste hinzuf�gen
		tabPane.addTab("Bestand", tabStore);
		tabPane.addTab("Reservierungen", tabReservations);
		tabPane.addTab("Mein Konto", tabMyAccount);
		
	}
		
	
	public static void main(String[] args) {
		GUI gui = new GUI();
	}
	
}
