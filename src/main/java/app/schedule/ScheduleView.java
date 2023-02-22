package app.schedule;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollPane;

import app.AppView;
import dao.DAO;
import dao.SqlUtil;
import entity.mapper.MemberRowMapper;
import gui.Gui;

public class ScheduleView extends AppView{
	public ScheduleView(Schedule schedule) {
		super(schedule);
		initRootPanel();
	}
	@Override
	public void initRootPanel() {
		rootPanel.setLayout(new BorderLayout());
		rootPanel.setSize(new Dimension(300, 300));

		SqlUtil s = new SqlUtil(DAO.getDataSource());
		rootPanel.add(new JScrollPane(Gui.createTable(s.selectTable("Members", new MemberRowMapper()))));
	}
	
	public static void main(String[] args) {
		Gui.createFrame(new ScheduleView(null).rootPanel);
	}
}