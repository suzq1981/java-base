package com.djt.mapper.test.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.djt.mapper.SpringMapperApplication;
import com.djt.mapper.dao.EmployeeMapper;
import com.djt.mapper.model.Department;
import com.djt.mapper.model.Employee;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringMapperApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestEmployeeMapper {

	@Autowired
	private EmployeeMapper empMapper;

	@Test
	public void testFindEmpById() {
		Employee emp = empMapper.findEmpById(1);
		System.out.println(emp);
	}

	@Test
	public void testCreateEmp() {
		Employee emp = Employee.builder().empName("苏秀芸").gender('F').address("浙江省苍南县灵溪镇").build();
		empMapper.createEmp(emp);
		System.out.println(emp);
	}

	@Test
	public void testCountGender() {
		Map parameter = new HashMap<>();

		parameter.put("gender", 'M');
		parameter.put("count", -1);
		empMapper.countGender(parameter);

		System.out.println(parameter.get("count"));
	}

	@Test
	public void testFindEmpByNameAndGender() {
		List<Employee> emps = empMapper.findEmpByNameAndGender("苏", 'M');
		System.out.println(emps);
	}

	@Test
	public void testFindEmpByNameAndGender2() {
		Map paramMap = new HashMap<>();
		paramMap.put("name", "苏");
		paramMap.put("gender", "F");
		List<Employee> emps = empMapper.findEmpByNameAndGender2(paramMap);
		System.out.println(emps);
	}

	@Test
	public void testFindEmpByDynamic() {
		Employee employee = Employee.builder().address("福建省").build();
		List<Employee> emps = empMapper.findEmpByDynamic(employee);
		System.out.println(emps);
	}

	@Test
	public void testDeleteByBatch() {
		List<Employee> emps = new ArrayList<Employee>();
		emps.add(Employee.builder().empId(8).build());
		emps.add(Employee.builder().empId(9).build());

		int result = empMapper.deleteByBatch(emps);
		System.out.println(result);
	}

	@Test
	public void testCreateBatch() {
		List<Employee> emps = new ArrayList<Employee>();
		emps.add(Employee.builder().empName("周绍姜").gender('M').address("上海市").build());
		emps.add(Employee.builder().empName("周绍值").gender('M').address("上海市").build());

		int result = empMapper.createBatch(emps);
		System.out.println(result);
	}

	@Test
	public void testFindEmpAndDept() {
		List<Employee> emps = empMapper.findEmpAndDept("苏", 'F');
		System.out.println(emps);
	}
	
	@Test
	public void testFindEmpByDept(){
		List<Employee> emps = empMapper.findEmpByDept(1);
		System.out.println(emps);
	}
	
	@Test
	public void testFindEmpLazyById(){
		Employee emp = empMapper.findEmpLazyById(1);
		System.out.println(emp.getEmpName());
		Department dept = emp.getDept();
		System.out.println(dept.getDeptName());
	}
	
	@Test
	public void testFindEmpByDeptId(){
		List<Employee> emps = empMapper.findEmpByDeptId(1);
		System.out.println(emps);
	}
}
