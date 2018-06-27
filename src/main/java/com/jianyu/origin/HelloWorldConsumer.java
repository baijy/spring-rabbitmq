package com.jianyu.origin;

import java.io.IOException;
import java.util.Map;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class HelloWorldConsumer {
	private final static String QUEUE_NAME = "ORIGIN_QUEUE";

	public static void main(String[] args) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		// 连接IP
		factory.setHost("127.0.0.1");
		// 默认监听端口
		factory.setPort(5672);
		// 虚拟机
		factory.setVirtualHost("/");

		// 设置访问的用户
		factory.setUsername("guest");
		factory.setPassword("guest");
		// 建立连接
		Connection conn = factory.newConnection();
		// 创建消息通道
		Channel channel = conn.createChannel();

		// 声明队列（默认交换机AMQP default，Direct）
		// String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		System.out.println(" Waiting for msg....");
		
		// 创建消费者，并接受消息
		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				String msg = new String(body, "UTF-8");
				System.out.println("Received msg : '" + msg + "'");
			}
		};
		
		// 开始获取消息
		// String queue, boolean autoAck, Consumer callback
		channel.basicConsume(QUEUE_NAME, true, consumer);
	}

}
