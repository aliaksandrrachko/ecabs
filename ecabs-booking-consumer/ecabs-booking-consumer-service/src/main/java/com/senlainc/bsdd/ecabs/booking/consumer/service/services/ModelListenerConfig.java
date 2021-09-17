package com.senlainc.bsdd.ecabs.booking.consumer.service.services;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

@Configuration
@EnableKafka
@ComponentScan(value = {"com.senlainc.bsdd.ecabs.adapter.api.mappers"})
public class ModelListenerConfig {
}
