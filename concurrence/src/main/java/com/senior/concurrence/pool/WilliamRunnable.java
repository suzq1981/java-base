package com.senior.concurrence.pool;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.TimeUnit;

@Data
@NoArgsConstructor
public class WilliamRunnable implements Runnable {

    private int by = 1;
    private String data;

    public WilliamRunnable(String data) {
        this.data = data;
    }

    public WilliamRunnable(String data, int by) {
        this.data = data;
        this.by = by;
    }

    @Override
    public void run() {
        try {
            int result = 1 / by;
            TimeUnit.SECONDS.sleep(1);
            System.out.println("Processing " + data);
        } catch (Exception e) {
            System.out.println("Exception: data" + data);
        }
    }
}
