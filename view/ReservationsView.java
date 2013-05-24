/**
 * Reservationen
 * 
 * @author ja
 * 这是一个考验。 （阿快）
 */

package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.io.File;

import model.*;
import core.*;


public class ReservationsView extends JFrame{
	
    private final int small = 50; 
    private final int big = 150; 

    private JTable oReservationsTable; 
	
	public ReservationsView()
	{
        oReservationsTable = new JTable(new Reservations()); 
        
        JScrollPane oPane = new JScrollPane(oReservationsTable); 

        getContentPane().add(oPane); 
        this.setSize(800, 450); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setVisible(true); 
        
        
        
    	CsvHandler oAllReservations = new CsvHandler("reservations");
    	//oAllReservations.iLines;
    	
    	
    	JLabel oTestLabel = new JLabel("REs");
    	//getContentPane().add(oTestLabel);
    	//System.out.println("asdasasd");
    	
    	
	}
}