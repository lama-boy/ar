package app.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import app.AppView;
import app.ArApplication;
import app.login.db.AirLineLoginDAO;
import gui.Gui;

public class AirLineMain extends AppView implements ActionListener {
	  
	//사진
	JLabel           lbl = Gui.createIconLabel(ArApplication.IMG_PATH+"loginplane.jpg");

	Font   titleFnt   = new Font("고딕체", Font.BOLD, 50);
	JPanel loginpane  = new JPanel();
	JLabel logintitle = new JLabel("WELCOME AIRLINE", JLabel.CENTER);
	JPanel centerpane = new JPanel();
	JPanel southpane  = new JPanel();
	
	JPanel     idpane  = new JPanel();
	JLabel     idlbl   = new JLabel("Login ID  : ");
	JTextField idfield = new JTextField(20);
	JPanel     pwdpane = new JPanel();
	JLabel     pwdlbl  = new JLabel(" PW         : ");
	JPasswordField pwdfield = new JPasswordField(20);
	
	JPanel  btnp     = new JPanel();
	JButton joinbtn  = new JButton("Join");
	JButton loginbtn = new JButton("Login");
  
	LoginApp loginApp;
	
	public AirLineMain(LoginApp loginApp) {
		super(loginApp);
		this.loginApp = loginApp;
		initRootPanel();
	}

	//이벤트 등록
	public void actionPerformed(ActionEvent ae) {
		Object eventBtn = ae.getSource();
		if (eventBtn == joinbtn) {
			loginApp.openSignUp();
		} else if (eventBtn == loginbtn) {
			getLoginData();
		}
	}

	//login DB
	public void getLoginData() {
		String user_id = idfield.getText();
		String user_pwd = pwdfield.getText();
		String admin[] = { "cjswo" };
		AirLineLoginDAO dao = new AirLineLoginDAO();
		
		int customLogin = 0;
		for (int i = 0; i < admin.length; i++) {
			if (user_id.equals(admin[i])) {
				int loginCheck = dao.getLogin(user_id, user_pwd);
				if (loginCheck == 0)
					JOptionPane.showMessageDialog(rootPanel, "아이디 또는 비밀번호가 틀렸습니다.");
				else if (loginCheck == 1) {
					customLogin = 1;
					System.out.println("dispose()");
					break;
				}
			}
		}
		
		if (user_id.equals("")) {
			JOptionPane.showMessageDialog(rootPanel, "아이디를 입력하세요.");
		} else if (user_pwd.equals("")) {
			JOptionPane.showMessageDialog(rootPanel, "비밀번호를 입력하세요.");
		} else {
			int loginCheck = dao.getLogin(user_id, user_pwd);
			if (loginCheck == 0)
				JOptionPane.showMessageDialog(rootPanel, "아이디 또는 비밀번호가 틀렸습니다.");
			else if (loginCheck == 1) {
//				dispose();
				System.out.println("dispose()");
			}
		}
	}

	public static void main(String[] args) {
		new AirLineMain(null);
	}

	@Override
	public void initRootPanel() {
		rootPanel.setLayout(new GridBagLayout());

		// 그리드백 제약사항을 정의
		GridBagConstraints gbc = new GridBagConstraints();

		// 전체를 채운다
		gbc.fill = GridBagConstraints.BOTH;

		// grid top
		gbc.weightx = 2.0;
		gbc.weighty = 1.5;
		gbc.gridx = 0;
		gbc.gridy = 1;
		rootPanel.add(loginpane, gbc);
		loginpane.setLayout(new BorderLayout());
		loginpane.add(logintitle);
		logintitle.setFont(titleFnt);
		
		// grid center
		gbc.weightx = 1.0;
		gbc.weighty = 3.0;
		gbc.gridx = 0;
		gbc.gridy = 2;
		rootPanel.add(centerpane, gbc);
		centerpane.setLayout(new BorderLayout());
		centerpane.add(lbl);
		centerpane.setBackground(Color.white);

		// grid south
		gbc.weightx = 1.0;
		gbc.weighty = 2.0;
		gbc.gridx = 0;
		gbc.gridy = 3;
		rootPanel.add(southpane, gbc);
		southpane.setLayout(new GridLayout(4, 1));
		southpane.setBackground(Color.white);
		 
		// ID
		southpane.add(idpane);
		idpane.add(idlbl);
		idpane.add(idfield);
		idpane.setBackground(Color.white);
		
		// PASSWORD
		southpane.add(pwdpane);
		pwdpane.add(pwdlbl);
		pwdpane.add(pwdfield);
		pwdpane.setBackground(Color.white);
		
		// BUTTON
		southpane.add(btnp);
		btnp.add(joinbtn);
		joinbtn.setBackground(new Color(100, 130, 255));
		joinbtn.setForeground(Color.black);
		btnp.add(loginbtn);
		loginbtn.setBackground(new Color(100, 130, 255));
		loginbtn.setForeground(Color.black);
		btnp.setLayout(new FlowLayout(FlowLayout.CENTER));
		btnp.setBackground(Color.white);

		// 이벤트
		joinbtn.addActionListener(this);
		loginbtn.addActionListener(this);
		
		// LOGIN 이벤트
		pwdfield.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_ENTER) {
					getLoginData();
				}
			}
		});
	}
}