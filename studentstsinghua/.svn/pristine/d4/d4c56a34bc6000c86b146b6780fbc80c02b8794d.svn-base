package com.qq.weixin.sdk.messageHadler;

import org.w3c.dom.Element;

import com.qq.weixin.sdk.message.Message;
import com.qq.weixin.sdk.message.MessageImage;

/**
 * handle image message
 * 
 * @author hujiawei
 * 
 */
public class MessageHandlerImage extends MessageHandlerHelper {

	@Override
	public Message handleSpecialMessage(Message message) {
		MessageImage messageImage = (MessageImage) message;

		return null;
	}

	@Override
	protected void parseSpecialMessage(Message message, Element root) {
		MessageImage messageImage = (MessageImage) message;
		messageImage.setPicUrl(root.getElementsByTagName(TAG_PICURL).item(0).getTextContent());
	}

}
