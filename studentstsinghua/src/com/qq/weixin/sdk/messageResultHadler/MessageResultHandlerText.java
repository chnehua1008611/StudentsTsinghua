package com.qq.weixin.sdk.messageResultHadler;

import com.qq.weixin.sdk.message.Message;
import com.qq.weixin.sdk.message.MessageResultText;

/**
 * message result text handler
 * 
 * @author hujiawei
 * 
 */
public class MessageResultHandlerText extends MessageResultHandlerHelper {

	@Override
	public String buildMessageResult(Message message) {
		MessageResultText messageResultText = (MessageResultText) message;
		StringBuffer buffer = new StringBuffer();
		String toUserName = wrapperContent(TAG_TOUSERNAME, messageResultText.getToUserName(), true);
		String fromUserName = wrapperContent(TAG_FROMUSERNAME, messageResultText.getFromUserName(), true);
		String createTime = wrapperContent(TAG_CREATETIME, messageResultText.getCreateTime() + "", true);
		String msgType = wrapperContent(TAG_MSGTYPE, messageResultText.getMsgType(), true);
		String content = wrapperContent(TAG_CONTENT, messageResultText.getContent(), true);
		String funcFlag = wrapperContent(TAG_FUNCFLAG, messageResultText.getFuncFlag() + "", true);
		buffer.append(toUserName).append(fromUserName).append(createTime).append(msgType).append(content).append(funcFlag);
		return wrapperXmlContent(buffer.toString());
	}

}
