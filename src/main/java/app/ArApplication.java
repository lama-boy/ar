package app;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.swing.Timer;

import app.dash.DashBoard;
import app.login.LoginApp;
import app.reserv.Reservation;
import gui.Gui;

public class ArApplication {
	public static final String RES_PATH = "src/main/resources/";
	public static final String IMG_PATH = RES_PATH + "images/";
	
	private static Properties config;
	
	public static void main(String[] args) {
		Gui.setLookAndFeel(Gui.NIMBUS);
		loadConfig();
		new ArApplication().run();;
	}

	public static void loadConfig() {
		config = new Properties();
		try {
			config.load(new FileReader(RES_PATH+"config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//앱 추가부분
	public void run() {
		AppService service = AppService.getInstance();
		service.addSubApp(new Reservation());
		service.addSubApp(new DashBoard());
		service.addSubApp(new LoginApp());
		service.addSubApp(null);
		service.addSubApp(null);
		service.addSubApp(null);
		service.addSubApp(null);
		service.addSubApp(null);
		service.addSubApp(null);
		service.start(config);
		
		//전체 동작 타이머 시작
		Timer timer = new Timer(1000, e->service.update());
		timer.start();
	}
}
