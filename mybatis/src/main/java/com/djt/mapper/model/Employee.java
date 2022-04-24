package com.djt.mapper.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(exclude = { "dept" })
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {

	private static final long serialVersionUID = -5044619645511197578L;
	
	private Integer empId;
	private String empName;
	private char gender;
	private String address;
	private Integer enable;
	private Department dept;

}
