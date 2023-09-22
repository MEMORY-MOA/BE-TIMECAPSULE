package com.moa.timecapsule.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTimeCapsuleMember is a Querydsl query type for TimeCapsuleMember
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTimeCapsuleMember extends EntityPathBase<TimeCapsuleMember> {

    private static final long serialVersionUID = 162165433L;

    public static final QTimeCapsuleMember timeCapsuleMember = new QTimeCapsuleMember("timeCapsuleMember");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final ComparablePath<java.util.UUID> memberId = createComparable("memberId", java.util.UUID.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final ComparablePath<java.util.UUID> timeCapsuleId = createComparable("timeCapsuleId", java.util.UUID.class);

    public final NumberPath<Long> timeCapsuleMemberId = createNumber("timeCapsuleMemberId", Long.class);

    public QTimeCapsuleMember(String variable) {
        super(TimeCapsuleMember.class, forVariable(variable));
    }

    public QTimeCapsuleMember(Path<? extends TimeCapsuleMember> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTimeCapsuleMember(PathMetadata metadata) {
        super(TimeCapsuleMember.class, metadata);
    }

}

