package com.moa.timecapsule.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTimeCapsuleItems is a Querydsl query type for TimeCapsuleItems
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTimeCapsuleItems extends EntityPathBase<TimeCapsuleItems> {

    private static final long serialVersionUID = 1803091905L;

    public static final QTimeCapsuleItems timeCapsuleItems = new QTimeCapsuleItems("timeCapsuleItems");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final NumberPath<Integer> boxShapeItemId = createNumber("boxShapeItemId", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Integer> effectItemId = createNumber("effectItemId", Integer.class);

    public final NumberPath<Integer> lockShapeItemId = createNumber("lockShapeItemId", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final ComparablePath<java.util.UUID> timeCapsuleId = createComparable("timeCapsuleId", java.util.UUID.class);

    public QTimeCapsuleItems(String variable) {
        super(TimeCapsuleItems.class, forVariable(variable));
    }

    public QTimeCapsuleItems(Path<? extends TimeCapsuleItems> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTimeCapsuleItems(PathMetadata metadata) {
        super(TimeCapsuleItems.class, metadata);
    }

}

