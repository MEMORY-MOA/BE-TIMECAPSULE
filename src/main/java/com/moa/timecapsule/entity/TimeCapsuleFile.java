package com.moa.timecapsule.entity;

import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class TimeCapsuleFile extends BaseEntity {

	@Id
	@Column(columnDefinition = "BINARY(16)")
	private UUID timeCapsuleFileId;

	@Column(nullable = false, columnDefinition = "BINARY(16)")
	private UUID timeCapsuleId;

	@Column(nullable = false, columnDefinition = "BINARY(16)")
	private UUID memberId;

	@Column(nullable = false)
	private ContentType contentType;

	@Column(nullable = false)
	private String fileUrl;
}
