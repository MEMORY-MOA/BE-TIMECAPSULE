package com.moa.timecapsule.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moa.timecapsule.entity.Timecapsule;

@Repository
public interface TimeCapsuleRepository extends JpaRepository<Timecapsule, String> {
	Page<Timecapsule> findTimecapsuleByTimeCapsuleIdInOrTitle(List<UUID> timeCapsuleIdList, String keyword,
		Pageable pageable);

	Optional<Timecapsule> findTimecapsuleByTimeCapsuleId(UUID timeCapsuleId);
}
