package com.qq.weixin.sdk.messageHadler;

import org.w3c.dom.Element;

import com.qq.weixin.sdk.message.Message;
import com.qq.weixin.sdk.message.MessageLocation;

/**
 * handle location message
 * 
 * @author hujiawei
 * 
 */
public class MessageHandlerLocation extends MessageHandlerHelper {

	@Override
	public Message handleSpecialMessage(Message message) {
		MessageLocation messageLocation = (MessageLocation) message;

		return null;
	}

	@Override
	protected void parseSpecialMessage(Message message, Element root) {
		MessageLocation messageLocation = (MessageLocation) message;
		messageLocation.setLocationX(root.getElementsByTagName(TAG_LOCATIONX).item(0).getTextContent());
		messageLocation.setLocationY(root.getElementsByTagName(TAG_LOCATIONY).item(0).getTextContent());
		messageLocation.setLabel(root.getElementsByTagName(TAG_LABEL).item(0).getTextContent());
		messageLocation.setScale(root.getElementsByTagName(TAG_SCALE).item(0).getTextContent());
	}

}
