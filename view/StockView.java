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
import model.Medium;
import model.User;
import controller.*;
import javax.swing.event.*;
import javax.swing.table.TableModel;

/**
 * 
 * @author weisseth
 *
 */
public class StockView extends JPanel {

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
	
	private static final int COL_TITLE = 0;
	private static final int COL_AUTHOR = 1;
	private static final int COL_PUBLISHER = 2;
	private static final int COL_EDITION = 3;
	private static final int COL_ISBN = 4;
	private static final int COL_COPIES = 5;
	private static final int COL_ONSTOCK = 6;
	private static final int COL_KEYWORDS = 7;
	private static final int AMOUNT_COLUMNS_VISIBLE = 8;
	private static final int AMOUNT_COLUMNS_INVISIBE = 1;
	private static final int COL_ID = 8;

	private JTable stockTable;
	private StockTableModelListener stockTableModelListener;
	private StockTableModel stockTableModel;
	private JScrollPane scrollPane;
	private JPanel scrollPanePanel;
	private JPanel[] scrollPaneBorderPanels;
	
	private JButton buttonLease, buttonReturn, buttonExtend, buttonDelete, buttonNew;

	//TODO media
	public StockView(Medium[] media){
		this();
	}
	
	//TODO media
	public StockView(int i){
		this();
	}

	public StockView() {
		
		stockTableModel = new StockTableModel();
		stockTableModelListener = new StockTableModelListener();
		stockTableModel.addTableModelListener(stockTableModelListener);
		
		ListSelectionModel listSelectionModel = new DefaultListSelectionModel();
		listSelectionModel.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
		listSelectionModel.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				/*
				 * Wird mit Zeilenselektion ausgeloest
				 */
				int row = stockTable.getSelectedRow();
				if(row >= 0){ //Nicht Header
					update(row);
				}
				
			}
			
		});
		
		stockTable = new JTable(stockTableModel);
		stockTable.setAutoCreateRowSorter(true);
		stockTable.setSelectionModel(listSelectionModel);
		scrollPane = new JScrollPane(stockTable);
		
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
		int role = MyAccount.getLoggedInUser().getRole();

		if(role == User.ROLE_STUDENT || role == User.ROLE_LECTURER){
			
			//Ausleihen
			buttonLease = getButtonLease();
			centerPanel.add(buttonLease);

			//Zurueckgeben
			buttonReturn = getButtonReturn();
			centerPanel.add(buttonReturn);
			
		}
		
		if(role == User.ROLE_LECTURER){
			
			//Verlaengern
			buttonExtend = getButtonExtend();
			centerPanel.add(buttonExtend);
			
		}
		
		if(role == User.ROLE_LIBRARIAN){
			
//			Neu hinzufuegen
			buttonNew = getButtonNew();
			centerPanel.add(buttonNew);
			
//			Loeschen
			buttonDelete = getButtonDelete();
			centerPanel.add(buttonDelete);

		}
		
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
	 * Gibt den initialisierten mit ActionListener ausgestatteten Rueckgabe-Button zurueck.
	 * @return
	 */
	private JButton getButtonLease(){
		JButton buttonLease = new JButton("Ausleihen");
		buttonLease.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int selectedIndex = stockTable.getSelectedRow();
				StockLogic stockLogic = StockLogic.getInstance();
				User user = MyAccount.getLoggedInUser();
				int mediaID = Integer.parseInt(String.valueOf(stockTable.getModel().getValueAt(stockTable.getSelectedRow(), COL_ID)));
				boolean success = stockLogic.leaseMedium(user, mediaID);
				if(success){
					update(selectedIndex);
				}
				
			}
			
		});
		buttonLease.setPreferredSize(new Dimension(126,30));
		buttonLease.setMargin(new Insets(0,0,0,0));
		buttonLease.setEnabled(false);
		return buttonLease;
	}
	
	private JButton getButtonNew(){
		JButton buttonNew = new JButton("Neu hinzufügen");
		buttonNew.setPreferredSize(new Dimension(126,30));
		buttonNew.setMargin(new Insets(0,0,0,0));
		
		buttonNew.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				stockTableModel.addRow();
				
			}
			
		});

		return buttonNew;
	}
	
	private JButton getButtonDelete(){
		JButton buttonDelete = new JButton("Löschen");
		buttonDelete.setPreferredSize(new Dimension(126,30));
		buttonDelete.setMargin(new Insets(0,0,0,0));
		buttonDelete.setEnabled(false);
		
		buttonDelete.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int id = Integer.parseInt(String.valueOf(stockTable.getModel().getValueAt(stockTable.getSelectedRow(), COL_ID)));
				MediaHandler.getInstance().deleteMedium(id);
				
				stockTableModel.deleteRow(stockTable.getSelectedRow());
				StockView.this.buttonDelete.setEnabled(false);
			}
			
		});

		return buttonDelete;
	}
	
	private JButton getButtonReturn(){
		JButton buttonReturn = new JButton("Zurückgeben");
		buttonReturn.setPreferredSize(new Dimension(126,30));
		buttonReturn.setMargin(new Insets(0,0,0,0));
		buttonReturn.setEnabled(false);
		
		buttonReturn.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int selectedIndex = stockTable.getSelectedRow();
				StockLogic stockLogic = StockLogic.getInstance();
				
				User user = MyAccount.getLoggedInUser();
				int mediaID = Integer.parseInt(String.valueOf(stockTable.getModel().getValueAt(stockTable.getSelectedRow(), COL_ID)));
				if(stockLogic.returnMedium(user, mediaID)){
					update(selectedIndex);	
				}
				
			}
			
		});

		return buttonReturn;
	}
	
	private JButton getButtonExtend(){
		JButton buttonExtend = new JButton("Verlängern");
		buttonExtend.setPreferredSize(new Dimension(126,30));
		buttonExtend.setMargin(new Insets(0,0,0,0));
		buttonExtend.setEnabled(false);
		buttonExtend.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
					
				int selectedIndex = stockTable.getSelectedRow();
				StockLogic stockLogic = StockLogic.getInstance();
				
				User user = MyAccount.getLoggedInUser();
				int mediaID = Integer.parseInt(String.valueOf(stockTable.getModel().getValueAt(stockTable.getSelectedRow(), COL_ID)));
				
				if(stockLogic.extendMedium(user, mediaID)){
					update(selectedIndex);
				}
				
			}
			
		});
		return buttonExtend;
	}
	
	private void update(int selectedIndex){
		updateButtons();
		stockTableModel.updateRow(selectedIndex);
		stockTableModel.fireTableRowsUpdated(selectedIndex, selectedIndex);
	}
	
	private void updateButtons(){
		
		User user = MyAccount.getLoggedInUser();
		
		int mediaID = Integer.parseInt(String.valueOf(stockTable.getModel().getValueAt(stockTable.getSelectedRow(), COL_ID)));
		StockLogic stockLogic = StockLogic.getInstance();
		
		if(buttonLease != null){
			buttonLease.setEnabled(stockLogic.isLeaseable(user, mediaID));
		}
		if(buttonReturn != null){
			buttonReturn.setEnabled(stockLogic.isReturnable(user, mediaID));
		}
		if(buttonExtend != null){
			buttonExtend.setEnabled(stockLogic.isExtendable(user, mediaID));
		}
		if(buttonDelete != null){
			if(stockTable.getRowCount() > 1){
				buttonDelete.setEnabled(true);
			}else{
				buttonDelete.setEnabled(false);
			}
		}
		
	}
	
	/**
	 * Innere Klasse StockTableModel
	 * @author weisseth
	 *
	 */
	@SuppressWarnings("serial")
	private class StockTableModel extends AbstractTableModel{

		private Object[][] data;
		private String[] columnNames = { 
				"Titel", 		//0
				"Autor", 		//1
				"Verlag", 
				"Auflage", 
				"ISBN", 
				"Exemplare", 	//5
				"Verfügbar", 
				"Stichworte",
				"ID"//7
		};

		private StockTableModel() {
			
			//Init Stock
			MediaHandler mediaHandler = MediaHandler.getInstance();
			Medium[] media = mediaHandler.getAllMedia();
			

			
			//Init Data
			int rows = media.length;
			int columns = columnNames.length;
			
			data = new Object[rows][columns+AMOUNT_COLUMNS_INVISIBE];
			
			//Init Data-Values
			for (int row = 0; row < media.length; row++){
				initRow(row, media[row]);
			}

		}
		
		private void addRow(){
			
//			int[] oldIDs = IDs;
//			if(oldIDs != null){
//				IDs = new int[oldIDs.length + 1];
//				for(int i = 0; i < oldIDs.length; i++){
//					IDs[i+1] = oldIDs[i];
//				}
//			}else{
//				IDs = new int[1];
//			}
//			
//			IDs[0] = MediaHandler.getInstance().getNewID();
			int id = MediaHandler.getInstance().getNewID();
//			int id = Integer.parseInt(String.valueOf(stockTable.getModel().getValueAt(stockTable.getSelectedRow(), COL_ID)));
			Medium newMedium = MediaHandler.getInstance().getMedium(id);
			
			int newRow = data.length;
			int[] ids = new int[data.length];
			for(int i = 0; i < newRow; i++){
				ids[i] = Integer.parseInt(String.valueOf(getValueAt(i, COL_ID)));
			}
			
			if(data != null){
				data = new Object[data.length+1][columnNames.length];
				//Init Data-Values
			}else{
				data = new Object[1][columnNames.length];
			}
			//Init Data-Values
//			Medium[] media = MediaHandler.getInstance().getAllMedia();//TODO modus
			for (int row = 0; row < ids.length; row++){
//				initRow(row, media[row]);
				initRow(row, MediaHandler.getInstance().getMedium(ids[row]));
				updateRow(row);
			}
			initRow(newRow, newMedium);
			
//			for(int i = 0; i < data.length; i++){
//				updateRow(i);
//			}
			
			fireTableDataChanged();
		}
		
		private void deleteRow(int row){
			
			if(data.length > 1){
				int id = Integer.parseInt(String.valueOf(stockTable.getValueAt(row, COL_ID)));
				MediaHandler.getInstance().deleteMedium(id);
				
				int[] ids = new int[data.length-1];
				for(int i = 0; i < ids.length; i++){
					if(i < row){
						ids[i] = Integer.parseInt(String.valueOf(stockTable.getValueAt(i, COL_ID)));
					}
					if(i >= row){
						ids[i] = Integer.parseInt(String.valueOf(stockTable.getValueAt(i+1, COL_ID)));
					}
					System.out.println(ids[i]);
				}
				data = new Object[data.length-1][AMOUNT_COLUMNS_VISIBLE+AMOUNT_COLUMNS_INVISIBE];
				
				for(int i = 0; i < data.length; i++){
					setValueAt(ids[i], i, COL_ID);
					updateRow(i);
				}
				
				fireTableDataChanged();
			}			
			
		}
		
		public void updateRow(int row){
			int id = Integer.parseInt(String.valueOf(stockTable.getModel().getValueAt(row, COL_ID)));
			Medium media = MediaHandler.getInstance().getMedium(id);
			data[row][COL_TITLE] = media.getTitle();
			data[row][COL_AUTHOR] = media.getAuthor();
			data[row][COL_PUBLISHER] = media.getPublisher();
			data[row][COL_EDITION] = media.getEdition();
			data[row][COL_ISBN] = media.getIsbn();
			data[row][COL_COPIES] = media.getCopies();
			data[row][COL_ONSTOCK] = media.getOnStock();
			data[row][COL_KEYWORDS] = media.getKeywords();
			data[row][COL_ID] = media.getID();
		}
		
		public void initRow(int row, Medium medium){
			data[row][COL_TITLE] = medium.getTitle();
			data[row][COL_AUTHOR] = medium.getAuthor();
			data[row][COL_PUBLISHER] = medium.getPublisher();
			data[row][COL_EDITION] = medium.getEdition();
			data[row][COL_ISBN] = medium.getIsbn();
			data[row][COL_COPIES] = medium.getCopies();
			data[row][COL_ONSTOCK] = medium.getOnStock();
			data[row][COL_KEYWORDS] = medium.getKeywords();
			data[row][COL_ID] = medium.getID();
		}

		@Override
		public Class<? extends Object> getColumnClass(int c) {
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
			if(data != null){
				return data.length;//TODO rowsVisible;
			}else{
				return 0;
			}
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
//			if(rowIndex < 0 || columnIndex < 0){
//				return 0;
//			}
			if(data != null){
				if(data[rowIndex][columnIndex].equals("null")){
					return "";
				}
				return data[rowIndex][columnIndex];
			}
			return null;
		}

		@Override
		public boolean isCellEditable(int row, int col) {
			if (MyAccount.getLoggedInUser().getRole() == User.ROLE_LIBRARIAN && col <= 7) {
				return true;
			}
			return false;
		}
		
		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			data[rowIndex][columnIndex] = aValue;
			fireTableCellUpdated(rowIndex, columnIndex);
	    }

	}
	
	private class StockTableModelListener implements TableModelListener {

		private void updateModel(Medium medium, int column, Object data){
			switch(column){
			case COL_TITLE:
				medium.setTitle(String.valueOf(data));
				break;
			case COL_AUTHOR:
				medium.setAuthor(String.valueOf(data));
				break;
			case COL_PUBLISHER:
				medium.setPublisher(String.valueOf(data));
				break;
			case COL_EDITION:
				try{
					medium.setEdition(Integer.parseInt(String.valueOf(data)));
				}catch(Exception e){
					medium.setEdition(0);
				}
				break;
			case COL_ISBN:
				medium.setIsbn(String.valueOf(data));
				break;
			case COL_COPIES:
				try{
					medium.setCopies(Integer.parseInt(String.valueOf(data)));
				}catch(Exception e){
					medium.setCopies(0);
				}
				break;
			case COL_ONSTOCK:
				try{
					medium.setOnStock(Integer.parseInt(String.valueOf(data)));
				}catch(Exception e){
					medium.setOnStock(0);
				}
				break;
			case COL_KEYWORDS:
				medium.setKeywords(String.valueOf(data));
				break;
			case COL_ID:
				medium.setID(Integer.parseInt(String.valueOf(data)));
				break;
		}
		}
		
	    public void tableChanged(TableModelEvent e) {
	    	
	        int row = e.getFirstRow();
	        int column = e.getColumn();
	        
	        TableModel model = (TableModel)e.getSource();
	        int id = Integer.parseInt(String.valueOf(stockTable.getModel().getValueAt(row, COL_ID)));
			Medium medium = MediaHandler.getInstance().getMedium(id);
			
	        if(column == TableModelEvent.ALL_COLUMNS){
	        	for(int col = 0; col < AMOUNT_COLUMNS_VISIBLE; col++){
	        		Object data = model.getValueAt(row, col);
	        		updateModel(medium, col, data);
	        	}
	        }else{
	        	Object data = model.getValueAt(row, column);
	        	updateModel(medium, column, data);
	        }
	    }
	}

}
