package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QSubConcept is a Querydsl query type for SubConcept
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSubConcept extends EntityPathBase<SubConcept> {

    private static final long serialVersionUID = -2102860034L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSubConcept subConcept = new QSubConcept("subConcept");

    public final QConcept concept;

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final StringPath createdBy = createString("createdBy");

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final StringPath name = createString("name");

    public QSubConcept(String variable) {
        this(SubConcept.class, forVariable(variable), INITS);
    }

    public QSubConcept(Path<? extends SubConcept> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSubConcept(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSubConcept(PathMetadata<?> metadata, PathInits inits) {
        this(SubConcept.class, metadata, inits);
    }

    public QSubConcept(Class<? extends SubConcept> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.concept = inits.isInitialized("concept") ? new QConcept(forProperty("concept")) : null;
    }

}

