package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

import app.ArApplication;
import gui.panel.CustomPanel;
import gui.panel.button.RoundButton;
import gui.table.DataTable;
import gui.table.ListTable;
import gui.table.StringTable;

public final class Gui {
	public static final String IMG_PATH = ArApplication.IMG_PATH;
	
	public static final String NIMBUS = "javax.swing.plaf.nimbus.NimbusLookAndFeel";
	public static final String WINDOWS = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
    public static final String MOTIF = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
    public static final String METAL = "javax.swing.plaf.metal.MetalLookAndFeel";
//    public static final String MAC = "com.apple.laf.AquaLookAndFeel";
    
    public static final Color DARK_BLUE = new Color(20, 20, 70);
    
	private Gui() {}
	
	public static JLabel createLabel(String text, Color fgColor, int size, int alignment) {
		return createLabel(text, fgColor, font(size), alignment);
	}

	public static JLabel createLabel(String text, Color fgColor, Font font, int alignment) {
		JLabel label = new JLabel(text);
		label.setHorizontalAlignment(alignment);
		label.setForeground(fgColor);
		label.setFont(font);
		return label;
	}
	
	public static JLabel createIconLabel(String path) {
		return new JLabel(new ImageIcon(path));
	}
	
	public static JLabel createIconLabel(String path, int width, int height) {
		return createIconLabel(path, width, height, null);
	}
	
	public static JLabel createIconLabel(String path, int width, int height, Consumer<?> action) {
		return createIconLabel(getResizedImage(path, width, height), width, height, action);
	}
	
	public static JLabel createIconLabel(Image image, int width, int height, Consumer<?> action) {
		JLabel label = new JLabel(new ImageIcon(getResizedImage(image, width, height)));
		if(action != null) {
			label.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) { action.accept(null); } });
		}
		return label;
	}
	
	public static ImageIcon getResizedIcon(String path, int width, int height) {
		return new ImageIcon(getResizedImage(path, width, height));
	}
	
	public static ImageIcon getResizedIcon(String path, String defaultPath, int width, int height) {
		File file = new File(path);
		if(file.exists())
			return getResizedIcon(path, width, height);
		else
			return getResizedIcon(defaultPath, width, height);
	}
	
	public static ImageIcon getResizedIcon(ImageIcon icon, int width, int height) {
		return new ImageIcon(getResizedImage(icon.getImage(), width, height));
	}
	
	public static Image getResizedImage(String path, int width, int height) {
		try {
			return getResizedImage(ImageIO.read(new File(path)), width, height);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Image getResizedImage(Image image, int width, int height) {
		return image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	}
	
	public static void addBorderOnEnterMouse(JComponent comp, Consumer<?> action) {
		addBorderOnEnterMouse(comp, action, Color.RED, 1);
	}

	public static void addBorderOnEnterMouse(JComponent comp, Consumer<?> action, int t) {
		addBorderOnEnterMouse(comp, action, Color.RED, t);
	}
	
	public static void addBorderOnEnterMouse(JComponent comp, Consumer<?> action, Color color, int t) {
		Border empty = BorderFactory.createEmptyBorder(t,t,t,t);
		Border line = BorderFactory.createLineBorder(color, t);
		comp.setBorder(empty);
		comp.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) { action.accept(null); }
			public void mouseEntered(MouseEvent e) { comp.setBorder(line); }
			public void mouseExited(MouseEvent e) { comp.setBorder(empty); }
		});
	}
	public static void setLookAndFeel(String className) {
        try {
            UIManager.setLookAndFeel(className);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
	public static StringTable createStringTable() {
		return new StringTable();
	}
	
	@SuppressWarnings("unchecked")
	public static ListTable createTable(List<?> dataList) {
		if(dataList == null || dataList.get(0) == null) 
			return new ListTable();
		
		if(dataList.get(0) instanceof List) {
			if(((List<?>)dataList.get(0)).get(0) instanceof String) {
				return new StringTable((List<List<String>>) dataList);
			}
		}
		return new DataTable(dataList);
	}
	
	public static JFrame createFrame(CustomPanel panel) {
		return createFrame(panel.getPanel());
	}
	
	public static JFrame createFrame(JComponent comp) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(comp);
		frame.pack();
		frame.setVisible(true);
		return frame;
	}
	
	/**
	 * position 0: Top-left, 1: Top-right, 2: bottom-left, 3: bottom-right, 4: Center
	 */
	public static void placeSubWindow(Window parentWindow, Window subWindow, int position) {
	    int parentX = parentWindow.getX();
	    int parentY = parentWindow.getY();
	    int parentWidth = parentWindow.getWidth();
	    int parentHeight = parentWindow.getHeight();
	    int subWidth = subWindow.getWidth();
	    int subHeight = subWindow.getHeight();
	    
	    switch(position) {
	        case 0: // Top-left
	            subWindow.setLocation(parentX, parentY);
	            break;
	        case 1: // Top-right
	            subWindow.setLocation(parentX + parentWidth - subWidth - 30, parentY + 30);
	            break;
	        case 2: // Bottom-left
	            subWindow.setLocation(parentX, parentY + parentHeight - subHeight);
	            break;
	        case 3: // Bottom-right
	            subWindow.setLocation(parentX + parentWidth - subWidth, parentY + parentHeight - subHeight);
	            break;
	        case 4: // Center
	            subWindow.setLocation(parentX + (parentWidth - subWidth) / 2, parentY + (parentHeight - subHeight) / 2);
	            break;
	        default:
	            throw new IllegalArgumentException("Invalid position: " + position);
	    }
	}
	
	public static void moveToCenter(Window window, int width, int height) {
		window.setSize(width, height);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		window.setLocation((screen.width - width) / 2, (screen.height - height) / 2);
	}
	
	public static void moveToCenter(Window window) {
		int width = window.getWidth();
		int height = window.getHeight();
		moveToCenter(window, width, height);
	}
	
    public static Font createFont(int size) {
        return new Font(Font.SANS_SERIF, Font.PLAIN, size);
    }
    
    public static Font createFont(String font, int size) {
        return new Font(font, Font.PLAIN, size);
    }
    
    public static JButton createButton(String name, Consumer<?> action) {
		JButton button = new JButton(name);
		button.addActionListener(e->action.accept(null));
		return button;
	}
    
    public static JButton createButton(ImageIcon icon, Consumer<?> action) {
		JButton button = new JButton(icon);
		button.addActionListener(e->action.accept(null));
		return button;
	}
    
    public static JButton createRoundButton(String text, Color bgColor, Color fgColor, int arc, Consumer<?> action) {
    	JButton button = new RoundButton(text);
    	button.setBackground(bgColor);
    	button.setForeground(fgColor);
    	button.addActionListener(b->action.accept(null));
    	return button;
    }
    
	public static boolean confirmDialog(JComponent parent, Object message, String title, int type) {
		return JOptionPane.showConfirmDialog(parent, message, title, type) == JOptionPane.OK_OPTION;
	}
	
	public static boolean confirmDialog(String message) {
		return confirmDialog(null, message);
	}
	
	public static boolean confirmDialog(JComponent parent, String message) {
		return JOptionPane.showConfirmDialog(parent, message) == JOptionPane.OK_OPTION;
	}
	
//-----------------------------------create Simple JFileChooser--------------------------------//
	public static JFileChooser createFileChooser(File file, String... exts) {
		JFileChooser fileChooser = new JFileChooser(file);
		if(exts != null && exts.length > 0) {
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Files", exts);
			fileChooser.setFileFilter(filter);
		}
		return fileChooser;
	}
	
	public static JFileChooser createFileChooser(String... exts) {
		return createFileChooser(new File(""), exts);
	}
	
	public static File[] getFiles(Component parent, File current, String... exts) {
        JFileChooser fileChooser = createFileChooser(exts);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setMultiSelectionEnabled(true);
        if (fileChooser.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFiles();
        } else {
            return new File[0];
        }
    }
	
	public static File getFile(Component parent, File current, String... exts) {
        JFileChooser fileChooser = createFileChooser(current, exts);
        if (fileChooser.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        } else {
            return null;
        }
    }
	
	public static File[] getFiles(String exts) {
		return getFiles(null, null, exts);
	}
	
	public static File getFile(String exts) {
		return getFile(null, null, exts);
	}
//-------------------------------------------End JFileChooser----------------------------------//

	public static Font font(int size) {
		return new Font("맑은 고딕", Font.PLAIN, size);
	}
}	
