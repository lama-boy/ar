package gui;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class WrapFrame extends JFrame{
	private int x, y, width, height, dirX, dirY;
	private WrapPanel wrapPanel;

	public WrapFrame(int width, int height) {
		setUndecorated(true);
		setAlwaysOnTop(true);
		setBackground(new Color(0,0,0,0));
		 
		wrapPanel = new WrapPanel(width, height);
		wrapPanel.addMouseListener(wrapPanel);
		wrapPanel.addMouseMotionListener(wrapPanel);
		addKeyListener(wrapPanel);
		setContentPane(wrapPanel);
		setVisible(true);
		pack();
	}
	
	public void setColor(Color color) {
		wrapPanel.color = color;
	}
	
	class WrapPanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener{
		private Color color = new Color(0, 0, 255, 108);
		
		WrapPanel(int width, int height) {
			setPreferredSize(new Dimension(width, height));
		}
		
		public void paint(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.setComposite(AlphaComposite.SrcOver.derive(0.5f));
			g2d.setColor(color);
			g2d.fillRect(0, 0, getWidth(), getHeight());
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
		    Point p = e.getPoint();
		    dirX = 0; dirY = 0;
		    x = getX();
		    y = getY();
		    width = getWidth();
		    height = getHeight();
		    
			int t = 20; // resize() 범위 pixel
		    if(p.x < t) dirX = -1; 
		    if(p.y < t) dirY = -1;
		    if(p.x > width - t) dirX = +1;
		    if(p.y > height - t) dirY = +1;
		    
		    if(dirX != 0 || dirY !=0) { 
		    	wrapPanel.setCursor(new Cursor(Cursor.MOVE_CURSOR));
		    }
	    }

		public void resize(Point p) {
			if(dirX == -1) { //왼쪽
				width = width + x - p.x;
				x = p.x;
			}
			if(dirY == -1) { //위쪽
				height = height + y - p.y;
				y = p.y;
			}
			if(dirX == +1) { //오른쪽
				width = p.x - x;
			}
			if(dirY == +1) { //아래쪽
				height = p.y - y;
			}
			
			//크기가 0이 된경우 방향 전환
			if(width < 0) { 
				dirX *= -1;
			}
			if(height < 0) { 
				dirY *= -1;
			}
			setBounds(x, y, width, height);
		}

		public void mouseDragged(MouseEvent e) {
			resize(e.getLocationOnScreen());
		}


		@Override
		public void mouseReleased(MouseEvent e) {
			wrapPanel.setCursor(Cursor.getDefaultCursor());
		}
		
		@Override
		public void keyTyped(KeyEvent e) {}

		@Override
		public void keyPressed(KeyEvent e) {
			Rectangle r = getBounds();
			int key = e.getKeyCode();
			switch (key) {
				case KeyEvent.VK_NUMPAD4: r.x--; r.width++; break;
				case KeyEvent.VK_NUMPAD6: r.width++; break;
				case KeyEvent.VK_NUMPAD8: r.y--; r.height++; break;
				case KeyEvent.VK_NUMPAD2: r.height++; break;
				case KeyEvent.VK_LEFT: r.x++; r.width--; break;
				case KeyEvent.VK_RIGHT: r.width--; break;
				case KeyEvent.VK_UP: r.y++; r.height--; break;
				case KeyEvent.VK_DOWN: r.height--; break;
			}
			if(width > 0 && height > 0) setBounds(r);
			System.out.println(r);
		}

		@Override
		public void keyReleased(KeyEvent e) {}

		@Override
		public void mouseMoved(MouseEvent e) {}
		
		@Override
		public void mouseClicked(MouseEvent e) {}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}
	}
}


