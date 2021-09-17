package com.senlainc.bsdd.ecabs.booking.producer.kafkasender;

import com.senlainc.bsdd.ecabs.adapter.api.dto.BookingDto;
import com.senlainc.bsdd.ecabs.booking.producer.api.producers.IBookingModelSender;
import org.springframework.stereotype.Component;

@Component
public class BookingModelSenderKafka extends AGenericModelSenderKafka<String, BookingDto>
        implements IBookingModelSender {

    protected BookingModelSenderKafka() {
        super(BookingDto.class);
    }
}
