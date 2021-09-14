package com.senlainc.bsdd.ecabs.booking.producer.rabbitmqsernder;

import com.rabbitmq.client.Channel;
import com.senlainc.bsdd.ecabs.adapter.Routing;
import lombok.SneakyThrows;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.support.ChannelInterceptor;

import static com.senlainc.bsdd.ecabs.adapter.Routing.MessageExchange.BOOKING_AUDIT_ROUTES_KEY;
import static com.senlainc.bsdd.ecabs.adapter.Routing.MessageExchange.BookingExchange.E_CABS_BOOKING_EXCHANGE;
import static com.senlainc.bsdd.ecabs.adapter.Routing.MessageExchange.E_CABS_MESSAGE_AUDIT_QUEUE;
import static com.senlainc.bsdd.ecabs.adapter.Routing.MessageExchange.E_CABS_MESSAGE_EXCHANGE;

@Configuration
public class RabbitMQConfig {

    @Bean
    Queue messageAuditQueue(){
        return new Queue(E_CABS_MESSAGE_AUDIT_QUEUE, false);
    }

    @Bean
    TopicExchange messageExchange(){
        return new TopicExchange(E_CABS_MESSAGE_EXCHANGE);
    }

    @Bean
    @Qualifier
    Binding messageExchangeBinding(Queue messageAuditQueue, TopicExchange messageExchange){
       return BindingBuilder.bind(messageAuditQueue).to(messageExchange).with(BOOKING_AUDIT_ROUTES_KEY);
    }

// ================================

    @Bean
    Queue bookingAddQueue(){
        return new Queue(Routing.MessageExchange.BookingExchange.E_CABS_BOOKING_ADD_QUEUE, false);
    }


// ==============================

    // TODO: think about bean and exceptions
    @Bean
    @SneakyThrows
    Channel channel(ConnectionFactory connectionFactory){
        Connection connection = connectionFactory.createConnection();
        Channel channel = connection.createChannel(true);
        channel.exchangeBind(E_CABS_MESSAGE_EXCHANGE, E_CABS_BOOKING_EXCHANGE,BOOKING_AUDIT_ROUTES_KEY);
        return channel;
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
