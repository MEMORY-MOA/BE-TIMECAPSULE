package com.moa.timecapsule.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moa.timecapsule.entity.TimeCapsuleMember;

@Repository
public interface TimeCapsuleMemberRepository extends JpaRepository<TimeCapsuleMember, Long> {
	Optional<TimeCapsuleMember> findByTimeCapsuleIdAndMemberId(UUID timeCapsuleId, UUID memberId);

	List<TimeCapsuleMember> findByTimeCapsuleId(UUID timeCapsuleId);

	List<TimeCapsuleMember> findByMemberId(UUID memberId);

	List<TimeCapsuleMember> findByTimeCapsuleIdAndMemberIdIn(UUID timeCapsuleId, List<UUID> friendIds);
}
