package app;


import static java.lang.Integer.parseInt;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import app.login.LoginApp;
import dao.DAO;
import dao.SqlUtil;
import entity.Member;

public class AppService {
	private List<SubApp> appList = new Vector<>();
	private AppContainer appContainer = new AppContainer();
	private SqlUtil sql = new SqlUtil(DAO.getDataSource());
	private static AppService appService;
	
	public static AppService getInstance() {
		if(appService == null) return appService = new AppService();
		else return appService;
	}

	private Member member;
	
	private AppService() {}
	
	public Member member() { 
		return member;
	};
	
	public void setMember(Member member) {
		this.member = member;
	}
	public Member getMember() {
		return member;
	}
	
	public void addSubApp(SubApp subApp) {
		appList.add(subApp);
		appContainer.addSubAppIcon(subApp);
	}
	
	public void start(Properties config) {
		int width = parseInt(config.getProperty("width", "500"));
		int height = parseInt(config.getProperty("height", "500"));
		appContainer.display(width, height);
		appContainer.addPanel(getSubApp(LoginApp.class));
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
	
	public boolean close(SubApp subApp) {
		appContainer.removePanel(subApp);
		return true;
	}
	
	public SqlUtil sql() {
		return sql;
	}
	
	public void update() {
		LocalDateTime time = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	    String formattedTime = time.format(formatter);
		appContainer.update(formattedTime);
		appList.forEach(a->a.update(time));
	}
}
