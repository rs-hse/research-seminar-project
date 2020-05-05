#!/bin/bash

HOST=localhost
PORT=9092
FILE_PATH=/Users/almaz/PyCharmProjects/research-seminar-project/twitter-api-simulator/winter_olympics.json
MESSAGES_PER_SECOND=60
NOISE_FACTOR=0.35

python twitter-api-simulator.py $HOST $PORT $FILE_PATH $MESSAGES_PER_SECOND $NOISE_FACTOR