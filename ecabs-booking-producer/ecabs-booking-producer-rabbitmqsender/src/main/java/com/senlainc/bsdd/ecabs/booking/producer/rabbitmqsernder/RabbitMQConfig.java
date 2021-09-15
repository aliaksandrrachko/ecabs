package com.senlainc.bsdd.ecabs.booking.producer.rabbitmqsernder;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.senlainc.bsdd.ecabs.adapter.routers.Routing.MESSAGE_EXCHANGE.BOOKING_EXCHANGE.E_CABS_BOOKING_ADD_QUEUE;
import static com.senlainc.bsdd.ecabs.adapter.routers.Routing.MESSAGE_EXCHANGE.BOOKING_EXCHANGE.E_CABS_BOOKING_ADD_ROUTES_KEY;
import static com.senlainc.bsdd.ecabs.adapter.routers.Routing.MESSAGE_EXCHANGE.BOOKING_EXCHANGE.E_CABS_BOOKING_DEL_QUEUE;
import static com.senlainc.bsdd.ecabs.adapter.routers.Routing.MESSAGE_EXCHANGE.BOOKING_EXCHANGE.E_CABS_BOOKING_DEL_ROUTES_KEY;
import static com.senlainc.bsdd.ecabs.adapter.routers.Routing.MESSAGE_EXCHANGE.BOOKING_EXCHANGE.E_CABS_BOOKING_EDIT_QUEUE;
import static com.senlainc.bsdd.ecabs.adapter.routers.Routing.MESSAGE_EXCHANGE.BOOKING_EXCHANGE.E_CABS_BOOKING_EDIT_ROUTES_KEY;
import static com.senlainc.bsdd.ecabs.adapter.routers.Routing.MESSAGE_EXCHANGE.BOOKING_EXCHANGE.E_CABS_BOOKING_EXCHANGE;
import static com.senlainc.bsdd.ecabs.adapter.routers.Routing.MESSAGE_EXCHANGE.E_CABS_MESSAGE_AUDIT_ROUTES_KEY;
import static com.senlainc.bsdd.ecabs.adapter.routers.Routing.MESSAGE_EXCHANGE.E_CABS_MESSAGE_AUDIT_QUEUE;
import static com.senlainc.bsdd.ecabs.adapter.routers.Routing.MESSAGE_EXCHANGE.E_CABS_MESSAGE_EXCHANGE;

@Configuration
@EnableRabbit
public class RabbitMQConfig {

    // == Message Exchange ==

    @Bean
    Queue messageAuditQueue() {
        return new Queue(E_CABS_MESSAGE_AUDIT_QUEUE, false);
    }

    @Bean
    TopicExchange messageExchange() {
        return new TopicExchange(E_CABS_MESSAGE_EXCHANGE);
    }

    @Bean
    @Qualifier
    Binding messageExchangeBinding(Queue messageAuditQueue, TopicExchange messageExchange) {
        return BindingBuilder.bind(messageAuditQueue).to(messageExchange).with(E_CABS_MESSAGE_AUDIT_ROUTES_KEY);
    }

    // == Booking Exchange ==

    @Bean
    Queue bookingAddQueue() {
        return new Queue(E_CABS_BOOKING_ADD_QUEUE, false);
    }

    @Bean
    Queue bookingEditQueue() {
        return new Queue(E_CABS_BOOKING_EDIT_QUEUE, false);
    }

    @Bean
    Queue bookingDelQueue() {
        return new Queue(E_CABS_BOOKING_DEL_QUEUE, false);
    }

    @Bean
    TopicExchange bookingExchange() {
        return new TopicExchange(E_CABS_BOOKING_EXCHANGE);
    }

    @Bean
    @Qualifier
    Binding bookingExchangeAddBinding(Queue bookingAddQueue, TopicExchange bookingExchange) {
        return BindingBuilder.bind(bookingAddQueue).to(bookingExchange).with(E_CABS_BOOKING_ADD_ROUTES_KEY);
    }

    @Bean
    @Qualifier
    Binding bookingExchangeEditBinding(Queue bookingEditQueue, TopicExchange bookingExchange) {
        return BindingBuilder.bind(bookingEditQueue).to(bookingExchange).with(E_CABS_BOOKING_EDIT_ROUTES_KEY);
    }

    @Bean
    @Qualifier
    Binding bookingExchangeDelBinding(Queue bookingDelQueue, TopicExchange bookingExchange) {
        return BindingBuilder.bind(bookingDelQueue).to(bookingExchange).with(E_CABS_BOOKING_DEL_ROUTES_KEY);
    }

    @Bean
    @Qualifier
    Binding bookingExchangeMessageExchangeBinding(TopicExchange messageExchange, TopicExchange bookingExchange) {
        return BindingBuilder.bind(bookingExchange).to(messageExchange).with(E_CABS_MESSAGE_AUDIT_ROUTES_KEY);
    }

    // TODO: think about exchange to exchange
    // TODO: why AmqpAdmin doesn't create automatically

    @Bean
    AmqpAdmin amqpAdmin(ConnectionFactory connectionFactory) {
        AmqpAdmin amqpAdmin = new RabbitAdmin(connectionFactory);

        Queue messageAuditQueue = messageAuditQueue();
        TopicExchange messageExchange = messageExchange();
        amqpAdmin.declareQueue(messageAuditQueue);
        amqpAdmin.declareExchange(messageExchange);
        amqpAdmin.declareBinding(messageExchangeBinding(messageAuditQueue, messageExchange));

        Queue bookingAddQueue = bookingAddQueue();
        Queue bookingEditQueue = bookingEditQueue();
        Queue bookingDelQueue = bookingDelQueue();
        TopicExchange bookingExchange = bookingExchange();
        amqpAdmin.declareQueue(bookingAddQueue);
        amqpAdmin.declareQueue(bookingEditQueue);
        amqpAdmin.declareQueue(bookingDelQueue);
        amqpAdmin.declareExchange(bookingExchange);
        amqpAdmin.declareBinding(bookingExchangeAddBinding(bookingAddQueue, bookingExchange));
        amqpAdmin.declareBinding(bookingExchangeEditBinding(bookingEditQueue, bookingExchange));
        amqpAdmin.declareBinding(bookingExchangeDelBinding(bookingDelQueue, bookingExchange));
        amqpAdmin.declareBinding(bookingExchangeMessageExchangeBinding(messageExchange, bookingExchange));

        return amqpAdmin;
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate rabbitJsonTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
