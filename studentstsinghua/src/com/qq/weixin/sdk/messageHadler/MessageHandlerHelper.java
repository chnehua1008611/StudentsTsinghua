package com.qq.weixin.sdk.messageHadler;

import java.util.Date;

import org.w3c.dom.Element;

import com.qq.weixin.sdk.message.IMessage;
import com.qq.weixin.sdk.message.Message;
import com.qq.weixin.sdk.message.MessageResultText;

/**
 * helper class for message handling
 * 
 * @author hujiawei
 * 
 */
public abstract class MessageHandlerHelper implements IMessageHadler, IMessage {

	protected Message message;

	// subclass implements it
	protected abstract void parseSpecialMessage(Message message, Element root);

	protected abstract Message handleSpecialMessage(Message message);

	// put common parse part here!
	@Override
	public void parseMessage(Message message, Element root) {
		message.setMsgType(root.getElementsByTagName(TAG_MSGTYPE).item(0).getTextContent());
		message.setMsgId(Long.parseLong((root.getElementsByTagName(TAG_MSGID)).item(0).getTextContent()));
		message.setCreateTime(Integer.parseInt((root.getElementsByTagName(TAG_CREATETIME)).item(0).getTextContent()));
		message.setFromUserName((root.getElementsByTagName(TAG_FROMUSERNAME)).item(0).getTextContent());
		message.setToUserName((root.getElementsByTagName(TAG_TOUSERNAME)).item(0).getTextContent());
		parseSpecialMessage(message, root);
	}

	@Override
	public Message handleMessage(Message message) {
		this.message = message;
		return handleSpecialMessage(message);
	}

	// build default result message
	protected Message buildDefaultMessageResultText() {
		MessageResultText messageResultText = new MessageResultText();
		messageResultText.setMsgType(MESSAGE_RESULT_TEXT);
		messageResultText
				.setContent("欢迎关注学生在清华！");
		messageResultText.setFromUserName(message.getToUserName());
		messageResultText.setToUserName(message.getFromUserName());
		messageResultText.setFuncFlag(1);//
		messageResultText.setCreateTime(getCurrentUnixTimestamp());
		return messageResultText;
	}

	// get current Unix time
	public int getCurrentUnixTimestamp() {
		Date date = new Date();
		return (int) (date.getTime() / 1000);
	}

}