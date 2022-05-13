package com.senior.jdk8.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Comparable<User> {

	private String name;
	private Integer age;

	@Override
	public int compareTo(User o) {
		return this.getName().compareToIgnoreCase(o.getName());
	}

}
