package com.senlainc.bsdd.ecabs.booking.producer.kafkasender;

import com.senlainc.bsdd.ecabs.adapter.api.dto.BookingDto;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

import static com.senlainc.bsdd.ecabs.adapter.routers.Routing.MESSAGE_EXCHANGE.BOOKING_EXCHANGE.E_CABS_BOOKING_ADD_ROUTES_KEY;
import static com.senlainc.bsdd.ecabs.adapter.routers.Routing.MESSAGE_EXCHANGE.BOOKING_EXCHANGE.E_CABS_BOOKING_DEL_ROUTES_KEY;
import static com.senlainc.bsdd.ecabs.adapter.routers.Routing.MESSAGE_EXCHANGE.BOOKING_EXCHANGE.E_CABS_BOOKING_EDIT_ROUTES_KEY;

@Configuration
@EnableKafka
public class KafkaConfig {
//
//    @Value(value = "${spring.kafka.producer.bootstrap-servers}")
//    private String bootstrapAddress;
//
//    @Value("${spring.kafka.producer.client-id}")
//    private String kafkaProducerId;
//
//    @Bean
//    public KafkaAdmin kafkaAdmin() {
//        Map<String, Object> configs = new HashMap<>();
//        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
//        return new KafkaAdmin(configs);
//    }
//
//    @Bean
//    public NewTopic booking_edit_topic() {
//        return new NewTopic(E_CABS_BOOKING_EDIT_ROUTES_KEY, 1, (short) 1);
//    }
//
//    @Bean
//    public NewTopic booking_delete_topic() {
//        return new NewTopic(E_CABS_BOOKING_DEL_ROUTES_KEY, 1, (short) 1);
//    }
//
//    @Bean
//    public NewTopic booking_add_topic() {
//        return new NewTopic(E_CABS_BOOKING_ADD_ROUTES_KEY, 1, (short) 1);
//    }
//
//    @Bean
//    public ProducerFactory<String, BookingDto> bookingProducerFactory() {
//        Map<String, Object> configProps = producerConfigs();
//        return new DefaultKafkaProducerFactory<>(configProps);
//    }
//
//    private Map<String, Object> producerConfigs() {
//        Map<String, Object> configProps = new HashMap<>();
//        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
//        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//        configProps.put(ProducerConfig.CLIENT_ID_CONFIG, kafkaProducerId);
//        return configProps;
//    }
//
//    @Bean
//    public KafkaTemplate<String, BookingDto> bookingKafkaTemplate() {
//        KafkaTemplate<String, BookingDto> kafkaTemplate = new KafkaTemplate<>(bookingProducerFactory());
//        kafkaTemplate.setMessageConverter(jsonMessageConverter());
//        return kafkaTemplate;
//    }
//
//    @Bean
//    public JsonMessageConverter jsonMessageConverter(){
//        return new JsonMessageConverter();
//    }
}
