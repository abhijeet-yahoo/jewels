package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QFgRetDt is a Querydsl query type for FgRetDt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QFgRetDt extends EntityPathBase<FgRetDt> {

    private static final long serialVersionUID = 162908656L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFgRetDt fgRetDt = new QFgRetDt("fgRetDt");

    public final NumberPath<Integer> bagId = createNumber("bagId", Integer.class);

    public final StringPath Barcode = createString("Barcode");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDt = createDateTime("createdDt", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final QFgRetMt fgRetMt;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final BooleanPath pendApprovalFlg = createBoolean("pendApprovalFlg");

    public final QStockMt stockMt;

    public final NumberPath<Integer> tranMtId = createNumber("tranMtId", Integer.class);

    public QFgRetDt(String variable) {
        this(FgRetDt.class, forVariable(variable), INITS);
    }

    public QFgRetDt(Path<? extends FgRetDt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QFgRetDt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QFgRetDt(PathMetadata<?> metadata, PathInits inits) {
        this(FgRetDt.class, metadata, inits);
    }

    public QFgRetDt(Class<? extends FgRetDt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.fgRetMt = inits.isInitialized("fgRetMt") ? new QFgRetMt(forProperty("fgRetMt"), inits.get("fgRetMt")) : null;
        this.stockMt = inits.isInitialized("stockMt") ? new QStockMt(forProperty("stockMt"), inits.get("stockMt")) : null;
    }

}

