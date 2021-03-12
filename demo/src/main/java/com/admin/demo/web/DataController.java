package com.admin.demo.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.admin.demo.entity.Item;
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
		
//		dataService.findAllItem().forEach(i -> {
//			System.out.println(i.getUsage().getUsageName());
//		});

		return usageList;
	}
	
	@RequestMapping("/item")
	@ResponseBody
	public List<Item> findItem(@RequestParam(value = "usageKey", required=false)List<String> usageKey,
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
}
