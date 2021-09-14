package com.senlainc.bsdd.ecabs.booking.producer.rabbitmqsernder;

import com.senlainc.bsdd.ecabs.adapter.Routing;
import com.senlainc.bsdd.ecabs.adapter.api.dto.BookingDto;
import com.senlainc.bsdd.ecabs.booking.producer.api.rabbitmq.IBookingRabbitMQSender;
import org.springframework.stereotype.Component;

@Component
public class BookingRabbitMQSender
        extends AGenericRabbitMQSender<Long, BookingDto>
        implements IBookingRabbitMQSender {

    protected BookingRabbitMQSender() {
        super(BookingDto.class, Routing.MessageExchange.E_CABS_MESSAGE_EXCHANGE);
    }
}
