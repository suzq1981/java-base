package com.djt.mvc3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.djt.mvc3.dto.EmployeeDto;
import com.djt.mvc3.dto.PetDto;

@Controller
@RequestMapping("/unit3")
@SessionAttributes(value = { "userinfo", "pet" }, types = { Integer.class })
public class Unit3Controller {

	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("name", "God's son");

		PetDto dog = new PetDto(1, "Dog", "black");
		PetDto cat = new PetDto(2, "Cat", "white");
		model.addAttribute(dog);
		model.addAttribute(cat);

		model.addAttribute("userinfo", "God's son");
		model.addAttribute("pet", cat);
		model.addAttribute("age", 40);

		System.out.println(model.asMap());
		return "unit3/index";
	}

	@RequestMapping("/testSession")
	public String testSession(@SessionAttribute("pet") PetDto cat) {
		System.out.println("Session: " + cat);
		return "unit3/result";
	}

	@ModelAttribute
	public void testModelAttribute(Model model) {
		PetDto pet = PetDto.builder().name("Pig").color("red").build();
		model.addAttribute("pet", pet);
		System.out.println("testModelAttribute 被执行了");
	}

	@RequestMapping("/testModelAttribute")
	public String testModelAttribute(@ModelAttribute("emp") EmployeeDto emp, Model model) {
		System.out.println(model.asMap());
		System.out.println(emp);
		return "unit3/model_attribute";
	}

}
