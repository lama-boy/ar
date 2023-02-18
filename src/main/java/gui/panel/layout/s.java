package gui.panel.layout;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;

import gui.Gui;

public class s {
public static void main(String[] args) {
	BorderLayoutPanel b = new BorderLayoutPanel();
	b.addEast(new JLabel("east"));
	b.addCenter(new JLabel("center"));
	b.addNorth(new JLabel("north"));
	b.addWest(new JLabel("west"));
	b.addSouth(new JLabel("south"));
}
}
