package com.djt.mvc3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.djt.mvc3.comm.Item;
import com.djt.mvc3.domain.Customer;
import com.djt.mvc3.domain.Pet;

@RequestMapping("/cust")
@Controller
public class CustomerController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("hobbys", Item.getHobbys());
		mv.addObject("pets", Pet.getPets());
		mv.addObject("customer", Customer.getCustomer());
		
		mv.setViewName("/cust/index");

		return mv;
	}
	
	@RequestMapping("/update")
	public String update(Customer cust){
		System.out.println(cust);
		return "/cust/result";
	}
}
