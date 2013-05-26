package com.qq.weixin.sdk.message.filter;

import java.util.List;
import java.util.regex.Pattern;

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
public class FilterShow extends MessageFilterHelper implements IMessageFilter {

	@Override
	public Message doSpecailMessageFilter(Message message) {
		if (message instanceof MessageText) {// it just handle text message
			MessageText messageText = (MessageText) message;
			if (Pattern.compile(ResourceManager.getValue("show")).matcher(messageText.getContent()).find()) {
				List<ItemArticle> articles;
				try {
					articles = new ShowService().getItemList();
					return buildMessageResultNews(articles);
				} catch (Exception e) {
					return buildMessageResultText(ResourceManager.getValue("error"));// important! it may happen
				}
			}
		}
		return null;
	}

}
