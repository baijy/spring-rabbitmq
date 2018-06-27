package com.jianyu.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * 第二个消费者
 * 
 * @author BaiJianyu <br>
 * @date 2018年6月27日下午3:03:34 <br>
 *       Better late than never. <br>
 */
public class SecondConsumer implements MessageListener {
	private Logger logger = LoggerFactory.getLogger(SecondConsumer.class);

	@Override
	public void onMessage(Message message) {
		logger.info("The second consumer received message : " + message);
	}

}
