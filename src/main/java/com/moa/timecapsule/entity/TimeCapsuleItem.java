package com.moa.timecapsule.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(
	name = "TimeCapsuleItem",
	uniqueConstraints = {
		@UniqueConstraint(
			name = "contstraintTimeCapsuleItem",
			columnNames = {"timeCapsuleId", "itemId"}
		)
	}
)
public class TimeCapsuleItem extends BaseEntity {
	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "timeCapsuleId", nullable = false)
	private UUID timeCapsuleId;

	@Column(name = "itemId", nullable = false)
	private int itemId;
}
