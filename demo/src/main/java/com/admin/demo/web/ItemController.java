package com.admin.demo.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.admin.demo.entity.Item;
import com.admin.demo.entity.Usage;
import com.admin.demo.service.ItemService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/item")
public class ItemController {
	
	private final ItemService itemService;
	
	@RequestMapping("/list")
	public String viewItemPage() {
		return "item/list";
	}
	
	@RequestMapping("/data/usage")
	@ResponseBody
	public List<Usage> crudUsage() {
		List<Usage> usageList = itemService.readUsage();
		
		itemService.readItem().forEach(i -> {
			System.out.println(i.getUsage().getUsageName());
		});

		return usageList;
	}
	
	@RequestMapping("/data/item")
	@ResponseBody
	public List<Item> crudItem() {
		List<Item> itemList = itemService.readItem();

		return itemList;
	}
}
