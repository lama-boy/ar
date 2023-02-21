package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
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
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

import gui.table.DataListTable;
import gui.table.StringListTable;
import gui.wiget.SimpleCalendar;

public final class Gui {
	public static final String NIMBUS = "javax.swing.plaf.nimbus.NimbusLookAndFeel";
	public static final String WINDOWS = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
    public static final String MOTIF = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
    public static final String METAL = "javax.swing.plaf.metal.MetalLookAndFeel";
//    public static final String MAC = "com.apple.laf.AquaLookAndFeel";
    
	private Gui() {}
	
	public static JLabel createIconLabel(String path, int width, int height) {
		return new JLabel(new ImageIcon(getResizedImage(path, width, height)));
	}
	
	public static JLabel createIconLabel(Image image, int width, int height) {
		return new JLabel(new ImageIcon(getResizedImage(image, width, height)));
	}
	
	public static ImageIcon getResizedIcon(String path, int width, int height) {
		return new ImageIcon(getResizedImage(path, width, height));
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
 
	@SuppressWarnings("unchecked")
	public static JTable createTable(List<?> dataList) {
		if(dataList == null || dataList.get(0) == null) 
			return new JTable();
		
		if(dataList.get(0) instanceof List) {
			if(((List<?>)dataList.get(0)).get(0) instanceof String) {
				return new StringListTable((List<List<String>>) dataList);
			}
		}
		return new DataListTable(dataList);
	}
	
	public static JFrame createFrame(JComponent comp) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(comp);
		frame.pack();
		frame.setVisible(true);
		return frame;
		
	}
	
	public static void moveToCenter(Window window) {
		int width = window.getWidth();
		int height = window.getHeight();
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		window.setLocation((screen.width - width) / 2, (screen.height - height) / 2);
	}
	
    public static Font createFont(int size) {
        return new Font(Font.SANS_SERIF, Font.PLAIN, size);
    }
    
    public static Font createFont(String font, int size) {
        return new Font(font, Font.PLAIN, size);
    }
    
	public static JButton createButton(String name, Consumer<?> c) {
		JButton button = new JButton(name);
		button.addActionListener(e->c.accept(null));
		return button;
	}
    
	public static String openCalendar() {
		return openCalendar(null, null);
	}
	
	public static String openCalendar(Frame parent) {
		return openCalendar(parent, null);
	}
	
	public static String openCalendar(String string) {
		return openCalendar(null, string);
	}
	
	public static String openCalendar(Frame parent, String format) {
		return new SimpleCalendar(parent, format).open();
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
}	
