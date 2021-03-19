package com.admin.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.admin.demo.entity.History;
import com.admin.demo.service.HistoryDataService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/data/history")
public class HistoryDataController {
	
	private final HistoryDataService historyDataService;
	
	@PostMapping("")
	@ResponseBody
	public History postHistory(@RequestBody History historyParam) {	
		
		History history = historyDataService.postHistory(historyParam);

		return history;
	}

}
