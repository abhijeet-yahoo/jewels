package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QPurity is a Querydsl query type for Purity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPurity extends EntityPathBase<Purity> {

    private static final long serialVersionUID = -1601309577L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPurity purity = new QPurity("purity");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final BooleanPath defPurity = createBoolean("defPurity");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QMetal metal;

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final StringPath name = createString("name");

    public final BooleanPath pure = createBoolean("pure");

    public final NumberPath<Double> purityConv = createNumber("purityConv", Double.class);

    public final NumberPath<Double> vPurity = createNumber("vPurity", Double.class);

    public final NumberPath<Double> waxWtConv = createNumber("waxWtConv", Double.class);

    public QPurity(String variable) {
        this(Purity.class, forVariable(variable), INITS);
    }

    public QPurity(Path<? extends Purity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPurity(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPurity(PathMetadata<?> metadata, PathInits inits) {
        this(Purity.class, metadata, inits);
    }

    public QPurity(Class<? extends Purity> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.metal = inits.isInitialized("metal") ? new QMetal(forProperty("metal")) : null;
    }

}

