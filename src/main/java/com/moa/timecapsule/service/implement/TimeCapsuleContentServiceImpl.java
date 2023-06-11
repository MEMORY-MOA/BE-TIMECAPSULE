package com.moa.timecapsule.service.implement;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moa.timecapsule.dto.TimeCapsuleTextDto;
import com.moa.timecapsule.entity.TimeCapsuleText;
import com.moa.timecapsule.exception.NotFoundException;
import com.moa.timecapsule.mapper.TimeCapsuleContentMapper;
import com.moa.timecapsule.repository.TimeCapsuleMemberRepository;
import com.moa.timecapsule.repository.TimeCapsuleTextRepository;
import com.moa.timecapsule.service.TimeCapsuleContentService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TimeCapsuleContentServiceImpl implements TimeCapsuleContentService {
	private final TimeCapsuleTextRepository timeCapsuleTextRepository;
	private final TimeCapsuleMemberRepository timeCapsuleMemberRepository;

	private final TimeCapsuleContentMapper timeCapsuleContentMapper;

	@Override
	public List<TimeCapsuleTextDto> selectTimeCapsuleTextList(UUID timeCapsuleId) {
		List<TimeCapsuleText> timeCapsuleTextList = timeCapsuleTextRepository.findByTimeCapsuleId(timeCapsuleId);

		return timeCapsuleContentMapper.toDto(timeCapsuleTextList);
	}

	@Override
	@Transactional
	public TimeCapsuleTextDto insertTimeCapsuleText(TimeCapsuleTextDto timeCapsuleTextDto) {
		checkTimeCapsuleMember(timeCapsuleTextDto.getTimeCapsuleId(), timeCapsuleTextDto.getMemberId());

		TimeCapsuleText timeCapsuleText = timeCapsuleTextRepository.save(
			timeCapsuleContentMapper.toEntity(timeCapsuleTextDto));

		return timeCapsuleContentMapper.toDto(timeCapsuleText);
	}

	@Override
	public TimeCapsuleTextDto selectTimeCapsuleText(UUID timeCapsuleTextId) {
		TimeCapsuleText timeCapsuleText = timeCapsuleTextRepository.findByTimeCapsuleTextId(timeCapsuleTextId)
			.orElseThrow(() -> new NotFoundException("해당 타임캡슐 텍스트가 없습니다."));

		return timeCapsuleContentMapper.toDto(timeCapsuleText);
	}

	private void checkTimeCapsuleMember(UUID timeCapsuleId, UUID memberId) {
		timeCapsuleMemberRepository.findByTimeCapsuleIdAndMemberId(timeCapsuleId, memberId)
			.orElseThrow(() -> new NotFoundException("타임캡슐 멤버가 아닙니다."));
	}
}
