package com.admin.demo.entity;

import java.time.LocalDateTime;

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
@Table(name="history", catalog="admin")
public class History {
	@Id
	@Column(name="history_key")
	private String historyKey;
	
	private String action;
	
	@Column(name="update_date")
	private LocalDateTime updateDate;
	
	private int count;
	
	private long price;
	
	@Column(name="goods_name")
	private String goodsName;
	
	@ManyToOne
	@JoinColumn(name="item_fk")
	private Item item;

}
