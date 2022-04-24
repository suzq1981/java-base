package com.djt.mapper.dao;

import org.apache.ibatis.annotations.Mapper;

import com.djt.mapper.model.Department;

@Mapper
public interface DepartmentMapper {

	Department findDeptById(Integer deptId);

	int createDept(Department dept);

	Department findDeptByIdAndE(Integer deptId, Integer enable);

}
