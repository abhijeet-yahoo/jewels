package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QAutoStyleParameter is a Querydsl query type for AutoStyleParameter
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QAutoStyleParameter extends EntityPathBase<AutoStyleParameter> {

    private static final long serialVersionUID = 1243274621L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAutoStyleParameter autoStyleParameter = new QAutoStyleParameter("autoStyleParameter");

    public final QCategory category;

    public final QCollectionMaster collectionMaster;

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> lastNo = createNumber("lastNo", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public QAutoStyleParameter(String variable) {
        this(AutoStyleParameter.class, forVariable(variable), INITS);
    }

    public QAutoStyleParameter(Path<? extends AutoStyleParameter> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QAutoStyleParameter(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QAutoStyleParameter(PathMetadata<?> metadata, PathInits inits) {
        this(AutoStyleParameter.class, metadata, inits);
    }

    public QAutoStyleParameter(Class<? extends AutoStyleParameter> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new QCategory(forProperty("category")) : null;
        this.collectionMaster = inits.isInitialized("collectionMaster") ? new QCollectionMaster(forProperty("collectionMaster")) : null;
    }

}

