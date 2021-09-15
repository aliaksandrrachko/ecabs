package com.senlainc.bsdd.ecabs.booking.producer.controller.controllers;

import com.senlainc.bsdd.ecabs.adapter.api.dto.BookingDto;
import com.senlainc.bsdd.ecabs.booking.producer.api.services.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/bookings")
public class BookingController {

    @Autowired
    private IBookingService bookingService;

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteBooking(@PathVariable String id) {
        this.bookingService.deleteBooking(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public void createBooking(@RequestBody BookingDto bookingDto) {
        this.bookingService.addBooking(bookingDto);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void editBooking(@PathVariable String id, @RequestBody BookingDto bookingDto) {
        this.bookingService.editBooking(id, bookingDto);
    }
}
