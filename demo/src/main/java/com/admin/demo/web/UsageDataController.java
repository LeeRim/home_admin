package com.admin.demo.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.admin.demo.entity.Usage;
import com.admin.demo.service.UsageDataService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/data/usage")
public class UsageDataController {
	
	private final UsageDataService usageDataService;
	
	@GetMapping()
	@ResponseBody
	public List<Usage> getUsage() {
		List<Usage> usageList = usageDataService.getUsage();
		return usageList;
	}

}
