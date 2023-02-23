package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import entity.TicketDTO;

public class TicketDAO {
	private Connection conn = null;
	
	//Constructor
	public TicketDAO() {
		conn = DBConn.getInstance();
	}
	
	
	public void close() {
		try {
			if(conn != null)conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insert(TicketDTO ticket) { // setTicket이라고 생각하면 됨.
		String customerName   = ticket.getCustomerName();
		String customerId     = ticket.getCustomerId();
		String seatNumber     = ticket.getSeatNumber();
		String airNum         = ticket.getAirNum();
		String depDate        = ticket.getDepDate();
		int cost              = ticket.getCost();
		int kidCnt            = ticket.getKidCnt();
		int adultCnt          = ticket.getAdultCnt();
		int HumanCnt          = ticket.getHumanCnt();
		
		SimpleDateFormat f = new SimpleDateFormat("yyyy년 MM월dd일 HH시mm분ss초");
		Calendar c = Calendar.getInstance();
		String reserveDate = f.format(c.getTime()); // 현재날짜를 전달.
		ticket.setReserveDate(reserveDate);
		
		String sql = "INSERT INTO TICKET VALUES(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, customerName);
			pstmt.setString(2, customerId);
			pstmt.setString(3, seatNumber);
			pstmt.setString(4, airNum);
			pstmt.setString(5, reserveDate);
			pstmt.setString(6, depDate);
			pstmt.setInt(7, cost);
			pstmt.setInt(8, kidCnt);
			pstmt.setInt(9, adultCnt);
			pstmt.setInt(10, HumanCnt);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
		try {
			if(pstmt != null)pstmt.close();
		} catch (SQLException e) {
			}
		}
	}

}
