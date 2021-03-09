package com.admin.demo.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.admin.demo.entity.Item;
import com.admin.demo.entity.Usage;
import com.admin.demo.service.DataService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/item")
public class ItemController {
	
	@RequestMapping("/list")
	public String viewItemPage() {
		return "item/list";
	}
	
}
