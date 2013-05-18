package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.table.*;
import model.Medium;
import model.User;
import controller.MediaHandler;

import javax.swing.event.*;
import javax.swing.table.TableModel;

/**
 * 
 * @author weisseth
 *
 */
public class StockView extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int SUBPANEL_EAST_WIDTH = 40;
	private static final int SUBPANEL_EAST_HEIGHT = 600;
	private static final int SUBPANEL_NORTH_WIDTH = 600;
	private static final int SUBPANEL_NORTH_HEIGHT = 40;

	private JTable stockTable;
	private StockTableModelListener stockTableModelListener;
	private StockTableModel stockTableModel;
	private JScrollPane scrollPane;
	private JPanel scrollPanePanel;
	private JPanel[] scrollPaneBorderPanels;
	private int mode;
	private int[] IDs;


	public StockView(int mode) {
		
		this.mode = mode;
		
		stockTableModel = new StockTableModel();
		stockTableModelListener = new StockTableModelListener();
		stockTableModel.addTableModelListener(stockTableModelListener);
		stockTable = new JTable(stockTableModel);
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
		//Init
		scrollPaneBorderPanels = new JPanel[4];
		for(int i = 0; i < scrollPaneBorderPanels.length; i++){
			scrollPaneBorderPanels[i] = new JPanel();
			//Size
			if(i % 2 == 0){
				scrollPaneBorderPanels[i].setPreferredSize(new Dimension(SUBPANEL_NORTH_WIDTH,SUBPANEL_NORTH_HEIGHT));
			}else{
				scrollPaneBorderPanels[i].setPreferredSize(new Dimension(SUBPANEL_EAST_WIDTH,SUBPANEL_EAST_HEIGHT));
			}
			//Color
			scrollPaneBorderPanels[i].setBackground(Color.WHITE);
		}
		//Layout
		scrollPanePanel.add(scrollPaneBorderPanels[0], BorderLayout.NORTH);
		scrollPanePanel.add(scrollPaneBorderPanels[1], BorderLayout.EAST);
		scrollPanePanel.add(scrollPaneBorderPanels[2], BorderLayout.SOUTH);
		scrollPanePanel.add(scrollPaneBorderPanels[3], BorderLayout.WEST);
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
				"Titel", 
				"Autor", 
				"Verlag", 
				"Auflage", 
				"ISBN", 
				"Exemplare", 
				"Verfügbar", 
				"Stichworte", 
				"Aktion"
		};

		private StockTableModel() {
			
			//Init Stock
			MediaHandler mediaHandler = MediaHandler.getInstance();
			Medium[] media = mediaHandler.getAllMedia();
			
			//Init Data
			int rows = media.length;
			int columns = 8;
			if(StockView.this.mode == User.ROLE_LIBRARIAN){
				columns++;
			}
			data = new Object[rows][columns];
			IDs  = new int[rows];
			
			//Init Data-Values
			for (int row = 0; row < media.length; row++){
				IDs[row] = media[row].getID();
				data[row][0] = media[row].getTitle();
				data[row][1] = media[row].getAuthor();
				data[row][2] = media[row].getPublisher();
				data[row][3] = media[row].getEdition();
				data[row][4] = media[row].getIsbn();
				data[row][5] = media[row].getCopies();
				data[row][6] = media[row].getOnStock();
				data[row][7] = media[row].getKeywords();
			}
			if(StockView.this.mode == User.ROLE_LIBRARIAN){
				for (int row = 0; row < media.length; row++){
					data[row][8] = new String("löschen"+row);
				}
			}
		}

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
			if (StockView.this.mode == User.ROLE_LIBRARIAN && col <= 7) {
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

	    public void tableChanged(TableModelEvent e) {
	        int row = e.getFirstRow();
	        int column = e.getColumn();
	        TableModel model = (TableModel)e.getSource();
	        Object data = model.getValueAt(row, column);
	        
	        MediaHandler mediaHandler = MediaHandler.getInstance();
			Medium medium = mediaHandler.getMedium(IDs[row]);
			switch(column){
				case 0:
					medium.setTitle(String.valueOf(data));
					break;
				case 1:
					medium.setAuthor(String.valueOf(data));
					break;
				case 2:
					medium.setPublisher(String.valueOf(data));
					break;
				case 3:
					medium.setEdition(Integer.parseInt(String.valueOf(data)));
					break;
				case 4:
					medium.setIsbn(String.valueOf(data));
					break;
				case 5:
					medium.setCopies(Integer.parseInt(String.valueOf(data)));
					break;
				case 6:
					medium.setOnStock(Integer.parseInt(String.valueOf(data)));
					break;
				case 7:
					medium.setKeywords(String.valueOf(data));
					break;
			}
	    }
	}

}
