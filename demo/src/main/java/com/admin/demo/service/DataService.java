package com.admin.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.demo.entity.Item;
import com.admin.demo.entity.Usage;
import com.admin.demo.repository.ItemRepository;
import com.admin.demo.repository.UsageRepository;

@Service
public class DataService {
	@Autowired 
	private UsageRepository usageRepository;
	@Autowired 
	private ItemRepository itemRepository;
	
	public List<Usage> findAllUsage(){
		List<Usage> usageList = (List<Usage>)usageRepository.findAll(); 
		return usageList;
	}
	
	public List<Item> findAllItem(){
		List<Item> itemList = (List<Item>)itemRepository.findAll(); 
		return itemList;
	}
	
	public List<Item> findAllByUsage_UsageKeyInAndDivisionInItem(List<String> usageKeyList, List<String> division){
		List<Item> itemList = (List<Item>)itemRepository.findAllByUsage_UsageKeyInAndDivisionIn(usageKeyList,division); 
		return itemList;
	}
}
