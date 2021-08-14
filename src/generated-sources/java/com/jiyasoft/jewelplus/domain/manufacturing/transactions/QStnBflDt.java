package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QStnBflDt is a Querydsl query type for StnBflDt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QStnBflDt extends EntityPathBase<StnBflDt> {

    private static final long serialVersionUID = 1358457867L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStnBflDt stnBflDt = new QStnBflDt("stnBflDt");

    public final NumberPath<Double> carat = createNumber("carat", Double.class);

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDt = createDateTime("createdDt", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final BooleanPath loss = createBoolean("loss");

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final QStnBflMt stnBflMt;

    public final QStoneInwardDt stnInwardDt;

    public final QStoneInwardMt stnInwardMt;

    public final NumberPath<Integer> stone = createNumber("stone", Integer.class);

    public final StringPath tranType = createString("tranType");

    public final NumberPath<Long> uniqueId = createNumber("uniqueId", Long.class);

    public QStnBflDt(String variable) {
        this(StnBflDt.class, forVariable(variable), INITS);
    }

    public QStnBflDt(Path<? extends StnBflDt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStnBflDt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStnBflDt(PathMetadata<?> metadata, PathInits inits) {
        this(StnBflDt.class, metadata, inits);
    }

    public QStnBflDt(Class<? extends StnBflDt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.stnBflMt = inits.isInitialized("stnBflMt") ? new QStnBflMt(forProperty("stnBflMt")) : null;
        this.stnInwardDt = inits.isInitialized("stnInwardDt") ? new QStoneInwardDt(forProperty("stnInwardDt"), inits.get("stnInwardDt")) : null;
        this.stnInwardMt = inits.isInitialized("stnInwardMt") ? new QStoneInwardMt(forProperty("stnInwardMt"), inits.get("stnInwardMt")) : null;
    }

}

