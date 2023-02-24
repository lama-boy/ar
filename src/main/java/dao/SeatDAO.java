package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.SeatDTO;


public class SeatDAO {
	private Connection conn = null;
	
	//Constructor
	public SeatDAO() {
		conn = DBConn.getInstance();
	}
	
	
	public void close() {
		try {
			if(conn != null)conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public ArrayList<String> getSeatList(String airnum, String depDate) {
		
		String sql = "";
		sql += "SELECT S.SEATNUMBER, S.DEPDATE ";
		sql += " FROM SEAT S JOIN A.AIRPLANE  ON S.AIRNUM = A.AIRNUM"	;
		sql += " WHERE S.AIRNUM = ? AND S.DEPDATE = ? AND S.RESERVED = 'Y'";
		ArrayList<String> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, airnum);
			pstmt.setString(2, depDate);
			rs    = pstmt.executeQuery();
			while(rs.next()) {
				String seatNumber = rs.getString("SEATNUM");
				list.add(seatNumber);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if( rs    != null )rs.close();
				if( pstmt != null )pstmt.close();
			}catch(SQLException e) {
			}
		}
		
		return list;
	}


	public void setSeatReserved(SeatDTO seatDTO) {
		String airnum = seatDTO.getAirnum();
		String seatNumber = seatDTO.getSeatNumber();
		String depdate = seatDTO.getDepDate();
		
		String sql = "";
		sql += "INSERT INTO SEAT ";
		sql += " VALUES (?,?,?,?) ";
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, airnum);
			pstmt.setString(2, seatNumber);
			pstmt.setString(3, "Y");
			pstmt.setString(4, depdate);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			}try {
				if( pstmt != null)pstmt.close();
		} catch (SQLException e) {
		}
	}
	public ArrayList<Integer> getRowAndCol(String airnum, String seatgrade) {
	
	String sql   = "";
	sql         += "SELECT SUBSTR(SEATNUMBER,1,1) ROW, SUBSTR(SEATNUMBER2,1) FROM SEAT COL";
	sql         += " WHERE AIRNUM = ?  AND SEATGARDE = ?";
	ArrayList<Integer> list = new ArrayList<>();
		
	PreparedStatement pstmt = null;
	ResultSet rs = null;
		
	try {
		pstmt = conn.prepareStatement(sql);
		if(rs.next()) {
			int row = rs.getInt("ROW");
			int col = rs.getInt("COL");
			list.add(row);
			list.add(col);
		}
		pstmt.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
	try {
		if( rs    != null )rs.close();
		if( pstmt != null)pstmt.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}
	return list;
		
	}

}
}



	
	
