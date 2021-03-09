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
@Table(name="place", catalog="admin")
public class Place {
	@Id
	@Column(name="place_key")
	private String placeKey;
	
	@Column(name="area_name")
	private String areaName;
	
	@Column(name="place_name")
	private String placeName;
	
	@Column(name="place_photo")
	private String placePhoto;

}
