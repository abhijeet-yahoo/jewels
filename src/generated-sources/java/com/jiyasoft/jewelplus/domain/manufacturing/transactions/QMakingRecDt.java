package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QMakingRecDt is a Querydsl query type for MakingRecDt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QMakingRecDt extends EntityPathBase<MakingRecDt> {

    private static final long serialVersionUID = -1270900235L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMakingRecDt makingRecDt = new QMakingRecDt("makingRecDt");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor color;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QComponent component;

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDt = createDateTime("createdDt", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QMakingMt makingMt;

    public final NumberPath<Double> metalRate = createNumber("metalRate", Double.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity purity;

    public final NumberPath<Double> qty = createNumber("qty", Double.class);

    public final NumberPath<Double> recWt = createNumber("recWt", Double.class);

    public final NumberPath<Long> uniqueId = createNumber("uniqueId", Long.class);

    public QMakingRecDt(String variable) {
        this(MakingRecDt.class, forVariable(variable), INITS);
    }

    public QMakingRecDt(Path<? extends MakingRecDt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMakingRecDt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMakingRecDt(PathMetadata<?> metadata, PathInits inits) {
        this(MakingRecDt.class, metadata, inits);
    }

    public QMakingRecDt(Class<? extends MakingRecDt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.color = inits.isInitialized("color") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor(forProperty("color")) : null;
        this.component = inits.isInitialized("component") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QComponent(forProperty("component")) : null;
        this.makingMt = inits.isInitialized("makingMt") ? new QMakingMt(forProperty("makingMt"), inits.get("makingMt")) : null;
        this.purity = inits.isInitialized("purity") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity(forProperty("purity"), inits.get("purity")) : null;
    }

}

