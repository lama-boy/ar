package gui.wiget;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.JPanel;

import app.AppView;

public class AnalogClock extends AppView{
	private Image buffi;
	private Graphics2D buffg;
	private LocalDateTime time;
	
	public AnalogClock() {
		initRootPanel();
	}
	
//	public void setSize(int width, int height) {
//		wrapPanel.setPreferredSize(new Dimension(width, height));
//	}
	
	private class DisplayGraphics extends Canvas {
		Font font = new Font("Consolas",Font.BOLD, 20);
		
		BasicStroke s1 = new BasicStroke(8,BasicStroke.CAP_ROUND,0);
		BasicStroke s2 = new BasicStroke(5,BasicStroke.CAP_ROUND,0);
		BasicStroke s3 = new BasicStroke(3,BasicStroke.CAP_ROUND,0);
		BasicStroke s4 = new BasicStroke(1,BasicStroke.CAP_ROUND,0);
		
		public void paint(Graphics g) {
			buffi = createImage(getWidth(),getHeight());
			buffg = (Graphics2D) buffi.getGraphics();
			update(g);
		}
		
		public void update(Graphics g) {
			buffg.clearRect(0, 0, getWidth(),getHeight());
			
			int cx = getWidth()/2;
			int cy = getHeight()/2;
			
			buffg.setStroke(s1);
			for (int i = 1; i <= 12; i++) {
				int angle = 360*i/12;
				int r = 180;
				double x = Math.sin(radian(angle)) * r;
				double y = -Math.cos(radian(angle)) * r;
			    int m = 10;
				buffg.drawOval(m, m, 450-m*2, 450-m*2);	
				int rr = 10;
				buffg.fillOval(getWidth()/2-rr,getHeight()/2-rr,rr*2,rr*2);
				//System.out.println("i:"+i+ "  angle: "+ angle + "  반지름: "+ r +"   x :" +x +"  y:" +y);
				drawNumber(buffg, String.valueOf(i), (int)(cx+x-7), (int)(cy+y+7));
			}
			
			buffg.setStroke(s3);
			for (int i = 0; i < 60; i++) {
				int angle = 360*i/60;
				int r = i%5 == 0 ? 195 : 203;
				double x = Math.sin(radian(angle)) * r;
				double y = -Math.cos(radian(angle)) * r;
				
				r += i%5 == 0 ? 20 : 10;
				double x2 = Math.sin(radian(angle)) * r;
				double y2 = -Math.cos(radian(angle)) * r;
				
				int cxx = (int)x, cyy = (int)y;
				int cxx2 = (int)x2, cyy2 = (int)y2;
				
//				System.out.println("i:"+i+ "  angle: "+ angle + "  반지름: "+ r +"   x :" +x +"  y:" +y);
				buffg.drawLine(cx+cxx, cy+cyy, cx+cxx2, cx+cyy2);
			}
			
			buffg.setStroke(s3);
		    int ma = 360*time.getMinute()/60;
		    int ha = (360*time.getHour()*5/60) + ma/12;
		    int sa = 360*time.getSecond()/60;
		    
		    double mx = Math.cos(radian(ha))*120;
		    double my = Math.sin(radian(ha))*120;
		    
			buffg.setStroke(s2);
			buffg.drawLine(cx, cy, (int)(cx+my), (int)(cy-mx));
		    
		    mx = Math.cos(radian(ma))*150;
		    my = Math.sin(radian(ma))*150;
		    
			buffg.setStroke(s3);
			buffg.drawLine(cx, cy, (int)(cx+my), (int)(cy-mx));
			
			mx = Math.cos(radian(sa))*170;
		    my = Math.sin(radian(sa))*170;
			
		    buffg.setStroke(s4);
			buffg.drawLine(cx, cy, (int)(cx+my), (int)(cy-mx));
			
			g.drawImage(buffi, 0, 0, this);
		}
		
		double radian(double degrees) {
			return degrees * (Math.PI / 180);
		}
		
		void drawNumber(Graphics g, String num, int x, int y) {
			g.setFont(font);
			g.drawString(num, x, y);
		}
	}

	private DisplayGraphics canvas = new DisplayGraphics();

//	JFrame frame = new JFrame("Analog Clock");

	public void update(LocalDateTime time) {
		this.time = time;
		canvas.repaint();
	}

	private JPanel wrapPanel = new JPanel(new BorderLayout());
	
	public JPanel initRootPanel() {
//		frame.setContentPane(rootPanel);
		wrapPanel.add(canvas, BorderLayout.CENTER);
//		rootPanel.add(new JTextArea("asdsadas"));
		wrapPanel.setPreferredSize(new Dimension(450, 450));

		rootPanel.add(wrapPanel);
		
//		frame.setResizable(false);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.pack();
//		frame.setLocation(500, 400);
//		frame.setVisible(true);
		
//		frame.createBufferStrategy(2);
//		strategy = frame.getBufferStrategy();
		update(LocalDateTime.now());
		return rootPanel;
	}
	
	public static void main(String[] args) {
		new AnalogClock();
	}
}
