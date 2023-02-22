package app.login.db;

import java.util.ArrayList;
import java.util.List;

public class AirLineSignUpDAO extends DBConn{

	public AirLineSignUpDAO() {}
	// 회원가입 db insert
	public int SignUpInsert(AirLineSignUpVO vo) {
		int result = 0;
		try{
			getConn();
			String sql = "";
			sql += "INSERT INTO AIRLINE ";
			sql += "  ( ID, NAME, PASSWORD, PHONE, EMAIL, GENDER, INDATE )";
			sql += " VALUES ";
			sql += "  ( ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getID());
			pstmt.setString(2, vo.getNAME());
			pstmt.setString(3, vo.getPASSWORD());
			pstmt.setString(4, vo.getPHONE());
			pstmt.setString(5, vo.getEMAIL());
			pstmt.setString(6, vo.getGENDER());
			pstmt.setString(7, vo.getINDATE());
//			pstmt.setInt(8, 0);
//			pstmt.setString(9, "White");
			
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally{
			dbClose();
		}
		return result;
	}
	// 회원 아이디 비밀번호 검색, 회원 유무 확인
	public List<AirLineSignUpVO> getidCheck(String getID){
		List<AirLineSignUpVO> lst = new ArrayList<AirLineSignUpVO>();		
		try {
			getConn();
			sql = "select ID from AIRLINE where ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, getID);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				AirLineSignUpVO vo = new AirLineSignUpVO();
				vo.setID(rs.getString(1));
				
				lst.add(vo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		
		return lst;
	}
}