package com.moa.timecapsule.repository.implement;

import static com.moa.timecapsule.entity.QTimeCapsuleMember.*;
import static com.moa.timecapsule.entity.QTimecapsule.*;

import java.sql.Time;
import java.util.List;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import com.moa.timecapsule.entity.Timecapsule;
import com.moa.timecapsule.repository.TimeCapsuleQueryRepository;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
@Slf4j
public class TimeCapsuleQueryRepositoryImpl implements TimeCapsuleQueryRepository {

	private final JPAQueryFactory queryFactory;

	@Override
	public Page<Timecapsule> findByMemberId(UUID memberId, Pageable page) {
		JPAQuery<Timecapsule> query = queryFactory.selectFrom(timecapsule)
			.from(timecapsule, timeCapsuleMember)
			.where(timeCapsuleMember.memberId.eq(memberId))
			.where(timecapsule.timeCapsuleId.eq(timeCapsuleMember.timeCapsuleId))
			.offset(page.getOffset())
			.limit(page.getPageSize());

		List<Timecapsule> timecapsuleList = query.fetch();

		JPAQuery<Long> countQuery = queryFactory.select(timecapsule.count())
			.from(timecapsule, timeCapsuleMember)
			.where(timeCapsuleMember.memberId.eq(memberId))
			.where(timecapsule.timeCapsuleId.eq(timeCapsuleMember.timeCapsuleId));

		return PageableExecutionUtils.getPage(timecapsuleList, page, countQuery::fetchOne);
	}

}
