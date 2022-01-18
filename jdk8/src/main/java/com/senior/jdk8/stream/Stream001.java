package com.senior.jdk8.stream;

import com.senior.jdk8.domain.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Stream001 {

    public static void main(String[] args) {
        List<User> userList = new ArrayList<User>();
        userList.add(new User("张娟", 37));
        userList.add(new User("苏忠清", 40));
        userList.add(new User("刘亦菲", 34));
        userList.add(new User("苏知恩", 10));
        userList.add(new User("木村光希", 18));
        userList.add(new User("苏知育", 8));
        userList.add(new User("苏茜", 3));

        userList.stream()
                .filter(user -> user.getName().startsWith("苏"))
                .filter(user -> user.getName().length() == 3)
                .sorted((u1, u2) -> u1.getAge() - u2.getAge())
                .forEach(u1 -> System.out.println(u1));

        Map<String, User> map = new HashMap<>();
        userList.stream().forEach(user -> map.put(user.getName(), user));
        System.out.println("-----------------------------");
        map.entrySet().stream()
                .filter(entry -> entry.getValue().getAge() >= 18)
                .sorted((u1, u2) -> u1.getValue().getAge() - u2.getValue().getAge())
                //.filter((entry) -> entry.getKey().startsWith("苏"))
                .forEach(entry -> System.out.println(entry.getValue()));


    }
}
