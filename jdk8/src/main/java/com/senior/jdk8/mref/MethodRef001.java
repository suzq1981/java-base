package com.senior.jdk8.mref;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import com.senior.jdk8.domain.User;

public class MethodRef001 {

	public static void main(String[] args) {
		List<User> userList = new ArrayList<User>();
		userList.add(new User("zhangjuan", 37));
		userList.add(new User("suzhongqing", 40));
		userList.add(new User("liuyifei", 34));
		userList.add(new User("suzhieng", 10));
		userList.add(new User("guangxi", 18));
		userList.add(new User("suzhiyu", 8));
		userList.add(new User("suxi", 3));

		Stream<String> stream = userList.stream().map(user -> user.getName());
		// stream.forEach(name -> System.out.println(name));
		String[] array = stream.toArray(String[]::new);

		// 方法引用
		Arrays.sort(array, String::compareToIgnoreCase);

		Stream.of(array).forEach(name -> System.out.println(name));

		System.out.println("----------------Print------------");
		userList.stream().sorted().forEach(user -> System.out.print(user.getName() + " "));

	}

}
