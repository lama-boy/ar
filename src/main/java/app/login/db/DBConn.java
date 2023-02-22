package app.login.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConn {
	private static String driver = "oracl.jdbc.OracleDriver";
	private static String dburl  = "jdbc:oracle:thin:@localhost:1521:xe";
	private static String dbid   = "ar";
	private static String dbpwd  = "1234";
	String sql              = null;
	Connection conn         = null;
	PreparedStatement pstmt = null;
	ResultSet rs            = null;
	
	public DBConn() {}
	
	public void getConn() {
		try {
			conn = DriverManager.getConnection(dburl,dbid,dbpwd);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void dbClose() {
		
		try {
			if(rs!=null)rs.close();
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}