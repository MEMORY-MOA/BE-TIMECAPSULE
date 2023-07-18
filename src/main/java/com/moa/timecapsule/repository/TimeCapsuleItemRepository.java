package com.moa.timecapsule.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moa.timecapsule.entity.TimeCapsuleItem;

@Repository
public interface TimeCapsuleItemRepository extends JpaRepository<TimeCapsuleItem, Long> {
	List<TimeCapsuleItem> findTimeCapsuleItemByTimeCapsuleId(UUID timeCapsuleId);

	Boolean existsTimeCapsuleItemByTimeCapsuleId(UUID timeCapsuleId);
}
