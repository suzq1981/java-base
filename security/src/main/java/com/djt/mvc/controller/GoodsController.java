package com.djt.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/goods")
public class GoodsController {

	@RequestMapping("/add")
	public String add() {
		return "goods/goodsAdd";
	}

	@RequestMapping("/delete")
	public String delete() {
		return "goods/goodsDelete";
	}

	@RequestMapping("/update")
	public String update() {
		return "goods/goodsUpdate";
	}

	@RequestMapping("/list")
	public String list() {
		return "goods/goodsList";
	}

}
