package app;

import static app.ArApplication.IMG_PATH;

import java.awt.Color;
import java.awt.Cursor;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.border.Border;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import gui.Gui;
import gui.panel.button.ButtonPanel;
import gui.panel.layout.GridBagPanel;
import test.Debug;
import util.Style;

public class ConfigDialog {
	private JDialog dialog;
	private AppContainer container;
	private int width = 200, height = 150, t = 2;
	
	private ButtonPanel styleBtnPanel = new ButtonPanel();
	
	private Border border1 = BorderFactory.createLineBorder(Color.RED, t);
	private Border border2 = BorderFactory.createEmptyBorder(t, t, t, t);
	
	private Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	public ConfigDialog(AppContainer appContainer) {
		this.container = appContainer;
		initDialog();
	}
	
	public void initDialog() {
		dialog = new JDialog(container.getFrame(), "Setting");
		dialog.setAlwaysOnTop(true);
		dialog.setIconImage(Gui.getResizedImage(IMG_PATH+"config.png", 30, 30));
		GridBagPanel gridBagPanel = new GridBagPanel();
		gridBagPanel.setBackground(new Color(22,22,22));
		dialog.setContentPane(gridBagPanel.getPanel());
		gridBagPanel.add(Gui.createLabel(" Style", Color.WHITE, 17, JLabel.CENTER), 1, 1 ,1 ,1);
		
		styleBtnPanel.setButtonSize(22, 22);
		styleBtnPanel.setBackground(Color.DARK_GRAY);

		gridBagPanel.add(styleBtnPanel, 2, 1, 1, 1);
		ButtonPanel applyBtnPanel = new ButtonPanel();
		applyBtnPanel.setBackground(gridBagPanel.getPanel().getBackground());
		applyBtnPanel.setColor(Gui.DARK_BLUE, Color.WHITE);
		applyBtnPanel.addButton("Apply&Close", b->applyAndClose());
		applyBtnPanel.addButton("Cancel", b->cancel());
		gridBagPanel.addNextRow(applyBtnPanel);
	}
	
	private void cancel() {
		loadStyle();
		container.setStyle();
		dialog.setVisible(false);
	}

	public void loadStyle() {
		try {
			JsonReader jsonReader = new JsonReader(new FileReader(ArApplication.RES_PATH+"style.json"));
			container.style = gson.fromJson(jsonReader, Style.class);
			styleBtnPanel.removeAll();
			int styleCount = container.style.colors.size();
			dialog.setSize(width + styleCount * 20, height);

			for(int i=0; i < Math.min(styleCount,8); i++) {
				final int s = i;
				styleBtnPanel.addButton(new ImageIcon(IMG_PATH+"n"+i+".PNG"), b->settingStyle(s));
				styleBtnPanel.getButton(i).setBorder(border2);
				styleBtnPanel.getButton(i).setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			Debug.sysout("++++++++++++style.json++++++++++\n", gson.toJson(container.style),"\n------------style------------");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void settingStyle(int s) {
		container.style.num = s;
		container.setStyle();
		styleBtnPanel.getButtonList().forEach
		(b->b.setBorder(styleBtnPanel.getButtonList().indexOf(b) == s ? border1 : border2)); 
	}

	public void open() {
		settingStyle(container.style.num);
		Gui.placeSubWindow(container.getFrame(), dialog, 1);
		dialog.setModal(true);
		dialog.setVisible(true);
	}
	
	public void applyAndClose() {
		try {
			JsonWriter jsonWriter = new JsonWriter(new FileWriter(ArApplication.RES_PATH+"style.json"));
			jsonWriter.setIndent("  ");
			gson.toJson(container.style, Style.class, jsonWriter);
			jsonWriter.flush();
			jsonWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		dialog.setVisible(false);
	}
	
	public JDialog getDialog() {
		return dialog;
	}
	
//	public static void main(String[] args) {
//		configDialog d =  new configDialog(new AppContainer());
//		d.loadStyle();
//		d.applyAndClose();
//	}
}
