package app;


import java.util.List;
import java.util.Vector;

import entity.MemberDTO;

public class AppService {
	private List<SubApp> appList = new Vector<>();
	private AppContainer appContainer = new AppContainer();
	private static AppService appService;
	private MemberDTO member = new MemberDTO();
	
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
	
	public void closeView(AppView appView) {
		appContainer.removeView(appView);
	}
	
	public void closeViews(AppView... appViews) {
		for(AppView view : appViews) closeView(view);
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
	
	public void setMemberDTO(MemberDTO member) {
		this.member = member;
	}
	public MemberDTO getMember() {
		return member;
	}
	
	public void start() {
		appContainer.initComponent();
		appContainer.setStyle(null); //null 을 넣어서 style.property 를 읽게 만든다
		appContainer.showFrame();
		update();
	}
	
	public void update() {
		appContainer.update();
	}
}
