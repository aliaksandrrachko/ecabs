package com.senlainc.bsdd.ecabs.booking.consumer.api.services;

import com.senlainc.bsdd.ecabs.adapter.api.dto.BookingDto;

public interface IBookingService {

    void bookingAudit(BookingDto bookingDto);

    void bookingEditConsumer(BookingDto bookingDto);

    void bookingAddConsumer(BookingDto bookingDto);

    void bookingDelConsumer(BookingDto bookingDto);
}
