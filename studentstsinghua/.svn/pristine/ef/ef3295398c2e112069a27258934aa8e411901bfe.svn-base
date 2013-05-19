package students.tsinghua.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.baidu.bae.api.util.BaeEnv;

/**
 * database helper
 * 
 * @author hujiawei
 * 
 */
public class DBHelper {

	public Connection getConnection() {
		String host = BaeEnv.getBaeHeader(BaeEnv.BAE_ENV_ADDR_SQL_IP);
		String port = BaeEnv.getBaeHeader(BaeEnv.BAE_ENV_ADDR_SQL_PORT);
		String username = BaeEnv.getBaeHeader(BaeEnv.BAE_ENV_AK);
		String password = BaeEnv.getBaeHeader(BaeEnv.BAE_ENV_SK);
		String driverName = "com.mysql.jdbc.Driver";
		String dbUrl = "jdbc:mysql://";
		String serverName = host + ":" + port + "/";
		String databaseName = "pblJUPSXKKeVTWfLxvEf";
		String connName = dbUrl + serverName + databaseName;
		Connection connection = null;
		try {
			Class.forName(driverName);
			connection = DriverManager.getConnection(connName, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public ResultSet doQuery(Connection connection, String sql) {
		Statement statement;
		ResultSet resultSet = null;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}

	public int doUpdate(Connection connection, String sql) {
		Statement statement;
		int result = 0;
		try {
			statement = connection.createStatement();
			result = statement.executeUpdate(sql);// returns 0 (nothing done) or 1 (1 row reflected)
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
