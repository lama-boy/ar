package test;

import dao.DAO;
import dao.SqlUtil;
import entity.Member;

public class T {
public static void main(String[] args) {
	SqlUtil sql = new SqlUtil(DAO.initDataSource());
	Member member = new Member();
	member.setId("asdasd12qwewqe3");
	sql.insert("members", member);
	}
}