package com.qq.weixin.sdk.message.filter;

import java.util.List;
import java.util.regex.Pattern;

import students.tsinghua.service.RoomService;
import students.tsinghua.service.ShowService;

import com.qq.weixin.sdk.ResourceManager;
import com.qq.weixin.sdk.message.ItemArticle;
import com.qq.weixin.sdk.message.Message;
import com.qq.weixin.sdk.message.MessageText;

/**
 * filter whether the message is for show
 * 
 * @author hujiawei
 * 
 */
public class FilterRoom extends MessageFilterHelper implements IMessageFilter {

	@Override
	public Message doSpecailMessageFilter(Message message) {
		if (message instanceof MessageText) {// it just handle text message
			MessageText messageText = (MessageText) message;
			if (Pattern.compile(ResourceManager.getValue("room")).matcher(messageText.getContent()).find()) {
				try {
					String result = new RoomService().getMessageResultText();
					return buildMessageResultText(result);
				} catch (Exception e) {
					return buildMessageResultText(ResourceManager.getValue("error"));// important! it may happen
				}
			}
		}
		return null;
	}

}
