package com.moa.timecapsule.service.implement;

import java.io.IOException;
import java.lang.runtime.ObjectMethods;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.moa.timecapsule.client.MemberFeignClient;
import com.moa.timecapsule.dto.TimeCapsuleFileDto;
import com.moa.timecapsule.dto.TimeCapsuleMemberDto;
import com.moa.timecapsule.dto.TimeCapsuleTextDto;
import com.moa.timecapsule.entity.ContentType;
import com.moa.timecapsule.entity.TimeCapsuleFile;
import com.moa.timecapsule.entity.TimeCapsuleText;
import com.moa.timecapsule.exception.FileUploadException;
import com.moa.timecapsule.exception.NotFoundException;
import com.moa.timecapsule.mapper.TimeCapsuleContentMapper;
import com.moa.timecapsule.repository.TimeCapsuleFileRepository;
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
	private final TimeCapsuleFileRepository timeCapsuleFileRepository;

	private final TimeCapsuleContentMapper timeCapsuleContentMapper;

	private final MemberFeignClient memberFeignClient;

	private final AmazonS3 amazonS3;

	@Value("${cloud.aws.s3.bucket}")
	private String bucket;

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

		TimeCapsuleMemberDto timeCapsuleMemberDto = memberFeignClient.getMemberInfo(timeCapsuleText.getMemberId());

		TimeCapsuleTextDto timeCapsuleTextDto = timeCapsuleContentMapper.toDto(timeCapsuleText);
		timeCapsuleTextDto.setTimeCapsuleMemberDto(timeCapsuleMemberDto);

		return timeCapsuleTextDto;
	}

	@Override
	@Transactional
	public TimeCapsuleFileDto insertTimeCapsuleImage(MultipartFile multipartFile, UUID timecapsuleId, UUID memberId) {
		UUID fileId = UUID.randomUUID();
		String fileUrl = uploadFile(multipartFile, timecapsuleId, fileId);

		TimeCapsuleFile timeCapsuleFile = TimeCapsuleFile.builder()
			.timeCapsuleFileId(fileId)
			.contentType(ContentType.IMAGE)
			.fileUrl(fileUrl)
			.timeCapsuleId(timecapsuleId)
			.memberId(memberId)
			.build();

		TimeCapsuleFile timeCapsuleFileResult = timeCapsuleFileRepository.save(timeCapsuleFile);

		return timeCapsuleContentMapper.toDto(timeCapsuleFileResult);
	}

	@Override
	public List<TimeCapsuleFileDto> selectTimeCapsuleFileList(UUID timecapsuleId) {
		List<TimeCapsuleFile> timeCapsuleFileList = timeCapsuleFileRepository.findByTimeCapsuleId(timecapsuleId);
		return timeCapsuleContentMapper.toDtoList(timeCapsuleFileList);
	}

	@Override
	public TimeCapsuleFileDto selectTimeCapsuleFile(UUID timeCapsuleFileId) {
		TimeCapsuleFile timeCapsuleFile = timeCapsuleFileRepository.findByTimeCapsuleFileId(timeCapsuleFileId)
			.orElseThrow(() -> new NotFoundException("해당 타임캡슐 파일이 없습니다."));

		TimeCapsuleMemberDto timeCapsuleMemberDto = memberFeignClient.getMemberInfo(timeCapsuleFile.getMemberId());

		TimeCapsuleFileDto timeCapsuleFileDto = timeCapsuleContentMapper.toDto(timeCapsuleFile);
		timeCapsuleFileDto.setTimeCapsuleMemberDto(timeCapsuleMemberDto);

		return timeCapsuleFileDto;
	}

	private void checkTimeCapsuleMember(UUID timeCapsuleId, UUID memberId) {
		timeCapsuleMemberRepository.findByTimeCapsuleIdAndMemberId(timeCapsuleId, memberId)
			.orElseThrow(() -> new NotFoundException("타임캡슐 멤버가 아닙니다."));
	}

	private String uploadFile(MultipartFile multipartFile, UUID timecapsuleId, UUID fileId){
		StringBuilder filename = new StringBuilder();
		filename.append(timecapsuleId).append("/").append(fileId).append(multipartFile.getOriginalFilename());

		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(multipartFile.getSize());
		metadata.setContentType(multipartFile.getContentType());

		try {
			amazonS3.putObject(bucket, filename.toString(), multipartFile.getInputStream(), metadata);
		} catch (IOException e) {
			throw new FileUploadException("파일 변환 실패");
		}
		return amazonS3.getUrl(bucket, filename.toString()).toString();
	}
}
