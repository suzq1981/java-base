package com.djt.mapper.test.service;

import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.djt.mapper.SpringMapperApplication;
import com.djt.mapper.service.IDepartmentService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringMapperApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestDepartmentService {

	@Autowired
	private IDepartmentService deptService;

	@Test
	public void testFindDeptById() {
		deptService.findDeptById(1);
	}

	@Test
	public void testTransaction() throws Exception {
		List<MyThread> list = Collections.synchronizedList(new ArrayList<MyThread>());
		list.add(new MyThread("t0", deptService));
		list.add(new MyThread("t1", deptService));
		list.add(new MyThread("t2", deptService));
		list.add(new MyThread("t3", deptService));
		list.add(new MyThread("t4", deptService));
		list.add(new MyThread("t5", deptService));
		list.add(new MyThread("t6", deptService));
		list.add(new MyThread("t7", deptService));
		list.add(new MyThread("t8", deptService));
		list.add(new MyThread("t9", deptService));

		for (MyThread t : list) {
			t.start();
		}
		while (true) {
			if (list.size() == 0) {
				break;
			}
			for (int k = 0; k < list.size(); k++) {
				MyThread t = list.get(k);
				TimeUnit.MILLISECONDS.sleep(100);
				System.out.println(t + ", state=" + t.getState());
				if (t.getState() == State.TERMINATED) {
					list.remove(t);
					k--;
				} else {
					long spentTime = System.currentTimeMillis() - t.getStartTime();
					if (spentTime > 2500) {
						t.stop();
					}
				}
			}
		}
	}

	@Test
	public void testTransaction2() throws Exception {
		MyThreadPoolExecutor pool = new MyThreadPoolExecutor(0, Integer.MAX_VALUE, 5L, TimeUnit.SECONDS,
				new SynchronousQueue<Runnable>());

		int index = 1;
		for (; index <= 5; index++) {
			pool.execute(new MyThread("t" + index, deptService));
		}

		TimeUnit.SECONDS.sleep(3);
		while (true) {
			if (pool.list.size() == 0) {
				break;
			}
			for (int i = 0; i < pool.list.size(); i++) {
				MyObject obj = pool.list.get(i);
				if (obj.getT().getState() == Thread.State.TERMINATED) {
					pool.list.remove(obj);
					i--;
				} else {
					if ((System.currentTimeMillis() - obj.getMyt().getStartTime()) > 2500) {
						if (obj.getT().getState() != Thread.State.TERMINATED) {
							obj.getT().stop();
						}
					}
				}

			}
			if (index <= 10) {
				pool.execute(new MyThread("t" + index, deptService));
				index++;
			}
			TimeUnit.MILLISECONDS.sleep(10);
		}
	}

	@Test
	public void testTransaction3() throws Exception {
		MyThreadPoolExecutor pool = new MyThreadPoolExecutor(5, 5, 5L, TimeUnit.SECONDS,
				new SynchronousQueue<Runnable>());

		int index = 1;

		for (; index <= 5; index++) {
			pool.execute(new MyThread("t" + index, deptService));
		}

		TimeUnit.SECONDS.sleep(3);

		while (true) {
			if (pool.map.keySet().size() == 0) {
				break;
			}
			Iterator<MyThread> it = pool.map.keySet().iterator();
			while (it.hasNext()) {
				MyThread myt = it.next();
				Thread t = pool.map.get(myt);
				if (t.getState() == Thread.State.TERMINATED) {
					it.remove();//当并发加入后
				} else {
					if (System.currentTimeMillis() - myt.getStartTime() > 2500) {
						if (t.getState() != Thread.State.TERMINATED) {
							//t.stop();
							t.interrupt();
						}
					}
				}

			}
			if (index <= 10) {
				pool.execute(new MyThread("t" + index, deptService));
				index++;
			}

			TimeUnit.MILLISECONDS.sleep(10);
		}

		System.out.println("YYYYYYYY, map.size=" + pool.map.size());
	}

	@Test
	public void testTransaction4() throws Exception {
		DelayQueue<DelayThread> queue = new DelayQueue<DelayThread>();
		for (int i = 1; i <= 10; i++) {
			TimeUnit.MILLISECONDS.sleep(100);
			DelayThread t = new DelayThread("t" + i, deptService);
			queue.add(t);
			t.start();
		}

		
		System.out.println("oooooooooooooooooooooooooooooo= " + Arrays.toString(queue.toArray()));
		System.out.println("oooooooooooooooooooooooooooooo= " + Arrays.toString(queue.toArray()));
		System.out.println("oooooooooooooooooooooooooooooo= " + Arrays.toString(queue.toArray()));
		System.out.println("oooooooooooooooooooooooooooooo= " + Arrays.toString(queue.toArray()));
		System.out.println("oooooooooooooooooooooooooooooo= " + Arrays.toString(queue.toArray()));
		System.out.println("oooooooooooooooooooooooooooooo= " + Arrays.toString(queue.toArray()));

		while (true) {
			if (queue.size() == 0) {
				break;
			}
			DelayThread t = queue.take();
			System.out.println("ppppppppppppppppppppp=" + t);
			if (t.getState() != State.TERMINATED) {
				t.stop();
			}
		}
	}
}
