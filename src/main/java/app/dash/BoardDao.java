package app.dash;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.DAO;
import entity.BoardVO;

public class BoardDao {
	public int insert( int num, String title, String content, 
			String writer, Date regDate) {
		
		String sql = " INSERT INTO BOARD ";
		sql		+= " (NUM, TITLE, CONTENT, WRITER, REGDATE) ";
		sql		+= " VALUES ";
		sql		+= " (Board_SEQ. NEXTVAL,     ?,       ?,      ?, SYSDATE )";
        
		try(Connection  conn = DAO.getConnection();
			PreparedStatement pstmt  = conn.prepareStatement(sql);) {
			
			pstmt.setString(1, title);
            pstmt.setString(2, content);
            pstmt.setString(3, writer);
 
            return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int insert(BoardVO vo) {
		int      num      = vo.getNum();
		String   title    = vo.getTitle();
		String   content  = vo.getContent();
		String   writer   = vo.getWriter();
		Date     regDate  = vo.getRegdate();

		int aftcnt = insert(num, title, content, writer, regDate);
		return aftcnt;
	}
	
	// 데이터 수정
	public int update(BoardVO vo) {
		String sql = " UPDATE BOARD ";
		sql		+= "  SET   TITLE=?,";
		sql		+= "        CONTENT=?,";
		sql		+= "        WRITER=?, ";
		sql		+= "        REGDATE=SYSDATE ";
		sql		+= " WHERE  NUM=? ";

		int aftcnt = 0; 
    	try(Connection conn = DAO.getConnection();
    		PreparedStatement pstmt  = conn.prepareStatement(sql);) {
    			
			pstmt.setString(1, vo.getTitle());
            pstmt.setString(2, vo.getContent());
            pstmt.setString(3, vo.getWriter());
            pstmt.setInt(4, vo.getNum());
 
            return pstmt.executeUpdate();
            
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	// 데이터 삭제
	public int delete(BoardVO vo) {
		
		String  sql = "";
		sql += " DELETE FROM BOARD ";
		sql += "  WHERE NUM = ? ";
		
		int aftcnt = 0;
		try(Connection  conn = DAO.getConnection();
				PreparedStatement pstmt  = conn.prepareStatement(sql);) {
			pstmt.setInt(1, vo.getNum());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	
	public List<BoardVO> select() { 
		List<BoardVO> list = new ArrayList<BoardVO>();
	
		String sql = "SELECT NUM, TITLE, CONTENT,";
		sql		+= " WRITER, REGDATE ";
		sql		+= " FROM BOARD";
		sql		+= " ORDER BY NUM DESC";
		
		try (Connection  conn = DAO.getConnection();
				PreparedStatement pstmt  = conn.prepareStatement(sql);
				ResultSet rs    = pstmt.executeQuery();
				) {

			while( rs.next() ) {
				BoardVO vo = new BoardVO();
				vo.num     = rs.getInt("num");
				vo.title   = rs.getString("title");
				vo.content = rs.getString("content");  
				vo.writer  = rs.getString("writer");
				vo.regdate = rs.getDate("regdate"); 
				
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// 검색
	public List<BoardVO> search(String search, String searchString) {
		List<BoardVO> list = new ArrayList<BoardVO>();
		String sql = "SELECT NUM, TITLE, CONTENT, WRITER, REGDATE";
		sql		+= " FROM BOARD ";
		sql		+= " WHERE " + search + " LIKE '%";
		sql     +=  searchString + "%' ORDER BY NUM DESC";
		
		System.out.println(sql);
		try (Connection  conn = DAO.getConnection();
				PreparedStatement pstmt  = conn.prepareStatement(sql);
				ResultSet rs    = pstmt.executeQuery(); ) {

			while ( rs.next() ) {
			BoardVO vo = new BoardVO();
			vo.setNum(rs.getInt("num"));
			vo.setTitle(rs.getString("title"));
			vo.setContent(rs.getString("content"));
			vo.setWriter(rs.getString("writer"));
			vo.setRegdate(rs.getDate("regdate"));
			
			list.add(vo);
		} 
		
        } catch (SQLException e1) {
			e1.printStackTrace();
        }
//		System.out.println(list);
		return list;
	}
	
}