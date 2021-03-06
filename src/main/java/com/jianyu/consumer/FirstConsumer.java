package com.jianyu.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * 第一个消费者
 * 
 * @author BaiJianyu <br>
 * @date 2018年6月27日下午2:59:57 <br>
 *       Better late than never. <br>
 */
public class FirstConsumer implements MessageListener {
	private Logger logger = LoggerFactory.getLogger(FirstConsumer.class);

	@Override
	public void onMessage(Message message) {
		logger.info("The first consumer received message : " + message);
	}

}
