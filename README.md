# eCabs

## MongoDb
Run docker container
```shell
docker run --name ecabs-mongo -d mongo:latest
```

## Docker compose
Fill with some value`YOUR_BROKER_HOST_PUBLIC_IP` at 
`KAFKA_ADVERTISED_LISTENERS:INSIDE://kafka:9093,OUTSIDE://YOUR_BROKER_HOST_PUBLIC_IP:9092`
in ``kafka_conf/docker-compose.yml`` file to call kafka from external hosts.

### Prerequisite
Docker-compose and docker required to run stuff from /kafka_directory

### Available console params

* ***- - consumer-enabled*** - you can disable/enable consumer if needed. Default is true
* ***- -kafka_bootstrap_servers*** - set your server address here to call kafka from external hosts. Default is localhost:9092
* ***- -kafka_username / --kafka_password*** - you can change default user in kafka_conf/kafka_server_jaas.conf. Default is admin/admin-secret
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
   * ecabs-booking-producer-kafkasender
   * ecabs-booking-service