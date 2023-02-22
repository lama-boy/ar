package util;

import static app.ArApplication.IMG_PATH;

import java.awt.Color;
import java.awt.Cursor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.border.Border;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import app.AppContainer;
import app.ArApplication;
import gui.Gui;
import gui.panel.button.ButtonPanel;
import gui.panel.layout.GridBagPanel;

public class SettingDialog {
	private JDialog dialog;
	private AppContainer appContainer;
	private int width = 250, height = 150, t = 2;
	
	private ButtonPanel styleBtnPanel = new ButtonPanel();
	private Properties style = new Properties(); 
	
	private Border border1 = BorderFactory.createLineBorder(Color.RED, t);
	private Border border2 = BorderFactory.createEmptyBorder(t, t, t, t);
	
	private Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	public SettingDialog(AppContainer appContainer) {
		this.appContainer = appContainer;
		initDialog();
	}
	
	public void initDialog() {
		loadStyle();
		dialog = new JDialog(appContainer.getFrame(), "Setting");
		dialog.setIconImage(Gui.getResizedImage(IMG_PATH+"config.png", 30, 30));
		dialog.setSize(width, height);
		GridBagPanel gridBagPanel = new GridBagPanel();
		gridBagPanel.setBackground(new Color(22,22,22));
		dialog.setContentPane(gridBagPanel.getPanel());
		gridBagPanel.add(Gui.createLabel(" Style", Color.WHITE, 17, JLabel.CENTER), 1, 1 ,1 ,1);
		
		styleBtnPanel.setButtonSize(22, 22);
		styleBtnPanel.setBackground(Color.DARK_GRAY);
		for(int i=1; i<=5; i++) {
			String s = String.valueOf(i);
			styleBtnPanel.addButton(new ImageIcon(IMG_PATH+"n"+i+".PNG"), b->setStyle(s));
			styleBtnPanel.getButton(i-1).setBorder(border2);
			styleBtnPanel.getButton(i-1).setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		gridBagPanel.add(styleBtnPanel, 2, 1, 1, 1);
		ButtonPanel commitBtnPanel = new ButtonPanel();
		commitBtnPanel.setBackground(gridBagPanel.getPanel().getBackground());
		commitBtnPanel.setColor(Gui.DARK_BLUE, Color.WHITE);
		commitBtnPanel.addButton("Apply&Close", b->applyAndClose());
		commitBtnPanel.addButton("Cancel", b->{dialog.dispose();});
		gridBagPanel.addNextRow(commitBtnPanel);
	}

	public void loadStyle() {
		try { style.load(new FileReader(ArApplication.RES_PATH+"style.properties")); } 
		catch (IOException e) {	e.printStackTrace(); }
		
		Reader reader;
		try {
			reader = new FileReader(ArApplication.RES_PATH+"style.json");
			JsonReader jsonReader = new JsonReader(reader);
			Style style = gson.fromJson(jsonReader, Style.class);
			System.out.println(gson.fromJson(gson.toJson(style), JsonObject.class));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void setStyle(String s) {
		styleBtnPanel.getButtonList().forEach(b->b.setBorder(border2));
		styleBtnPanel.getButton(Integer.parseInt(s)-1).setBorder(border1);
		style.setProperty("style", s);
	}

	public void open() {
		setStyle(style.getProperty("style"));
		dialog.setVisible(true);
	}
	
	public void applyAndClose() {
		style.setProperty("style",style.getProperty("style"));
		appContainer.setStyle(style);
		try {
			style.store(Files.newBufferedWriter(new File(ArApplication.RES_PATH + "style.properties").toPath()),"App Container Style.properties");
		} catch (IOException e) {
			e.printStackTrace();
		}
		dialog.dispose();
	}

	public JDialog getDialog() {
		return dialog;
	}
}
