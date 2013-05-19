package com.qq.weixin.sdk.messageHadler;

import java.util.logging.Logger;

import org.w3c.dom.Element;

import com.qq.weixin.sdk.message.Message;
import com.qq.weixin.sdk.message.MessageResultText;
import com.qq.weixin.sdk.message.MessageText;

/**
 * handle text message
 * 
 * @author hujiawei
 * 
 */
public class MessageHandlerText extends MessageHandlerHelper {

	Logger logger = Logger.getLogger(getClass().getName());

	@Override
	public Message handleSpecialMessage(Message message) {
		MessageText messageText = (MessageText) message;
		String content = messageText.getContent();
		MessageResultText messageResultText = new MessageResultText();
		messageResultText.setMsgType(MESSAGE_RESULT_TEXT);
		if (content.indexOf("帮助") >= 0) {
			messageResultText.setContent("true" + " 有帮助");
		} else {
			messageResultText.setContent("false" + " 没有帮助");
		}
		messageResultText.setFromUserName(message.getToUserName());
		messageResultText.setToUserName(message.getFromUserName());
		messageResultText.setFuncFlag(1);//
		messageResultText.setCreateTime(getCurrentUnixTimestamp());
		return messageResultText;
	}

	@Override
	protected void parseSpecialMessage(Message message, Element root) {
		MessageText messageText = (MessageText) message;
		messageText.setContent(root.getElementsByTagName(TAG_CONTENT).item(0).getTextContent());
	}

}
