package com.qq.weixin.sdk.messageResultHadler;

import com.qq.weixin.sdk.message.Message;

/**
 * interface for handling message result
 * 
 * @author hujiawei
 * 
 */
public interface IMessageResultHandler {

	// build the content of message result
	public String buildMessageResult(Message message);

}
