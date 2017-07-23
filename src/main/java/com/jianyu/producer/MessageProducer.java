package com.jianyu.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * 
 * @author BaiJianyu
 *
 */
@Service
public class MessageProducer {
	private Logger logger= LoggerFactory.getLogger(MessageProducer.class);
	
	@Autowired
	@Qualifier("amqpTemplate")
	private AmqpTemplate amqptemplate;
	
	@Autowired
	@Qualifier("amqpTemplate2")
	private AmqpTemplate amqptemplate2;
	
	public void sendMessage(Object message) {
		//将三条同样的消息发送到交换器，带有不同的RouteKey
		logger.info("Send message : "+message);
		
		// 这个template对应的Exchange绑定了两个queue，演示两个queue绑定同一个交换器
		// Exchange模式为direct，通过RouteKey匹配指定的队列
		amqptemplate.convertAndSend("FirstKey",message); // 第一个参数：RouteKey, 第二个参数：消息
		amqptemplate.convertAndSend("SecondKey",message);
		
		// 这个template对应的Exchange绑定了一个queue
		// Exchange模式为topic，通过topic匹配关心该主题的队列
		amqptemplate2.convertAndSend("msg.Third.send",message);
	}
	
}
