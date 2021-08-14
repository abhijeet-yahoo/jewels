package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QReadyBagIssDt is a Querydsl query type for ReadyBagIssDt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QReadyBagIssDt extends EntityPathBase<ReadyBagIssDt> {

    private static final long serialVersionUID = 1252779956L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReadyBagIssDt readyBagIssDt = new QReadyBagIssDt("readyBagIssDt");

    public final QBagMt bagMt;

    public final NumberPath<Double> bagPcs = createNumber("bagPcs", Double.class);

    public final NumberPath<Double> carat = createNumber("carat", Double.class);

    public final StringPath colorNm = createString("colorNm");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDt = createDateTime("createdDt", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final StringPath purityNm = createString("purityNm");

    public final QReadyBagIssMt readyBagIssMt;

    public final NumberPath<Integer> refReadyBagId = createNumber("refReadyBagId", Integer.class);

    public final NumberPath<Integer> stone = createNumber("stone", Integer.class);

    public QReadyBagIssDt(String variable) {
        this(ReadyBagIssDt.class, forVariable(variable), INITS);
    }

    public QReadyBagIssDt(Path<? extends ReadyBagIssDt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QReadyBagIssDt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QReadyBagIssDt(PathMetadata<?> metadata, PathInits inits) {
        this(ReadyBagIssDt.class, metadata, inits);
    }

    public QReadyBagIssDt(Class<? extends ReadyBagIssDt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.bagMt = inits.isInitialized("bagMt") ? new QBagMt(forProperty("bagMt"), inits.get("bagMt")) : null;
        this.readyBagIssMt = inits.isInitialized("readyBagIssMt") ? new QReadyBagIssMt(forProperty("readyBagIssMt"), inits.get("readyBagIssMt")) : null;
    }

}

