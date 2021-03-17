package com.admin.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.admin.demo.entity.Place;

@Repository
public interface PlaceRepository extends JpaRepository<Place, String>{

}
