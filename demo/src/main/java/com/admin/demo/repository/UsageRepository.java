package com.admin.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.admin.demo.entity.Usage;

@Repository
public interface UsageRepository extends JpaRepository<Usage, String>{

}
