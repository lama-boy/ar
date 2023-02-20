package app.dash;

import app.AppService;
import app.AppView;
import app.SubApp;
import dao.DAO;
import entity.Member;

public class DashBoard extends SubApp {
	private DashBoardWrite dashBoardWrite = new DashBoardWrite(this);;
	private DashBoardList dashBoardList = new DashBoardList(this);;
	
	public void save() {
		DAO.sql.insert("members", new Member());
	}
	
	public void openWrite() {
		AppService.getInstance().openView(dashBoardWrite);
	}
	
	public void openList() {
		AppService.getInstance().openView(dashBoardList);
	}
	
	@Override
	public AppView requestView() {
		return dashBoardList;
	}
}