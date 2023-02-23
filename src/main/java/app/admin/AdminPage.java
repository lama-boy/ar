package app.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import app.AppView;
import dao.DAO;
import gui.Gui;
import gui.panel.button.ButtonPanel;
import gui.panel.layout.BorderLayoutPanel;
import gui.table.StringTable;

public class AdminPage extends AppView{
	private AdminApp adminApp;
	
	private int tableIndex = 1;
	
	private JLabel titleLabel = new JLabel();
	
	private JPanel tablePanel = new JPanel(new BorderLayout());
	
	public AdminPage(String title, AdminApp adminApp) {
		super(title, adminApp);
		this.adminApp = adminApp;
		initRootPanel();
	}
	
	@Override
	public void initRootPanel() {
		BorderLayoutPanel rootblPanel = new BorderLayoutPanel();
		rootPanel = rootblPanel.getPanel();

		titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		titleLabel.setForeground(Color.darkGray);
		JPanel northPanel = rootblPanel.newPanel(BorderLayout.NORTH);
		northPanel.setBackground(Color.LIGHT_GRAY);
		northPanel.add(titleLabel);

		BorderLayoutPanel centerPanel = new BorderLayoutPanel();
		rootblPanel.addCenter(centerPanel);
		centerPanel.addWest(Gui.createButton("", b->createTable(-1)));
		centerPanel.addCenter(tablePanel);
		centerPanel.addEast(Gui.createButton("", b->createTable(+1)));
		
		ButtonPanel botBtnPanel = new ButtonPanel();
		rootblPanel.addSouth(botBtnPanel);
		botBtnPanel.addButton("여행 패키지 등록", b->action(1));
		
		createTable(0);
	}
	
	private void action(int i) {
		switch (i) {
			case 1: adminApp.openPackage(); break;
		}
	}

	public void createTable(int i) {
		final int tableCount = 3;
		tableIndex += i;
		if(tableIndex <= 0) tableIndex = tableCount;
		if(tableIndex > tableCount) tableIndex = 1;
		String query = "", title = "";
		switch (tableIndex) {
			case 1: title = "회원 목록"; query = "select * from members"; ; break;
			case 2: title = "보드 이름 제목"; query = "select id, name from members"; break;
			case 3: title = "회원 아이디 패스워드"; query = "select id, password from members"; break;
		}
		titleLabel.setText(title);
		tablePanel.removeAll();
		StringTable table = new StringTable(DAO.sql.select(query));
		table.setColumnsSize(200,400,200);
		tablePanel.add(new JScrollPane(table));
	}
}