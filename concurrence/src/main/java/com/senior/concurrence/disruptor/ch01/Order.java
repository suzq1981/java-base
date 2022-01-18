package com.senior.concurrence.disruptor.ch01;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    private int orderId;
    private Date orderTime;
    private double price;
    private String addr;

}
