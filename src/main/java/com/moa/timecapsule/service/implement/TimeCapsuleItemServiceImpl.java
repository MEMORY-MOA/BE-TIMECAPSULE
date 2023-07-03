package com.moa.timecapsule.service.implement;

import org.springframework.stereotype.Service;

import com.moa.timecapsule.dto.TimeCapsuleItemDto;
import com.moa.timecapsule.entity.TimeCapsuleItem;
import com.moa.timecapsule.mapper.TimeCapsuleItemMapper;
import com.moa.timecapsule.repository.TimeCapsuleItemRepository;
import com.moa.timecapsule.service.TimeCapsuleItemService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TimeCapsuleItemServiceImpl implements TimeCapsuleItemService {
	private final TimeCapsuleItemRepository timeCapsuleItemRepository;
	private final TimeCapsuleItemMapper timeCapsuleItemMapper;

	@Override
	public void insertTimeCapsuleItem(TimeCapsuleItemDto timeCapsuleItemDto) {
		TimeCapsuleItem timeCapsuleItem = timeCapsuleItemMapper.dtoToEntity(timeCapsuleItemDto);
		timeCapsuleItemRepository.save(timeCapsuleItem);
	}
}
