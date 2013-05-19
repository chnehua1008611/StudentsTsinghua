package com.qq.weixin.sdk;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.qq.weixin.sdk.message.IMessage;
import com.qq.weixin.sdk.message.Message;
import com.qq.weixin.sdk.message.MessageEvent;
import com.qq.weixin.sdk.message.MessageImage;
import com.qq.weixin.sdk.message.MessageLink;
import com.qq.weixin.sdk.message.MessageLocation;
import com.qq.weixin.sdk.message.MessageText;
import com.qq.weixin.sdk.messageHadler.MessageHandlerHelper;
import com.qq.weixin.sdk.messageHadler.MessageHandlerEvent;
import com.qq.weixin.sdk.messageHadler.MessageHandlerImage;
import com.qq.weixin.sdk.messageHadler.MessageHandlerLink;
import com.qq.weixin.sdk.messageHadler.MessageHandlerLocation;
import com.qq.weixin.sdk.messageHadler.MessageHandlerText;
import com.qq.weixin.sdk.messageResultHadler.MessageResultHandlerHelper;
import com.qq.weixin.sdk.messageResultHadler.MessageResultHandlerMusic;
import com.qq.weixin.sdk.messageResultHadler.MessageResultHandlerNews;
import com.qq.weixin.sdk.messageResultHadler.MessageResultHandlerText;

/**
 * weixin tool class
 * 
 * @author hujiawei
 * 
 */
public class WeixinUtil implements IMessage {

	private Logger logger = Logger.getLogger("weixin");
	private HttpServletRequest request;// request
	private Message message;// message comes from
	private HttpServletResponse response;// response
	private Message messageResult;// message will return
	private MessageHandlerHelper messageHadler;// handle message
	private MessageResultHandlerHelper messageResultHandler;// handle result message

	// deal the message from user
	public void dealMessage(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		try {
			// logger.log(Level.INFO, "request character encoding :" + request.getCharacterEncoding());//null
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/xml;charset=UTF-8");// important!must set charset=UTF-8,otherwise words are not shown right
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		parseInputStreamToMessage();
		messageResult = messageHadler.handleMessage(message);
		writeMessageResult();
	}

	// write the result message
	private void writeMessageResult() {
		if (messageResult.getMsgType().equalsIgnoreCase(MESSAGE_RESULT_TEXT)) {
			messageResultHandler = new MessageResultHandlerText();
		} else if (messageResult.getMsgType().equalsIgnoreCase(MESSAGE_RESULT_NEWS)) {
			messageResultHandler = new MessageResultHandlerNews();
		} else if (messageResult.getMsgType().equalsIgnoreCase(MESSAGE_RESULT_MUSIC)) {
			messageResultHandler = new MessageResultHandlerMusic();
		}
		try {
			response.getWriter().print(messageResultHandler.buildMessageResult(messageResult));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// parse the message content to Message
	private void parseInputStreamToMessage() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			Document document = factory.newDocumentBuilder().parse(request.getInputStream());
			Element root = document.getDocumentElement();
			String type = (root.getElementsByTagName(TAG_MSGTYPE)).item(0).getTextContent();// filter CDATA... -- text/image/...
			if (type.equalsIgnoreCase(MESSAGE_TEXT)) {
				message = new MessageText();
				messageHadler = new MessageHandlerText();
			} else if (type.equalsIgnoreCase(MESSAGE_EVENT)) {
				message = new MessageEvent();
				messageHadler = new MessageHandlerEvent();
			} else if (type.equalsIgnoreCase(MESSAGE_IMAGE)) {
				message = new MessageImage();
				messageHadler = new MessageHandlerImage();
			} else if (type.equalsIgnoreCase(MESSAGE_LINK)) {
				message = new MessageLink();
				messageHadler = new MessageHandlerLink();
			} else if (type.equalsIgnoreCase(MESSAGE_LOCATION)) {
				message = new MessageLocation();
				messageHadler = new MessageHandlerLocation();
			}
			messageHadler.parseMessage(message, root);// do the default/common parse!
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public MessageHandlerHelper getMessageHadler() {
		return messageHadler;
	}

	public void setMessageHadler(MessageHandlerHelper messageHadler) {
		this.messageHadler = messageHadler;
	}

	public Message getMessageResult() {
		return messageResult;
	}

	public void setMessageResult(Message messageResult) {
		this.messageResult = messageResult;
	}

	public MessageResultHandlerHelper getMessageResultHandler() {
		return messageResultHandler;
	}

	public void setMessageResultHandler(MessageResultHandlerHelper messageResultHandler) {
		this.messageResultHandler = messageResultHandler;
	}

}