package app.schedule;

import app.AppView;
import app.SubApp;

public class Schedule extends SubApp{
	private ScheduleView scheduleView = new ScheduleView(this);
	@Override
	public AppView requestView() {
		return scheduleView;
	}

}
