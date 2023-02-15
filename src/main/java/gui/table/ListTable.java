package gui.table;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public abstract class ListTable extends JTable{
	protected AbstractTableModel tableModel;
	
	public void update() {
		tableModel.fireTableDataChanged();
	}
}
