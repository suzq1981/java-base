package com.djt.mvc3.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetDto {

	private Integer petId;
	private String name;
	private String color;
	
	public static List<PetDto> getPets(){
		List<PetDto> pets = new ArrayList<PetDto>();
		pets.add(PetDto.builder().petId(1).name("MiKi").color("White").build());
		pets.add(PetDto.builder().petId(2).name("Bokkt").color("Red").build());
		pets.add(PetDto.builder().petId(3).name("Kitty").color("Black").build());
		
		return pets;
	}
	
}
