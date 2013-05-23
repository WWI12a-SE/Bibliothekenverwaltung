/**
 * Tabelle für Reservationen
 * 
 * @author ja 阿快
 */

package view;
import controller.*;
import core.*;

import javax.swing.table.DefaultTableModel;

import model.User;

public class Reservations  extends DefaultTableModel{
	
	private CsvHandler oResHandle = null;	// Handler im globalen Objekt
	private int rows = 0, cols = 0;			// Dimensionen
    private Object[] oCellData = null;		// Platzhalter für Zellen 

    public Reservations() { 
    	super();
    	this.oResHandle = new CsvHandler("reservations");
    	this.rows = CsvHandler.iLines;
    	this.cols = CsvHandler.iColons;
    	oCellData = new Object[cols];
        initTable();
    }
    

    /**
     * Tabelle
     */
    private void initTable() { 
        
    	// Beschriftungen für Spalten
    	this.addColumn("Eintrag");
    	this.addColumn("Nutzer-ID");
        this.addColumn("Buch-ID");
        this.addColumn("Rückgabe bis");
        this.addColumn("Laufende Ausleihe");
        
        // Alle Ausleihen via ID abholen
        String[] aAllReservations = new String[rows];
        aAllReservations = this.oResHandle.getAllIDs();
        
        
        // Zellen befüllen
        // i... Spalte
        // j... Zeile
        for (int j = 0; j < rows; j++) {
        	
        	// Je Zeile das Objekt anlegen
        	String[] aCellVal = new String[cols];
        	aCellVal = this.oResHandle.getLineById(aAllReservations[j]);
        	
        	
            for (int i = 0; i < cols; i++) { 
                //oCellData[i] = j + "/" + i;
            	
            	switch(i){
            	case 0:	// Ausleihen-ID
            		oCellData[i] = aCellVal[i];
            		break;
            	case 1:	// Benutzer-ID
            		User oUser = new User();
            		oUser.setLoginName(aCellVal[i]);
            		oCellData[i] = oUser.getFirstName() + oUser.getLastName();
            		break;
            	case 2:	// Buch
            		Medium oMedium = new Medium();
            		oMedium.setID(aCellVal[i]);
            		oCellData[i] = oMedium.getTitle();
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
        
    	
        /*
    	for (int i = 0; i < cols; i++) { 
            this.addColumn(Integer.toString(i));
        } 

        for (int j = 0; j < rows; j++) { 
            for (int i = 0; i < cols; i++) { 
                rowData[i] = j + " | " + i; 
            } 
            this.addRow(rowData); 
        }
        */
    } 
}