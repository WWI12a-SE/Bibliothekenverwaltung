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
import javax.swing.JOptionPane;
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

 public class GUI extends JFrame {

    private static final long serialVersionUID = 1L;

	/**
	 * Das obere Panel "top" wird angelegt. Ihm werden zwei Panels, "topNorth" und topSouth", hinzugefuegt.
	 * 
	 * Die Abmelden- und Speichern-Buttons kommen in das Panel "topNorth"
	 * Der Abmelden-Button bekommt einen ActionListener.
	 * 
	 * Der Speichern-Button bekommt ebenfalls einen ActionListener, der alle Aenderungen speichert.
	 * Das Textfeld "SearchField" und der Button "searchButton" werden angelegt und dem "topSouth"-Panel hinzugefuegt.
	 * Hinzu kommt eine ActionListener für den Button.
	 * Dies geschieht, indem die jeweiligen Handler aufgerufen werden.
	 * 
	 * Zum Schluß wir die Tableiste angelegt und kommt in den unteren Bereich des Frames.
	 * Zugleich werden die Panels für die Tableiste erzeugt.
	 */
	public GUI()
    {
		super();
        User loggedInUser = MyAccount.getLoggedInUser();
       
        this.setTitle("Bibliotheksverwaltung");
        this.setSize(600,600);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel top = new JPanel(new BorderLayout());
        this.add(top, BorderLayout.NORTH);
        JPanel topNorth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel topSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        top.add(topNorth, BorderLayout.NORTH);
        top.add(topSouth, BorderLayout.SOUTH);
       
        
        JButton saveButton = new JButton("Speichern");
        topNorth.add(saveButton);
       
        JButton quitButton = new JButton("Abmelden");
        topNorth.add(quitButton);
        
        saveButton.addActionListener(new ActionListener() {
            
        	/**
        	* Wird der Speicher-Button geklickt, muss jeweils eine Instanz des User-, Reservations- und Media-Handlers "geholt" werden.
        	* An dieser Instanz wird dann die "save"-Methode aufgerufen.
        	**/
        	
            @Override
            public void actionPerformed(ActionEvent arg0) {
                
                UserHandler.getInstance().save();
                ReservationHandler.getInstance().save();
                MediaHandler.getInstance().save();
                              
            }});
       
        quitButton.addActionListener(new ActionListener(){

        	/**
        	 * Wird der Abmelde-Button geklickt, oeffnet sich ein Dialog.
        	 * Wählt der Nutzer die Ja-Option wird er abmeldet und zurück zum Login-Fenster gefuehrt.
        	 */
        	
            @Override
            public void actionPerformed(ActionEvent e) {
                openStandardDialog();
            }
       
            /**
             * Methode zum Oeffnen des Dialogs.
             */
            private void openStandardDialog()
            {
            	Object[] options = {"Ja","Nein"};
    				int optionDialog = JOptionPane.showOptionDialog(null, "Wollen Sie sich wirklich abmelden?", "Achtung", 
    				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
    				
    				if(optionDialog == JOptionPane.YES_OPTION){
                    {
                    	MyAccount.logout();
                    	setVisible(false);
              
                    	Login login = new Login();
                    	login.setVisible(true);
                    }
              
                    if(optionDialog == JOptionPane.NO_OPTION)
                    {
                  
                    }
             }}}
        );
       
        JButton searchButton = new JButton("Suchen");
        final JTextField searchField = new JTextField();
        searchField.setPreferredSize(new Dimension (88, 26));
        topSouth.add(searchField);
        topSouth.add(searchButton);
        searchButton.addActionListener(new ActionListener () {
        	
        	/**
        	 * Der Such-Button bekommt einen ActionListener, der die eingegebene Zeile im Suchfeld liest.
        	 */
            
        	@Override
            public void actionPerformed(ActionEvent e) {
           
                searchField.getText();
                               
            }});
       
        JTabbedPane tabPane = new JTabbedPane(JTabbedPane.TOP,JTabbedPane.SCROLL_TAB_LAYOUT);
        tabPane.setSize(200, 200);
        this.add(tabPane, BorderLayout.CENTER);
        JPanel tabStore = new StockView(MediaHandler.getInstance().getAllMedia());
        JPanel tabReservations = new JPanel();
        JPanel tabMyAccount = new JPanel();
        tabStore.setSize(200, 200);
        tabReservations.setSize(200, 200);
        tabMyAccount.setSize(200, 200);
        tabMyAccount.setLayout(new FlowLayout(FlowLayout.LEFT));
       
        tabPane.addTab("Bestand", tabStore);
        tabPane.addTab("Reservierungen", tabReservations);
        tabPane.addTab("Mein Konto", tabMyAccount);
       
        tabMyAccount.add(new ModifyUserDataPanel());
       
    }
       
   public static void main(String[] args) {
        GUI gui = new GUI();
    }
   
}

