package com.moa.timecapsule.mapper;

import com.moa.timecapsule.controller.response.InvitationLinkResponse;
import com.moa.timecapsule.dto.InvitationDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvitationToTimeCapsuleDtoMapper {
	InvitationLinkResponse toInvitationLinkResponse(InvitationDto invitationDto);
}
