package com.moa.timecapsule.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moa.timecapsule.entity.Timecapsule;

@Repository
public interface TimeCapsuleRepository extends JpaRepository<Timecapsule, String> {
	Optional<Timecapsule> findTimecapsuleByTimeCapsuleId(UUID timeCapsuleId);
}
