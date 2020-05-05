## TWITTER API SIMULATOR

- python program that read tweets from file and put them to the kafka topic
- params:
    - kafka connection params
    - number of tweets per second
    - noise factor, number in percentage that represents the randomness of tweet stream 

### Dev team: Habibullah, Almaz

### How to run the module?

- download the kafka
`wget http://mirror.linux-ia64.org/apache/kafka/2.5.0/kafka_2.12-2.5.0.tgz `
- unzip kafka
`tar -xzf kafka_2.12-2.5.0.tgz`
- run zookeeper
`bin/zookeeper-server-start.sh config/zookeeper.properties`
- run kafka
`bin/kafka-server-start.sh config/server.properties`