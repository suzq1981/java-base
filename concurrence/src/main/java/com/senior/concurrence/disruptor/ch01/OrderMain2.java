package com.senior.concurrence.disruptor.ch01;

import java.util.Date;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventTranslatorTwoArg;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.TimeoutException;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

public class OrderMain2 {

	public static void main(String[] args) throws TimeoutException {
		OrderThreadFactory threadFactory = new OrderThreadFactory();
		OrderEventFactory eventFactory = new OrderEventFactory();
		OrderEventHandlerAddress orderEventHandlerAddress = new OrderEventHandlerAddress();
		OrderEventHandlerPrice orderEventHandlerPrice = new OrderEventHandlerPrice();
		int bufferSize = 1024;

		Disruptor<Order> disruptor = new Disruptor<Order>(eventFactory, bufferSize, threadFactory, ProducerType.MULTI,
				new BlockingWaitStrategy());

		disruptor.handleEventsWith(orderEventHandlerAddress).handleEventsWith(orderEventHandlerPrice);
		disruptor.setDefaultExceptionHandler(new DefaultExceptionHandler<>());

		disruptor.start();

		RingBuffer<Order> ringBuffer = disruptor.getRingBuffer();

		ExecutorService pool = Executors.newCachedThreadPool();
		CyclicBarrier barrier = new CyclicBarrier(10);

		for (int i = 1; i <= 20; i++) {
			final int orderId = i;
			pool.submit(new Thread(() -> {
				try {
					barrier.await();
					ringBuffer.publishEvent(ORDER_TRANSLATOR, orderId, new Date());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}));
		}

		/*
		 * for (int j = 1; true; j++) {
		 * ringBuffer.publishEvent(ORDER_TRANSLATOR, j, new Date()); }
		 */
		// pool.shutdown();
		// disruptor.shutdown(10,TimeUnit.SECONDS);
	}

	private static final EventTranslatorTwoArg<Order, Integer, Date> ORDER_TRANSLATOR = new EventTranslatorTwoArg<Order, Integer, Date>() {
		@Override
		public void translateTo(Order order, long sequence, Integer orderId, Date dateTime) {
			order.setOrderId(orderId);
			order.setOrderTime(dateTime);
			System.out.println("Produce: " + order);
		}
	};

}
