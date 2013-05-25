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
import core.CsvHandler;

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
	private static final int AMOUNT_COLUMNS = 8;

	private JTable stockTable;
	private StockTableModelListener stockTableModelListener;
	private StockTableModel stockTableModel;
	private JScrollPane scrollPane;
	private JPanel scrollPanePanel;
	private JPanel[] scrollPaneBorderPanels;
	
	private JButton buttonLease, buttonReturn, buttonExtend, buttonDelete, buttonNew;
	
	private int mode;
	private int[] IDs;

	//TODO media
	public StockView(int mode, Medium[] media){
		this(mode);
	}

	public StockView(int mode) {
		
		this.mode = mode;
		
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
				update(stockTable.getSelectedRow());
				
			}
			
		});
		
		stockTable = new JTable(stockTableModel);//, JTable.DefaultTableColumnModel(), listSelectionModel);
//		stockTable.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
		stockTable.setSelectionModel(listSelectionModel);
		
//		stockTable.setFillsViewportHeight(true);
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

		if(this.mode == User.ROLE_STUDENT || this.mode == User.ROLE_LECTURER){
			
			//Ausleihen
			buttonLease = getButtonLease();
			centerPanel.add(buttonLease);

			//Zurueckgeben
			buttonReturn = getButtonReturn();
			centerPanel.add(buttonReturn);
			
		}
		
		if(this.mode == User.ROLE_LECTURER){
			
			//Verlaengern
			buttonExtend = getButtonExtend();
			centerPanel.add(buttonExtend);
			
		}
		
		if(this.mode == User.ROLE_LIBRARIAN){
			
			//Neu hinzufuegen
//			buttonNew = getButtonNew();
//			centerPanel.add(buttonNew);
			
			//Loeschen
//			buttonDelete = getButtonDelete();
//			centerPanel.add(buttonDelete);

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
				int mediaID = IDs[selectedIndex];
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
		buttonNew.setEnabled(false);
		
		buttonNew.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int selectedIndex = stockTable.getSelectedRow();
				StockLogic stockLogic = StockLogic.getInstance();
				
				User user = MyAccount.getLoggedInUser();
				int mediaID = IDs[selectedIndex];
				if(stockLogic.returnMedium(user, mediaID)){
					update(selectedIndex);	
				}
				
			}
			
		});

		return buttonNew;
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
				int mediaID = IDs[selectedIndex];
				if(stockLogic.returnMedium(user, mediaID)){
					update(selectedIndex);	
				}
				
			}
			
		});

		return buttonReturn;
	}
	
	private JButton getButtonExtend(){
		JButton buttonReturn = new JButton("Verlängern");
		buttonReturn.setPreferredSize(new Dimension(126,30));
		buttonReturn.setMargin(new Insets(0,0,0,0));
		buttonReturn.setEnabled(false);
		buttonReturn.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
					
				int selectedIndex = stockTable.getSelectedRow();
				StockLogic stockLogic = StockLogic.getInstance();
				
				User user = MyAccount.getLoggedInUser();
				int mediaID = IDs[selectedIndex];
				
				if(stockLogic.extendMedium(user, mediaID)){
					update(selectedIndex);
				}
				
			}
			
		});
		return buttonReturn;
	}
	
	private void update(int selectedIndex){
		updateButtons();
		stockTableModel.updateRow(selectedIndex);
		stockTableModel.fireTableRowsUpdated(selectedIndex, selectedIndex);
	}
	
	private void updateButtons(){
		
		User user = MyAccount.getLoggedInUser();
		int mediaID = IDs[stockTable.getSelectedRow()];
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
			buttonDelete.setEnabled(stockLogic.isReturnable(user, mediaID));
		}
		if(buttonNew != null){
			buttonNew.setEnabled(stockLogic.isReturnable(user, mediaID));
		}
		//TODO
//		CsvHandler handler = new CsvHandler("reservations");
//		System.out.println("--CSV-Reservations----------------------");
//		handler.viewMap();
//		System.out.println("------------------------");
		ReservationHandler.getInstance().viewTable();
//		MediaHandler.getInstance().save();
//		ReservationHandler.getInstance().save();
//		UserHandler.getInstance().save();
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
				"Stichworte", 	//7
		};

		private StockTableModel() {
			
			//Init Stock
			MediaHandler mediaHandler = MediaHandler.getInstance();
			Medium[] media = mediaHandler.getAllMedia();
			
			//Init Data
			int rows = media.length;
			int columns = columnNames.length;
			data = new Object[rows][columns];
			IDs  = new int[rows];
			
			//Init Data-Values
			for (int row = 0; row < media.length; row++){
				IDs[row] = media[row].getID();
				updateRow(row);
			}

		}
		
		public void updateRow(int row){
			Medium media = MediaHandler.getInstance().getMedium(IDs[row]);
			data[row][COL_TITLE] = media.getTitle();
			data[row][COL_AUTHOR] = media.getAuthor();
			data[row][COL_PUBLISHER] = media.getPublisher();
			data[row][COL_EDITION] = media.getEdition();
			data[row][COL_ISBN] = media.getIsbn();
			data[row][COL_COPIES] = media.getCopies();
			data[row][COL_ONSTOCK] = media.getOnStock();
			data[row][COL_KEYWORDS] = media.getKeywords();
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

	}
	
	private class StockTableModelListener implements TableModelListener {

		private void updateCol(Medium medium, int column, Object data){
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
				medium.setEdition(Integer.parseInt(String.valueOf(data)));
				break;
			case COL_ISBN:
				medium.setIsbn(String.valueOf(data));
				break;
			case COL_COPIES:
				medium.setCopies(Integer.parseInt(String.valueOf(data)));
				break;
			case COL_ONSTOCK:
				medium.setOnStock(Integer.parseInt(String.valueOf(data)));
				break;
			case COL_KEYWORDS:
				medium.setKeywords(String.valueOf(data));
				break;
		}
		}
		
	    public void tableChanged(TableModelEvent e) {
	    	
	        int row = e.getFirstRow();
	        int column = e.getColumn();
	        
	        TableModel model = (TableModel)e.getSource();
	        
	        MediaHandler mediaHandler = MediaHandler.getInstance();
			Medium medium = mediaHandler.getMedium(IDs[row]);
			
	        if(column == TableModelEvent.ALL_COLUMNS){
	        	for(int col = 0; col < AMOUNT_COLUMNS; col++){
	        		Object data = model.getValueAt(row, col);
	        		updateCol(medium, col, data);
	        	}
	        }else{
	        	Object data = model.getValueAt(row, column);
	        	updateCol(medium, column, data);
	        }
	        
	    }
	}

}
