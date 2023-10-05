package com.moa.timecapsule.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTimeCapsuleText is a Querydsl query type for TimeCapsuleText
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTimeCapsuleText extends EntityPathBase<TimeCapsuleText> {

    private static final long serialVersionUID = -1326995188L;

    public static final QTimeCapsuleText timeCapsuleText = new QTimeCapsuleText("timeCapsuleText");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath color = createString("color");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final ComparablePath<java.util.UUID> memberId = createComparable("memberId", java.util.UUID.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath text = createString("text");

    public final ComparablePath<java.util.UUID> timeCapsuleId = createComparable("timeCapsuleId", java.util.UUID.class);

    public final ComparablePath<java.util.UUID> timeCapsuleTextId = createComparable("timeCapsuleTextId", java.util.UUID.class);

    public QTimeCapsuleText(String variable) {
        super(TimeCapsuleText.class, forVariable(variable));
    }

    public QTimeCapsuleText(Path<? extends TimeCapsuleText> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTimeCapsuleText(PathMetadata metadata) {
        super(TimeCapsuleText.class, metadata);
    }

}

