package app.login.db;

import java.util.ArrayList;
import java.util.List;

public class AirLineLoginDAO extends DBConn {

	public AirLineLoginDAO() {}

	public ArrayList<AirLineLoginVO> LoginAllSelect() {
		
		ArrayList<AirLineLoginVO> AVO = new ArrayList<AirLineLoginVO>();
		try {
			getConn();
			sql = "select userid, userpwd from AIRLINE ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				AirLineLoginVO vo = new AirLineLoginVO(rs.getString(0), rs.getString(1));
				AVO.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return AVO;
	}

	public int getLogin(String userid, String userpwd) {
		List<AirLineLoginVO> AVO = new ArrayList<AirLineLoginVO>();

		int state = 0;
		try {
			getConn();
			sql = "select userid, userpwd from AIRLINE where userid = ? and userpwd = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setString(2, userpwd);
			rs = pstmt.executeQuery();
			if (rs.next())
				state = 1;
		} catch (Exception e) {
			System.out.println("DB 아이디 또는 패스워드 에러" + e.getMessage());
		} finally {
			dbClose();
		}
		return state;
	}

}