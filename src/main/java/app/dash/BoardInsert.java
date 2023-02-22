package app.dash;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import app.AppService;
import app.AppView;
import entity.BoardVO;

public class BoardInsert extends AppView {
	private JTextField title;
    private JTextField writer;
            DashBoard  dash;
            JFrame     frame;
            BoardList  bList = null;
  
    public BoardInsert(DashBoard dash) {
    	this.dash = dash;
    	rootPanel.setLayout(null);
    }

	@Override
	public void initRootPanel() {
		rootPanel.removeAll();
		 
        JLabel lblNewLabel = new JLabel("글제목");
        lblNewLabel.setBounds(12, 25, 57, 15);
        rootPanel.add(lblNewLabel);
 
        title = new JTextField("글제목을 입력해주세요.");
        title.setBounds(81, 22, 600, 25);
        rootPanel.add(title);
        title.setColumns(10);
 
        JLabel lblNewLabel_1 = new JLabel("글내용");
        lblNewLabel_1.setBounds(12, 59, 57, 15);
        rootPanel.add(lblNewLabel_1);
 
        JTextArea textArea = new JTextArea("글내용을 입력해주세요.");
        textArea.setLineWrap(true);
        textArea.setRows(5);
        textArea.setBounds(81, 53, 600, 480);
        rootPanel.add(textArea);
 
        JLabel lblNewLabel_2 = new JLabel("작성자");
        lblNewLabel_2.setBounds(12, 550, 57, 15);
        rootPanel.add(lblNewLabel_2);
 
        writer = new JTextField(" ");
        writer.setBounds(81, 545, 116, 25);
        rootPanel.add(writer);
        writer.setColumns(10);
 
        JButton btnWrite = new JButton("작성완료");
        btnWrite.setBounds(450, 550, 116, 23);
        btnWrite.addActionListener(new ActionListener() {
 
            @Override
            public void actionPerformed(ActionEvent e) {
                BoardDao dao = new BoardDao();
                BoardVO vo = new BoardVO();
 
                String titles = title.getText();
                String txtarea = textArea.getText();
                String name = writer.getText();
 
                vo.setTitle(titles);
                vo.setContent(txtarea);
                vo.setWriter(name);
 
                dao.insert(vo);
                
                
                // 현재창 닫기
                dash.openList();
            }
        });
        rootPanel.add(btnWrite);
 
        JButton btnClose = new JButton("닫기");
        btnClose.setBounds(580, 550, 97, 23);
        btnClose.addActionListener(new ActionListener() {
 
            @Override
            public void actionPerformed(ActionEvent e) {
            	AppService.getInstance().closeView(BoardInsert.this);
                dash.openList();
            }
        });
        rootPanel.add(btnClose);		
	}
}
