package app.login;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import app.AppView;
import app.login.db.AirLineSignUpDAO;
import app.login.db.AirLineSignUpVO;


public class AirLineSignUp extends AppView implements ActionListener {
	
	Font   fnt      = new Font("고딕체", Font.BOLD, 15);
	JLabel titleLbl = new JLabel("회 원 가 입");
	
	JLabel         idLbl    = new JLabel("아이디");
	JTextField     idField  = new JTextField(30);
	JButton        check    = new JButton("중복 확인");
	JLabel         pwdLbl   = new JLabel("비밀번호");
	JPasswordField pwdField = new JPasswordField(30);
	JLabel         pwdcheckLbl   = new JLabel("비밀번호 확인");
	JPasswordField pwdcheckField = new JPasswordField(30);
	
	JLabel     Knamelbl   = new JLabel("이름(한글)");
	JTextField Knamefield = new JTextField(30);
	JLabel     Enamelbl   = new JLabel("이름(영문)");
	JTextField Enamefield = new JTextField(30);
	
	JLabel     telLbl     = new JLabel("연락처");
	JTextField telField   = new JTextField(30);
	JLabel     emailLbl   = new JLabel("이메일");
	JTextField emailField = new JTextField(30);
	
	JLabel genderLbl = new JLabel("성별");
	String gender[]  = { "여", "남" };
	
	DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(gender);
	JComboBox<String> genderCombo = new JComboBox<String>(model);
	JButton signUpBtn = new JButton("회원가입");
	JButton cancelBtn = new JButton("취소");
	AirLineSignUpDAO dao = new AirLineSignUpDAO();
	
	private LoginApp loginApp;
	
	public AirLineSignUp(LoginApp loginApp) {
		super(loginApp);
		this.loginApp = loginApp;
		initRootPanel();
	}

	@Override
	public void initRootPanel() {
		rootPanel = new JPanel(null);
		int x1 = 300;
		int x2 = 410;
		rootPanel.add(titleLbl).setBounds(400, 50, 200, 30);
		titleLbl.setFont(fnt);

		rootPanel.add(idLbl).setBounds(x1, 120, 100, 30);
		rootPanel.add(idField).setBounds(x2, 120, 250, 30);
		rootPanel.add(check).setBounds(410, 155, 100, 30);
		idLbl.setFont(fnt);
		idField.setFont(fnt);
		check.setFont(fnt);
		check.setBackground(new Color(200, 200, 200));
		check.setForeground(Color.white);

		rootPanel.add(pwdLbl).setBounds(x1, 190, 100, 30);
		rootPanel.add(pwdField).setBounds(x2, 190, 250, 30);
		pwdLbl.setFont(fnt);
		pwdField.setFont(fnt);

		rootPanel.add(pwdcheckLbl).setBounds(x1, 240, 100, 30);
		rootPanel.add(pwdcheckField).setBounds(x2, 240, 250, 30);
		pwdcheckLbl.setFont(fnt);
		pwdcheckField.setFont(fnt);

		rootPanel.add(Knamelbl).setBounds(x1, 290, 100, 30);
		rootPanel.add(Knamefield).setBounds(x2, 290, 250, 30);
		Knamelbl.setFont(fnt);
		Knamefield.setFont(fnt);

		rootPanel.add(telLbl).setBounds(x1, 390, 100, 30);
		rootPanel.add(telField).setBounds(x2, 390, 250, 30);
		telLbl.setFont(fnt);
		telField.setFont(fnt);

		rootPanel.add(Enamelbl).setBounds(x1, 340, 100, 30);
		rootPanel.add(Enamefield).setBounds(x2, 340, 250, 30);
		Enamelbl.setFont(fnt);
		Enamefield.setFont(fnt);

		rootPanel.add(emailLbl).setBounds(x1, 440, 100, 30);
		rootPanel.add(emailField).setBounds(x2, 440, 250, 30);
		emailLbl.setFont(fnt);
		emailField.setFont(fnt);

		rootPanel.add(genderLbl).setBounds(x1, 490, 100, 30);
		rootPanel.add(genderCombo).setBounds(x2, 490, 250, 30);
		genderLbl.setFont(fnt);
		genderCombo.setFont(fnt);
		genderCombo.setBackground(Color.white);

		rootPanel.add(signUpBtn).setBounds(400, 570, 100, 30);
		rootPanel.add(cancelBtn).setBounds(530, 570, 100, 30);
		signUpBtn.setFont(fnt);
		signUpBtn.setBackground(new Color(200, 200, 200));
		signUpBtn.setForeground(Color.white);
		signUpBtn.setEnabled(false);
		cancelBtn.setFont(fnt);
		cancelBtn.setBackground(new Color(200, 200, 200));
		cancelBtn.setForeground(Color.white);

		rootPanel.setBackground(Color.white);
//		setSize(900, 700);

		check.addActionListener(this);
		signUpBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
	}
	
	// 이벤트 등록
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		if (obj instanceof JButton) {
			String btn = ae.getActionCommand();
			if (btn.equals("회원가입")) {
				String id = idField.getText();
				String password = pwdField.getText();
				String pwdCheck = pwdcheckField.getText();
				if (id.equals("")) {
					JOptionPane.showMessageDialog(rootPanel, "아이디를 입력하셔야 합니다");
				} else if (password.equals("")) {
					JOptionPane.showMessageDialog(rootPanel, "비밀번호를 입력하셔야 합니다");
				} else if (password.length() < 6 || password.length() > 20) {
					JOptionPane.showMessageDialog(rootPanel, "비밀번호는 6자리 이상, 20자리 이하만 가능 합니다.");
				} else if (pwdCheck.equals("")) {
					JOptionPane.showMessageDialog(rootPanel, "비교할 비밀번호를 입력해 주시기 바랍니다");
				} else if (!password.equals(pwdCheck)) {
					JOptionPane.showMessageDialog(rootPanel, "비밀번호가 일치하지 않습니다");
				} else if (checkPWDMethod(password) == 1) {
					JOptionPane.showMessageDialog(rootPanel, "이름(한글)을 입력하셔야 합니다");
				} else if (Enamefield.getText().equals("")) {
					JOptionPane.showMessageDialog(rootPanel, "이름(영문)을 입력하셔야 합니다");
				} else if (telField.getText().equals("")) {
					JOptionPane.showMessageDialog(rootPanel, "연락처를 입력하셔야 합니다");
				} else if (emailField.getText().equals("")) {
					JOptionPane.showMessageDialog(rootPanel, "이메일을 입력하셔야 합니다");
				} else if (emailCheck(emailField.getText()) == 1) {
					JOptionPane.showMessageDialog(rootPanel, "잘못된 이메일을 입력하셨습니다");
				} else {
					
					AirLineSignUpVO vo = new AirLineSignUpVO(idField.getText(), pwdField.getText(),
							Knamefield.getText(), Enamefield.getText().toUpperCase(), telField.getText(),
							emailField.getText(), (String) genderCombo.getSelectedItem());

					int result = dao.SignUpInsert(vo);
					if (result > 0) { // 회원등록 성공함
						JOptionPane.showMessageDialog(rootPanel, "회원가입에 성공하였습니다\n원활한 이용을 위하여\n로그인 해주시기 바랍니다");
					} else { // 회원등록 실패함
						JOptionPane.showMessageDialog(rootPanel, "회원가입에 실패하였습니다\n 관리자에게 문의해 주시기 바랍니다");
					}
//					dispose();
					loginApp.openMain();
				}
			} else if (btn.equals("취소")) {
//				dispose();
				loginApp.openMain();
			} else if (btn.equals("중복 확인")) {
				String idSearch = idField.getText();
				System.out.println(idSearch.length());
				if (idSearch.equals("")) {
					JOptionPane.showMessageDialog(rootPanel, "아이디를 입력하셔야 합니다");
					// id 특수문자 포함 확인
				} else if (idSearch.length() < 6 || idSearch.length() > 15) {
					JOptionPane.showMessageDialog(rootPanel, "아이디는 6자리 이상, 15자리 이하만 가능 합니다.");
				} else if (checkIDMethod(idSearch) == 1) {
					JOptionPane.showMessageDialog(rootPanel, "아이디는 특수문자 포함이 불가능합니다");
				} else {
					List<AirLineSignUpVO> result = dao.getidCheck(idSearch);
					if (result.size() == 0) {
						JOptionPane.showMessageDialog(rootPanel, "사용 가능한 아이디 입니다");
						signUpBtn.setEnabled(true);
						idField.setEnabled(false);
					} else {
						JOptionPane.showMessageDialog(rootPanel, "등록되어 있는 아이디 입니다");
					}

				}
			}
		}
	}

	public int checkIDMethod(String id) {
		int check = 0;
		char alpha;
		int code;
		for (int i = 0; i < id.length(); i++) {
			alpha = id.charAt(i);
			code = (int) alpha;
			if (code >= 0 && code <= 47 || code >= 58 && code <= 63 || code >= 91 && code <= 96
					|| code >= 123 && code <= 127) {
				check = 1;
			}
		}
		return check;
	}

	public int checkPWDMethod(String pwd) {
		int check = 0;
		char alpha;
		int code;
		for (int i = 0; i < pwd.length(); i++) {
			alpha = pwd.charAt(i);
			code = (int) alpha;
			if (code >= 0 && code <= 32 || code >= 36 && code <= 47 || code >= 58 && code <= 63
					|| code >= 91 && code <= 96 || code >= 123 && code <= 127) {
				check = 1;

			}
		}
		return check;
	}

	public int emailCheck(String email) {
		int emailCheck = 0;
		// email에 @ 가 있는가? email에 .이 올바르게 있나 ? email에 특수문자가 있나?
		if (email.indexOf("@") == -1 || period(email) == true || specialCharacter(email) == 0) {
			emailCheck = 1;
		}
		// 0이면 이메일 체크결과 이상 없다, 1이면 이상 있다
		return emailCheck;
	}

	public Boolean period(String email) {
		String spl[] = email.split("@");
		// split은 @ 기준으로 앞뒤로 나눈다
		// spl[0] = id, spl[1] = email주소
		int num = spl[1].lastIndexOf(".") - spl[1].indexOf("."); // last와 index의 값차이를 검사

		boolean clear = false;

		// "@"의 앞단 "." 있는지 확인
		if (spl[0].indexOf(".") == -1)
			;
		else
			clear = true;

		// "@"의 뒷단 도메인 부분의 "." 들의 간격이 4칸이상 떨어지면 이메일이 아니다
		if (num < 4)
			;
		else
			clear = true;

		// 이메일에 .이 잘못되었을 경우 true를 보낸다
		return clear;
	}

	public int specialCharacter(String email) {
		String text[] = { "#", "!", "$", "%", "^", "&", "*", "(", ")", "-", "_", "+", "=", ",", "[", "]", "{", "}", ":",
				";", "'", "?", "<", ">" };

		int result = 0;
		for (int i = 0; i < text.length; i++) {
			if (email.indexOf(text[i]) == -1) {
				result = 1;
			} else {
				break;
			}
		}
		return result;
	}
}