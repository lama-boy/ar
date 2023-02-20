package app;


import java.util.List;
import java.util.Vector;

import entity.Member;

public class AppService {
	private List<SubApp> appList = new Vector<>();
	private AppContainer appContainer = new AppContainer();
	private static AppService appService;
	private Member member = new Member();
	
	public static AppService getInstance() {
		return appService == null ? appService = new AppService() : appService;
	}
	private AppService() {}
	
	public void addSubApp(SubApp subApp) {
		if(subApp != null && getSubApp(subApp.getClass()) == null)
			appList.add(subApp);
	}
	
	public void removeSubApp(SubApp subApp) {
		if(subApp != null) {
			appContainer.removeViews(subApp);
			appList.remove(subApp);
			updateSubAppIcons();
		}
	}
	
	public void openView(AppView appView) {
		appContainer.addView(appView);
	}
	
	public boolean closeView(AppView appView) {
		appContainer.removeView(appView);
		return true;
	}
	
	public SubApp getSubApp(Class<? extends SubApp> subAppClass) {
		for(SubApp subApp : appList) {
			if(subApp.getClass().equals(subAppClass)) {
//				sysout("getSubApp  :  " + subApp);
				return subApp;
			}
		}
		return null;
	}
	
	public void updateSubAppIcons() {
		appContainer.addAppIcons(appList);
	}
	
	public AppContainer getContainer() {
		return appContainer;
	}
	
	public void setMember(Member member) {
		this.member = member;
	}
	public Member getMember() {
		return member;
	}
	
	public void start() {
		appContainer.initComponent();
		update();
	}
	
	public void update() {
		appContainer.update();
	}
}
