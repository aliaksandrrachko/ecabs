package com.senlainc.bsdd.ecabs.booking.producer.service.services;

import com.senlainc.bsdd.ecabs.adapter.api.dto.BookingDto;
import com.senlainc.bsdd.ecabs.booking.producer.api.producers.IBookingModelSender;
import com.senlainc.bsdd.ecabs.booking.producer.api.services.IBookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import static com.senlainc.bsdd.ecabs.adapter.routers.Routing.MESSAGE_EXCHANGE.BOOKING_EXCHANGE.E_CABS_BOOKING_ADD_ROUTES_KEY;
import static com.senlainc.bsdd.ecabs.adapter.routers.Routing.MESSAGE_EXCHANGE.BOOKING_EXCHANGE.E_CABS_BOOKING_DEL_ROUTES_KEY;
import static com.senlainc.bsdd.ecabs.adapter.routers.Routing.MESSAGE_EXCHANGE.BOOKING_EXCHANGE.E_CABS_BOOKING_EDIT_ROUTES_KEY;

@Service
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Slf4j
public class BookingProducerService implements IBookingService {

    @Autowired
    @Qualifier("bookingRabbitMQSender")
    private IBookingModelSender bookingSender;

    @Override
    public void deleteBooking(String id) {
        BookingDto dtoToSend = BookingDto.builder().id(id).build();
        this.bookingSender.send(dtoToSend, E_CABS_BOOKING_DEL_ROUTES_KEY);
    }

    @Override
    public void addBooking(BookingDto bookingDto) {
        this.bookingSender.send(bookingDto, E_CABS_BOOKING_ADD_ROUTES_KEY);
    }

    @Override
    public void editBooking(String id, BookingDto bookingDto) {
        bookingDto.setId(id);
        this.bookingSender.send(bookingDto, E_CABS_BOOKING_EDIT_ROUTES_KEY);
    }
}
