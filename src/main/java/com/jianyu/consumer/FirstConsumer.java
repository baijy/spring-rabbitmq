package com.jianyu.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
/**
 * 第一个消费者，监听queueFirst
 * @author BaiJianyu
 *
 */
public class FirstConsumer implements MessageListener {
	Logger logger = LoggerFactory.getLogger(FirstConsumer.class);

	@Override
	public void onMessage(Message message) {
		logger.info("The first consumer received message : "+message);
	}
	
}
