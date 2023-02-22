package app.dash;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import app.AppView;
import entity.BoardVO;
import gui.Gui;

public class BoardList extends AppView {
	
	JButton     btn1, btn2;
	JTable      jTable;
	JLabel      lbl;
	JComboBox   comboBox;
	JScrollPane pane;
	JTextField  searchTxt;
	
	BoardInsert  bInsert = null;
	BoardList   bList   = null;
	
	DashBoard dash;
	
	List<BoardVO> voList;
	
	public BoardList(DashBoard dash) {
		super(dash);
		this.dash = dash;
		initRootPanel();
	}

	@Override
	public void initRootPanel() {
		rootPanel.removeAll();
		rootPanel.setLayout(null);
		
		lbl      = new JLabel("검색조건");
		lbl.setBounds(300, 20, 56, 15);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"title", "content", "writer"}));
        comboBox.setBounds(360, 17, 74, 21);
        
		btn1     = new JButton("검색");
		btn1.setBounds(580, 16, 106, 23);
		
		searchTxt      = new JTextField();
		searchTxt.setBounds(440, 17, 133, 21);
		searchTxt.setColumns(10);
		
		btn2     = Gui.createButton("글작성", b->dash.openInsert());
		btn2.setBounds(580, 580, 97, 23);
		
		rootPanel.add(lbl);
		rootPanel.add(comboBox);
		rootPanel.add(btn1);
		rootPanel.add(searchTxt);
		rootPanel.add(btn2);
		
		// 버튼 기능 부여
		// 검색버튼 클릭
		btn1.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("검색버튼 클릭");
				
				createTable(dash.dao().search(String.valueOf(comboBox.getSelectedItem()), searchTxt.getText()));
				
			//	setVisible(false);
			}
		});
		createTable(null);
	}
	
	public void createTable(List<BoardVO> list) {
		jTable   = new JTable();
		if(list != null && !list.isEmpty()) 
			voList = list;
		else
			voList = dash.dao().select();
		String[] colNames = new String[] {"글번호", "제목", "내용...", "작성자", "작성일"};
        Object[][] rowDatas = new Object[voList.size()][colNames.length];
        
        for (int i = 0; i < voList.size(); i++) {
            rowDatas[i] = new Object[] {
            		voList.get(i).getNum(),
            		voList.get(i).getTitle(),
            		voList.get(i).getContent(),
            		voList.get(i).getWriter(),
            		voList.get(i).getRegdate()
            };
        }
        
		// data를 model에 담에서 채움
		jTable.setModel( 
			new DefaultTableModel( rowDatas, colNames ) {
				boolean[] columnEditables = new boolean[] {
		                false, false, false, true, false
		            };
		        public boolean isCellEditable(int row, int column) {
		            return columnEditables[column];
		        }
			}
		);
		
		jTable.getColumnModel().getColumn(0).setResizable(false);
        jTable.getColumnModel().getColumn(0).setPreferredWidth(70);
        jTable.getColumnModel().getColumn(1).setResizable(false);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(300);
        jTable.getColumnModel().getColumn(2).setResizable(false);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(300);
        jTable.getColumnModel().getColumn(4).setResizable(false);
        jTable.getColumnModel().getColumn(4).setPreferredWidth(200);
       
        jTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowNum = jTable.getSelectedRow();
                BoardVO vo = new BoardVO();
                vo = voList.get(rowNum);
               
                dash.openUpdate(vo);
            }
        });
		
        if(pane != null) rootPanel.remove(pane);
		pane = new JScrollPane(jTable);
		pane.setBounds(20, 49, 670, 520);
		rootPanel.add(pane);
	}
}
