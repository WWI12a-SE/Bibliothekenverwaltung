package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

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
 * zeigt davon abhängig Register an.
 * @author ja
 * 
 * Register:
 * - PanelStock (Bücher)
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
		
		//dem Frame und dem bottomPanel ein GridLayout geben
		this.setLayout(new GridLayout(2, 0));
		this.setTitle("Bibliotheksverwaltung");
		this.setSize(600,600);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		//oberen JPanel anlegen und ihn wieder in zwei Panels untergliedern
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(2,0));
		JPanel savePanel = new JPanel();
		JPanel searchPanel = new JPanel();
		topPanel.add(searchPanel);
		topPanel.add(savePanel);
		this.add(topPanel);
		
		//Abmelden/Speichern-Button dem savePanel hinzufügen
		//Such-TextField anlegen und searchPanel hinzufügen
		JButton quitSaveButton = new JButton();
		JLabel quitSaveLabel = new JLabel("Abmelden/Speichern");
		quitSaveButton.add(quitSaveLabel);
		savePanel.add(quitSaveButton);
		savePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JTextField searchField = new JTextField();
		searchField.setPreferredSize(new Dimension (130, 26));
		searchPanel.add(searchField);
		searchPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JButton searchButton = new JButton("Suchen");
		searchPanel.add(searchButton);
		
		
		//Tableiste anlegen - kommt in den unteren Bereich des Frames
		//Panels für die tabdPane anlegen
		JTabbedPane tabPane = new JTabbedPane(JTabbedPane.TOP,JTabbedPane.SCROLL_TAB_LAYOUT);
		tabPane.setSize(200, 200);
		this.add(tabPane);
		JPanel tabStore = new JPanel();
		JPanel tabReservations = new JPanel();
		JPanel tabMyAccount = new JPanel();
		tabStore.setSize(200, 200);
		tabReservations.setSize(200, 200);
		tabMyAccount.setSize(200, 200);
		
		//die Panels der Tableiste hinzufügen
		tabPane.addTab("Bestand", tabStore);
		tabPane.addTab("Reservierungen", tabReservations);
		tabPane.addTab("Mein Konto", tabMyAccount);
		
		//Bestand-Tab
		String[] dataMedia = new String[] {"eins", "zwei", "drei", "vier"};
		final JList storeList = new JList(dataMedia);
		storeList.addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				storeList.getSelectedIndices();
				
			}});
		storeList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tabStore.add(storeList);
		tabStore.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		//Reservierungen
		String[] dataReservations = new String[] {"vier", "fünf", "sechs", "sieben"};
		final JList reservationList = new JList(dataReservations);
		storeList.addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				reservationList.getSelectedIndices();
				
			}});
		storeList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tabReservations.add(reservationList);
		tabReservations.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		//persönliche Daten
		String[] dataUser = new String[] {"Vorname", "Nachname", "Strasse", "Ort"};
		final JList userList = new JList(dataUser);
		storeList.addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				userList.getSelectedIndices();
				
			}});
		storeList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tabMyAccount.add(userList);
		tabMyAccount.setLayout(new FlowLayout(FlowLayout.LEFT));
		
	}
		
	
	public static void main(String[] args) {
		GUI gui = new GUI();
	}
	
}
