package com.moa.timecapsule.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class Timecapsule {
    @Id
    @Column(length = 36)
    private String UUID;

    private String title;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "closed_at")
    private LocalDate closedAt;

    @Column(name = "opened_at")
    private LocalDate openedAt;

    @Column(name = "is_opened")
    private boolean isOpened;

    private String member;
}
