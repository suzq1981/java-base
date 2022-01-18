package com.djt.mvc3.comm;

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
public class Item {

	private String label;
	private String value;
	
	public static List<Item> getHobbys(){
		List<Item> hobbies = new ArrayList<Item>();
		hobbies.add(Item.builder().label("篮球").value("basketball").build());
		hobbies.add(Item.builder().label("足球").value("football").build());
		hobbies.add(Item.builder().label("读书").value("reading").build());
		hobbies.add(Item.builder().label("烹饪").value("cooking").build());
		
		return hobbies;
	}
	
}
