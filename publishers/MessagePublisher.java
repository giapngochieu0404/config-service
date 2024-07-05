package com.hieuubuntu.configservice.publishers;

public interface MessagePublisher<T> {

    void publish(String channel, T message);
}
