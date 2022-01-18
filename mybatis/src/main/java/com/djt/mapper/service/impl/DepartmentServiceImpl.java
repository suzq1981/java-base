package com.djt.mapper.service.impl;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.djt.mapper.dao.DepartmentMapper;
import com.djt.mapper.model.Department;
import com.djt.mapper.service.IDepartmentService;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DepartmentServiceImpl implements IDepartmentService {

	@Autowired
	private DepartmentMapper departmentMapper;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Department findDeptById(Integer deptId) {
		long s1 = System.currentTimeMillis();
		Department dept = departmentMapper.findDeptById(deptId);
		System.out.println(dept.getDeptName());
		System.out.println("第一次所用时间：" + (System.currentTimeMillis() - s1));
		// System.out.println(dept.getEmps());
		long s2 = System.currentTimeMillis();
		Department dept2 = departmentMapper.findDeptById(deptId);
		System.out.println(dept2.getDeptName());
		System.out.println("第二次所用时间：" + (System.currentTimeMillis() - s2));
		long s3 = System.currentTimeMillis();
		Department dept3 = departmentMapper.findDeptById(deptId);
		System.out.println(dept3.getDeptName());
		System.out.println("第三次所用时间：" + (System.currentTimeMillis() - s3));
		return dept;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void testTransaction() throws Exception {
		System.out.println(Thread.currentThread().getName() + "  ,XXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		Department dept1 = Department.builder().deptName(Thread.currentThread().getName() + "-001")
				.description(Thread.currentThread().getName()).build();
		int step1 = (ThreadLocalRandom.current().nextInt(500, 2000));
		TimeUnit.MILLISECONDS.sleep(step1);
		departmentMapper.createDept(dept1);

		Department dept2 = Department.builder().deptName(Thread.currentThread().getName() + "-002")
				.description(Thread.currentThread().getName()).build();
		int step2 = ThreadLocalRandom.current().nextInt(500, 2000);
		TimeUnit.MILLISECONDS.sleep(step2);
		departmentMapper.createDept(dept2);

		Department dept3 = Department.builder().deptName(Thread.currentThread().getName() + "-003")
				.description(Thread.currentThread().getName()).build();
		int step3 = ThreadLocalRandom.current().nextInt(500, 2000);
		TimeUnit.MILLISECONDS.sleep(step3);

		int sleepTime = step1 + step2 + step3;
		if (sleepTime < 2500) {
			// System.out.println(Thread.currentThread().getName() +
			// " service sleep time=" + sleepTime);
		}

		dept3.setDescription(String.valueOf(sleepTime));
		departmentMapper.createDept(dept3);
		
	}
}
