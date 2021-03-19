package com.admin.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.admin.demo.entity.History;

public interface HistoryRepository extends JpaRepository<History, String>{
	@Query(value = "SELECT nextval(admin.seq_history) FROM dual", nativeQuery = true)
	public Long getNextVal();
	
	Optional<History> findById(String historyKey);
}
