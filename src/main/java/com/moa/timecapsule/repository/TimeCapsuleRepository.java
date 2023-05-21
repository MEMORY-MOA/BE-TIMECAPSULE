package com.moa.timecapsule.repository;

import java.util.UUID;

import com.moa.timecapsule.entity.Timecapsule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeCapsuleRepository extends JpaRepository<Timecapsule, String> {
	Timecapsule findTimecapsuleByTimeCapsuleId(UUID timeCapsuleId);
}
