package com.senlainc.bsdd.ecabs.booking.producer.service.services;

import com.senlainc.bsdd.ecabs.adapter.api.dto.BookingDto;
import com.senlainc.bsdd.ecabs.booking.producer.api.rabbitmq.IBookingRabbitMQSender;
import com.senlainc.bsdd.ecabs.booking.producer.api.services.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.senlainc.bsdd.ecabs.adapter.Routing.MessageExchange.BookingExchange.BOOKING_ADD_ROUTES_KEY;
import static com.senlainc.bsdd.ecabs.adapter.Routing.MessageExchange.BookingExchange.BOOKING_DELETE_ROUTES_KEY;
import static com.senlainc.bsdd.ecabs.adapter.Routing.MessageExchange.BookingExchange.BOOKING_PUT_ROUTES_KEY;

@Service
public class BookingService implements IBookingService {

    @Autowired
    private IBookingRabbitMQSender bookingRabbitMQSender;

    @Override
    public void deleteBooking(Long id) {
        BookingDto dtoToSend = BookingDto.builder().id(id).build();
        this.bookingRabbitMQSender.send(dtoToSend, BOOKING_DELETE_ROUTES_KEY);
    }

    @Override
    public void addBooking(BookingDto bookingDto) {
        this.bookingRabbitMQSender.send(bookingDto, BOOKING_ADD_ROUTES_KEY);

    }

    @Override
    public void editBooking(Long id, BookingDto bookingDto) {
        bookingDto.setId(id);
        this.bookingRabbitMQSender.send(bookingDto, BOOKING_PUT_ROUTES_KEY);
    }
}
