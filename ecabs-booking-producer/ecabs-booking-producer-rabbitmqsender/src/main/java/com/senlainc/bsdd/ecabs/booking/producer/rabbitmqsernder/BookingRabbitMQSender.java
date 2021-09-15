package com.senlainc.bsdd.ecabs.booking.producer.rabbitmqsernder;

import com.senlainc.bsdd.ecabs.adapter.routers.Routing;
import com.senlainc.bsdd.ecabs.adapter.api.dto.BookingDto;
import com.senlainc.bsdd.ecabs.booking.producer.api.rabbitmq.IBookingRabbitMQSender;
import org.springframework.stereotype.Component;

@Component
public class BookingRabbitMQSender
        extends AGenericRabbitMQSender<String, BookingDto>
        implements IBookingRabbitMQSender {

    protected BookingRabbitMQSender() {
        super(BookingDto.class, Routing.MESSAGE_EXCHANGE.E_CABS_MESSAGE_EXCHANGE);
    }
}
