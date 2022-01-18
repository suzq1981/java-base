package com.djt.mapper.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.djt.mapper.model.Employee;

@Mapper
public interface EmployeeMapper {

	Employee findEmpById(Integer empId);

	int createEmp(Employee emp);

	void countGender(Map parameter);
	
	List<Employee> findEmpByNameAndGender(String empName, char gender);
	
	List<Employee> findEmpByNameAndGender2(Map map);
	
	List<Employee> findEmpByDynamic(Employee emp);
	
	int deleteByBatch(List<Employee> emps);
	
	int createBatch(List<Employee> emps);
	
	List<Employee> findEmpAndDept(String empName, char gender);
	
	List<Employee> findEmpByDept(Integer deptId);

	Employee findEmpLazyById(Integer empId);
	
	List<Employee> findEmpByDeptId(Integer deptId);
}
