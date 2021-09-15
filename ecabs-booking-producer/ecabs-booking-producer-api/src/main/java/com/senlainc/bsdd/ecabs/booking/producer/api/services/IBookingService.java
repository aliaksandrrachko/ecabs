package com.senlainc.bsdd.ecabs.booking.producer.api.services;

import com.senlainc.bsdd.ecabs.adapter.api.dto.BookingDto;

public interface IBookingService {

    void deleteBooking(String id);

    void addBooking(BookingDto bookingDto);

    void editBooking(String id, BookingDto bookingDto);
}
