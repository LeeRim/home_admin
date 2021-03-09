package com.admin.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.admin.demo.entity.Item;

public interface ItemRepository extends JpaRepository<Item, String> {
	List<Item> findAllByUsage_UsageKeyInAndDivisionIn(List<String> usageKeyList, List<String> divisionList);
}
