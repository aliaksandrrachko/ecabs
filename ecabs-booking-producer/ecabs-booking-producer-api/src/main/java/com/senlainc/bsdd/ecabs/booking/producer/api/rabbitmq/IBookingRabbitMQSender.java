package com.senlainc.bsdd.ecabs.booking.producer.api.rabbitmq;

import com.senlainc.bsdd.ecabs.adapter.api.dto.BookingDto;

public interface IBookingRabbitMQSender extends IGenericRabbitMQSender<Long, BookingDto> {

}
