package com.admin.demo.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.admin.demo.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.demo.entity.History;
import com.admin.demo.repository.HistoryRepository;

@Service
public class HistoryDataService {
	@Autowired 
	private HistoryRepository historyRepository;

	public List<History> getHistory(List<String> itemKey) {

		List<History> historyList = new ArrayList<History>();
		if (itemKey==null) {
			historyList = historyRepository.findAll();
		}else {
			itemKey = (itemKey==null) ? new ArrayList<String>():itemKey;
			historyList = historyRepository.findDistinctByItem_ItemKeyIn(itemKey);
		}

		return historyList;
	}
	
	public History postHistory(History historyParam) {
		String nextkey = "history"+historyRepository.getNextVal();
		History history = History.builder()
				.historyKey(nextkey)
				.item(historyParam.getItem())
				.action(historyParam.getAction())
				.updateDate(LocalDateTime.now())
				.count(historyParam.getCount())
				.price(historyParam.getPrice())
				.goodsName(historyParam.getGoodsName())
				.build();
		
		History newhistory = historyRepository.save(history);

		return newhistory;
	}

}
