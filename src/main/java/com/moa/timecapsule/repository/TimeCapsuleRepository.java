package com.moa.timecapsule.repository;

import com.moa.timecapsule.entity.Timecapsule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeCapsuleRepository extends JpaRepository<Timecapsule, String> {
}
