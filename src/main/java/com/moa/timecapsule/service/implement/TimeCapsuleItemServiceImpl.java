package com.moa.timecapsule.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.moa.timecapsule.dto.ItemDto;
import com.moa.timecapsule.dto.TimeCapsuleItemListDto;
import com.moa.timecapsule.dto.TimeCapsuleItemRegisterDto;
import com.moa.timecapsule.dto.TimeCapsuleItemViewDto;
import com.moa.timecapsule.entity.Item;
import com.moa.timecapsule.entity.TimeCapsuleItems;
import com.moa.timecapsule.exception.NotFoundException;
import com.moa.timecapsule.mapper.TimeCapsuleItemMapper;
import com.moa.timecapsule.repository.ItemRepository;
import com.moa.timecapsule.repository.MemberItemRepository;
import com.moa.timecapsule.repository.TimeCapsuleItemRepository;
import com.moa.timecapsule.repository.TimeCapsuleMemberRepository;
import com.moa.timecapsule.service.TimeCapsuleItemService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TimeCapsuleItemServiceImpl implements TimeCapsuleItemService {
	private final TimeCapsuleItemRepository timeCapsuleItemRepository;
	private final TimeCapsuleMemberRepository timeCapsuleMemberRepository;
	private final ItemRepository itemRepository;
	private final MemberItemRepository memberItemRepository;
	private final TimeCapsuleItemMapper timeCapsuleItemMapper;

	@Override
	public void insertTimeCapsuleItem(TimeCapsuleItemRegisterDto timeCapsuleItemRegisterDto) {
		UUID memberId = timeCapsuleItemRegisterDto.getMemberId();
		UUID timeCapsuleId = timeCapsuleItemRegisterDto.getTimeCapsuleId();

		checkTimeCapsuleMember(timeCapsuleId, memberId);
		TimeCapsuleItems timeCapsuleItems = timeCapsuleItemRepository.findTimeCapsuleItemsByTimeCapsuleId(
			timeCapsuleId);

		existsMemberTimeCapsuleItem(memberId, timeCapsuleItemRegisterDto.getBoxShapeItemId());
		existsMemberTimeCapsuleItem(memberId, timeCapsuleItemRegisterDto.getEffectItemId());
		existsMemberTimeCapsuleItem(memberId, timeCapsuleItemRegisterDto.getLockShapeItemId());

		if (timeCapsuleItems != null) {
			timeCapsuleItems = timeCapsuleItemMapper.updateMemberEntityFromTimeCapsuleItemRegisterDto(timeCapsuleItems,
				timeCapsuleItemRegisterDto);
		}
		if (timeCapsuleItems == null) {
			timeCapsuleItems = timeCapsuleItemMapper.toEntity(timeCapsuleId, timeCapsuleItemRegisterDto);
		}
		timeCapsuleItemRepository.save(timeCapsuleItems);
	}

	public TimeCapsuleItemListDto findTimeCapsuleItem(TimeCapsuleItemViewDto timeCapsuleItemViewDto) {
		UUID memberId = timeCapsuleItemViewDto.getMemberId();
		UUID timeCapsuleId = timeCapsuleItemViewDto.getTimeCapsuleId();
		checkTimeCapsuleMember(timeCapsuleId, memberId);

		TimeCapsuleItems timeCapsuleItems = timeCapsuleItemRepository.findTimeCapsuleItemsByTimeCapsuleId(
			timeCapsuleId);

		if (timeCapsuleItems == null) {
			throw new NotFoundException("타임캡슐 아이템이 존재하지 않습니다.");
		}
		List<Integer> timeCapsuleItemIdList = new ArrayList<>();
		timeCapsuleItemIdList.add(timeCapsuleItems.getBoxShapeItemId());
		timeCapsuleItemIdList.add(timeCapsuleItems.getEffectItemId());
		timeCapsuleItemIdList.add(timeCapsuleItems.getLockShapeItemId());

		List<ItemDto> timeCapsuleItemDtoList = new ArrayList<>();
		for (Integer itemId : timeCapsuleItemIdList) {
			Item item = itemRepository.findItemByItemId(itemId);
			if (item != null) {
				ItemDto itemDto = timeCapsuleItemMapper.toDto(item);
				timeCapsuleItemDtoList.add(itemDto);
			}
		}

		return TimeCapsuleItemListDto.builder()
			.itemDtoList(timeCapsuleItemDtoList)
			.build();
	}

	private void checkTimeCapsuleMember(UUID timeCapsuleId, UUID memberId) {
		timeCapsuleMemberRepository.findByTimeCapsuleIdAndMemberId(timeCapsuleId, memberId)
			.orElseThrow(() -> new NotFoundException("타임캡슐 멤버가 아닙니다."));
	}

	private void existsMemberTimeCapsuleItem(UUID memberId, Integer itemId) {
		if (itemId != null && !memberItemRepository.existsMemberItemByMemberIdAndItemId(memberId, itemId)) {
			throw new NotFoundException("해당 아이템은 사용자가 보유하고 있지 않습니다.");
		}
	}
}
