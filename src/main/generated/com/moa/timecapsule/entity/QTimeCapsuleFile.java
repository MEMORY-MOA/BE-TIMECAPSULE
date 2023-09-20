package com.moa.timecapsule.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTimeCapsuleFile is a Querydsl query type for TimeCapsuleFile
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTimeCapsuleFile extends EntityPathBase<TimeCapsuleFile> {

    private static final long serialVersionUID = -1327408805L;

    public static final QTimeCapsuleFile timeCapsuleFile = new QTimeCapsuleFile("timeCapsuleFile");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final EnumPath<ContentType> contentType = createEnum("contentType", ContentType.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath fileUrl = createString("fileUrl");

    public final ComparablePath<java.util.UUID> memberId = createComparable("memberId", java.util.UUID.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final ComparablePath<java.util.UUID> timeCapsuleFileId = createComparable("timeCapsuleFileId", java.util.UUID.class);

    public final ComparablePath<java.util.UUID> timeCapsuleId = createComparable("timeCapsuleId", java.util.UUID.class);

    public QTimeCapsuleFile(String variable) {
        super(TimeCapsuleFile.class, forVariable(variable));
    }

    public QTimeCapsuleFile(Path<? extends TimeCapsuleFile> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTimeCapsuleFile(PathMetadata metadata) {
        super(TimeCapsuleFile.class, metadata);
    }

}

