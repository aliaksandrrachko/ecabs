package com.senlainc.bsdd.ecabs.booking.producer.api.rabbitmq;

import com.senlainc.bsdd.ecabs.adapter.api.dto.AEntityDto;

import java.io.Serializable;

public interface IGenericRabbitMQSender<K extends Serializable, T extends AEntityDto<K>> {

    Class<T> getGenericClass();
    void send(T bookingDto, String routingKey);
}
