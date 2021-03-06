package com.jianyu.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * 消息生产者
 * @author BaiJianyu <br>
 * @date 2018年6月27日下午2:59:40 <br>
 * Better late than never. <br>
 */
@Service
public class MessageProducer {
	private Logger logger = LoggerFactory.getLogger(MessageProducer.class);
	
	@Autowired
	@Qualifier("amqpTemplate")
	private AmqpTemplate amqpTemplate;
	
	@Autowired
	@Qualifier("amqpTemplate2")
	private AmqpTemplate amqpTemplate2;

	/**
	 * 演示三种交换机的使用
	 * 
	 * @param message
	 */
	public void sendMessage(Object message) {
		logger.info("Send message:" + message);
		
		// amqpTemplate 关联了 MY_DIRECT_EXCHANGE
		// amqpTemplate2 关联了 MY_TOPIC_EXCHANGE

		// Exchange 为 direct 模式，直接指定routingKey
		// 在没有指定交换机的情况下，默认发送到template关联的交换机
		amqpTemplate.convertAndSend("FirstKey", "[Direct,FirstKey] "+message); // MY_FIRST_QUEUE会收到
		amqpTemplate.convertAndSend("SecondKey", "[Direct,SecondKey] "+message); // MY_SECOND_QUEUE会收到
		
        // Exchange模式为topic，通过topic匹配关心该主题的队列
		// MY_THIRD_QUEUE会收到
		amqpTemplate2.convertAndSend("msg.Third.send","[Topic,msg.Third.send] "+message);
		
		// 广播消息，与Exchange绑定的所有队列都会收到消息，routingKey为空
		// 绑定了两个队列，MY_FIRST_QUEUE 和 MY_FOURTH_QUEUE会收到
		amqpTemplate2.convertAndSend("MY_FANOUT_EXCHANGE",null,"[Fanout] "+message); 
	}

}
