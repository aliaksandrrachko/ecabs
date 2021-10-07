package com.senlainc.bsdd.ecabs.booking.consumer.api.repositories;

import com.senlainc.bsdd.ecabs.adapter.entity.entities.Booking;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends ReactiveMongoRepository<Booking, String> {
}
