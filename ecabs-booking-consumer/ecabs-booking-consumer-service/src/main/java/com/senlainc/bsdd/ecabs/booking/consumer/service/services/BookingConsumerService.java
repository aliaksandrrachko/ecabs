package com.senlainc.bsdd.ecabs.booking.consumer.service.services;

import com.senlainc.bsdd.ecabs.adapter.api.dto.BookingDto;
import com.senlainc.bsdd.ecabs.adapter.api.mappers.BookingDtoMapper;
import com.senlainc.bsdd.ecabs.adapter.entity.entities.Booking;
import com.senlainc.bsdd.ecabs.booking.consumer.api.repositories.BookingRepository;
import com.senlainc.bsdd.ecabs.booking.consumer.api.services.IBookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.senlainc.bsdd.ecabs.adapter.routers.Routing.MESSAGE_EXCHANGE.BOOKING_EXCHANGE.E_CABS_BOOKING_ADD_ROUTES_KEY;
import static com.senlainc.bsdd.ecabs.adapter.routers.Routing.MESSAGE_EXCHANGE.BOOKING_EXCHANGE.E_CABS_BOOKING_DEL_ROUTES_KEY;
import static com.senlainc.bsdd.ecabs.adapter.routers.Routing.MESSAGE_EXCHANGE.BOOKING_EXCHANGE.E_CABS_BOOKING_EDIT_ROUTES_KEY;
import static com.senlainc.bsdd.ecabs.adapter.routers.Routing.MESSAGE_EXCHANGE.BOOKING_EXCHANGE.E_CABS_BOOKING_EXCHANGE;
import static com.senlainc.bsdd.ecabs.adapter.routers.Routing.MESSAGE_EXCHANGE.E_CABS_MESSAGE_EXCHANGE;

@Service
@Transactional(readOnly = true)
@Slf4j
// TODO: this service doesn't delete messages
public class BookingConsumerService implements IBookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BookingDtoMapper bookingDtoMapper;

    @Override
    @KafkaListener(topics = {E_CABS_BOOKING_ADD_ROUTES_KEY,
            E_CABS_BOOKING_EDIT_ROUTES_KEY,
            E_CABS_BOOKING_DEL_ROUTES_KEY},
            groupId = E_CABS_MESSAGE_EXCHANGE)
    public void bookingAudit(final @Payload BookingDto bookingDto) {
        log.info("BookingDto:{} received. Method:[bookingAudit]", bookingDto);
    }

    @Override
    @Transactional
    @KafkaListener(topics = E_CABS_BOOKING_EDIT_ROUTES_KEY, groupId = E_CABS_BOOKING_EXCHANGE)
    public void bookingEditConsumer(final @Payload BookingDto bookingDto) {
        log.info("BookingDto:{} received. Method:[bookingEditConsumer]", bookingDto);
        Booking bookingToSave = bookingDtoMapper.toEntity(bookingDto);
        bookingRepository.save(bookingToSave);
    }

    @Override
    @Transactional
    @KafkaListener(topics = E_CABS_BOOKING_ADD_ROUTES_KEY, groupId = E_CABS_BOOKING_EXCHANGE)
    public void bookingAddConsumer(final @Payload BookingDto bookingDto) {
        log.info("BookingDto:{} received. Method:[bookingAddConsumer]", bookingDto);
        Booking booking = bookingDtoMapper.toEntity(bookingDto);
        booking.setCreatedOn(LocalDateTime.now().withNano(0));
        booking.setLastModifiedOn(LocalDateTime.now().withNano(0));
        bookingRepository.save(booking);
    }

    @Override
    @Transactional
    @KafkaListener(topics = E_CABS_BOOKING_DEL_ROUTES_KEY, groupId = "")
    public void bookingDelConsumer(final @Payload BookingDto bookingDto) {
        log.info("BookingDto:{} received. Method:[bookingDelConsumer]", bookingDto);
        Optional<Booking> bookingForDeleting = bookingRepository.findById(bookingDto.getId());
        bookingForDeleting.ifPresent(booking -> bookingRepository.delete(booking));
    }
}
