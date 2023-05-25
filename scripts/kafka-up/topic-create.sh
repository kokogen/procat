#!/bin/zsh

/bin/kafka-topics --bootstrap-server localhost:29092 --create --replication-factor 1 --partitions 1 --topic procat