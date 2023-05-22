package com.moa.timecapsule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moa.timecapsule.entity.Timecapsule;

@Repository
public interface TimeCapsuleRepository extends JpaRepository<Timecapsule, String> {
}
