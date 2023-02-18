package app.dash;

import app.AppService;
import app.SubApp;
import app.AppView;
import entity.Member;

public class DashBoard extends SubApp {
	private DashBoardWrite dashBoardWrite = new DashBoardWrite(this);;
	private DashBoardList dashBoardList = new DashBoardList(this);;
	
	public void save() {
		AppService.getInstance().sql().insert("members", new Member());
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