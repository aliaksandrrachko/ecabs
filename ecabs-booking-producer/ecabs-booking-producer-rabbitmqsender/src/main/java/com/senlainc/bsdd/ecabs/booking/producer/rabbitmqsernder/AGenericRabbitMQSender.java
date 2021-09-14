package com.senlainc.bsdd.ecabs.booking.producer.rabbitmqsernder;

import com.senlainc.bsdd.ecabs.adapter.api.dto.AEntityDto;
import com.senlainc.bsdd.ecabs.booking.producer.api.rabbitmq.IGenericRabbitMQSender;
import lombok.Getter;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AGenericRabbitMQSender<K extends Number, T extends AEntityDto<K>> implements IGenericRabbitMQSender<K, T> {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Getter
    private final String exchange;

    private final Class<T> clazz;

    protected AGenericRabbitMQSender(Class<T> clazz, String exchange) {
        this.clazz = clazz;
        this.exchange = exchange;
    }

    @Override
    public Class<T> getGenericClass() {
        return this.clazz;
    }

    @Override
    public void send(T object, String routingKey) {
        rabbitTemplate.convertAndSend(exchange, routingKey, object);
    }
}
