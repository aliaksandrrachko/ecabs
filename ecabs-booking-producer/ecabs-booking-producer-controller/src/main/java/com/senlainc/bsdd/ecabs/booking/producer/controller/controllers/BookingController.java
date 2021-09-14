package com.senlainc.bsdd.ecabs.booking.producer.controller.controllers;

import com.senlainc.bsdd.ecabs.adapter.api.dto.BookingDto;
import com.senlainc.bsdd.ecabs.booking.producer.api.services.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/bookings")
public class BookingController {

    @Autowired
    private IBookingService bookingService;

    @DeleteMapping(value = "/{id}")
    public void deleteBooking(@PathVariable Long id){
        this.bookingService.deleteBooking(id);
    }

    @PostMapping
    public void createBooking(@RequestBody BookingDto bookingDto){
        this.bookingService.addBooking(bookingDto);
    }

    @PutMapping(value = "/{id}")
    public void editBooking(@PathVariable Long id, @RequestBody BookingDto bookingDto){
        this.bookingService.editBooking(id, bookingDto);
    }
}
