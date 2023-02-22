package gui.wiget;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import gui.Gui;

public class SimpleCalendar {
	private JDialog frame;
	private JPanel content = new JPanel();

	private JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	private Listener listener = new Listener();
	
	private JLabel dateLabel;
	private JButton prevYear, nextYear, prevMonth, nextMonth;
	private JPanel centerPanel = new JPanel();
	private LocalDate date;
	private Border border = BorderFactory.createLineBorder(Color.GRAY);
	private List<DayPanel> days = new ArrayList<>();
	private int rows = 7, cols = 7, height = 30, width = 40;
	private Color color1 = new Color(233,233,233), color2 = new Color(170,130, 255);
	private String returnValue, format = "YYYYMMdd";
	private Frame parent;
	
	public void setFormat(String format) {
		if(format != null && !format.isEmpty())
			this.format = format;
	}
	
	public String open() {
		if(parent == null) {
			Gui.moveToCenter(frame);
		}
		frame.setVisible(true);
		return getValue();
	}
	
	public SimpleCalendar() {
		this(null, null);
	}
	
	public SimpleCalendar(Frame parent, String format) {
		this.parent = parent;
		frame = new JDialog(parent);

		setFormat(format);
		frame.setModal(true);
		frame.setTitle("Simple Calendar");
		frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		content.setLayout(new BorderLayout());
		content.add(northPanel, BorderLayout.NORTH);
		content.setBorder(new EmptyBorder(5,5,5,5));
//		content.setPreferredSize(new Dimension(300,300));
		
		northPanel.add(prevYear = createButton("◀"));
		northPanel.add(prevMonth = createButton("◁"));
		
		northPanel.add(dateLabel = new JLabel());
		dateLabel.setFont(new Font("Consolas",Font.PLAIN,25));
		dateLabel.setBorder(new EmptyBorder(10,10,0,10));
		dateLabel.setPreferredSize(new Dimension(120,30));
		
		northPanel.add(nextMonth = createButton("▷"));
		northPanel.add(nextYear = createButton("▶"));

		content.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new GridLayout(rows, cols));
		centerPanel.setBorder(border);
		frame.setContentPane(content);

		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				DayPanel dayPanel = new DayPanel();
				dayPanel.setBorder(border);
				dayPanel.setBackground(color1);
				dayPanel.addMouseListener(listener);
				if(r != 0) days.add(dayPanel);
				else {
					switch(c) {
						case 0: dayPanel.label.setText("SUN"); dayPanel.label.setForeground(Color.RED); break;
						case 1: dayPanel.label.setText("MON"); break;
						case 2: dayPanel.label.setText("TUE"); break;
						case 3: dayPanel.label.setText("WED"); break;
						case 4: dayPanel.label.setText("THU"); break;
						case 5: dayPanel.label.setText("FRI"); break;
						case 6: dayPanel.label.setText("SAT"); dayPanel.label.setForeground(Color.BLUE); break;
					}
				}
				dayPanel.setPreferredSize(new Dimension(width, height));
				centerPanel.add(dayPanel);
			}
		}

		// 달력 초기화
		updateCalendar(LocalDate.now());
		
		frame.pack();
		frame.setAlwaysOnTop(true);
	}
	
	private void updateCalendar(LocalDate date) {
		int year = date.getYear(), month = date.getMonthValue();
		date = LocalDate.of(year, month, 1);
		this.date = date;
		
		dateLabel.setText(year + "/" + month);

		int week = date.getDayOfWeek().getValue(); // MON~SUN 1~7

		LocalDate startDate = (date.getDayOfWeek() != DayOfWeek.SUNDAY) ? date.minusDays(week) : date;
		
		for(int d = 0; d< (rows-1) * cols; d++){
			date = startDate.plusDays(d);
			DayPanel dayPanel = days.get(d);
			dayPanel.date = date;
			dayPanel.isCurrentMonth = month == date.getMonthValue();
			dayPanel.setDay(date.getDayOfMonth(), date.getDayOfWeek().getValue());
		}
	}

	@SuppressWarnings("serial")
	private class DayPanel extends JPanel {
		JLabel label = new JLabel();
		boolean isCurrentMonth;
		LocalDate date;
		
		public DayPanel() {
			add(label);
		}
		
		void setDay(int day, int week) {
			label.setText(String.valueOf(day));
			if(isCurrentMonth) {
				label.setFont(new Font("Consolas",Font.PLAIN,20));
				if(week == 6) { //토요일
					label.setForeground(Color.BLUE);
				}else if(week == 7) { //일요일
					label.setForeground(Color.RED);
				} else {
					label.setForeground(Color.BLACK);
				}
			}else {
				label.setFont(new Font("Consolas",Font.PLAIN,15));
				label.setForeground(Color.GRAY);
			}
		}
	}

	private JButton createButton(String str) {
		JButton button = new JButton(str);
		button.addActionListener(listener);
		button.setMargin(new Insets(0, 0, 0, 0));
		button.setPreferredSize(new Dimension(40, 30));
		return button;
	}
	
	public String getValue() {
		return returnValue;
	}
	
	class Listener implements ActionListener, MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			DayPanel dayPanel = (DayPanel) e.getSource();
			if(dayPanel.isCurrentMonth) {
				returnValue = dayPanel.date.format(DateTimeFormatter.ofPattern(format));
				frame.dispose();
			}
		}

		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}

		@Override
		public void mouseEntered(MouseEvent e) {
			setColor(e, true);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			setColor(e, false);
		}

		public void setColor(MouseEvent e, boolean isEnter){
			DayPanel dayPanel = (DayPanel) e.getSource();
			if(!isEnter || !dayPanel.isCurrentMonth) {
				dayPanel.setBackground(color1);
			} else {
				dayPanel.setBackground(color2);				
			}
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton)e.getSource();
			if(prevYear == button) updateCalendar(date.minusYears(1));
			if(prevMonth == button) updateCalendar(date.minusMonths(1));
			if(nextYear == button) updateCalendar(date.plusYears(1));
			if(nextMonth == button) updateCalendar(date.plusMonths(1));
		}
	}
	
	public static String getDate() {
		return getDate(null, null);
	}
	
	public static String getDate(Frame parent) {
		return getDate(parent, null);
	}
	
	public static String getDate(String string) {
		return getDate(null, string);
	}
	
	public static String getDate(Frame parent, String format) {
		return new SimpleCalendar(parent, format).open();
	}
	
	public static void main(String[] args) {
		Gui.setLookAndFeel(Gui.NIMBUS);
		String str = new SimpleCalendar().open();
		System.out.println(str);
	}
}
