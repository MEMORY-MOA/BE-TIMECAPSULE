package com.moa.timecapsule.session;

import com.moa.timecapsule.util.Redis2Util;
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

	private final Hashtable<UUID, UUID> userList = new Hashtable<>(); // key, memberId

	private final Redis2Util redis2Util;

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
		return redis2Util.getSize(String.valueOf(timeCapsuleId));
	}

	public void increaseSessionList(UUID timeCapsuleId, UUID memberId, UUID key) {
		if (!userList.contains(memberId)) {
			userList.put(key, memberId);
			redis2Util.set(String.valueOf(timeCapsuleId), String.valueOf(key));
		}
		log.info("link "+ redis2Util.getSize(String.valueOf(timeCapsuleId)) + " 명이 접속하고 있습니다.");
	}

	public void decreaseSessionList(UUID timeCapsuleId, UUID key) {
		userList.remove(key);
		redis2Util.delete(String.valueOf(timeCapsuleId), String.valueOf(key));
		log.info("unlink "+ redis2Util.getSize(String.valueOf(timeCapsuleId)) + " 명이 접속하고 있습니다.");
	}
}
