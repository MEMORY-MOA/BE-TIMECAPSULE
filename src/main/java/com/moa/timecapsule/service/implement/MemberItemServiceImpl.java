package com.moa.timecapsule.service.implement;

import org.springframework.stereotype.Service;

import com.moa.timecapsule.dto.MemberItemDto;
import com.moa.timecapsule.entity.MemberItem;
import com.moa.timecapsule.mapper.MemberItemMapper;
import com.moa.timecapsule.repository.MemberItemRepository;
import com.moa.timecapsule.service.MemberItemService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberItemServiceImpl implements MemberItemService {
	private final MemberItemRepository memberItemRepository;
	private final MemberItemMapper memberItemMapper;

	@Override
	public void insertMemberItem(MemberItemDto memberItemDto) {
		MemberItem memberItem = memberItemMapper.dtoToEntity(memberItemDto);
		memberItemRepository.save(memberItem);
	}
}
