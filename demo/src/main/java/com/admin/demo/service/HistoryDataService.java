package com.admin.demo.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.demo.entity.History;
import com.admin.demo.repository.HistoryRepository;

@Service
public class HistoryDataService {
	@Autowired 
	private HistoryRepository historyRepository;
	
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
