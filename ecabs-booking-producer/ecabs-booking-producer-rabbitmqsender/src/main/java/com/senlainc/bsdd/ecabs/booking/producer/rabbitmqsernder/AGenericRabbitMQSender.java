package com.senlainc.bsdd.ecabs.booking.producer.rabbitmqsernder;

import com.senlainc.bsdd.ecabs.adapter.api.dto.AEntityDto;
import com.senlainc.bsdd.ecabs.booking.producer.api.producers.IGenericModelSender;
import lombok.Getter;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.Serializable;

public abstract class AGenericRabbitMQSender<K extends Serializable, T extends AEntityDto<K>> implements IGenericModelSender<K, T> {

    @Autowired
    @Qualifier(value = "rabbitJsonTemplate")
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
        this.rabbitTemplate.convertAndSend(exchange, routingKey, object);
    }
}
