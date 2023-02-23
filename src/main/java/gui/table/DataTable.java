package gui.table;

import java.lang.reflect.Field;
import java.util.List;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class DataTable extends ListTable {
	private List<?> dataList;
	
	public DataTable(List<?> dataList) {
		this.dataList = dataList;
		tableModel = new DataTableModel();
		setModel(tableModel);
	}
	
	private class DataTableModel extends AbstractTableModel {
		private Field[] fields;

		public DataTableModel() {
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