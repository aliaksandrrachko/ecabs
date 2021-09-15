package com.senlainc.bsdd.ecabs.booking.consumer.api.repositories;

import com.senlainc.bsdd.ecabs.adapter.entity.entities.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends MongoRepository<Booking, Long> {
}
