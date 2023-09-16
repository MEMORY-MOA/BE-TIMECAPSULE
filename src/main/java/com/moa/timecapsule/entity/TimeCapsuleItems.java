package com.moa.timecapsule.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@Entity
@NoArgsConstructor
public class TimeCapsuleItems extends BaseEntity {
	@Id
	@Column(name = "timeCapsuleId", nullable = false)
	private UUID timeCapsuleId;

	@Column(name = "boxShapeItemId", nullable = false)
	private Integer boxShapeItemId;

	@Column(name = "effectItemId", nullable = false)
	private Integer effectItemId;

	@Column(name = "lockShapeItemId", nullable = false)
	private Integer lockShapeItemId;
}
