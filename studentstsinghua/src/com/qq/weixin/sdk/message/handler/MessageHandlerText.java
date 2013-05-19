package com.qq.weixin.sdk.message.handler;

import org.w3c.dom.Element;

import com.qq.weixin.sdk.message.Message;
import com.qq.weixin.sdk.message.MessageText;
import com.qq.weixin.sdk.message.filter.FilterChain;
import com.qq.weixin.sdk.message.filter.FilterHelp;
import com.qq.weixin.sdk.message.filter.FilterShow;

/**
 * handle text message <br>
 * every handler can create its own filter chain to handler message or not create it by handling directly
 * 
 * @author hujiawei
 * 
 */
public class MessageHandlerText extends MessageHandlerHelper {

	@Override
	public Message handleSpecialMessage(Message message) {
		FilterChain filterChain = new FilterChain();
		filterChain.addFilter(new FilterHelp());
		filterChain.addFilter(new FilterShow());
		Message messageResult = filterChain.doFilterChain(message);
		if (messageResult != null) {
			return messageResult;
		} else {
			return buildDefaultMessageResultText();
		}
	}

	@Override
	protected void parseSpecialMessage(Message message, Element root) {
		MessageText messageText = (MessageText) message;
		messageText.setContent(root.getElementsByTagName(TAG_CONTENT).item(0).getTextContent());
	}

}
