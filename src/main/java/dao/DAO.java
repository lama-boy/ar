package dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import test.Debug;

public class DAO {
	private static final String url = "jdbc:oracle:thin:@//192.168.0.213:1521/xe";
	private static final String user = "ar";
	private static final String password = "1234";
	
	private DAO() {}

	private static HikariDataSource hikariDataSource;

	public static Connection getConnection() {
		try {
			return getDataSource().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static DataSource getDataSource() {
		if(hikariDataSource == null) {
			return initDataSource();
		}else 
			return hikariDataSource;
	}

	public static DataSource initDataSource() {
		return initDataSource(url, user, password);
	}
	
	public static DataSource initDataSource(String url, String user, String password) {
		if(hikariDataSource == null) {
			HikariConfig config = new HikariConfig();
			config.setJdbcUrl(url);
			config.setUsername(user);
			config.setPassword(password);
			config.setMaximumPoolSize(3);
			config.setConnectionTimeout(30000);
			config.setValidationTimeout(5000);
			return hikariDataSource = new HikariDataSource(config);
		}
		return hikariDataSource;
	}
}
