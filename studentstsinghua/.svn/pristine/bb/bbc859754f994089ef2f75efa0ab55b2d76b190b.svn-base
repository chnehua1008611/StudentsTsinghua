package com.qq.weixin.sdk.message.handler;

import org.w3c.dom.Element;

import com.qq.weixin.sdk.message.Message;
import com.qq.weixin.sdk.message.MessageEvent;

/**
 * handle event message
 * 
 * @author hujiawei
 * 
 */
public class MessageHandlerEvent extends MessageHandlerHelper {


	@Override
	public Message handleSpecialMessage(Message message) {
		MessageEvent messageEvent = (MessageEvent) message;

		return null;
	}

	@Override
	protected void parseSpecialMessage(Message message, Element root) {
		MessageEvent messageEvent = (MessageEvent) message;
		messageEvent.setEvent(root.getElementsByTagName(TAG_EVENT).item(0).getTextContent());
		messageEvent.setEventKey(root.getElementsByTagName(TAG_EVENTKEY).item(0).getTextContent());
	}

}
