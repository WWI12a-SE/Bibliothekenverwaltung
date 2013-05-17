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
	private JScrollPane scrollPane;
	private JPanel scrollPanePanel;
	private JPanel[] scrollPaneBorderPanels;
	private int mode;


	public StockView(int mode) {
		
		this.mode = mode;
		
		stockTable = new JTable(new StockTableModel());
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
		scrollPaneBorderPanels = new JPanel[4];
		for(int i = 0; i < scrollPaneBorderPanels.length; i++){
			//Init
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
	private class StockTableModel extends AbstractTableModel {

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
			int columns = Medium.AMOUNT_COLUMNS;
			if(StockView.this.mode == User.ROLE_LIBRARIAN){
				columns++;
			}
			data = new Object[rows][columns];
			
			//Init Data-Values
			for (int row = 0; row < media.length; row++){
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
			return String.class;
//			return getValueAt(0, c).getClass();
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

	}

}
