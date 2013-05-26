/**
 * Tabelle für Reservationen. NUR Tabelle!
 * 
 * @author ja 阿快
 */

package view;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import view.ReservationsTable2.FooTableModel;

import controller.*;
import core.*;
import model.*;

public class ReservationsTable  extends DefaultTableModel{
	
	private CsvHandler oResHandle = null;	// Handler im globalen Objekt
	private int rows = 0, cols = 0;			// Dimensionen
    private Object[] oCellData = null;		// Platzhalter für Zellen
    private boolean blIsRole = false;		// Dieser kleine Merker sagt uns, ob die View für Rollen ausgelegt sein muss.

    public ReservationsTable() { 
    	super();
    	
    	// Variablen füllen
    	this.oResHandle = new CsvHandler("reservations");	// CSV-Handler
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
            		// Identifikator der Ausleihe an die View übergeben.
            		//ReservationsView.iEditEntry = Integer.parseInt(aCellVal[i]);
            		
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

    /**
     * Zum Sortieren per Klick auf Spaltentitel
     * @author ja
     */
    class FooTableModel extends DefaultTableModel {
        public FooTableModel(Object[][] rowData, Object[] headers) {
            super(rowData, headers);
        }
 
        public void sortByColumn(final int clm) {
            Collections.sort(this.dataVector, new Comparator() {
                public int compare(Object o1, Object o2) {
                    Vector v1 = (Vector) o1;
                    Vector v2 = (Vector) o2;
 
                    int size1 = v1.size();
                    if (clm >= size1)
                        throw new IllegalArgumentException("max column idx: "
                                + size1);
 
                    String s1 = (String) v1.get(clm);
                    String s2 = (String) v2.get(clm);
 
                    return s1.compareTo(s2);
                }
            });
        }
    }
}
