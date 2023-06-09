package com.moa.timecapsule.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moa.timecapsule.entity.TimeCapsuleText;

@Repository
public interface TimeCapsuleTextRepository extends JpaRepository<TimeCapsuleText, UUID> {
	List<TimeCapsuleText> findByTimeCapsuleId(UUID timeCapsuleId);

	Optional<TimeCapsuleText> findByTimeCapsuleTextId(UUID timeCapsuleTextId);
}
