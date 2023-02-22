package entity;

import javax.swing.JCheckBox;

public class SeatDTO extends JCheckBox{
	private int Row;
	private int Column;
	private boolean isSelected;
	private int airNum;
	public SeatDTO() {}
	public SeatDTO(int row, int column, boolean isSelected, int airNum) {
		Row = row;
		Column = column;
		this.isSelected = isSelected;
		this.airNum = airNum;
	}
	public int getRow() {
		return Row;
	}
	public void setRow(int row) {
		Row = row;
	}
	public int getColumn() {
		return Column;
	}
	public void setColumn(int column) {
		Column = column;
	}
	public boolean isSelected() {
		return isSelected;
	}
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	public int getairNum() {
		return airNum;
	}
	public void setairNum(int airNum) {
		this.airNum = airNum;
	}
}