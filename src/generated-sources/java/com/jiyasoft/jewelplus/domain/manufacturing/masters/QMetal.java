package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QMetal is a Querydsl query type for Metal
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QMetal extends EntityPathBase<Metal> {

    private static final long serialVersionUID = 1607667281L;

    public static final QMetal metal = new QMetal("metal");

    public final NumberPath<Integer> compId = createNumber("compId", Integer.class);

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final StringPath name = createString("name");

    public final ListPath<Purity, QPurity> purity = this.<Purity, QPurity>createList("purity", Purity.class, QPurity.class, PathInits.DIRECT2);

    public final NumberPath<Double> specificGravity = createNumber("specificGravity", Double.class);

    public final NumberPath<Double> tzRate = createNumber("tzRate", Double.class);

    public QMetal(String variable) {
        super(Metal.class, forVariable(variable));
    }

    public QMetal(Path<? extends Metal> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMetal(PathMetadata<?> metadata) {
        super(Metal.class, metadata);
    }

}

