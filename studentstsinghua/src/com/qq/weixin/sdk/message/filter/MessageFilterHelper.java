package com.qq.weixin.sdk.message.filter;

import org.w3c.dom.Element;

import com.qq.weixin.sdk.message.Message;
import com.qq.weixin.sdk.message.handler.MessageHandlerHelper;

/**
 * message filter helper class <br>
 * extends message handler helper,then filter can access the message handling method
 * 
 * @author hujiawei
 * 
 */
public class MessageFilterHelper extends MessageHandlerHelper implements IMessageFilter {

	@Override
	public Message doMessageFilter(Message message) {
		return null;
	}

	@Override
	protected void parseSpecialMessage(Message message, Element root) {// do nothing

	}

	@Override
	protected Message handleSpecialMessage(Message message) {// do nothing
		return null;
	}

}
