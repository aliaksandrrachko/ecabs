package com.senlainc.bsdd.ecabs.booking.producer.api.producers;

import com.senlainc.bsdd.ecabs.adapter.api.dto.AEntityDto;

import java.io.Serializable;

public interface IGenericModelSender<K extends Serializable, T extends AEntityDto<K>> {

    Class<T> getGenericClass();
    void send(T object, String routingKey);
}
