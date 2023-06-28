package com.moa.timecapsule.entity;

import java.sql.Types;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;

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
public class TimeCapsuleMember extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long timeCapsuleMemberId;

	@Column(nullable = false, columnDefinition = "BINARY(16)")
	private UUID timeCapsuleId;

	@Column(nullable = false, columnDefinition = "BINARY(16)")
	private UUID memberId;
}
