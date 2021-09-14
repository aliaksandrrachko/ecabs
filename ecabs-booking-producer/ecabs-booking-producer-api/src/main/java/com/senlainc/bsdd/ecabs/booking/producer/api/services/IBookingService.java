package com.senlainc.bsdd.ecabs.booking.producer.api.services;

import com.senlainc.bsdd.ecabs.adapter.api.dto.BookingDto;

public interface IBookingService {

    void deleteBooking(Long id);

    void addBooking(BookingDto bookingDto);

    void editBooking(Long id, BookingDto bookingDto);
}
