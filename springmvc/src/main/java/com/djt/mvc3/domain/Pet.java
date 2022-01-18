package com.djt.mvc3.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pet {

	private Integer id;
	private String name;

	public static List<Pet> getPets() {
		List<Pet> pets = new ArrayList<Pet>();
		pets.add(Pet.builder().id(1).name("Micky").build());
		pets.add(Pet.builder().id(2).name("Tokky").build());
		pets.add(Pet.builder().id(3).name("Googsy").build());
		
		return pets;
	}

}
