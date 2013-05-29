//package view;
//
//import javax.swing.JFrame;
//import javax.swing.JScrollPane;
//import javax.swing.JTable;
//
//public class UserTable extends JTable {
//
//	/**
//	 * Tabellenansicht �ber alle Benutzer, die registriert sind und B�cher ausgeliehen haben.
//	 * Ansicht nur f�r den Bibliothekar
//	 */
//	
//	//constructor
//	public UserTable (){
//		
//	}
//	
//	//Tabellenaufbau
//	
//	
//	
////	public static void main(String[] args) {
////		String[][] rowData = {
////			    { "Japan", "245" }, { "USA", "240" }, { "Italien", "220" },
////			    { "Spanien", "217" }, {"T�rkei", "215"} ,{ "England", "214" },
////			    { "Frankreich", "190" }, {"Griechenland", "185" },
////			    { "Deutschland", "180" }, {"Portugal", "170" }
////			  	};
////			    String[] columnNames = {
////			        "Land", "Durchschnittliche Sehdauer pro Tag in Minuten"
////			    };
////			  JFrame f = new JFrame();
////			  f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
////			  JTable table = new JTable( rowData, columnNames );
////			  f.add( new JScrollPane(table) );
////			  f.pack();
////			  f.setVisible( true );
////	}
//
//	
//}
//
//
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.*;
import model.User;
import controller.UserHandler;

import javax.swing.event.*;
import javax.swing.table.TableModel;

/**
 * 
 * @author weisseth
 * @author Sandra Lang
 *
 */
public class UserTable extends JPanel {

//Formatierungs-Variablen
	private static final long serialVersionUID = 1L;
	
	private static final int SUBPANEL_NORTH_INDEX = 0;
	private static final int SUBPANEL_EAST_INDEX = 1;
	private static final int SUBPANEL_SOUTH_INDEX = 2;
	private static final int SUBPANEL_WEST_INDEX = 3;
	
	private static final int SUBPANEL_VERTICAL_WIDTH = 40;
	private static final int SUBPANEL_VERTICAL_HEIGHT = 600;
	private static final int SUBPANEL_HORIZONTAL_WIDTH = 600;
	private static final int SUBPANEL_HORIZONTAL_HEIGHT = 40;
	private static final int SUBPANEL_SEPERATOR_WIDTH = 20;
	private static final int SUBPANEL_SEPERATOR_HEIGHT = 600;
	
	private static final int COL_Role = 0;
	private static final int COL_Loginname = 1;
	private static final int COL_Firstname = 2;
	private static final int COL_Lastname = 3;
	private static final int COL_E_Mail = 4;
	private static final int COL_Password = 5;

	private JTable userTable;
	private UserTableModelListener userTableModelListener;
	private UserTableModel userTableModel;
	private JScrollPane scrollPane;
	private JPanel scrollPanePanel;
	private JPanel[] scrollPaneBorderPanels;
	
	//Buttons
	private JButton buttonDelete, buttonNew;
	private int mode;

	//constructor
	public UserTable(int mode) {
		
		this.mode = User.ROLE_LIBRARIAN;
		
		userTableModel = new UserTableModel();
		userTableModelListener = new UserTableModelListener();
		userTableModel.addTableModelListener(userTableModelListener);
		
		ListSelectionModel listSelectionModel = new DefaultListSelectionModel();
		listSelectionModel.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
		listSelectionModel.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				
//				//Buttons enablen / diseblen
//				if(buttonLease != null){
//					
//					int mediaID = IDs[userTable.getSelectedRow()];
//					
//					StockLogic stockLogic = StockLogic.getInstance();
//					boolean showButtonLease = stockLogic.isReservable(mediaID);
//					buttonLease.setEnabled(showButtonLease);
//					
//					if(buttonReturn != null){
//						buttonReturn.setEnabled(!showButtonLease);
//					}
//				}	
			}
		});
		
		//Tabelle
		userTable = new JTable(userTableModel);//, JTable.DefaultTableColumnModel(), listSelectionModel);
//		stockTable.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
		userTable.setSelectionModel(listSelectionModel);
//		stockTable.getse
		
//		stockTable.setFillsViewportHeight(true);
		scrollPane = new JScrollPane(userTable);
		
		scrollPanePanel = new JPanel();
		scrollPanePanel.setLayout(new BorderLayout());
		scrollPanePanel.add(scrollPane, BorderLayout.CENTER);
		
		this.addBorderPanels();
		this.setLayout(new GridLayout(1,1));
		this.add(scrollPanePanel);
	}
	
	private void addBorderPanels(){
		//Array-Init
		scrollPaneBorderPanels = new JPanel[4];
		//All
		for(int i = 0; i < scrollPaneBorderPanels.length; i++){
			//Init
			scrollPaneBorderPanels[i] = new JPanel();
			//Color
			scrollPaneBorderPanels[i].setBackground(Color.WHITE);
		}
		//Size
		scrollPaneBorderPanels[SUBPANEL_NORTH_INDEX].setPreferredSize(new Dimension(SUBPANEL_HORIZONTAL_WIDTH, SUBPANEL_HORIZONTAL_HEIGHT));
		scrollPaneBorderPanels[SUBPANEL_EAST_INDEX].setPreferredSize(new Dimension(200, SUBPANEL_VERTICAL_HEIGHT));
		scrollPaneBorderPanels[SUBPANEL_SOUTH_INDEX].setPreferredSize(new Dimension(SUBPANEL_HORIZONTAL_WIDTH, SUBPANEL_HORIZONTAL_HEIGHT));
		scrollPaneBorderPanels[SUBPANEL_WEST_INDEX].setPreferredSize(new Dimension(40, SUBPANEL_VERTICAL_HEIGHT));
		
		//Inner-Layout
		scrollPaneBorderPanels[SUBPANEL_EAST_INDEX].setLayout(new BorderLayout());
		
		JPanel middleBorderPanel = new JPanel();
		JPanel eastBorderPanel = new JPanel();
		JPanel centerPanel = new JPanel();
		
		middleBorderPanel.setBackground(Color.WHITE);
		eastBorderPanel.setBackground(Color.WHITE);
		centerPanel.setBackground(Color.WHITE);

		//Buttons initialisiert
			//UserLoeschen
			buttonDelete = getButtonDelete();
			centerPanel.add(buttonDelete);
			
			//UserErstellen
			buttonNew = getButtonNew();
			centerPanel.add(buttonNew);

		
		//Border fuer Buttons	
		middleBorderPanel.setPreferredSize(new Dimension(SUBPANEL_SEPERATOR_WIDTH, SUBPANEL_SEPERATOR_HEIGHT));
		eastBorderPanel.setPreferredSize(new Dimension(SUBPANEL_VERTICAL_WIDTH, SUBPANEL_VERTICAL_HEIGHT));
		scrollPaneBorderPanels[SUBPANEL_EAST_INDEX].add(middleBorderPanel, BorderLayout.WEST);
		scrollPaneBorderPanels[SUBPANEL_EAST_INDEX].add(eastBorderPanel, BorderLayout.EAST);
		scrollPaneBorderPanels[SUBPANEL_EAST_INDEX].add(centerPanel, BorderLayout.CENTER);
		
		//Layout
		scrollPanePanel.add(scrollPaneBorderPanels[SUBPANEL_NORTH_INDEX], BorderLayout.NORTH);
		scrollPanePanel.add(scrollPaneBorderPanels[SUBPANEL_EAST_INDEX], BorderLayout.EAST);
		scrollPanePanel.add(scrollPaneBorderPanels[SUBPANEL_SOUTH_INDEX], BorderLayout.SOUTH);
		scrollPanePanel.add(scrollPaneBorderPanels[SUBPANEL_WEST_INDEX], BorderLayout.WEST);
	}
	
	
	/**
	 * Button-Methoden
	 * @return
	 */
	// Methode von NewButton
	private JButton getButtonNew() {
		JButton buttonNew = new JButton("Neu erstellen");
		buttonNew.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				//erstelle eine neue Zeile 
				
			userTableModel.addNewRow();
			}
		});
		buttonNew.setPreferredSize(new Dimension(126,30));
		buttonNew.setMargin(new Insets(0,0,0,0));
		return buttonNew;		
	}
	
			
	//Methode fuer den DeleteButton
	private JButton getButtonDelete(){
		JButton buttonDelete = new JButton("Entfernen");
		buttonDelete.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(userTable.getSelectedRowCount()!=0){
					//zeile markiert, dann hohle massage wollen sie loeschen?; gebe die information an Handler weiter
					Object[] options = {"Ja","Nein"};
					int optionDialog = JOptionPane.showOptionDialog(null, "Wollen Sie diesen Benutzer wirklich entfernen?", "Achtung", 
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
					if(optionDialog == JOptionPane.YES_OPTION){
					int row = getSelectedIndex();
					userTableModel.deleteRow(row);
					}
				}
			}
		});
		
		buttonDelete.setPreferredSize(new Dimension(126,30));
		buttonDelete.setMargin(new Insets(0,0,0,0));
		buttonDelete.setEnabled(true);
		return buttonDelete;
	}
	
	// Index der gewaehlten Zeile
	private int getSelectedIndex(){
		int row = userTable.getSelectedRow();
		if(row != -1){
			row = userTable.convertRowIndexToModel(row);
		}
		return row;
	}
	
	/**
	 * Innere Klasse UserTableModel
	 * @author weisseth, Sandra Lang
	 *
	 */
	@SuppressWarnings("serial")
	private class UserTableModel extends AbstractTableModel{

		private Object[][] data;
		private String[] columnNames = { 
				"Rolle", 		
				"Loginname", 		
				"Vorname", 
				"Nachname", 
				"E-Mail", 
				"Password", 	
		};

		//constructor UserTable
		private UserTableModel() {
			
			//Init Stock
			UserHandler userHandler = UserHandler.getInstance();
			User[] users = userHandler.getAllUsers();
			
			//Init Data
			int rows = users.length;
			int columns = columnNames.length;
			data = new Object[rows][columns];
			
			//Init Data-Values
			for (int row = 0; row < users.length; row++){
				//IDs[row] = users[row].getID();
				data[row][COL_Role] = users[row].getRole();
				data[row][COL_Loginname] = users[row].getLoginName();
				data[row][COL_Firstname] = users[row].getFirstName();
				data[row][COL_Lastname] = users[row].getLastName();
				data[row][COL_E_Mail] = users[row].getEmail();
				data[row][COL_Password]= users[row].getPassword();
			}
		}

		// Ueberschriebene Methoden
		@Override
		public Class<? extends Object> getColumnClass(int c) {
//			return String.class;
			return getValueAt(0, c).getClass();
		}

		@Override
		public String getColumnName(int colIndex) {
			return columnNames[colIndex];
		}

		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		@Override
		public int getRowCount() {
			return data.length;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			return data[rowIndex][columnIndex];
		}

		@Override
		public boolean isCellEditable(int row, int col) {
			if (mode == User.ROLE_LIBRARIAN && col <= 7) {
				return true;
			}
			return false;
		}
		
		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			data[rowIndex][columnIndex] = aValue;
			fireTableCellUpdated(rowIndex, columnIndex);
	    }
		
		/**
		 * Loescht eine Zeile aus der Tabelle und aus dem UserHandler
		 * @param row : Integer - Die tatsaechliche Zeile des Datensatzes (unabhaengig der Table-Darstellung)
		 */
		private void deleteRow(int row){	
			if(data.length > 1){
				String loginName = String.valueOf(userTable.getValueAt(row, COL_Loginname));
				UserHandler.getInstance().deleteUser(loginName);
				
				String[] ids = new String[data.length-1];
				for(int i = 0; i < ids.length; i++){
					if(i < row){
						ids[i] = String.valueOf(userTable.getValueAt(i, COL_Loginname));
					}
					if(i >= row){
						ids[i] = String.valueOf(userTable.getValueAt(i+1, COL_Loginname));
					}
					System.out.println(ids[i]);
				}
				data = new Object[data.length-1][6];
				
				for(int i = 0; i < data.length; i++){
					setValueAt(ids[i], i, COL_Loginname);
					updateRow(i);
				}
				fireTableDataChanged();
			}	
		}
		
		// neue Zeile hinzufuegen
		private void addNewRow(){
			
			if(data.length > 1){
				String newName = "Bitte Usernamen eingeben";
				UserHandler.getInstance().getUser(newName);
				
				String[] ids = new String[data.length+1];
				for(int i = 0; i < data.length; i++){
					
						ids[i] = String.valueOf(userTable.getValueAt(i, COL_Loginname));
	
					System.out.println(ids[i]);
				}
				ids[ids.length-1]= newName;
				data = new Object[data.length+1][6];
				
				for(int i = 0; i < data.length; i++){
					setValueAt(ids[i], i, COL_Loginname);
					updateRow(i);
				}
				fireTableDataChanged();
			}	
		}
		
		/**
		 * Setzt die Table-Werte einer Zeile auf die des entsrechenden Users. Der loginName des Users muss hierzu gesetzt sein.
		 * @param row : Integer
		 */
		public void updateRow(int row){
			String loginName = String.valueOf(userTable.getModel().getValueAt(row, COL_Loginname));
			User user = UserHandler.getInstance().getUser(loginName);
			data[row][COL_Role] = user.getRole();
			data[row][COL_Loginname] = user.getLoginName();
			data[row][COL_Firstname] = user.getFirstName();
			data[row][COL_Lastname] = user.getLastName();
			data[row][COL_E_Mail] = user.getEmail();
			data[row][COL_Password]= user.getPassword();
		}
	}
	
	//Listener fuer UserTable
	private class UserTableModelListener implements TableModelListener {

	    public void tableChanged(TableModelEvent e) {
	        int row = e.getFirstRow();
	        int column = e.getColumn();
	        TableModel model = (TableModel)e.getSource();
	        Object data = model.getValueAt(row, column);
	        	        
			UserHandler userHandler = UserHandler.getInstance();
			User users = userHandler.getUser(String.valueOf(userTable.getValueAt(getSelectedIndex(), COL_Loginname)));
			switch(column){
				case COL_Role:
					users.setRole(1);
					break;
				case COL_Loginname:
					users.setLoginName(String.valueOf(data));
					break;
				case COL_Firstname:
					users.setFirstName(String.valueOf(data));
					break;
				case COL_Lastname:
					users.setLastName(String.valueOf(data));
					break;
				case COL_E_Mail:
					users.setEmail(String.valueOf(data));
					break;
				case COL_Password:
					users.setPassword(String.valueOf(data));
					break;
			}
	    }
	}
}
