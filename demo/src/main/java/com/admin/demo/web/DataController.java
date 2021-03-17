package com.admin.demo.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.admin.demo.entity.Item;
import com.admin.demo.entity.Place;
import com.admin.demo.entity.Usage;
import com.admin.demo.service.DataService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/data")
public class DataController {
	
	private final DataService dataService;
	
	@RequestMapping("/usage")
	@ResponseBody
	public List<Usage> findUsage() {
		List<Usage> usageList = dataService.findAllUsage();
		return usageList;
	}
	
	@RequestMapping("/place")
	@ResponseBody
	public List<Place> findPlace() {
		List<Place> placeList = dataService.findAllPlace();
		return placeList;
	}
	
	@GetMapping("/item")
	@ResponseBody
	public List<Item> readItem(@RequestParam(value = "usageKey", required=false)List<String> usageKey,
	        @RequestParam(value = "division", required=false)List<String> division) {
		
		List<Item> itemList = new ArrayList<Item>();
		if (usageKey==null && division==null) {
			itemList = dataService.findAllItem();
		}else {			
			usageKey = (usageKey==null) ? new ArrayList<String>():usageKey;
			division = (division==null) ? new ArrayList<String>():division;
			itemList = dataService.findAllByUsage_UsageKeyInAndDivisionInItem(usageKey,division);
		}

		return itemList;
	}
	
	@PostMapping("/item")
	@ResponseBody
	public Item createItem(@RequestBody Item itemParam) {	
		String nextkey = dataService.getItemNextKey();
		Item item = Item.builder()
				.itemKey(nextkey)
				.itemName(itemParam.getItemName())
				.usage(itemParam.getUsage())
				.place(itemParam.getPlace())
				.division(itemParam.getDivision())
				.spot(itemParam.getSpot())
				.unit(itemParam.getUnit())
				.count(itemParam.getCount())
				.build();
		
		Item newItem = dataService.saveItem(item);

		return newItem;
	}
	
	@GetMapping("/item/{key}")
	@ResponseBody
	public Item selectItem(@PathVariable String key) {	
		Optional<Item> selectItem = dataService.findByIdItem(key);
		Item item = selectItem.get();
		return item;
	}
	
	@PostMapping("/item/{key}")
	@ResponseBody
	public Item updateItem(@PathVariable String key, @RequestBody Item itemParam) {	
		Item item = Item.builder()
				.itemKey(key)
				.itemName(itemParam.getItemName())
				.usage(itemParam.getUsage())
				.place(itemParam.getPlace())
				.division(itemParam.getDivision())
				.spot(itemParam.getSpot())
				.unit(itemParam.getUnit())
				.count(itemParam.getCount())
				.build();
		
		Item newItem = dataService.saveItem(item);
		
		return newItem;
	}
}
