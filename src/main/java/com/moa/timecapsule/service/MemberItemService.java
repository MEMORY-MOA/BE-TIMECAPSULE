package com.moa.timecapsule.service;

import com.moa.timecapsule.dto.MemberItemDto;
import com.moa.timecapsule.dto.MemberItemListDto;
import com.moa.timecapsule.dto.MemberItemViewDto;

public interface MemberItemService {
	void insertMemberItem(MemberItemDto memberItemDto);

	MemberItemListDto findMemberItem(MemberItemViewDto memberItemViewDto);
}
