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

import model.User;

import controller.MediaHandler;
import controller.MyAccount;
import controller.ReservationHandler;
import controller.UserHandler;

/**
 * 
 * Die Klasse GUI legt den Hauptframe an. Abhaengig vom Nutzer werden unterschiedliche Register angezeigt.
 *
 */

public class GUI extends JFrame {
	
	public GUI() 
	{
		super();
		
		/**
		 *Der Frame wird gestaltet.
		 */
		this.setTitle("Bibliotheksverwaltung");
		this.setSize(600,600);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		/** 
		 * Das obere Panel "top" wird angelegt. Ihm werden zwei Panels, "topNorth" und topSouth", hinzugefuegt.
		 */
		JPanel top = new JPanel(new BorderLayout());
		this.add(top, BorderLayout.NORTH);
		JPanel topNorth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel topSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		top.add(topNorth, BorderLayout.NORTH);
		top.add(topSouth, BorderLayout.SOUTH);
		
		/**
		 * Der Abmelden/Speichern-Button kommt in das Panel "topNorth"
		 * Er bekommt einen ActionListener, der beim Klicken speichert und zurück zum Login-Fenster fuehrt.
		 */
		JButton quitSaveButton = new JButton("Abmelden/Speichern");
		topNorth.add(quitSaveButton);
		quitSaveButton.addActionListener(new ActionListener() {

			/**
			 * Der ActionListener.
			 */
			@Override
			public void actionPerformed(ActionEvent arg0) {
				/**
				 * Alle Daten werden gespeichert, indem die jeweiligen Handler aufgerufen werden.
				 * Da es immer nur jeweils einen Handler für User, Reservations und Media gibt, muss jeweils deren Instanz "geholt" werden.
				 * An der Instanz wird dann die "save"-Methode aufgerufen.
				 */
				UserHandler.getInstance().save();
				ReservationHandler.getInstance().save();
				MediaHandler.getInstance().save();
				
				/**
				 * Das Hauptfenster wird unsichtbar.
				 */
				setVisible(false);
				/**
				 * Das Login-Fenster wird wieder aufgebaut.
				 */
				Login login = new Login();
				login.setVisible(true);
				
			}});
		
		/**
		 * Das Textfeld "SearchField" und der Button "searchButton" wird angelegt und dem "topSouth"-Panel hinzugefuegt.
		 * Hinzu kommt eine ActionListener für den Button.
		 */
		JButton searchButton = new JButton("Suchen");
		final JTextField searchField = new JTextField();
		searchField.setPreferredSize(new Dimension (88, 26));
		topSouth.add(searchField);
		topSouth.add(searchButton);
		searchButton.addActionListener(new ActionListener () {

			/**
			 * Der ActionListener
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				/**
				 * Die Methode liest den eingegebenen Text aus dem "SearchField" aus.
				 */
				searchField.getText();
								
			}});
		
		/**
		 * Die Tableiste wird angelegt und kommt in den unteren Bereich des Frames.
		 * Zugleich werden die Panels für die Tableiste erzeugt.
		 */
		JTabbedPane tabPane = new JTabbedPane(JTabbedPane.TOP,JTabbedPane.SCROLL_TAB_LAYOUT);
		this.add(tabPane, BorderLayout.CENTER);
	
		/**
		 * Die Panels werden der Tableiste hinzugefuegt.
		 * Panel, um Bestand einzusehen, hinzufügen.
		 * Panel, um Nutzerdaten zu bearbeiten, hinzufügen.
		 * Panel, um Reservierungen einzusehen, hinzufügen.
		 */
		
		User loggedInUser = MyAccount.getLoggedInUser();
		if (loggedInUser != null) {
			JPanel tabStore = new StockView(MyAccount.getLoggedInUser().getRole());
			tabPane.addTab("Bestand", tabStore);

			JPanel tabReservations = new JPanel();
			tabPane.addTab("Reservierungen", tabReservations);
			
			JPanel tabMyAccount = new ModifyUserDataPanel();
			tabPane.addTab("Mein Konto", tabMyAccount);
			tabMyAccount.setLayout(new FlowLayout(FlowLayout.LEFT));
		}

		
		

		
	}
		
	
	public static void main(String[] args) {
		GUI gui = new GUI();
	}
	
}
