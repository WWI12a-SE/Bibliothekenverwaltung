/**
 * Tabelle für Reservationen. NUR Tabelle!
 * 
 * @author ja 阿快
 */

package view;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import controller.*;
import core.*;
import model.*;

public class ReservationsTable  extends DefaultTableModel{
	
	private CsvHandler oResHandle = null;	// Handler im globalen Objekt
	private int rows = 0, cols = 0;			// Dimensionen
    private Object[] oCellData = null;		// Platzhalter für Zellen
    private User oTheUser = null;			// Angemeldeter Benutzer
    private boolean blIsRole = false;		// Dieser kleine Merker sagt uns, ob die View für Rollen ausgelegt sein muss.

    public ReservationsTable() { 
    	super();
    	
    	// Variablen füllen
    	this.oResHandle = new CsvHandler("reservations");	// CSV-Handler
    	
    	this.oTheUser = MyAccount.getLoggedInUser();
    	this.cols = oResHandle.iColons;
    	this.rows = oResHandle.iLines;
    	
    	oCellData = new Object[this.cols];
    	
    	// Abfeuern
        initTable();
    	
    }
    

    /**
     * Tabelle
     */
    private void initTable() {
    	
    	// Beschriftungen für Spalten	////////////////////////////////
    	this.addColumn("Eintrag");	// Später ausblenden
        this.addColumn("Buch");
    	this.addColumn("ausgeliehen von");
        this.addColumn("Rückgabe bis");
        this.addColumn("Laufende Ausleihe");
        
        // Alle Ausleihen via ID abholen	////////////////////////////////
        String[] aAllReservations = new String[rows];
        aAllReservations = this.oResHandle.getAllIDs();
        
        
        // Zellen befüllen	////////////////////////////////
        // i... Spalte
        // j... Zeile
        for (int j = 0; j < this.rows; j++) {
        	
        	// Je Zeile das Ausleihen-Objekt anlegen
        	String[] aCellVal = new String[this.cols];
        	aCellVal = this.oResHandle.getLineById(aAllReservations[j]);
        	
        	// Zellen in Zeile befüllen
            for (int i = 0; i < this.cols; i++) { 
                //oCellData[i] = j + "/" + i;
            	
            	switch(i){
            	case 0:	// Ausleihen-ID
            		ReservationsView.iEditEntry = aCellVal[i]; //+++ als String casten	// Identifikator der Ausleihe an die View übergeben.
            		
            		oCellData[i] = aCellVal[i];
            		break;
            	case 1:	// Benutzer-ID
            		User oUser = UserHandler.getInstance().getUser(aCellVal[i]);
            		oCellData[i+1] = oUser.getFirstName() + " " + oUser.getLastName();
            		break;
            	case 2:	// Buch
            		int iMediumID = Integer.parseInt(aCellVal[i]);
            		Medium oMedium = MediaHandler.getInstance().getMedium(iMediumID);
            		oCellData[i-1] = oMedium.getTitle();
            		break;
            	case 3: // Rückgabe
            		oCellData[i] = aCellVal[i];
            		break;
            	case 4: // Verlängert
            		oCellData[i] = aCellVal[i];
            		break;            		
            	}
            	
            } 
              
            this.addRow(oCellData);
        }
    }
}
