package com.admin.demo.web;

import com.admin.demo.entity.Item;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.admin.demo.entity.History;
import com.admin.demo.service.HistoryDataService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/data/history")
public class HistoryDataController {
	
	private final HistoryDataService historyDataService;

	@GetMapping()
	@ResponseBody
	public List<History> getHistory(@RequestParam(value = "itemKey", required=false)List<String> itemKey) {

		List<History> historyList = historyDataService.getHistory(itemKey);

		return historyList;
	}
	
	@PostMapping("")
	@ResponseBody
	public History postHistory(@RequestBody History historyParam) {	
		
		History history = historyDataService.postHistory(historyParam);

		return history;
	}

}
