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
        
        
        

        
        // Hilfslabel
        JLabel lblHelp = new JLabel(String.valueOf(this.iEditEntry));
        oPanelBtn.add(lblHelp);
        
        // Button: Zurückgeben (Immer zeigen)
        JButton btnReturn = new JButton("Zurückgeben");
        btnReturn.addActionListener(new ActionReturn(this.iEditEntry));
        oPanelBtn.add(btnReturn);
        
        // Button: Verlängern (Zeigen, wenn es die Rolle zulässt: Bibliothekar: immer, andere: je nach erlaubter Frist
        //if (this.oTheUser.getRole() > User.ROLE_STUDENT){
        JButton btnExtend = new JButton("Verlängern");
        btnExtend.addActionListener(new ActionExtend(this.iEditEntry));
        oPanelBtn.add(btnExtend);
        //}
		
		// Basis-Layout
        this.setSize(800, 450); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setVisible(true);	
	}
	

    
    /** AKTIONEN *****************************************/
    
    /**
     * Zurückgeben, Ausleihe beenden
     * @author ja
     */
    private class ActionReturn implements ActionListener{
    	
    	private int iEdit = 0;
    	
    	/**
    	 * Constructor. Nimmt einen übergebenen Integer entgegen, der als
    	 * Identifikator verwendet wird.
    	 * @param iEditLine
    	 */
    	ActionReturn(int iEditLine){
    		this.iEdit = iEditLine;
    	}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("Zurück damit!" + String.valueOf(this.iEdit));
			
		}    	
    }

    
    
    /**
     * Ausleihe verlängern
     * @author ja
     */
    private class ActionExtend implements ActionListener{

    	private int iEdit = 0;
    	
    	/**
    	 * Constructor. Nimmt einen übergebenen Integer entgegen, der als
    	 * Identifikator verwendet wird.
    	 * @param iEditLine
    	 */
    	ActionExtend(int iEditLine){
    		this.iEdit = iEditLine;
    	}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("Mehr davon" + String.valueOf(this.iEdit));
		}    	
    }
}