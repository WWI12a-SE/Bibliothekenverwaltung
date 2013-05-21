package view;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class UserTable extends JTable {

	/**
	 * Tabellenansicht über alle Benutzer, die registriert sind und Bücher ausgeliehen haben.
	 * Ansicht nur für den Bibliothekar
	 */
	
	//constructor
	public UserTable (){
		
	}
	
	//Tabellenaufbau
	
	
	
	public static void main(String[] args) {
		String[][] rowData = {
			    { "Japan", "245" }, { "USA", "240" }, { "Italien", "220" },
			    { "Spanien", "217" }, {"Türkei", "215"} ,{ "England", "214" },
			    { "Frankreich", "190" }, {"Griechenland", "185" },
			    { "Deutschland", "180" }, {"Portugal", "170" }
			  	};
			    String[] columnNames = {
			        "Land", "Durchschnittliche Sehdauer pro Tag in Minuten"
			    };
			  JFrame f = new JFrame();
			  f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
			  JTable table = new JTable( rowData, columnNames );
			  f.add( new JScrollPane(table) );
			  f.pack();
			  f.setVisible( true );
	}

}


