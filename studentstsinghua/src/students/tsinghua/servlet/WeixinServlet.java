package students.tsinghua.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qq.weixin.sdk.WeixinUtil;

/**
 * to deal with messages from Weixin server
 * 
 * @author hujiawei
 * 
 */
public class WeixinServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private WeixinUtil weixinUtil;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		weixinUtil = new WeixinUtil();
	}

	@Override
	// doGet is for check the url and token!
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/plain");
		String signature = req.getParameter("signature");
		String nonce = req.getParameter("nonce");
		String timestamp = req.getParameter("timestamp");
		if (weixinUtil.checkSignature(signature, timestamp, nonce)) {
			String echostr = req.getParameter("echostr");
			resp.getWriter().print(echostr);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/xml;charset=UTF-8");// important for showing Chinese words right
		String signature = req.getParameter("signature");
		String nonce = req.getParameter("nonce");
		String timestamp = req.getParameter("timestamp");
		if (weixinUtil.checkSignature(signature, timestamp, nonce)) {
			weixinUtil.dealMessage(req.getInputStream(), resp.getOutputStream());
		}
	}

	@Override
	public void destroy() {
		super.destroy();
		weixinUtil = null;
	}

}
