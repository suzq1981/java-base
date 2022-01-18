package com.senior.concurrence.disruptor.ch02;

import com.lmax.disruptor.EventFactory;
import com.senior.concurrence.disruptor.ch01.Order;

public class MessageEventFactory implements EventFactory<Message> {

    @Override
    public Message newInstance() {
        return Message.builder().build();
    }

}
