package com.djt.mapper.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.djt.mapper.model.JmxHeap;

@RestController
@RequestMapping("/jmx")
public class JmxController {

	@RequestMapping("/memory")
	public String highMemory() throws Exception {
		List<JmxHeap> list = new ArrayList<JmxHeap>();
		int time = 0;
		while (true) {
			list.add(JmxHeap.builder().id(UUID.randomUUID().toString()).alloca(new Long[10][1024]).build());

			if (time >= 100) {
				break;
			}
			TimeUnit.MILLISECONDS.sleep(100);
		}
		return "high memory ok...";
	}

	@RequestMapping("/cpu")
	public String highCpu() {
		new Thread(() -> {
			List<String> strList = new ArrayList<String>();
			while (true) {
				long result = LongStream.rangeClosed(0, 200000000).sum();
				strList.add(UUID.randomUUID().toString().toUpperCase() + result);
			}
		}, "Badou-CPU-high").start();
		return "high cpu ok...";
	}

	@RequestMapping("/cpu2")
	public String highCpu2() {
		int compare = 1;
		while (true) {
			LongStream.rangeClosed(0, 200000000).sum();
			if (compare != 1) {
				break;
			}
		}
		return "ok";
	}

}
