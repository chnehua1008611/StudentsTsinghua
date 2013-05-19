package com.qq.weixin.sdk.message.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.qq.weixin.sdk.ResourceManager;
import com.qq.weixin.sdk.message.ItemArticle;
import com.qq.weixin.sdk.message.Message;
import com.qq.weixin.sdk.message.MessageResultNews;
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
				// return buildMessageResultText(ResourceManager.getValue("result_show"));
				ItemArticle itemArticle = new ItemArticle();
				itemArticle.setTitle("title1");
				itemArticle.setDescription("decription1");
				itemArticle.setUrl("www.baidu.com");
				itemArticle.setPicUrl("www.baidu.com/img/519_feb248f1f5270aba8b65f6852f0eef3d.gif");

				ItemArticle itemArticle2 = new ItemArticle();
				itemArticle2.setTitle("title2");
				itemArticle2.setDescription("decription2");
				itemArticle2.setUrl("www.sina.com");
				itemArticle2.setPicUrl("www.baidu.com/img/baidu_jgylogo3.gif");

				List<ItemArticle> articles = new ArrayList<ItemArticle>();
				articles.add(itemArticle);
				articles.add(itemArticle2);

				return buildMessageResultNews(articles);
			}
		}
		return null;
	}

}
