package gui.table;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class StringTable extends ListTable{
	private List<List<String>> stringList;
	private List<String> columnNames;
	
	public StringTable() {}
	
	public StringTable(String...columns) {
		setColumns(columns);
	}
	
	public StringTable(List<List<String>> stringList, String... columns) {
		setList(stringList);
		setColumns(columns);
	}
	
	public void setStringModel() {
		if(tableModel == null && columnNames != null && !columnNames.isEmpty()
				&& stringList != null && !stringList.isEmpty())
			setModel(tableModel = new StringListTableModel());
	}
	
	public void setColumns(String... columns) {
		if(columns != null && columns.length != 0) {
			columnNames = Arrays.asList(columns);
		} else if(!stringList.isEmpty()) {
			columnNames = stringList.get(0); 
			stringList.remove(0);
		}
		setStringModel();
	}
	
	public void setList(List<List<String>> stringList) {
		this.stringList = stringList;
		setStringModel();
	}
	
	public AbstractTableModel getModel() {
		return tableModel;
	}
	
	
	public List<List<String>> getList(){
		return stringList;
	}
	
	public void update() {
		tableModel.fireTableDataChanged();
	}
	
	private class StringListTableModel extends AbstractTableModel {
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}

		@Override
		public int getRowCount() {
			if(stringList == null) return 0;
			return stringList.size();
		}

		@Override
		public int getColumnCount() {
			if(stringList == null) return 1;
			return stringList.get(0).size();
		}

		@Override
		public String getColumnName(int col) {
			return columnNames.get(col);
		}

		@Override
		public Object getValueAt(int row, int col) {
			if(stringList == null) return "";
			else return stringList.get(row).get(col);
		}
	}
}