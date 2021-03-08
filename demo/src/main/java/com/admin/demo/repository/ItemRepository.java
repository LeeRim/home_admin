package com.admin.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.admin.demo.entity.Item;

public interface ItemRepository extends JpaRepository<Item, String> {

}
