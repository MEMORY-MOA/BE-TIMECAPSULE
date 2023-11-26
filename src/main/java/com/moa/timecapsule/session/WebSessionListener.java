package com.moa.timecapsule.session;

import com.moa.timecapsule.util.RedisUtil;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Hashtable;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSessionListener implements HttpSessionListener {

	class UserToken {

	}
	private final RedisUtil redisUtil;

	/**
	 * session.setAttribute 실행 되는 순간 같은 sessionId 일경우 1회만 실행
	 * @param httpSessionEvent
	 */
	@Override
	public void sessionCreated(HttpSessionEvent httpSessionEvent) {
		log.info("sessionCreated -> {}", httpSessionEvent.getSession().getAttribute("linkTokenId"));
	}

	/**
	 * session 이 소멸되는 시점에 실행, 기본 단위는 초(1분 미만은 설정할 수 없음)
	 * @param httpSessionEvent
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
		HttpSession session = httpSessionEvent.getSession();

		UUID user = (UUID) session.getAttribute("linkTokenId");
		log.info("sessionDestroyed 실행 : " + user);
	}

	/**
	 * 현제 HashTable에 담겨 있는 유저 리스트, 즉 session list
	 */
	public Long currentSessionList(UUID timeCapsuleId){
		return redisUtil.getSize("TC"+timeCapsuleId);
	}

	public void increaseSessionList(UUID timeCapsuleId, UUID memberId, UUID key) {
		String tcTimeCapsuleId = "TC" + timeCapsuleId;
		String tcKey = "TCK" + key;

		redisUtil.set(tcTimeCapsuleId, tcKey);
		redisUtil.setDataExpire(tcKey, String.valueOf(memberId), 5 * 60);

		log.info("link "+ redisUtil.getSize(tcTimeCapsuleId) + " 명이 접속하고 있습니다.");
	}

	public void decreaseSessionList(UUID timeCapsuleId, UUID key) {
		String tcTimeCapsuleId = "TC" + timeCapsuleId;
		String tcKey = "TCK" + key;

		redisUtil.delete(tcTimeCapsuleId, tcKey);
		log.info("unlink "+ redisUtil.getSize(tcTimeCapsuleId) + " 명이 접속하고 있습니다.");
	}
}
