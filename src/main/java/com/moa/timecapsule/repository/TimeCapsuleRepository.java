package com.moa.timecapsule.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moa.timecapsule.entity.Timecapsule;

@Repository
public interface TimeCapsuleRepository extends JpaRepository<Timecapsule, String> {
	Timecapsule findTimecapsuleByTimeCapsuleId(UUID timeCapsuleId);

	List<Timecapsule> findTimecapsuleByTimeCapsuleIdInOrTitle(List<UUID> timeCapsuleIdList, String keyword);
}
