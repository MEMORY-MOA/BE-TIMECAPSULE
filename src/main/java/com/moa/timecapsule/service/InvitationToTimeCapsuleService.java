package com.moa.timecapsule.service;

import com.moa.timecapsule.dto.InvitationDto;

import java.util.UUID;

public interface InvitationToTimeCapsuleService {
	InvitationDto create(UUID member, UUID timeCapsuleId);

    void accept(UUID member, UUID timeCapsuleId);
}
