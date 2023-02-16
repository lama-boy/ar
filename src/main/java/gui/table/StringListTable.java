package gui.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class StringListTable extends ListTable{
	private List<List<String>> stringList;
	
	public StringListTable(List<List<String>> stringList) {
		this.stringList = stringList;
		setModel(tableModel = new StringListTableModel());
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
			return stringList.size()-1;
		}

		@Override
		public int getColumnCount() {
			return stringList.get(0).size();
		}

		@Override
		public String getColumnName(int col) {
			return stringList.get(0).get(col);
		}

		@Override
		public Object getValueAt(int row, int col) {
			return stringList.get(row+1).get(col);
		}
	}
}