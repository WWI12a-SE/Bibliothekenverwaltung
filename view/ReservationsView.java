/**
 * Reservationen
 * View-Renderer. Setzt View-Klassen ein.
 * 
 * @author 阿快
 */

package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import controller.*;
import model.*;
import core.*;


public class ReservationsView extends JFrame{
    private JTable oReservationsTable = null;
    private User oTheUser = null;
    public static int iEditEntry = 0;
	
    /**
     * Construct
     */
	public ReservationsView()
	{
		// Linkes Panel (Tabelle)
		JPanel oPanelTable = new JPanel();
        getContentPane().add(oPanelTable, BorderLayout.WEST);		// Links ausrichten
        oReservationsTable = new JTable(new ReservationsTable());	// Tabelle reinholen        
        JScrollPane oPane = new JScrollPane(oReservationsTable);
        oPanelTable.add(oPane);										// Tabelle einfügen
        
        // Rechtes Panel (Schaltflächen)
        JPanel oPanelBtn = new JPanel();
        getContentPane().add(oPanelBtn, BorderLayout.EAST);			// Rechts ausrichten
        
        // Button: Zurückgeben
        JButton btnReturn = new JButton("Zurückgeben");
        oPanelBtn.add(btnReturn);
        

        
        // Button: Verlängern
        //if (this.oTheUser.getRole() > User.ROLE_STUDENT){
        JButton btnExtend = new JButton("Verlängern");
        oPanelBtn.add(btnExtend);
        //}
        
        // Hilfslabel
        JLabel lblHelp = new JLabel(this.iEditEntry);	//++++ als String casten
        oPanelBtn.add(lblHelp);

        
        /*
		JPanel oPanel = new ReservationsLayout();
		this.add(oPanel);
		*/
		
		// Basis-Layout
        this.setSize(800, 450); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setVisible(true);	
	}
	

    
    
    
    /**
     * Zurückgeben, Ausleihe beenden
     * @author ja
     */
    private class ActionReturn implements ActionListener{
    	
		@Override
		public void actionPerformed(ActionEvent arg0){
			
		}    	
    }

    
    
    /**
     * Ausleihe verlängern
     * @author ja
     */
    private class ActionExtend implements ActionListener{
    	
		@Override
		public void actionPerformed(ActionEvent arg0){
			
		}    	
    }
}