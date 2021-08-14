package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QProductSize is a Querydsl query type for ProductSize
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QProductSize extends EntityPathBase<ProductSize> {

    private static final long serialVersionUID = 1307661658L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductSize productSize = new QProductSize("productSize");

    public final QCategory category;

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final StringPath createdBy = createString("createdBy");

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final StringPath name = createString("name");

    public QProductSize(String variable) {
        this(ProductSize.class, forVariable(variable), INITS);
    }

    public QProductSize(Path<? extends ProductSize> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QProductSize(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QProductSize(PathMetadata<?> metadata, PathInits inits) {
        this(ProductSize.class, metadata, inits);
    }

    public QProductSize(Class<? extends ProductSize> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new QCategory(forProperty("category")) : null;
    }

}

