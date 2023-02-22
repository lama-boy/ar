package app.admin;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;

import app.AppService;
import app.AppView;
import dao.DAO;
import dao.SqlUtil;
import gui.Gui;
import gui.panel.button.ButtonPanel;
import gui.panel.layout.BorderLayoutPanel;

public class CustomerServiceBoard extends AppView{
	private AdminApp adminApp;
	
	public CustomerServiceBoard(String title, AdminApp adminApp) {
		super(title, adminApp);
		this.adminApp = adminApp;
		initRootPanel();
	}
	
	@Override
	public void initRootPanel() {
		BorderLayoutPanel blPanel = new BorderLayoutPanel();
		rootPanel = blPanel.getPanel();
		ButtonPanel btnPanel = new ButtonPanel();
		blPanel.newScroll(Gui.createTable(DAO.sql.select("select * from members")), BorderLayout.CENTER);
		blPanel.addSouth(btnPanel);
		btnPanel.addButton("답변하기", b->test(1));
	}
	
	private Object test(int i) {
		System.out.println(i);
		if(i == 1) {
			//답변하기 
			
		}
		if(i == 2) {
			
		}
		return null;
	}
}
