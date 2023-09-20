package com.moa.timecapsule.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMemberItem is a Querydsl query type for MemberItem
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberItem extends EntityPathBase<MemberItem> {

    private static final long serialVersionUID = 1032531022L;

    public static final QMemberItem memberItem = new QMemberItem("memberItem");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> itemId = createNumber("itemId", Integer.class);

    public final ComparablePath<java.util.UUID> memberId = createComparable("memberId", java.util.UUID.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public QMemberItem(String variable) {
        super(MemberItem.class, forVariable(variable));
    }

    public QMemberItem(Path<? extends MemberItem> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberItem(PathMetadata metadata) {
        super(MemberItem.class, metadata);
    }

}

