package com.admin.demo.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.admin.demo.entity.Place;
import com.admin.demo.service.PlaceDataService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/data/place")
public class PlaceDataController {
	private final PlaceDataService placeDataService;

	@GetMapping()
	@ResponseBody
	public List<Place> getPlace() {
		List<Place> placeList = placeDataService.getPlace();
		return placeList;
	}

}
