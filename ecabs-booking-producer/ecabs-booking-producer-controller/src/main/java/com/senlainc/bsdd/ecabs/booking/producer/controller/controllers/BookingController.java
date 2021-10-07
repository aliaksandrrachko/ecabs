package com.senlainc.bsdd.ecabs.booking.producer.controller.controllers;

import com.senlainc.bsdd.ecabs.adapter.api.dto.BookingDto;
import com.senlainc.bsdd.ecabs.booking.producer.api.services.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/rest/bookings")
public class BookingController {

    @Autowired
    private IBookingService bookingService;

    @DeleteMapping(value = "/{id}")
    public Mono<ServerResponse> deleteBooking(@PathVariable String id) {
        this.bookingService.deleteBooking(id);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body("Booking will delete", String.class);
    }

    @PostMapping
    public Mono<ServerResponse> createBooking(@RequestBody BookingDto bookingDto) {
        this.bookingService.addBooking(bookingDto);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body("Booking will create", String.class);
    }

    @PutMapping(value = "/{id}")
    public Mono<ServerResponse> editBooking(@PathVariable String id, @RequestBody BookingDto bookingDto) {
        this.bookingService.editBooking(id, bookingDto);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body("Booking will edit", String.class);
    }
}
