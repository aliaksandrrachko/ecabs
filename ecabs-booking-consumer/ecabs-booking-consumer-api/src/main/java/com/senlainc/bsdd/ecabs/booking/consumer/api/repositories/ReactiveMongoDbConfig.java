package com.senlainc.bsdd.ecabs.booking.consumer.api.repositories;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories
@EntityScan(value = {"com.senlainc.bsdd.ecabs.adapter.entity.entities"})
public class ReactiveMongoDbConfig {
}
