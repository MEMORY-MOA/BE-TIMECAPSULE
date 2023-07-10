package com.moa.timecapsule.client;

import java.util.List;
import java.util.UUID;

import com.moa.timecapsule.dto.FriendDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.moa.timecapsule.dto.FeignFriendIdListDto;
import com.moa.timecapsule.dto.TimeCapsuleMemberDto;

@FeignClient(name = "memberFeignClient", url = "http://localhost:8080")
public interface MemberFeignClient {

	@GetMapping(value = "/internal-members/{member-id}")
	TimeCapsuleMemberDto getMemberInfo(@PathVariable("member-id") UUID memberId);

	@GetMapping(value = "/internal-members/find-members")
	List<TimeCapsuleMemberDto> getMembersInfo(@RequestParam("member-id") List<UUID> friendsIdList);

	@GetMapping(value = "/internal-users/search")
	FeignFriendIdListDto searchFriendsIdByNickname(@RequestHeader("memberId") UUID memberId,
		@RequestParam("keyword") String keyword);

	@GetMapping(value = "/internal-friends" +
		"/acceptance")
	void acceptFriend(FriendDto friendDto);
}
