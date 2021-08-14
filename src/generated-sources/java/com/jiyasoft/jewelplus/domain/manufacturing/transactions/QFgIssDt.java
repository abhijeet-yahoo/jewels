package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QFgIssDt is a Querydsl query type for FgIssDt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QFgIssDt extends EntityPathBase<FgIssDt> {

    private static final long serialVersionUID = 155013080L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFgIssDt fgIssDt = new QFgIssDt("fgIssDt");

    public final NumberPath<Integer> bagId = createNumber("bagId", Integer.class);

    public final StringPath Barcode = createString("Barcode");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDt = createDateTime("createdDt", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final QFgIssMt fgIssMt;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final BooleanPath pendApprovalFlg = createBoolean("pendApprovalFlg");

    public final NumberPath<Integer> tranMtId = createNumber("tranMtId", Integer.class);

    public QFgIssDt(String variable) {
        this(FgIssDt.class, forVariable(variable), INITS);
    }

    public QFgIssDt(Path<? extends FgIssDt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QFgIssDt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QFgIssDt(PathMetadata<?> metadata, PathInits inits) {
        this(FgIssDt.class, metadata, inits);
    }

    public QFgIssDt(Class<? extends FgIssDt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.fgIssMt = inits.isInitialized("fgIssMt") ? new QFgIssMt(forProperty("fgIssMt"), inits.get("fgIssMt")) : null;
    }

}

