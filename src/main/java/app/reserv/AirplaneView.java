package app.reserv;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import app.AppView;
import dao.DAO;
import gui.Gui;
import gui.table.ListTable;
import gui.table.StringTable;

public class AirplaneView extends AppView {
	
	JLabel airPlanLbl;
	StringTable jtable = new StringTable();
	JFrame frame;
	JScrollPane sp;
	JButton confirmBtn;
	DefaultTableModel model;
	Reservation reserv;
	
	public AirplaneView(Reservation r) {
		this.reserv = r;
		initRootPanel();
	}

	@Override
	public void initRootPanel() {
		rootPanel.setBackground(new Color(240, 248, 255));
		rootPanel.setBounds(0, 0, 850, 700);
		rootPanel.setLayout(null);
		
		// 페이지 제목
		airPlanLbl = new JLabel("항공 일정");
		airPlanLbl.setBounds(377, 30, 96, 30);
		airPlanLbl.setFont(new Font("맑은 고딕", Font.BOLD, 22));
		rootPanel.add(airPlanLbl);
	
		// 데이터 표
		jtable.setColumns("출발지", "도착지", "출발시간", "도착시간", "시작날짜", "종료날짜", "항공편명");
		jtable.setList(DAO.sql.select("select DEPPLACE, ARRPLACE, DEPTIME, ARRTIME, STARTDATE, ENDDATE, AIRNUM from airplan", false));
		
		sp = new JScrollPane(jtable);
		sp.setBounds(140, 93, 568, 436);
		jtable.setBackground(new Color(175, 238, 238));
		jtable.setBorder(new LineBorder(new Color(0, 0, 0)));
		rootPanel.add(sp);
		
		// 확인 버튼: 출발지, 도착지 고르는 페이지로 이동
		confirmBtn = new JButton("예약하기");
		confirmBtn.setBounds(357, 558, 136, 44);
		confirmBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reserv.openReservView();
			}
		});
		confirmBtn.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		confirmBtn.setBackground(new Color(175, 238, 238));
		rootPanel.add(confirmBtn);
	}

}
