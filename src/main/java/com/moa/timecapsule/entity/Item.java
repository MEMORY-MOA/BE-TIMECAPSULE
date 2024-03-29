package com.moa.timecapsule.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Item {
	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private Integer itemId;

	@Column(nullable = false)
	private ItemType itemType;

	@Column(nullable = false)
	private String item;

	@Column(nullable = false)
	private String imgUrl;
}
