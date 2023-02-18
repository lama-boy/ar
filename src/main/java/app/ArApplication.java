package app;

import javax.swing.Timer;

import app.dash.DashBoard;
import app.login.LoginApp;
import app.reserv.Reservation;
import gui.Gui;

public class ArApplication {
	public static final String RES_PATH = "src/main/resources/";
	public static final String IMG_PATH = RES_PATH + "images/";
	
	
	public static void main(String[] args) {
		Gui.setLookAndFeel(Gui.NIMBUS);
		new ArApplication().run();
	}

	//앱 추가부분
	public void run() {
		AppService service = AppService.getInstance();
		service.addSubApp(new Reservation());
		service.addSubApp(new DashBoard());
		service.addSubApp(new LoginApp());
		service.start();
		
		//전체 동작 타이머 시작
		Timer timer = new Timer(1000, e->service.update());
		timer.start();
	}
}
