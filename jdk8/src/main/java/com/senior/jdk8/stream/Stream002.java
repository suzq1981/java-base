package com.senior.jdk8.stream;

import com.senior.jdk8.domain.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Stream002 {

    public static void main(String[] args) {
        List<User> userList = new ArrayList<User>();
        userList.add(new User("zhangjuan", 37));
        userList.add(new User("suzhongqing", 40));
        userList.add(new User("liuyifei", 34));
        userList.add(new User("suzhieng", 10));
        userList.add(new User("guangxi", 18));
        userList.add(new User("suzhiyu", 8));
        userList.add(new User("suxi", 3));

        List<User> collect = userList.stream().filter(user -> user.getAge() >= 18).collect(Collectors.toList());

        Stream<String> stream = userList.stream().map(user -> user.getName());
        //stream.forEach(name -> System.out.println(name));
        String[] array = stream.toArray(String[]::new);

        collect.stream().forEach(user -> System.out.println(user));

        Arrays.sort(array, String::compareToIgnoreCase);

        for (String o : array) {
            System.out.println(o);
        }

    }

}
