package gui;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window;
import java.io.File;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;

import gui.table.DataListTable;
import gui.table.StringListTable;

public final class Gui {
	private Gui() {}
	
	@SuppressWarnings("unchecked")
	public static JTable createTable(List<?> dataList) {
		if(dataList.get(0) == null) 
			return null;
		
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
    
	public static JButton createButton(String name, Consumer<?> c) {
		JButton button = new JButton(name);
		button.addActionListener(e->c.accept(null));
		return button;
	}
    
	public static Cursor getResizeCursor(int dirX, int dirY) {
		int type = Cursor.DEFAULT_CURSOR;
		if(dirX == -1 && dirY == -1) type = Cursor.NW_RESIZE_CURSOR;
		else if(dirX == 0 && dirY == -1) type = Cursor.N_RESIZE_CURSOR;
		else if(dirX == 1 && dirY == -1) type = Cursor.NE_RESIZE_CURSOR;
		else if(dirX == -1 && dirY == 0) type = Cursor.W_RESIZE_CURSOR;
		else if(dirX == 1 && dirY == 0) type = Cursor.E_RESIZE_CURSOR;
		else if(dirX == -1 && dirY == 1) type = Cursor.SW_RESIZE_CURSOR;
		else if(dirX == 0 && dirY == 1) type = Cursor.S_RESIZE_CURSOR;
		else if(dirX == 1 && dirY == 1) type = Cursor.SE_RESIZE_CURSOR;
		return new Cursor(type);
	}
	
	public static JLabel createImageLabel(String fileName, int width, int height) {
		JLabel label = new JLabel(new ImageIcon(fileName));
		label.setPreferredSize(new Dimension(width, height));
		return label;
	}
	
	public static JLabel createIamgeLabel(String fileName) {
		return new JLabel(new ImageIcon(fileName));
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
	public static JFileChooser createFileChooser(String currentDirectory, String... exts) {
		JFileChooser fileChooser = new JFileChooser(currentDirectory);
		if(exts != null && exts.length > 0) {
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Files", exts);
			fileChooser.setFileFilter(filter);
		}
		return fileChooser;
	}
	
	public static File[] getFiles(Component parent, String currentDirectory, String... exts) {
        JFileChooser fileChooser = createFileChooser(currentDirectory, exts);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setMultiSelectionEnabled(true);
        if (fileChooser.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFiles();
        } else {
            return new File[0];
        }
    }
	
	public static File getFile(Component parent, String currentDirectory, String... exts) {
        JFileChooser fileChooser = createFileChooser(currentDirectory, exts);
        if (fileChooser.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        } else {
            return null;
        }
    }
	
	public static File[] getFiles(String... exts) {
		return getFiles(null, "", exts);
	}
	
	public static File getFile(String... exts) {
		return getFile(null, "", exts);
	}
//-------------------------------------------End JFileChooser----------------------------------//

	public static void main(String[] args) {
        // 사용 예시
        String[] exts = {"txt", "java", "html"}; // 선택 가능한 확장자 목록
        File selectedFiles = getFile(); // 현재 디렉토리에서 확장자가 지정된 파일들을 선택

        // 선택된 파일들을 출력
        System.out.println(selectedFiles.getAbsolutePath());
        
        String jarPath = "/path/to/example.jar";
    }
}	
