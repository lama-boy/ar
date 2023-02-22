package test;

import gui.Gui;
import gui.wiget.ZonedClock;

public class J {
	public static void main(String[] args) {
		ZonedClock z = new ZonedClock(100, null, 1) ;
System.out.println(	z.getPanel().getSize());
		Gui.createFrame(z.getPanel());
	}
}
