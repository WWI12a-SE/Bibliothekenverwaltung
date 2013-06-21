package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Medium;
import model.User;

import controller.MediaHandler;
import controller.MyAccount;
import controller.ReservationHandler;
import controller.UserHandler;

 public class GUI extends JFrame {
	 
	private JPanel tabStock;
	private JTabbedPane tabPane;

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
        this.setSize(880,500);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //Frame in die Bildschirm-Mitte
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screenSize.width - this.getWidth())/2;
		int y = (screenSize.height - this.getHeight())/2;
		this.setLocation(x,y);

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
        searchField.setPreferredSize(new Dimension (123, 26));
        topSouth.add(searchField);
        topSouth.add(searchButton);
        searchButton.addActionListener(new ActionListener () {
        	
        	/**
        	 * Der Such-Button bekommt einen ActionListener, der die eingegebene Zeile im Suchfeld liest.
        	 */
            
        	@Override
            public void actionPerformed(ActionEvent e) {
           
                String search = searchField.getText();
                search = search.trim();
                search = search.toLowerCase();
                MediaHandler handler = MediaHandler.getInstance();
                Medium[] media = handler.getAllMedia();
                
                if(!search.equals("")){//Nur Suche wenn spezifiziert, somst alle Medien anzeigen
                	ArrayList<Integer> foundMediaIDs = new ArrayList<Integer>();
                    
                    //Search
                    for(int i = 0; i < media.length; i++){
                    	if(
                    			media[i].getAuthor().toLowerCase().contains(search)
                    			|| media[i].getTitle().toLowerCase().contains(search)
                    			|| media[i].getPublisher().toLowerCase().contains(search)
                    			|| media[i].getKeywords().toLowerCase().contains(search)){
                    		
                    		foundMediaIDs.add(media[i].getID());
                    	}
                    }
                    
                    //Collect found Media
                    media = new Medium[foundMediaIDs.size()];
                    for(int i = 0; i < media.length; i++){
                    	media[i] = handler.getMedium(foundMediaIDs.get(i));
                    }
                }                
                
                //Refresh View
                tabStock = new StockView(media);
                tabPane.revalidate();
                tabPane.repaint();
                ChangeListener[] cls = tabPane.getChangeListeners();
                for(int i = 0; i < cls.length; i++){
                	cls[i].stateChanged(new ChangeEvent((Object)tabPane));
                }
                               
            }});
       

        // Hier wird die Tabpane mit ihren Panels angelegt.
        // ------------------------------------------------
        tabPane = new JTabbedPane(JTabbedPane.TOP,JTabbedPane.SCROLL_TAB_LAYOUT);
        tabPane.setSize(200, 200);
        this.add(tabPane, BorderLayout.CENTER);
        
        // Fuege Bestandsliste hinzu
        tabStock = new StockView(MediaHandler.getInstance().getAllMedia());
        //tabStore.setSize(200, 200);
        tabPane.addTab("Bestand", tabStock);

        // Fuege Reservierungen hinzu
        final JPanel tabReservations = new JPanel();//new ReservationsView();
        tabReservations.setSize(200, 200);
        tabReservations.setLayout(new GridLayout(1,1));
        //tabReservations.add(new ...) PANEL MUSS AUCH UNTEN IM CHANGE LISTENER HINZUGEFÜGT WERDEN
        tabReservations.add(new JPanel());//	new ReservationsView() TODO
        tabPane.addTab("Reservierungen", tabReservations);
        
        // Bibliothekare bekommen eine Nutzerliste, ...
        if(loggedInUser.getRole() == User.ROLE_LIBRARIAN)
        {
        	JPanel tabUserTable = new JPanel();
        	tabUserTable.setSize(200, 200);
        	tabUserTable.setLayout(new GridLayout(1,1));
        	tabUserTable.add(new UserTable(loggedInUser.getRole()));
        	tabPane.addTab("Alle Nutzer", tabUserTable);
        }
        
        // ... alle anderen sehen ihren Nutzeraccount.
        else
        {
    		JPanel tabMyAccount = new JPanel();
    		tabMyAccount.setSize(200, 200);
    		tabMyAccount.setLayout(new FlowLayout(FlowLayout.LEFT));
    		tabMyAccount.add(new ModifyUserDataPanel());
    		tabPane.addTab("Mein Konto", tabMyAccount);    		
    	}
        
        // Lade die Reservierungs-Tab und Bestands-Tab neu, jedes Mal bevor sie angezeigt werden.
        tabPane.addChangeListener(new ChangeListener() {
			@Override
			/**
			 * Methode prueft, an welcher Komponente (Tab) sich etwas geaendert hat.
			 */
			public void stateChanged(ChangeEvent e) {
				JTabbedPane pane;
				// An welcher Komponente wurde etwas ge-changed? Am tabPane!
				pane = (JTabbedPane) e.getSource();  
				
				// Falls dort (am tabPane) gerade das ReservationTab geoeffnet wurde:
				if(pane.getSelectedComponent() == tabReservations)
				{
					tabReservations.removeAll();
					//tabReservations.add(...);
					tabReservations.add(new JPanel());//	new ReservationsView() TODO
				}
				
				// Falls dort (am tabPane) gerade die StockView geöffnet wurde:
				else if(pane.getSelectedComponent() instanceof StockView)
				{
					// Welches Tab ist offen? Das i-te.
					int i = pane.getSelectedIndex();  
					// Im i-ten Tab eine neue StockView laden.
//					pane.setComponentAt(i, new StockView(MediaHandler.getInstance().getAllMedia()));
					pane.setComponentAt(i, tabStock);
				}
			}
        });
    }
       
   public static void main(String[] args) {
        GUI gui = new GUI();
    }
   
}

