package com.djt.mapper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.djt.mapper.model.Department;
import com.djt.mapper.model.Employee;
import com.djt.mapper.service.IEmployeeService;

@RestController
@RequestMapping("/emp")
public class EmpController {

	@Autowired
	private IEmployeeService employeeService;
        // 1、添加指定的员工信息
	// 2、返回添加的数量
	@RequestMapping("/crt")
	public int create() {
		Employee emp = Employee.builder().empName("冯存钱").gender('M').address("浙江省绍兴市嘉善县").dept(Department.builder().deptId(2).build()).build();
		return employeeService.createEmp(emp);
	}
        // 1、删除指定姓名的员工
	// 2、返回删除的行数
	@RequestMapping("/del")
	public int delete() {
		System.out.println("DDDDDDDDDDDDD");
		return employeeService.deleteEmpByName("冯存钱");
	}
}
