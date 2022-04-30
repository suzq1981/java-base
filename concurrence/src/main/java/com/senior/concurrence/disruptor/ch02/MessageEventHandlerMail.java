package com.senior.concurrence.disruptor.ch02;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import com.lmax.disruptor.WorkHandler;

public class MessageEventHandlerMail implements WorkHandler<Message>{

	 @Override
	    public void onEvent(Message message) throws Exception {
	        handle(message);
	    }

	    public void handle(Message message) throws Exception {
	        message.setFrom(String.valueOf(ThreadLocalRandom.current().nextInt(100, 1000)));
	        TimeUnit.MILLISECONDS.sleep(10);
	        System.out.println(System.currentTimeMillis() + " : " + Thread.currentThread().getName() + " *Send Mail* " + message);
	    }

}
