package com.qq.weixin.sdk.messageHadler;

import org.w3c.dom.Element;

import com.qq.weixin.sdk.message.Message;
import com.qq.weixin.sdk.message.MessageLink;

/**
 * handle event message
 * 
 * @author hujiawei
 * 
 */
public class MessageHandlerLink extends MessageHandlerHelper {

	@Override
	public Message handleSpecialMessage(Message message) {
		MessageLink messageLink = (MessageLink) message;

		return null;
	}

	@Override
	protected void parseSpecialMessage(Message message, Element root) {
		MessageLink messageLink = (MessageLink) message;
		messageLink.setUrl(root.getElementsByTagName(TAG_URL).item(0).getTextContent());
		messageLink.setDescription(root.getElementsByTagName(TAG_DESCRIPTION).item(0).getTextContent());
		messageLink.setTitle(root.getElementsByTagName(TAG_TITLE).item(0).getTextContent());
	}

}