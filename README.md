# eCabs

## RabbitMQ
### Configuration
User docker official image.
```shell
docker pull rabbitmq
```

Run
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