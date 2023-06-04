package com.moa.timecapsule.listener;

import java.util.Enumeration;
import java.util.Hashtable;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebListener
public class WebSessionListener implements HttpSessionListener {

	private static WebSessionListener sessionListener = new WebSessionListener();
	private static final Hashtable loginSessionList = new Hashtable();

	/**
	 * 싱글톤 생성
	 * @return
	 */
	public static synchronized WebSessionListener getInstance() {
		if (sessionListener != null) {
			return sessionListener;
		}
		sessionListener = new WebSessionListener();
		return sessionListener;
	}

	/**
	 * session.setAttribute 실행 되는 순간 같은 sessionId 일경우 1회만 실행
	 * @param httpSessionEvent
	 */
	@Override
	public void sessionCreated(HttpSessionEvent httpSessionEvent) {
		log.info("sessionCreated -> {}", httpSessionEvent.getSession().getAttribute("userId"));
	}

	/**
	 * session 이 소멸되는 시점에 실행, 기본 단위는 초(1분 미만은 설정할 수 없음)
	 * @param httpSessionEvent
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
		log.info("sessionDestroyed 실행");

		//로그아웃 유저 삭제
		synchronized (loginSessionList) {
			loginSessionList.remove(httpSessionEvent.getSession().getId());
		}

		currentSessionList();
	}

	/**
	 * 현제 HashTable에 담겨 있는 유저 리스트, 즉 session list
	 */
	public void currentSessionList() {
		Enumeration elements = loginSessionList.elements();
		HttpSession session;
		while (elements.hasMoreElements()) {
			session = (HttpSession)elements.nextElement();

			String userId = (String)session.getAttribute("userId");
			log.info("currentSessionUserList -> userId {} ", userId);
		}
	}

	/**
	 * session 생성
	 * @param request
	 * @param value
	 */
	public void setSession(HttpServletRequest request, String value) {
		log.info("setSession 실행");
		HttpSession session = request.getSession();
		session.setAttribute("userId", value);
		session.setMaxInactiveInterval(2);

		//HashMap에 Login에 성공한 유저 담기
		synchronized (loginSessionList) {
			loginSessionList.put(session.getId(), session);
		}
		currentSessionList();
	}

	/**
	 * session 삭제
	 * 세션이 remove 되면 destroyed 함수 실행
	 * @param request
	 */
	public void removeSession(HttpServletRequest request) {
		log.info("removeSession 실행");

		HttpSession session = request.getSession();

		session.removeAttribute("userId");
		session.invalidate();
	}

	/**
	 * 현재 로그인한 유저가 이미 존재 하는지 확인
	 * @param request
	 * @param loginUserId
	 * @return boolean
	 */
	public boolean isLoginUser(HttpServletRequest request, String loginUserId) {
		Enumeration elements = loginSessionList.elements();
		HttpSession session;

		while (elements.hasMoreElements()) {
			session = (HttpSession)elements.nextElement();
			String userId = (String)session.getAttribute("userId");
			if (loginUserId.equals(userId) && (!session.getId().equals(request.getSession().getId()))) {
				return true;
			}
		}

		return false;
	}
}
