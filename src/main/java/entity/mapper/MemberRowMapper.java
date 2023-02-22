package entity.mapper;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import entity.MemberDTO;

public class MemberRowMapper implements RowMapper<MemberDTO>{
	
	@Override
	public MemberDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		MemberDTO member = new MemberDTO();
		member.setId(rs.getString("ID"));
		member.setName(rs.getString("NAME"));
		member.setPassword(rs.getString("PASSWORD"));
		return member; 
	}
}
