package com.djt.mvc3.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

	private Integer id;
	private String name;
	private String gender;
	private Pet pet;
	private String[] hobbys;
	
	public static Customer getCustomer(){
		Customer customer = Customer.builder().id(1).name("苏忠清").gender("M").build();
		customer.setPet(Pet.getPets().get(1));
		String[] hs = new String[]{"basketball","reading","cooking"};
		customer.setHobbys(hs);
		
		return customer;
	}

}
