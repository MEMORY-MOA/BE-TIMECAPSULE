package com.moa.timecapsule.service;

import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.moa.timecapsule.dto.TimeCapsuleFileDto;
import com.moa.timecapsule.dto.TimeCapsuleTextDto;

public interface TimeCapsuleContentService {
	List<TimeCapsuleTextDto> selectTimeCapsuleTextList(UUID timeCapsuleId);

	TimeCapsuleTextDto insertTimeCapsuleText(TimeCapsuleTextDto timeCapsuleTextDto);

	TimeCapsuleTextDto selectTimeCapsuleText(UUID timeCapsuleTextId);

	TimeCapsuleFileDto insertTimeCapsuleImage(MultipartFile multipartFile, UUID timecapsuleId, UUID memberId);

	List<TimeCapsuleFileDto> selectTimeCapsuleFileList(UUID timecapsuleId);

	TimeCapsuleFileDto selectTimeCapsuleFile(UUID timeCapsuleFileId);

	void checkTimeCapsuleMember(UUID timeCapsuleId, UUID memberId);
}
