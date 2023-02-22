package app.login;

import app.AppService;
import app.AppView;
import app.SubApp;

public class LoginApp extends SubApp{
	private AirLineMain airLineMain = new AirLineMain(this); 
	private AirLineSignUp airLineSignUp = new AirLineSignUp(this); 
	
	public void openSignUp() {
		AppService.getInstance().closeView(airLineSignUp);
		AppService.getInstance().openView(airLineSignUp);
	}
	
	public void openMain() {
		AppService.getInstance().closeView(airLineSignUp);
		AppService.getInstance().openView(airLineMain);
	}
	
	@Override
	public AppView requestView() {
		return airLineMain;
	}
}
