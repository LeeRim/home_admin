package com.admin.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.demo.entity.Item;
import com.admin.demo.entity.Place;
import com.admin.demo.entity.Usage;
import com.admin.demo.repository.ItemRepository;
import com.admin.demo.repository.PlaceRepository;
import com.admin.demo.repository.UsageRepository;

@Service
public class DataService {
	@Autowired 
	private UsageRepository usageRepository;
	@Autowired 
	private PlaceRepository placeRepository;
	@Autowired 
	private ItemRepository itemRepository;
	
	public List<Usage> findAllUsage(){
		List<Usage> usageList = (List<Usage>)usageRepository.findAll(); 
		return usageList;
	}
	
	public List<Place> findAllPlace(){
		List<Place> placeList = (List<Place>)placeRepository.findAll(); 
		return placeList;
	}
	
	public String getItemNextKey() {
		Long nextval = itemRepository.getNextVal();
		String nextkey = "item"+Long.toString(nextval);
		return nextkey;
	}
	
	public Optional<Item> findByIdItem(String itemKey) {
		Optional<Item> item = itemRepository.findById(itemKey);
		return item;
	}
	
	public List<Item> findAllItem(){
		List<Item> itemList = (List<Item>)itemRepository.findAll(); 
		return itemList;
	}
	
	public List<Item> findAllByUsage_UsageKeyInAndDivisionInItem(List<String> usageKeyList, List<String> division){
		List<Item> itemList = (List<Item>)itemRepository.findAllByUsage_UsageKeyInAndDivisionIn(usageKeyList,division); 
		return itemList;
	}
	
	public Item saveItem(Item item) {
		Item newItem = itemRepository.save(item);
		return newItem;
	}
}
