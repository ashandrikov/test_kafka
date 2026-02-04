#### 1. Скачать бинарник kafka

https://kafka.apache.org/community/downloads/  (binary)

#### 2. Создать config/kraft/server.properties файл

```
process.roles=broker,controller
node.id=1
controller.quorum.voters=1@localhost:9093
listeners=PLAINTEXT://localhost:9092,CONTROLLER://localhost:9093
advertised.listeners=PLAINTEXT://localhost:9092
listener.security.protocol.map=PLAINTEXT:PLAINTEXT,CONTROLLER:PLAINTEXT
inter.broker.listener.name=PLAINTEXT
controller.listener.names=CONTROLLER
log.dirs=/tmp/kafka-logs
```

#### 3.  Сгенерировать clusterId
```
cd ../kafka-v4.1.1
bin/kafka-storage.sh random-uuid
```

#### 4. Переинициализировать
```
bin/kafka-storage.sh format -t <clusterId> -c config/kraft/server.properties
```

#### 5. Запуск
```
bin/kafka-server-start.sh config/kraft/server.properties
```

#### 6. Создать тестовый топик (в новом терминале)
```
bin/kafka-topics.sh --create --topic test --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
```

#### 7. Проверить список топиков
```
bin/kafka-topics.sh --list --bootstrap-server localhost:9092
```

#### 8. Запуск продюсера (в новом терминале)

```
bin/kafka-console-producer.sh --producer.config config/producer.properties --bootstrap-server localhost:9092 --topic test
```

#### 9. Запуск консьюмера (в новом терминале)
 
```  
bin/kafka-console-consumer.sh --consumer.config config/consumer.properties --bootstrap-server localhost:9092 --topic test --from-beginning --group test-debug-group
```

#### 10. DEBUG: Создать offsets (если не создается автоматически при запуске кафки)

```
bin/kafka-topics.sh \
--bootstrap-server localhost:9092 \
--create \
--topic __consumer_offsets \
--partitions 50 \
--replication-factor 1 \
--config cleanup.policy=compact \
--config compression.type=producer \
--config segment.bytes=104857600
```

#### 11. DEBUG: Проверить наличие сообщений 

```
bin/kafka-dump-log.sh \
   --files /tmp/kafka-logs/test-0/00000000000000000000.log \
   --print-data-log
```

