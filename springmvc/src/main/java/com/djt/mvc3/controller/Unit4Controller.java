package com.djt.mvc3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.djt.mvc3.comm.Item;
import com.djt.mvc3.dto.EmployeeDto;
import com.djt.mvc3.dto.PetDto;

@Controller
@RequestMapping("/unit4")
public class Unit4Controller {

	@RequestMapping("/load/{id}")
	public String load(@PathVariable Integer id, Model model) {
		System.out.println(id);
		EmployeeDto emp = EmployeeDto.builder().empId(id).empName("苏忠清").gender('M').build();
		emp.setHobbies(new String[] { "basketball", "football", "reading" });
		emp.setPet(PetDto.getPets().get(1));
		
		model.addAttribute("emp", emp);
		model.addAttribute("hobbys", Item.getHobbys());
		model.addAttribute("pets", PetDto.getPets());

		return "/unit4/update";
	}

	@RequestMapping("/update")
	public String update(EmployeeDto emp) {
		
		System.out.println(emp);
		return "/unit4/result";
	}

}
