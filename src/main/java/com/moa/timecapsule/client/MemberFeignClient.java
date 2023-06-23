package com.moa.timecapsule.client;

import java.util.List;
import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.moa.timecapsule.dto.TimeCapsuleMemberDto;

@FeignClient(name = "memberFeignClient", url = "http://localhost:8080")
public interface MemberFeignClient {

	@GetMapping(value = "/internal-users/{member-id}")
	TimeCapsuleMemberDto getMemberInfo(@PathVariable("member-id") UUID memberId);

	@GetMapping(value = "/internal-users/find-members")
	List<TimeCapsuleMemberDto> getMembersInfo(@RequestParam("member-id") List<UUID> friendsIdList);
}