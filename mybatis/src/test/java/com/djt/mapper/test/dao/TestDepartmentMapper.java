package com.djt.mapper.test.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.djt.mapper.SpringMapperApplication;
import com.djt.mapper.dao.DepartmentMapper;
import com.djt.mapper.model.Department;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringMapperApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestDepartmentMapper {

	@Autowired
	private DepartmentMapper mapper;

	@Test
	public void testFindDeptById() throws Exception {
		long s1 = System.currentTimeMillis();
		Department dept = mapper.findDeptById(1);
		System.out.println(dept.getDeptName());
		System.out.println("第一次所用时间：" + (System.currentTimeMillis() - s1));
		// System.out.println(dept.getEmps());
		Thread.sleep(10000);
		long s2 = System.currentTimeMillis();
		Department dept2 = mapper.findDeptById(1);
		System.out.println(dept2.getDeptName());
		System.out.println("第二次所用时间：" + (System.currentTimeMillis() - s2));
	}

}
