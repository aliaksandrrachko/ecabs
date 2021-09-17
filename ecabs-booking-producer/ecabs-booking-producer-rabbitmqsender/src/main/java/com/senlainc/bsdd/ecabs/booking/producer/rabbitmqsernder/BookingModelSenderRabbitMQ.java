package com.senlainc.bsdd.ecabs.booking.producer.rabbitmqsernder;

import com.senlainc.bsdd.ecabs.adapter.routers.Routing;
import com.senlainc.bsdd.ecabs.adapter.api.dto.BookingDto;
import com.senlainc.bsdd.ecabs.booking.producer.api.producers.IBookingModelSender;
import org.springframework.stereotype.Component;

@Component
public class BookingModelSenderRabbitMQ
        extends AGenericModelSenderRabbitMQ<String, BookingDto>
        implements IBookingModelSender {

    protected BookingModelSenderRabbitMQ() {
        super(BookingDto.class, Routing.MESSAGE_EXCHANGE.E_CABS_MESSAGE_EXCHANGE);
    }
}
