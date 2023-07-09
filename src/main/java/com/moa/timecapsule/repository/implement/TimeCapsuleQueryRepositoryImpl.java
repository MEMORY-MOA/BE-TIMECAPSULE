package com.moa.timecapsule.repository.implement;

import static com.moa.timecapsule.entity.QTimeCapsuleMember.*;
import static com.moa.timecapsule.entity.QTimecapsule.*;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.moa.timecapsule.entity.Timecapsule;
import com.moa.timecapsule.repository.TimeCapsuleQueryRepository;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TimeCapsuleQueryRepositoryImpl implements TimeCapsuleQueryRepository {

	private final JPAQueryFactory queryFactory;

	@Override
	public List<Timecapsule> findByMemberId(UUID memberId, Pageable page) {
		JPAQuery<Timecapsule> query = queryFactory.selectFrom(timecapsule)
			.from(timecapsule, timeCapsuleMember)
			.where(timeCapsuleMember.memberId.eq(memberId))
			.where(timecapsule.timeCapsuleId.eq(timeCapsuleMember.timeCapsuleId))
			.offset(page.getOffset())
			.limit(page.getPageSize());

		return query.fetch();
	}

}
