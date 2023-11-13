package com.moa.timecapsule.entity;

import java.sql.Types;
import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

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
public class Timecapsule extends BaseEntity {
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(columnDefinition = "BINARY(16)")
	private UUID timeCapsuleId;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private LocalDate closedAt;

	@Column(nullable = false)
	private LocalDate openedAt;

	@ColumnDefault("false")
	private boolean isOpened;

	@Column(nullable = false, columnDefinition = "BINARY(16)")
	private UUID creator;

	private int shape;
}
