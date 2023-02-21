package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class WrapFrame extends JFrame implements Runnable {
	private float alpha = 0;
	private float da = 0.07f;
	private float maxAlpha = 0.5f;
	private long waitMilliseconds= 150;
	
	public static final Font DEFAULT_FONT = new Font("맑은 고딕", Font.PLAIN, 20);
	private JPanel panel = new JPanel(new BorderLayout());
	
	public WrapFrame(JComponent parent, JComponent comp) {
		if(comp != null) 
			panel.add(comp);
		if(parent != null) {
			setLocation(parent.getLocationOnScreen());
			setSize(parent.getSize());
		}
	}
	
	public WrapFrame(JComponent parent) {
		this(parent, null);
	}
	
	{	
		setUndecorated(true);
		setAlwaysOnTop(true);
		setOpacity(0f);
		setContentPane(panel);
	}
	
	public void setProperties(float alpha, float da, float maxAlpha, long waitMilliseconds) {
		if(alpha >= 0) this.alpha = alpha;
		if(da > 0) this.da = da;
		if(maxAlpha > 0 && maxAlpha <= 1) this.maxAlpha = maxAlpha;
		if(waitMilliseconds > 0) this.waitMilliseconds = waitMilliseconds;
	}
	
	public void setPanelColor(Color color) {
		panel.setBackground(color);
	}
	
	public static void alert(JComponent... parents) {
		alert(null, null, parents);
	}
	
	public static void alert(String msg, JComponent... parents) {
		alert(msg, null, parents);
	}
	
	public static void alert(String msg, Font font, JComponent... parents) {
		for(JComponent parent : parents) {
			WrapFrame frame = new WrapFrame(parent);
			if(msg != null) {
				JLabel label = new JLabel(msg);
				label.setHorizontalAlignment(JLabel.CENTER);
				label.setForeground(Color.YELLOW);
				if(font == null) 
					font = WrapFrame.DEFAULT_FONT;
				label.setFont(font);
				frame.getContentPane().add(label);
			}
			frame.setPanelColor(Color.RED);
			frame.setProperties(0, 0.08f, 0.5f, 170);
			frame.start();	
		}
	}
	
	public static void mouseTooltip(JComponent parent, String text) {
		parent.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				WrapFrame frame = new WrapFrame(parent);
				frame.setLocation(e.getLocationOnScreen().x, parent.getLocationOnScreen().y+ parent.getHeight());
				frame.setProperties(0, 0.1f, 0.7f, 1000);
				JPanel newPanel = new JPanel();
				frame.setContentPane(newPanel);
				JLabel label = new JLabel(text);
				newPanel.add(label);
				label.setHorizontalAlignment(JLabel.CENTER);
				label.setFont(new Font("맑은 고딕", Font.ITALIC, 15));
				label.setForeground(Color.BLACK);
				newPanel.setBorder(new LineBorder(Color.DARK_GRAY, 1));
				frame.pack();
				frame.start();
			}
		});
	}
	
	public static void tooltip(JComponent parent, String text) {
		WrapFrame frame = new WrapFrame(parent);
		frame.setProperties(0, 0.1f, 0.7f, 1000);
		JLabel label = new JLabel(text);
		label.setHorizontalAlignment(JLabel.CENTER);
		JPanel panel = (JPanel) frame.getContentPane();
		panel.setBackground(Color.DARK_GRAY);
		label.setFont(DEFAULT_FONT);
		label.setForeground(Color.WHITE);
		panel.setBorder(new LineBorder(Color.YELLOW, 2));
		frame.getContentPane().add(label);
		frame.start();
	}
	
	public void start() {
		setVisible(true);
		Thread thread = new Thread(this);
		thread.start();
	}

	public void run() {
		while(true) {
			try {
				Thread.sleep(33);
			alpha += da;
			if(alpha > maxAlpha) {
				alpha = maxAlpha - da;
				da *= -1;
				Thread.sleep(waitMilliseconds);
			}else if(alpha < 0) {
				break;
			}
			if(alpha >=0 && alpha <= 1) setOpacity(alpha);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		dispose();
	}
}
