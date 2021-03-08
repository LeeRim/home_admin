package com.admin.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.demo.entity.Item;
import com.admin.demo.entity.Usage;
import com.admin.demo.repository.ItemRepository;
import com.admin.demo.repository.UsageRepository;

@Service
public class ItemService {
	
	@Autowired 
	private UsageRepository usageRepository;
	@Autowired 
	private ItemRepository itemRepository;
	
	public List<Usage> readUsage(){
		List<Usage> usageList = (List<Usage>)usageRepository.findAll(); 
		return usageList;
	}
	
	public List<Item> readItem(){
		List<Item> itemList = (List<Item>)itemRepository.findAll(); 
		return itemList;
	}

}
