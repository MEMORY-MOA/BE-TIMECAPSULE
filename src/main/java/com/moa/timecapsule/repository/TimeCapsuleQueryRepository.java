package com.moa.timecapsule.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.moa.timecapsule.entity.Timecapsule;

@Repository
public interface TimeCapsuleQueryRepository {
	List<Timecapsule> findByMemberId(UUID memberId, Pageable page);
}
