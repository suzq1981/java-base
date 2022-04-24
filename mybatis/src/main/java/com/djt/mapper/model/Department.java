package com.djt.mapper.model;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString(exclude = { "emps" })
@NoArgsConstructor
@AllArgsConstructor
public class Department implements Serializable {

	private static final long serialVersionUID = -5608719557656666534L;
	
	private Integer deptId;
	private String deptName;
	private String description;
	private Integer enable;
	private List<Employee> emps;
}
