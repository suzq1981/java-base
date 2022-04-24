package com.djt.mapper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.djt.mapper.dao.EmployeeMapper;
import com.djt.mapper.model.Employee;
import com.djt.mapper.service.IEmployeeService;


@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EmployeeServiceImpl implements IEmployeeService {
	
	@Autowired
	private EmployeeMapper empMapper;

	@Override
	public int createEmp(Employee emp) {
		return empMapper.createEmp(emp);
	}

	@Override
	public int deleteEmpByName(String empName) {
		return empMapper.deleteEmpByName(empName);
	}

}
