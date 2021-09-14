package com.senlainc.bsdd.ecabs.booking.producer.api.rabbitmq;

import com.senlainc.bsdd.ecabs.adapter.api.dto.AEntityDto;

public interface IGenericRabbitMQSender<K extends Number, T extends AEntityDto<K>> {

    Class<T> getGenericClass();
    void send(T bookingDto, String routingKey);
}
