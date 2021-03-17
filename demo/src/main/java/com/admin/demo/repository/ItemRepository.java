package com.admin.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.admin.demo.entity.Item;

public interface ItemRepository extends JpaRepository<Item, String> {
	@Query(value = "SELECT nextval(admin.seq_item) FROM dual", nativeQuery = true)
	public Long getNextVal();
    //public BigDecimal getNextValMySequence();
	
	Optional<Item> findById(String itemKey);
	
	List<Item> findAllByUsage_UsageKeyInAndDivisionIn(List<String> usageKeyList, List<String> divisionList);
}
