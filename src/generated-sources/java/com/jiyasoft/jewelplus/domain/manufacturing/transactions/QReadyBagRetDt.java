package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QReadyBagRetDt is a Querydsl query type for ReadyBagRetDt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QReadyBagRetDt extends EntityPathBase<ReadyBagRetDt> {

    private static final long serialVersionUID = 1260675532L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReadyBagRetDt readyBagRetDt = new QReadyBagRetDt("readyBagRetDt");

    public final QBagMt bagMt;

    public final NumberPath<Double> bagPcs = createNumber("bagPcs", Double.class);

    public final NumberPath<Double> carat = createNumber("carat", Double.class);

    public final StringPath colorNm = createString("colorNm");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDt = createDateTime("createdDt", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final BooleanPath pendApprovalFlg = createBoolean("pendApprovalFlg");

    public final StringPath purityNm = createString("purityNm");

    public final QReadyBagRetMt readyBagRetMt;

    public final NumberPath<Integer> refReadyBagId = createNumber("refReadyBagId", Integer.class);

    public final NumberPath<Integer> stone = createNumber("stone", Integer.class);

    public QReadyBagRetDt(String variable) {
        this(ReadyBagRetDt.class, forVariable(variable), INITS);
    }

    public QReadyBagRetDt(Path<? extends ReadyBagRetDt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QReadyBagRetDt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QReadyBagRetDt(PathMetadata<?> metadata, PathInits inits) {
        this(ReadyBagRetDt.class, metadata, inits);
    }

    public QReadyBagRetDt(Class<? extends ReadyBagRetDt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.bagMt = inits.isInitialized("bagMt") ? new QBagMt(forProperty("bagMt"), inits.get("bagMt")) : null;
        this.readyBagRetMt = inits.isInitialized("readyBagRetMt") ? new QReadyBagRetMt(forProperty("readyBagRetMt"), inits.get("readyBagRetMt")) : null;
    }

}

