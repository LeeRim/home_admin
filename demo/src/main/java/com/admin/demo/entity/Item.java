package com.admin.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="item", catalog="admin")
public class Item {
	@Id
	@Column(name="item_key")
	private String itemKey;
	
	@Column(name="item_name")
	private String itemName;
	
	@Column(name="place_fk")
	private String placeFK;
	
	private String division;
	
	private String spot;
	
	private String unit;
	
	private String count;
	
	@ManyToOne
	@JoinColumn(name="usage_fk")
	private Usage usage;
}