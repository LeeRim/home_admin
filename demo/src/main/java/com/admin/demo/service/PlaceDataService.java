package com.admin.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.demo.entity.Place;
import com.admin.demo.repository.PlaceRepository;

@Service
public class PlaceDataService {
	@Autowired 
	private PlaceRepository placeRepository;
	
	public List<Place> getPlace() {
		List<Place> placeList = (List<Place>)placeRepository.findAll(); 
		return placeList;
	}

}
