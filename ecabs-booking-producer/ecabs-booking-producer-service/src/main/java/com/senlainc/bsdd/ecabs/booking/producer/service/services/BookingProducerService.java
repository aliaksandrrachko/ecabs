package com.senlainc.bsdd.ecabs.booking.producer.service.services;

import com.senlainc.bsdd.ecabs.adapter.api.dto.BookingDto;
import com.senlainc.bsdd.ecabs.booking.producer.api.rabbitmq.IBookingRabbitMQSender;
import com.senlainc.bsdd.ecabs.booking.producer.api.services.IBookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.senlainc.bsdd.ecabs.adapter.routers.Routing.MESSAGE_EXCHANGE.BOOKING_EXCHANGE.E_CABS_BOOKING_ADD_ROUTES_KEY;
import static com.senlainc.bsdd.ecabs.adapter.routers.Routing.MESSAGE_EXCHANGE.BOOKING_EXCHANGE.E_CABS_BOOKING_DEL_ROUTES_KEY;
import static com.senlainc.bsdd.ecabs.adapter.routers.Routing.MESSAGE_EXCHANGE.BOOKING_EXCHANGE.E_CABS_BOOKING_EDIT_ROUTES_KEY;

@Service
@Slf4j
public class BookingProducerService implements IBookingService {

    @Autowired
    private IBookingRabbitMQSender bookingRabbitMQSender;

    @Override
    public void deleteBooking(Long id) {
        BookingDto dtoToSend = BookingDto.builder().id(id).build();
        this.bookingRabbitMQSender.send(dtoToSend, E_CABS_BOOKING_DEL_ROUTES_KEY);
    }

    @Override
    public void addBooking(BookingDto bookingDto) {
        this.bookingRabbitMQSender.send(bookingDto, E_CABS_BOOKING_ADD_ROUTES_KEY);
    }

    @Override
    public void editBooking(Long id, BookingDto bookingDto) {
        bookingDto.setId(id);
        this.bookingRabbitMQSender.send(bookingDto, E_CABS_BOOKING_EDIT_ROUTES_KEY);
    }
}
