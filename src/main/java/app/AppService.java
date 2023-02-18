package app;


import java.util.List;
import java.util.Properties;
import java.util.Vector;

import dao.DAO;
import dao.SqlUtil;
import entity.Member;

public class AppService {
	private List<SubApp> appList = new Vector<>();
	private AppContainer appContainer = new AppContainer();
	private SqlUtil sql = new SqlUtil(DAO.getDataSource());
	private static AppService appService;
	private Member member = new Member();
	
	public static AppService getInstance() {
		return appService == null ? appService = new AppService() : appService;
	}
	private AppService() {}
	
	public void setMember(Member member) {
		this.member = member;
	}
	public Member getMember() {
		return member;
	}
	
	public void addSubApp(SubApp subApp) {
		appList.add(subApp);
	}
	
	public void addSubAppIcons() {
		appContainer.addAppIcons(appList);
	}
	
	public void start() {
		appContainer.initComponent();
		//시작시 LoginApp 을 실행시킨다.
//		appContainer.addAppPanel(getSubApp(LoginApp.class));
		update();
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getSubApp(Class<T> subAppClass) {
		for(SubApp subApp : appList) {
			if(subApp.getClass().equals(subAppClass)) {
				return (T) subApp;
			}
		}
		return null;
	}
	
	public void openView(AppView subAppView) {
		appContainer.openView(subAppView);
	}
	
	public boolean closeView(AppView subAppView) {
		appContainer.removeView(subAppView);
		return true;
	}
	
	public SqlUtil sql() {
		return sql;
	}
	
	public void update() {
		appContainer.update();
	}
}
