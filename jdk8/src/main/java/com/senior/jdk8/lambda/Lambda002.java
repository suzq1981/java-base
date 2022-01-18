package com.senior.jdk8.lambda;

import com.senior.jdk8.domain.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lambda002 {

    public static void main(String[] args) {
        List<User> userList = new ArrayList<User>();
        userList.add(new User("张娟", 37));
        userList.add(new User("苏忠清", 40));
        userList.add(new User("苏知恩", 10));
        userList.add(new User("苏知育", 8));

        System.out.println("排序前：");
        for (User user : userList) {
            System.out.println(user);
        }

        Collections.sort(userList, (u0, u1) -> u0.getAge() - u1.getAge());

        System.out.println("排序后：");
        for (User user : userList) {
            System.out.println(user);
        }
    }

}
