package entity.mapper;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import entity.Member;

public class MemberRowMapper implements RowMapper<Member>{
	
	@Override
	public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
		Member member = new Member();
		member.setId(rs.getString("ID"));
		member.setName(rs.getString("NAME"));
		member.setEmail(rs.getString("EMAIL"));
		return member; 
	}
}
