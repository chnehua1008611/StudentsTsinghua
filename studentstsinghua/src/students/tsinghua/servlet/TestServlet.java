package students.tsinghua.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import students.tsinghua.dao.DBHelper;

public class TestServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello BAE World!");
		String sql = "select * from TShow";
		DBHelper dbHelper = new DBHelper();
		Connection connection = dbHelper.getConnection();
		try {
			resp.getWriter().println(connection.getMetaData().getURL().toString());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		ResultSet rs = null;
		try {
			rs = dbHelper.doQuery(connection, sql);
			String name = "";
			while (rs.next()) {
				name = rs.getString("name");
				resp.getWriter().println(name);
			}
		} catch (SQLException e) {
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
			}
		}

		// for test the request content
		// BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));
		// String line = null;
		// StringBuffer messageBuffer = new StringBuffer();
		// while ((line = reader.readLine()) != null) {
		// messageBuffer.append(line + "\n");
		// }
		// resp.getWriter().print(messageBuffer.toString());//for test
	}

}
