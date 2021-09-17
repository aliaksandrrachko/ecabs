package com.senlainc.bsdd.ecabs.booking.producer.kafkasender;

import com.senlainc.bsdd.ecabs.adapter.api.dto.AEntityDto;
import com.senlainc.bsdd.ecabs.booking.producer.api.producers.IGenericModelSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import java.io.Serializable;

public abstract class AGenericModelSenderKafka<K extends Serializable, T extends AEntityDto<K>>
        implements IGenericModelSender<K, T> {

    @Autowired
    private KafkaTemplate<String, T> kafkaTemplate;

    private final Class<T> clazz;


    protected AGenericModelSenderKafka(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public Class<T> getGenericClass() {
        return this.clazz;
    }

    @Override
    public void send(T object, String routingKey) {
        kafkaTemplate.send(routingKey, object);
    }
}
