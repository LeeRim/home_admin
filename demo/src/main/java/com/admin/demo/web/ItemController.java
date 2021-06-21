package com.admin.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.admin.demo.entity.Item;
import com.admin.demo.service.ItemDataService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/item")
public class ItemController {
	
	private final ItemDataService itemDataService;
	
	@RequestMapping("/list")
	public String viewItemPage() {
		return "item/list";
	}
	
	@RequestMapping("/history")
	public String viewItemhistoryPage(@RequestParam(value = "itemKey", required=false)String itemKey, Model model) {
		Item item = itemDataService.getItemKey(itemKey);
		if(item.getDivision().equals("somo")) {
		}
		model.addAttribute(item);
		return "item/history";
	}
	
}
