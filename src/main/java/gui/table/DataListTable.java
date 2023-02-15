package gui.table;

import java.lang.reflect.Field;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class DataListTable extends ListTable {
	private DataTableModel tableModel;
	
	public DataListTable(List<?> dataList) {
		tableModel = new DataTableModel(dataList);
		setModel(tableModel);
	}
	
	private class DataTableModel extends AbstractTableModel {
		private List<?> dataList;
		private Field[] fields;

		public DataTableModel(List<?> dataList) {
			this.dataList = dataList;
			fields = dataList.get(0).getClass().getDeclaredFields();
			for(Field field : fields) {
				field.setAccessible(true);
			}
		}

		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}

		@Override
		public int getRowCount() {
			return dataList.size();
		}

		@Override
		public int getColumnCount() {
			return fields.length;
		}

		@Override
		public String getColumnName(int col) {
			return fields[col].getName();
		}

		@Override
		public Object getValueAt(int row, int col) {
			try {
				return fields[col].get(dataList.get(row));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
			return "";
		}
	}
}