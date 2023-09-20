package com.moa.timecapsule.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moa.timecapsule.entity.TimeCapsuleFile;
import com.moa.timecapsule.entity.TimeCapsuleText;

@Repository
public interface TimeCapsuleFileRepository extends JpaRepository<TimeCapsuleFile, UUID> {
	List<TimeCapsuleFile> findByTimeCapsuleId(UUID timeCapsuleId);

	Optional<TimeCapsuleFile> findByTimeCapsuleFileId(UUID timeCapsuleTextId);
}
