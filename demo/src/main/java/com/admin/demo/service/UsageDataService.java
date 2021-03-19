package com.admin.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.demo.entity.Usage;
import com.admin.demo.repository.UsageRepository;

@Service
public class UsageDataService {
	@Autowired 
	private UsageRepository usageRepository;
	
	public List<Usage> getUsage() {
		List<Usage> usageList = (List<Usage>)usageRepository.findAll();
		return usageList;
	}

}
