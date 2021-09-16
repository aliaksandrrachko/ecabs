# eCabs

## RabbitMQ
### Configuration
Run The RabbitMQ
```shell
docker run -d --hostname ecabs-rabbit --name rabbit-assignment \
 -e RABBITMQ_DEFAULT_VHOST=/ \
 -p 15672:15672 rabbitmq:3-management
```

Credentials:
* username: guest
* password: guest

### Register the Listener and Send a Message
Spring AMQP’s RabbitTemplate provides everything you need to send and receive messages with RabbitMQ. However, you need to:

* Configure a message listener container.
* Declare the queue, the exchange, and the binding between them.
* Configure a component to send some messages to test the listener.

## MongoDb
Run docker container
```shell
docker run --name ecabs-mongo -d mongo:latest
```

## Docker compose
You can use docker-compose.yml, but I don't understand why microservices start earlier than ecabs-rabbit.
```shell
docker-compose up
```

## Projects and modules
 * ecabs-adapter
   * ecabs-adapter-api (Dtos and mappers)
   * ecabs-adapter-entity (Entities)
   * ecabs-adapter-router (Routers constants)
 * ecabs-booking-consumer
   * ecabs-booking-consumer-api
   * ecabs-booking-consumer-main
   * ecabs-booking-consumer-service
 * ecabs-booking-producer
   * ecabs-booking-producer-api
   * ecabs-booking-producer-controller
   * ecabs-booking-producer-main
   * ecabs-booking-producer-rabbitmqsender
   * ecabs-booking-service