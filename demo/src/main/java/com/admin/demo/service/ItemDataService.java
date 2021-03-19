package com.admin.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.demo.entity.Item;
import com.admin.demo.repository.ItemRepository;

@Service
public class ItemDataService {
	@Autowired 
	private ItemRepository itemRepository;
	
	public List<Item> getItem(List<String> usageKey, List<String> division) {
		
		List<Item> itemList = new ArrayList<Item>();
		if (usageKey==null && division==null) {
			itemList = itemRepository.findAll();
		}else {			
			usageKey = (usageKey==null) ? new ArrayList<String>():usageKey;
			division = (division==null) ? new ArrayList<String>():division;
			itemList = itemRepository.findAllByUsage_UsageKeyInAndDivisionIn(usageKey,division);
		}
		
		return itemList;
	}
	
	public Item postItem(Item itemParam) {	
		String nextkey = "item"+itemRepository.getNextVal();
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
		
		Item newItem = itemRepository.save(item);
		
		return newItem;
	}
	
	public Item getItemKey(String key) {	
		Optional<Item> selectItem = itemRepository.findById(key);
		Item item = selectItem.get();
		return item;
	}
	
	public Item postItemKey(String key, Item itemParam) {	
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
		
		Item newItem = itemRepository.save(item);
		
		return newItem;
	}

}
