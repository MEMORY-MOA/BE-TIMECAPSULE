package com.moa.timecapsule.service.implement;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.moa.timecapsule.dto.TimeCapsuleItemDto;
import com.moa.timecapsule.dto.TimeCapsuleItemIdTypeDto;
import com.moa.timecapsule.entity.ItemType;
import com.moa.timecapsule.entity.TimeCapsuleItem;
import com.moa.timecapsule.exception.NotFoundException;
import com.moa.timecapsule.mapper.TimeCapsuleItemMapper;
import com.moa.timecapsule.repository.ItemRepository;
import com.moa.timecapsule.repository.MemberItemRepository;
import com.moa.timecapsule.repository.TimeCapsuleItemRepository;
import com.moa.timecapsule.repository.TimeCapsuleMemberRepository;
import com.moa.timecapsule.service.TimeCapsuleItemService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TimeCapsuleItemServiceImpl implements TimeCapsuleItemService {
	private final TimeCapsuleItemRepository timeCapsuleItemRepository;
	private final TimeCapsuleMemberRepository timeCapsuleMemberRepository;
	private final ItemRepository itemRepository;
	private final MemberItemRepository memberItemRepository;
	private final TimeCapsuleItemMapper timeCapsuleItemMapper;

	@Override
	public void insertTimeCapsuleItem(TimeCapsuleItemDto timeCapsuleItemDto) {
		UUID memberId = timeCapsuleItemDto.getMemberId();
		UUID timeCapsuleId = timeCapsuleItemDto.getTimeCapsuleId();
		checkTimeCapsuleMember(timeCapsuleId, memberId);

		for (TimeCapsuleItemIdTypeDto timeCapsuleItemIdTypeDto : timeCapsuleItemDto.getTimeCapsuleItemIdTypeDtoList()) {
			ItemType itemType = timeCapsuleItemIdTypeDto.getItemType();
			int itemId = timeCapsuleItemIdTypeDto.getItemId();
			existsMemberTimeCapsuleItem(memberId, itemId);
			existsTimeCapsuleItem(itemId, itemType);
			TimeCapsuleItem timeCapsuleItem = timeCapsuleItemMapper.toEntity(timeCapsuleId, itemId);

			timeCapsuleItemRepository.save(timeCapsuleItem);
		}
	}

	private void checkTimeCapsuleMember(UUID timeCapsuleId, UUID memberId) {
		timeCapsuleMemberRepository.findByTimeCapsuleIdAndMemberId(timeCapsuleId, memberId)
			.orElseThrow(() -> new NotFoundException("타임캡슐 멤버가 아닙니다."));
	}

	private void existsMemberTimeCapsuleItem(UUID memberId, int itemId) {
		if (!memberItemRepository.existsMemberItemByItemIdAndMemberId(itemId, memberId)) {
			throw new NotFoundException("해당 아이템은 사용자가 보유하고 있지 않습니다.");
		}
	}

	private void existsTimeCapsuleItem(int itemId, ItemType itemType) {
		if (!itemRepository.existsItemByItemIdAndItemType(itemId, itemType)) {
			throw new NotFoundException("해당 아이템은 존재하지 않습니다.");
		}
	}
}
