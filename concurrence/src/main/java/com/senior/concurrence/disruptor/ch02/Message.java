package com.senior.concurrence.disruptor.ch02;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private int id;
    private String content;
    private String from;
    private String to;
}
