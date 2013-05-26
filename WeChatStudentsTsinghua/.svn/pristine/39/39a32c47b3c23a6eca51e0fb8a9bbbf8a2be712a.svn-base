package students.tsinghua.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CronResultServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String reason = req.getParameter("reason");
		String url = req.getParameter("url");
		if (reason != null) {
			int code = Integer.parseInt(reason);
			if (code == 3) {
				resp.getWriter().println("cron for url " + url + ", result is success");
			} else if (code == 4) {
				resp.getWriter().println("cron for url " + url + ", result is failure");
			} else {// 5
				resp.getWriter().println("cron for url " + url + ", result is timeout");
			}
		}
	}

}
