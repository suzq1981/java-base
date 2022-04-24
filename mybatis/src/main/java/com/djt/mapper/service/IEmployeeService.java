package com.djt.mapper.service;

import com.djt.mapper.model.Employee;

public interface IEmployeeService {
	
	int createEmp(Employee emp);
	
	int deleteEmpByName(String empName);

}
