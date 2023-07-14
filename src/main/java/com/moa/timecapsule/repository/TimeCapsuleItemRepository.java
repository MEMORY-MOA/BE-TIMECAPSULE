package com.moa.timecapsule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moa.timecapsule.entity.TimeCapsuleItem;

@Repository
public interface TimeCapsuleItemRepository extends JpaRepository<TimeCapsuleItem, Long> {
}
