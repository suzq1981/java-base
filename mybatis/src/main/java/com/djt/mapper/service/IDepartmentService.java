package com.djt.mapper.service;

import com.djt.mapper.model.Department;

public interface IDepartmentService {
	
	Department findDeptById(Integer deptId);
	
	void testTransaction() throws Exception;

}
