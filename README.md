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

## Zookeeper
### 1. Create a network
```shell
docker network create ecabs-network --driver bridge
```

### 2. Launch the Zookeeper server instance
```shell
docker run -d --name zookeeper-server \
    --network ecabs-network \
    -e ALLOW_ANONYMOUS_LOGIN=yes \
    bitnami/zookeeper:latest
```

### 3. Launch the Kafka server instance
```shell
docker run -d --name kafka-server \
    --network ecabs-network \
    -e ALLOW_PLAINTEXT_LISTENER=yes \
    -e KAFKA_CFG_ZOOKEEPER_CONNECT=zookeeper-server:2181 \
    bitnami/kafka:latest
```

### 4. Launch your Kafka client instance
```shell
   docker run -it --rm \
    --network app-tier \
    -e KAFKA_CFG_ZOOKEEPER_CONNECT=zookeeper-server:2181 \
    bitnami/kafka:latest kafka-topics.sh --list  --zookeeper zookeeper-server:2181
```

## To start messaging with docker-compose installed go to terminal and run
See: https://habr.com/ru/post/505720/
```shell
docker-compose -f ./docker-compose.yml up -d
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