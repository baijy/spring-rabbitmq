package com.jianyu.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
/**
 * 第三个消费者，监听queueThird
 * @author BaiJianyu
 *
 */
public class ThirdConsumer implements MessageListener {
	Logger logger = LoggerFactory.getLogger(ThirdConsumer.class);
	
	@Override
	public void onMessage(Message message) {
		logger.info("The third cosumer received message : "+message);
	}

}
