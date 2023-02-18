package test;

import static test.Debug.sysout;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import app.ArApplication;
import dao.DAO;
import dao.SqlUtil;
import entity.Member;
import gui.Gui;

public class Test {
	public Test() {
		Properties config = new Properties();
		try {
			config.load(new FileReader("src/main/resources/config.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DAO.initDataSource(config.getProperty("url"), config.getProperty("user"), config.getProperty("password"));
//		JdbcTemplate t = new JdbcTemplate(DAO.getDataSource());
		SqlUtil s = new SqlUtil(DAO.getDataSource());
		Member member = new Member();
		member.setId("123");
		member.setName("aa123");
		s.insert("members", member);
		
		Gui.createFrame(Gui.createTable(null));
	}
	
	public static void main(String[] args) {
		sysout("Start Test");
		new Test();
		sysout("End Test");
	}
}
