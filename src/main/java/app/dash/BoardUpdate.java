package app.dash;

import java.awt.Rectangle;
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
import gui.Gui;

public class BoardUpdate extends AppView {
	private JTextField title;
    private JTextField writer;
    JTextArea content = new JTextArea();

    DashBoard dash;
    BoardVO vo;
    public BoardUpdate(DashBoard dash) {
    	super(dash);
        this.dash = dash;
        
        // 게시글 수정
        initRootPanel();
    }
    
    public void setData(BoardVO vo) {
    	this.vo = vo;
    	title.setText(vo.getTitle());
    	writer.setText(vo.writer);
    	content.setText(vo.getContent());
    }
    
    public void getFormData() {
    	vo.setTitle(title.getText());
    	vo.setWriter(writer.getText());
    	vo.setContent(content.getText());
    }
    
	@Override
	public void initRootPanel() {
		rootPanel.setLayout(null);
		 
        JLabel lblNewLabel = new JLabel("글제목");
        lblNewLabel.setBounds(12, 25, 57, 15);
        rootPanel.add(lblNewLabel);
 
        title = new JTextField();
        title.setBounds(81, 22, 340, 21);
        rootPanel.add(title);
        title.setColumns(10);
 
        JLabel lblNewLabel_1 = new JLabel("글내용");
        lblNewLabel_1.setBounds(12, 59, 57, 15);
        rootPanel.add(lblNewLabel_1);
 
        content.setLineWrap(true);
        content.setRows(5);
        content.setBounds(81, 53, 440, 200);
        rootPanel.add(content);
 
        JLabel lblNewLabel_2 = new JLabel("작성자");
        lblNewLabel_2.setBounds(12, 140, 57, 15);
        rootPanel.add(lblNewLabel_2);
 
        writer = new JTextField();
        writer.setBounds(81, 137, 116, 21);
        rootPanel.add(writer);
        writer.setColumns(10);
 
        JButton btnWrite = new JButton("글수정");
        btnWrite.setBounds(81, 180, 97, 23);
        btnWrite.addActionListener(new ActionListener() {
 
            @Override
            public void actionPerformed(ActionEvent e) {
            	getFormData();
                dash.dao().update(vo);
                dash.openList();
            }
        });
        rootPanel.add(btnWrite);
 
        JButton btnDel = new JButton("글삭제");
        btnDel.setBounds(190, 180, 97, 23);
        btnDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dash.dao().delete(vo);
                dash.openList();
            }
        });
        rootPanel.add(btnDel);
 
        JButton btnClose = Gui.createButton("닫기", b->dash.openList());
        btnClose.setBounds(299, 180, 97, 23);
        rootPanel.add(btnClose);
        
        JButton open = new JButton("OPEN");
        open.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//s
				System.out.println(123213);
			}
		});
        
        JButton open2 = Gui.createButton("닫기", b->System.out.println(123214));
        JButton open3 = Gui.createButton("닫기2", b->System.out.println(123214));
        JButton open4 = Gui.createButton("닫기3", b->System.out.println(123214));

        rootPanel.add(open);
        rootPanel.add(open2);
        rootPanel.add(open3);
        rootPanel.add(open4);
        
        open.setBounds(299, 380, 97, 23);
        open2.setBounds(299, 480, 97, 23);
        open3.setBounds(299, 520, 97, 23);
        open4.setBounds(299, 580, 97, 23);

	}
}
