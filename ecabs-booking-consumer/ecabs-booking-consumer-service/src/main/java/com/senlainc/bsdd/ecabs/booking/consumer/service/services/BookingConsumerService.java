package com.senlainc.bsdd.ecabs.booking.consumer.service.services;

import com.senlainc.bsdd.ecabs.adapter.api.dto.BookingDto;
import com.senlainc.bsdd.ecabs.adapter.api.mappers.BookingDtoMapper;
import com.senlainc.bsdd.ecabs.adapter.entity.entities.Booking;
import com.senlainc.bsdd.ecabs.booking.consumer.api.repositories.BookingRepository;
import com.senlainc.bsdd.ecabs.booking.consumer.api.services.IBookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

import static com.senlainc.bsdd.ecabs.adapter.routers.Routing.MESSAGE_EXCHANGE.BOOKING_EXCHANGE.E_CABS_BOOKING_ADD_QUEUE;
import static com.senlainc.bsdd.ecabs.adapter.routers.Routing.MESSAGE_EXCHANGE.BOOKING_EXCHANGE.E_CABS_BOOKING_DEL_QUEUE;
import static com.senlainc.bsdd.ecabs.adapter.routers.Routing.MESSAGE_EXCHANGE.BOOKING_EXCHANGE.E_CABS_BOOKING_EDIT_QUEUE;
import static com.senlainc.bsdd.ecabs.adapter.routers.Routing.MESSAGE_EXCHANGE.E_CABS_MESSAGE_AUDIT_QUEUE;

@Service
@Transactional(readOnly = true)
@Slf4j
public class BookingConsumerService implements IBookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BookingDtoMapper bookingDtoMapper;

    @Override
    @RabbitListener(queues = E_CABS_MESSAGE_AUDIT_QUEUE)
    public void bookingAudit(BookingDto bookingDto) {
        log.info("BookingDto:{} received from 'ecabs-message-audit-queue'", bookingDto);
    }

    @Override
    @Transactional
    @RabbitListener(queues = E_CABS_BOOKING_EDIT_QUEUE)
    public void bookingEditConsumer(BookingDto bookingDto) {
        log.info("BookingDto:{} received from 'ecabs-message-edit-queue'", bookingDto);
        Booking bookingToSave = bookingDtoMapper.toEntity(bookingDto);
        bookingRepository.save(bookingToSave);
    }

    @Override
    @Transactional
    @RabbitListener(queues = E_CABS_BOOKING_ADD_QUEUE)
    public void bookingAddConsumer(BookingDto bookingDto) {
        log.info("BookingDto:{} received from 'ecabs-message-add-queue'", bookingDto);
        Booking booking = bookingDtoMapper.toEntity(bookingDto);
        booking.setCreatedOn(LocalDateTime.now().withNano(0));
        booking.setLastModifiedOn(LocalDateTime.now().withNano(0));
        booking.setPrice(new BigDecimal("125.23"));
        booking.setWaitingTime(Duration.between(LocalDateTime.now().withNano(0), LocalDateTime.now().withNano(0).plusDays(2)));
        bookingRepository.save(booking);
    }

    @Override
    @Transactional
    @RabbitListener(queues = E_CABS_BOOKING_DEL_QUEUE)
    public void bookingDelConsumer(BookingDto bookingDto) {
        log.info("BookingDto:{} received from 'ecabs-message-del-queue'", bookingDto);
        Optional<Booking> bookingForDeleting = bookingRepository.findById(bookingDto.getId());
        bookingForDeleting.ifPresent(booking -> bookingRepository.delete(booking));
    }
}
