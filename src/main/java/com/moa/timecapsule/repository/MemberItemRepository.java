package com.moa.timecapsule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moa.timecapsule.entity.MemberItem;

@Repository
public interface MemberItemRepository extends JpaRepository<MemberItem, Long> {
}

