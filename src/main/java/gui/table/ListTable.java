package gui.table;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public abstract class ListTable extends JTable{
	protected AbstractTableModel tableModel;

	{ setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); }
	
	public void update() {
		tableModel.fireTableDataChanged();
	}
	
	public void setColumnsSize(int... sizes) {
		for (int i = 0; i < Math.min(getColumnCount(), sizes.length); i++) {
			getColumn(getColumnName(i)).setPreferredWidth(sizes[i]);
		}
	}
}
