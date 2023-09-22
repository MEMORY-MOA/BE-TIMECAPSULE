package com.moa.timecapsule.entity;

import java.sql.Types;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@Entity
@SuperBuilder
@NoArgsConstructor
public class TimeCapsuleText extends BaseEntity {

	@Id
	@GeneratedValue(generator = "uuid2", strategy = GenerationType.IDENTITY)
	@UuidGenerator
	@Column(columnDefinition = "BINARY(16)")
	private UUID timeCapsuleTextId;

	@Column(nullable = false, columnDefinition = "BINARY(16)")
	private UUID timeCapsuleId;

	@Column(nullable = false, columnDefinition = "BINARY(16)")
	private UUID memberId;

	@Column(nullable = false)
	private String text;

	private String color;
}
