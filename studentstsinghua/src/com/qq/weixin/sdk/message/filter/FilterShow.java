package com.qq.weixin.sdk.message.filter;

import java.util.regex.Pattern;

import com.qq.weixin.sdk.ResourceManager;
import com.qq.weixin.sdk.message.Message;
import com.qq.weixin.sdk.message.MessageText;

/**
 * filter whether the message is for help
 * 
 * @author hujiawei
 * 
 */
public class FilterShow extends MessageFilterHelper implements IMessageFilter {

	@Override
	public Message doMessageFilter(Message message) {
		if (message instanceof MessageText) {
			MessageText messageText = (MessageText) message;
			if (Pattern.compile(ResourceManager.getValue("show")).matcher(messageText.getContent()).find()) {
				return buildMessageResultText(ResourceManager.getValue("show_info"));
			}
		}
		return null;
	}

}