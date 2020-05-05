import sys
from kafka import KafkaProducer
from time import sleep
from json import dumps
from datetime import datetime
import random

KAFKA_TOPIC = "twitter-stream"


def print_params(host, port, file_path, messages_per_second, noise_factor):
    print("TWITTER API DATASOURCE SIMULATOR")
    print("========   HOST:  {}".format(host))
    print("========   PORT:  {}".format(port))
    print("========   FILE:  {}".format(file_path))
    print("========   MESSAGE PER SEC:  {}".format(messages_per_second))
    print("========   NOISE FACTOR: {}".format(noise_factor))


def send_message(producer, message):
    producer.send(KAFKA_TOPIC, message)


def create_kafka_producer(host, port):
    host_url = str(host + ":" + port)
    return KafkaProducer(bootstrap_servers=[host_url],
                         value_serializer=lambda x:
                         dumps(x).encode('utf-8'))


def messages_per_second_with_noise_factor(mps, nf):
    nf_normalized = float(nf) * 100
    rnd = random.randint(-nf_normalized, nf_normalized)
    return int(mps) + int(rnd)


def current_timestamp():
    return datetime.today().timestamp()


def process_next_step(producer, file, mps, nf, start_time):
    actual_messages_count = messages_per_second_with_noise_factor(mps, nf)
    print("In this iteration I'm going to send {} messages".format(actual_messages_count))
    line = file.readline()
    cnt = 1

    while line:
        json = line.strip()
        line = file.readline()
        send_message(producer, json)
        cnt += 1

        if cnt > actual_messages_count:
            producer.flush()
            one_minute = 60 * 1000
            time_remain_sec = (one_minute - (current_timestamp() - start_time)) / 1000

            # print("Current timestamp: {}".format(current_timestamp()))
            # print("Start   timestamp: {}".format(start_time))
            # print("Time remaining   : {}".format(time_remain_sec))

            print(time_remain_sec)
            if time_remain_sec > 0:
                sleep(time_remain_sec)

            print("Successfully sent {} messages".format(cnt))
            process_next_step(producer, file, mps, nf, current_timestamp())

    producer.flush()


def start_sending(producer, file, mps, nf):
    process_next_step(producer, file, mps, nf, current_timestamp())


if __name__ == '__main__':
    host = sys.argv[1]
    port = sys.argv[2]
    file_path = sys.argv[3]
    messages_per_second = sys.argv[4]
    noise_factor = sys.argv[5]

    print_params(host, port, file_path, messages_per_second, noise_factor)
    producer = create_kafka_producer(host, port)
    start_sending(producer, open(file_path, 'r'), messages_per_second, noise_factor)
