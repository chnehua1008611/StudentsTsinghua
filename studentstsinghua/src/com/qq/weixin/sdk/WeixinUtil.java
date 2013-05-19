package com.qq.weixin.sdk;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.codec.digest.DigestUtils;
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
import com.qq.weixin.sdk.message.handler.MessageHandlerEvent;
import com.qq.weixin.sdk.message.handler.MessageHandlerHelper;
import com.qq.weixin.sdk.message.handler.MessageHandlerImage;
import com.qq.weixin.sdk.message.handler.MessageHandlerLink;
import com.qq.weixin.sdk.message.handler.MessageHandlerLocation;
import com.qq.weixin.sdk.message.handler.MessageHandlerText;
import com.qq.weixin.sdk.message.result.handler.MessageResultHandlerHelper;
import com.qq.weixin.sdk.message.result.handler.MessageResultHandlerMusic;
import com.qq.weixin.sdk.message.result.handler.MessageResultHandlerNews;
import com.qq.weixin.sdk.message.result.handler.MessageResultHandlerText;

/**
 * weixin tool class
 * 
 * @author hujiawei
 * 
 */
public class WeixinUtil implements IMessage {

	private Logger logger = Logger.getLogger("weixin");
	private InputStream inputStream;// request inputstream
	private Message message;// message comes from
	private OutputStream outputStream;// response outputstream
	private Message messageResult;// message will return
	private MessageHandlerHelper messageHadler;// handle message
	private MessageResultHandlerHelper messageResultHandler;// handle result message

	private static final String TOKEN = "StudentsTsinghua";

	// deal the message from user
	public void dealMessage(InputStream inputStream, OutputStream outputStream) {
		this.inputStream = inputStream;
		this.outputStream = outputStream;
		try {
			parseInputStreamToMessage();
		} catch (Exception e) {
			e.printStackTrace();// parse message occurs exception
		}
		messageResult = messageHadler.handleMessage(message);
		try {
			writeMessageResult();
		} catch (IOException e) {
			e.printStackTrace();// write message occurs exception
		}
	}

	// write the result message
	private void writeMessageResult() throws IOException {
		if (messageResult.getMsgType().equalsIgnoreCase(MESSAGE_RESULT_TEXT)) {
			messageResultHandler = new MessageResultHandlerText();
		} else if (messageResult.getMsgType().equalsIgnoreCase(MESSAGE_RESULT_NEWS)) {
			messageResultHandler = new MessageResultHandlerNews();
		} else if (messageResult.getMsgType().equalsIgnoreCase(MESSAGE_RESULT_MUSIC)) {
			messageResultHandler = new MessageResultHandlerMusic();
		}
		String resultContent = messageResultHandler.buildMessageResult(messageResult);
		// String resultContent = ResourceManager.getValue("result");
		logger.log(Level.INFO, resultContent);
		new PrintWriter(outputStream).print(resultContent);
	}

	// parse the message content to Message
	private void parseInputStreamToMessage() throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		Document document = factory.newDocumentBuilder().parse(inputStream);
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
	}

	// check signature
	public boolean checkSignature(String signature, String timestamp, String nonce) {
		// dictionary sort
		String[] dataStrings = new String[] { TOKEN, timestamp, nonce };
		Arrays.sort(dataStrings);
		// construct the three string
		String data = dataStrings[0] + dataStrings[1] + dataStrings[2];
		// sha1
		String flag = DigestUtils.shaHex(data);
		// check
		if (data.equalsIgnoreCase(signature)) {
			return true;
		} else {
			return false;
		}
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public OutputStream getOutputStream() {
		return outputStream;
	}

	public void setOutputStream(OutputStream outputStream) {
		this.outputStream = outputStream;
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
