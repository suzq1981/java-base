package com.djt.mvc2.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.djt.mvc2.dto.User;
import com.google.common.util.concurrent.RateLimiter;

@RestController
@RequestMapping("/rl")
public class RateLimiterController {

	private RateLimiter rateLimiter1 = RateLimiter.create(2);
	private RateLimiter rateLimiter2 = RateLimiter.create(5);
	private RateLimiter rateLimiter3 = RateLimiter.create(30);

	@RequestMapping("/demo1")
	public String demo1() {
		boolean acquired = rateLimiter1.tryAcquire();
		if (acquired) {
			// 处理业务信息
			return "success";
		}
		// 拒绝
		return "refuse";
	}

	@RequestMapping(value = "/demo2", method = RequestMethod.POST)
	public User demo2(@RequestBody User user) {
		rateLimiter2.acquire();
		user.setAge(18);
		return user;
	}

	@RequestMapping("/demo3")
	public String demo3() {
		rateLimiter3.acquire();
		return "Demo3";
	}

}
