package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import app.reserv.SelectSeat;
import entity.AirplaneDTO;

public class AirplaneDAO {
		private Connection conn = null;
		
		SelectSeat selectSeat;
		
		//Constructor
		public AirplaneDAO() {
			conn = DBConn.getInstance();
		}
		public void close() {
			try {
				if(conn != null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		//비행기 조회
		public Vector<Vector> getAirSchedule() {
			
			Vector<Vector> list = new Vector<Vector>();
			
			AirplaneDTO schedule = null;
			
			String sql = "";
			sql       += "SELECT * ";
			sql       += " FROM AIRPLAN";
			
			
			PreparedStatement pstmt = null;
			ResultSet         rs    = null;
			try {
				pstmt = conn.prepareStatement(sql);
				
				rs    = pstmt.executeQuery();
				while(rs.next()) {
					String airNum   = rs.getString("AIRNUM");
					String depPlace = rs.getString("DEPPLACE"); 
					String arrPlace = rs.getString("ARRPLACE");
					String depTime  = rs.getString("DEPTIME"); 
					String arrTime  = rs.getString("ARRTIME"); 
					String startDate  = rs.getString("STARTDATE");
					String endDate = rs.getString("ENDDATE");
					
					Vector v = new Vector();
					v.add(airNum);
					v.add(depPlace);
					v.add(depTime);
					v.add(arrPlace);
					v.add(arrTime);
					v.add(startDate);
					v.add(endDate);
					
					list.add(v);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					if(rs    != null)rs.close();
					if(pstmt != null)pstmt.close();
				} catch (SQLException e) {
				}
			}
			
			return list;
		}
		
}
