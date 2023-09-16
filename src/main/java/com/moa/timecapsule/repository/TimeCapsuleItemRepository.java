package com.moa.timecapsule.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moa.timecapsule.entity.TimeCapsuleItems;

@Repository
public interface TimeCapsuleItemRepository extends JpaRepository<TimeCapsuleItems, Long> {
	TimeCapsuleItems findTimeCapsuleItemsByTimeCapsuleId(UUID timeCapsuleId);
}
