package com.moa.timecapsule.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.moa.timecapsule.dto.ItemDto;
import com.moa.timecapsule.dto.TimeCapsuleItemIdTypeDto;
import com.moa.timecapsule.dto.TimeCapsuleItemListDto;
import com.moa.timecapsule.dto.TimeCapsuleItemRegisterDto;
import com.moa.timecapsule.dto.TimeCapsuleItemViewDto;
import com.moa.timecapsule.entity.Item;
import com.moa.timecapsule.entity.ItemType;
import com.moa.timecapsule.entity.TimeCapsuleItem;
import com.moa.timecapsule.exception.DuplicateException;
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
		existsTimeCapsuleItem(timeCapsuleId);
		for (TimeCapsuleItemIdTypeDto timeCapsuleItemIdTypeDto : timeCapsuleItemRegisterDto.getTimeCapsuleItemIdTypeDtoList()) {
			ItemType itemType = timeCapsuleItemIdTypeDto.getItemType();
			int itemId = timeCapsuleItemIdTypeDto.getItemId();
			existsMemberTimeCapsuleItem(memberId, itemId);
			existsItem(itemId, itemType);
		}
		for (TimeCapsuleItemIdTypeDto timeCapsuleItemIdTypeDto : timeCapsuleItemRegisterDto.getTimeCapsuleItemIdTypeDtoList()) {
			ItemType itemType = timeCapsuleItemIdTypeDto.getItemType();
			int itemId = timeCapsuleItemIdTypeDto.getItemId();
			TimeCapsuleItem timeCapsuleItem = timeCapsuleItemMapper.toEntity(timeCapsuleId, itemId);

			timeCapsuleItemRepository.save(timeCapsuleItem);
		}
	}

	public TimeCapsuleItemListDto findTimeCapsuleItem(TimeCapsuleItemViewDto timeCapsuleItemViewDto) {
		UUID memberId = timeCapsuleItemViewDto.getMemberId();
		UUID timeCapsuleId = timeCapsuleItemViewDto.getTimeCapsuleId();
		checkTimeCapsuleMember(timeCapsuleId, memberId);

		List<TimeCapsuleItem> timeCapsuleItemList = timeCapsuleItemRepository.findTimeCapsuleItemByTimeCapsuleId(
			timeCapsuleId);
		List<ItemDto> timeCapsuleItemDtoList = new ArrayList<>();
		for (TimeCapsuleItem timeCapsuleItem : timeCapsuleItemList) {
			int itemId = timeCapsuleItem.getItemId();
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

	private void existsMemberTimeCapsuleItem(UUID memberId, int itemId) {
		if (!memberItemRepository.existsMemberItemByItemIdAndMemberId(itemId, memberId)) {
			throw new NotFoundException("해당 아이템은 사용자가 보유하고 있지 않습니다.");
		}
	}

	private void existsItem(int itemId, ItemType itemType) {
		if (!itemRepository.existsItemByItemIdAndItemType(itemId, itemType)) {
			throw new NotFoundException("해당 아이템은 존재하지 않습니다.");
		}
	}

	private void existsTimeCapsuleItem(UUID timeCapsuleId) {
		if (timeCapsuleItemRepository.existsTimeCapsuleItemByTimeCapsuleId(timeCapsuleId)) {
			throw new DuplicateException("이미 해당 타임캡슐의 아이템은 생성되어 있습니다.");
		}
	}
}
