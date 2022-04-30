package com.senior.concurrence.disruptor.ch02;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.EventHandlerGroup;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.*;

public class MessageMain0 {

    public static void main(String[] args) throws Exception {
        MessageEventFactory eventFactory = new MessageEventFactory();
        int bufferSize = 1024;

        Disruptor<Message> disruptor = new Disruptor<Message>(eventFactory, bufferSize, Executors.defaultThreadFactory(),
                ProducerType.MULTI, new BlockingWaitStrategy());
        disruptor.setDefaultExceptionHandler(new MessageExceptionHandler());

        MessageEventHandlerContent[] contents = new MessageEventHandlerContent[1];
        MessageEventHandlerFrom[] froms = new MessageEventHandlerFrom[2];
        MessageEventHandlerTo[] tos = new MessageEventHandlerTo[2];

        contents[0] = new MessageEventHandlerContent();

        for (int i = 0; i < 2; i++) {
            froms[i] = new MessageEventHandlerFrom();
            tos[i] = new MessageEventHandlerTo();
        }

        EventHandlerGroup<Message> baseGroup = disruptor.handleEventsWithWorkerPool(contents);
        
        EventHandlerGroup<Message> fromGroup = baseGroup.thenHandleEventsWithWorkerPool(froms);
        EventHandlerGroup<Message> toGroup = baseGroup.thenHandleEventsWithWorkerPool(tos);
        EventHandlerGroup<Message> fromAndToGroup = fromGroup.and(toGroup);
        fromAndToGroup.thenHandleEventsWithWorkerPool(new MessageEventHandlerMail());
        
        disruptor.start();

        RingBuffer<Message> ringBuffer = disruptor.getRingBuffer();

        ExecutorService pool = Executors.newCachedThreadPool();
        CyclicBarrier barrier = new CyclicBarrier(2);
        for (int i = 1; i <= 2; i++) {
            final Integer id = i;
            pool.execute(() -> {
                try {
                    barrier.await();
                    ringBuffer.publishEvent(MSG_TRANSLATOR, id);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }


    }

    private static final EventTranslatorOneArg<Message, Integer> MSG_TRANSLATOR = new EventTranslatorOneArg<Message, Integer>() {
        @Override
        public void translateTo(Message event, long sequence, Integer id) {
            event.setId(id);
            event.setContent(String.valueOf(ThreadLocalRandom.current().nextDouble(100, 1000)));
            System.out.println("Produce message " + event);
        }
    };

}
