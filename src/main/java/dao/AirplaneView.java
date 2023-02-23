package dao;

import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AirplaneView extends JFrame{
	
	JTable jtable;
	
	public AirplaneView() {
		init();
	}
	private void init() {
		jtable = new JTable();
		
		jtable.setModel(
				new DefaultTableModel( getDataList() , getColumnList() ) {				
					// 기본 option 설정 - 각 cell 에 대한 편집가능여부 :isCellEditable
					@Override
					public boolean isCellEditable(int row, int column) {
						return false;   // 모든 cell 편집불가능
				}
			}				
		);
		
	}

	//dao에 있는 getAirSchedule 
	private Vector<Vector> getDataList(){
	AirplaneDAO adao = new AirplaneDAO();
	Vector<Vector> list = adao.getAirSchedule();
	return list;
	}
	
	private Vector<String> getColumnList(){
		Vector<String> cols = new Vector<>();
		cols.add("비행기번호");
		cols.add("출발지");
		cols.add("출발 시간");
		cols.add("도착지");
		cols.add("도착시간");
		return cols;
	}
}
