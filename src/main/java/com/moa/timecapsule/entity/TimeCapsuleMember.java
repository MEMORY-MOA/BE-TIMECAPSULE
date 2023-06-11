package com.moa.timecapsule.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@Entity
@SuperBuilder
@NoArgsConstructor
public class TimeCapsuleMember extends BaseEntity {
	@Id
	@GeneratedValue
	private Long timeCapsuleMemberId;

	@Column(nullable = false)
	private UUID timeCapsuleId;

	@Column(nullable = false)
	private UUID memberId;
}
