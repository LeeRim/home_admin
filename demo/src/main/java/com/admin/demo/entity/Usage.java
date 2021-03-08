package com.admin.demo.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name="usage", catalog="admin")
public class Usage {
	
	@Id
	@Column(name="usage_key")
	private String usageKey;
	
	@Column(name="usage_name")
	private String usageName;

}
