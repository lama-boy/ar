package app.reserv;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.toedter.calendar.JDateChooser;

import app.AppView;
import app.SubApp;
import dao.DAO;

public class Reservation extends SubApp{
	private ReservView reservView = new ReservView(this);
	
	public Reservation() {
		
	}
	
	@Override
	public AppView requestView() {
		return reservView;
	}
	
	public void reservTicket(Object object) {
		DAO.sql.insert("reserv", object);
	}
	
	public void cancelTicket(Object object) {
		DAO.sql.update("reserv", object, "ticketId");
	}
}
