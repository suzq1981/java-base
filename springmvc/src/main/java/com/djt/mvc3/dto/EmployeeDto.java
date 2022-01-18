package com.djt.mvc3.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto{

	private Integer empId;
	private String empName;
	private char gender;
	private String[] hobbies;
	private PetDto pet;
	private PetDto[] pets;

}
