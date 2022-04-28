package com.senior.concurrence.ch04;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

public class OldCollectionOper {

	public static void mapRemove() {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 1; i <= 10000; i++) {
			map.put(String.valueOf("Key-" + i), i);
		}

		Iterator<Entry<String, Integer>> it = map.entrySet().iterator();
		for (; it.hasNext();) {
			if (it.next().getValue() % 2 == 0) {
				it.remove();
			}
		}

		System.out.println("map size " + map.size());
	}

	public static void listRemove() {
		List<String> list = new ArrayList<String>();

		for (int i = 1; i <= 10000; i++) {
			list.add(String.valueOf(i));
		}

		new Thread(() -> {
			for (int i = 10001; i <= 10010; i++) {
				try {
					TimeUnit.MILLISECONDS.sleep(10);
				} catch (Exception e) {
					e.printStackTrace();
				}
				list.add(i + "");
			}
		}).start();

		for (int i = 0; i < list.size(); i++) {
			String value = list.get(i);
			if (Integer.valueOf(value) % 2 == 0) {
				try {
					TimeUnit.MILLISECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				list.remove(value);
			}
		}

		System.out.println("list size " + list.size());
	}
	
	public static void cowList(){
		List<String> list = new CopyOnWriteArrayList<String>();

		for (int i = 1; i <= 10000; i++) {
			list.add(String.valueOf(i));
		}

		new Thread(() -> {
			for (int i = 10001; i <= 10010; i++) {
				try {
					TimeUnit.MILLISECONDS.sleep(10);
				} catch (Exception e) {
					e.printStackTrace();
				}
				list.add(i + "");
			}
		}).start();

		for (int i = 0; i < list.size(); i++) {
			String value = list.get(i);
			if (Integer.valueOf(value) % 2 == 0) {
				try {
					TimeUnit.MILLISECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				list.remove(value);
			}
		}

		System.out.println("copy on write list size " + list.size());
	}

	public static void main(String[] args) {
//		mapRemove();
		listRemove();
		cowList();
	}

}
