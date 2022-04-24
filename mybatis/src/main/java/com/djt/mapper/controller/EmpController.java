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

	/**
	 * 用来创建指定的员工
	 * @return 返回创建员工数
	 */
	@RequestMapping("/crt")
	public int create() {
		Employee emp = Employee.builder().empName("冯存钱").gender('M').address("浙江省绍兴市嘉善县").dept(Department.builder().deptId(2).build()).build();
		return employeeService.createEmp(emp);
	}

	/**
	 * 该方法是用来根据员工姓名删除指定的员工信息
	 * @return 返回所删除员工数
	 */
	@RequestMapping("/del")
	public int delete() {
		System.out.println("DDDDDDDDDDDDD");
		return employeeService.deleteEmpByName("冯存钱");
	}
}
