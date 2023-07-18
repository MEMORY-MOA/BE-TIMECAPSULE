package com.moa.timecapsule.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.moa.timecapsule.dto.ItemDto;
import com.moa.timecapsule.dto.MemberItemDto;
import com.moa.timecapsule.dto.MemberItemListDto;
import com.moa.timecapsule.dto.MemberItemViewDto;
import com.moa.timecapsule.entity.Item;
import com.moa.timecapsule.entity.MemberItem;
import com.moa.timecapsule.exception.DuplicateException;
import com.moa.timecapsule.exception.NotFoundException;
import com.moa.timecapsule.mapper.MemberItemMapper;
import com.moa.timecapsule.repository.ItemRepository;
import com.moa.timecapsule.repository.MemberItemRepository;
import com.moa.timecapsule.service.MemberItemService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberItemServiceImpl implements MemberItemService {
	private final MemberItemRepository memberItemRepository;
	private final ItemRepository itemRepository;
	private final MemberItemMapper memberItemMapper;

	@Override
	public void insertMemberItem(MemberItemDto memberItemDto) {
		UUID memberId = memberItemDto.getMemberId();
		int itemId = memberItemDto.getItemId();

		existsTimeCapsuleItem(itemId);
		checkMemberIdAndItemIdDuplicate(memberId, itemId);
		MemberItem memberItem = memberItemMapper.dtoToEntity(memberItemDto);
		memberItemRepository.save(memberItem);
	}

	@Override
	public MemberItemListDto findMemberItem(MemberItemViewDto memberItemViewDto) {
		List<MemberItem> memberItemList = memberItemRepository.findMemberItemByMemberId(
			memberItemViewDto.getMemberId());
		List<ItemDto> memberItemDtoList = new ArrayList<>();
		for (MemberItem memberItem : memberItemList) {
			Item item = itemRepository.findItemByItemIdAndItemType(memberItem.getItemId(),
				memberItemViewDto.getItemType());
			if (item != null) {
				ItemDto itemDto = memberItemMapper.toDto(item);
				memberItemDtoList.add(itemDto);
			}
		}
		return MemberItemListDto.builder()
			.itemDtoList(memberItemDtoList)
			.build();
	}

	private void existsTimeCapsuleItem(int itemId) {
		if (!itemRepository.existsItemByItemId(itemId)) {
			throw new NotFoundException("해당 아이템은 존재하지 않습니다.");
		}
	}

	private void checkMemberIdAndItemIdDuplicate(UUID memberId, int itemId) {
		if (memberItemRepository.existsMemberItemByMemberIdAndItemId(memberId, itemId)) {
			throw new DuplicateException("사용자는 이미 해당 아이템을 보유하고 있습니다");
		}
	}
}
