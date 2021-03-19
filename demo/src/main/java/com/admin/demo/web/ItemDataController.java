package com.admin.demo.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.admin.demo.entity.Item;
import com.admin.demo.service.ItemDataService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/data/item")
public class ItemDataController {
	
	private final ItemDataService itemDataService;
	
	@GetMapping()
	@ResponseBody
	public List<Item> getItem(@RequestParam(value = "usageKey", required=false)List<String> usageKey,
	        @RequestParam(value = "division", required=false)List<String> division) {
		
		List<Item> itemList = itemDataService.getItem(usageKey,division);
		
		return itemList;
	}
	
	@PostMapping()
	@ResponseBody
	public Item postItem(@RequestBody Item itemParam) {	
		
		Item item = itemDataService.postItem(itemParam);

		return item;
	}
	
	@GetMapping("/{key}")
	@ResponseBody
	public Item getItemKey(@PathVariable String key) {	
		
		Item item = itemDataService.getItemKey(key);
		
		return item;
	}
	
	@PostMapping("/{key}")
	@ResponseBody
	public Item postItemKey(@PathVariable String key, @RequestBody Item itemParam) {

		Item item = itemDataService.postItemKey(key, itemParam);

		return item;
	}

}
