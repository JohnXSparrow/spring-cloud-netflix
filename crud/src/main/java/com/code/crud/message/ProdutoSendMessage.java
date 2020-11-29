package com.code.crud.message;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.code.crud.vo.ProdutoVO;

@Component
public class ProdutoSendMessage {
	
	@Value("${crud.rabbitmq.exchange}")
	private String exchange;
	
	@Value("${crud.rabbitmq.routingkey}")
	private String routingkey;
	
	public final RabbitTemplate rabbitTemplate;

	@Autowired
	public ProdutoSendMessage(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	public void sendMessage(ProdutoVO produtoVO) {
		rabbitTemplate.convertAndSend(exchange, routingkey, produtoVO);
	}

}
