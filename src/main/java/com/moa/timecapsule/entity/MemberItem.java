package com.moa.timecapsule.entity;

import java.util.UUID;

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
public class MemberItem extends BaseEntity {
	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private UUID memberId;

	@Column(nullable = false)
	private Integer itemId;
}

