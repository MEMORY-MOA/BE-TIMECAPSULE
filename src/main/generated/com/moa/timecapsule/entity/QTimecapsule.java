package com.moa.timecapsule.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTimecapsule is a Querydsl query type for Timecapsule
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTimecapsule extends EntityPathBase<Timecapsule> {

    private static final long serialVersionUID = 171642719L;

    public static final QTimecapsule timecapsule = new QTimecapsule("timecapsule");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final DatePath<java.time.LocalDate> closedAt = createDate("closedAt", java.time.LocalDate.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final ComparablePath<java.util.UUID> creator = createComparable("creator", java.util.UUID.class);

    public final BooleanPath isOpened = createBoolean("isOpened");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final DatePath<java.time.LocalDate> openedAt = createDate("openedAt", java.time.LocalDate.class);

    public final ComparablePath<java.util.UUID> timeCapsuleId = createComparable("timeCapsuleId", java.util.UUID.class);

    public final StringPath title = createString("title");

    public QTimecapsule(String variable) {
        super(Timecapsule.class, forVariable(variable));
    }

    public QTimecapsule(Path<? extends Timecapsule> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTimecapsule(PathMetadata metadata) {
        super(Timecapsule.class, metadata);
    }

}

