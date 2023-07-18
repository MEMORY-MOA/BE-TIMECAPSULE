package com.moa.timecapsule.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.moa.timecapsule.dto.MemberItemDto;
import com.moa.timecapsule.dto.MemberItemIdsDto;
import com.moa.timecapsule.dto.MemberItemViewDto;
import com.moa.timecapsule.entity.Item;
import com.moa.timecapsule.entity.MemberItem;
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
		MemberItem memberItem = memberItemMapper.dtoToEntity(memberItemDto);
		memberItemRepository.save(memberItem);
	}

	@Override
	public MemberItemIdsDto findMemberItem(MemberItemViewDto memberItemViewDto) {
		List<MemberItem> memberItemList = memberItemRepository.findMemberItemByMemberId(
			memberItemViewDto.getMemberId());
		List<Integer> memberItemIdList = new ArrayList<>();
		for (MemberItem memberItem : memberItemList) {
			Item item = itemRepository.findItemByItemIdAndItemType(memberItem.getItemId(),
				memberItemViewDto.getItemType());
			if (item != null) {
				memberItemIdList.add(item.getItemId());
			}
		}
		return MemberItemIdsDto.builder()
			.itemIdList(memberItemIdList)
			.build();
	}
}
