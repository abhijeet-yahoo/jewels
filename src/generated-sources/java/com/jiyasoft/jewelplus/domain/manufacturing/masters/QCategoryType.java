package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QCategoryType is a Querydsl query type for CategoryType
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCategoryType extends EntityPathBase<CategoryType> {

    private static final long serialVersionUID = 1305888462L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCategoryType categoryType = new QCategoryType("categoryType");

    public final QCategory category;

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final StringPath createdBy = createString("createdBy");

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final StringPath defaultCatg = createString("defaultCatg");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final StringPath name = createString("name");

    public final NumberPath<Double> rate = createNumber("rate", Double.class);

    public QCategoryType(String variable) {
        this(CategoryType.class, forVariable(variable), INITS);
    }

    public QCategoryType(Path<? extends CategoryType> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCategoryType(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCategoryType(PathMetadata<?> metadata, PathInits inits) {
        this(CategoryType.class, metadata, inits);
    }

    public QCategoryType(Class<? extends CategoryType> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new QCategory(forProperty("category")) : null;
    }

}

