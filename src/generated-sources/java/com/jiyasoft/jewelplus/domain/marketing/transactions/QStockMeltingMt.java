package com.jiyasoft.jewelplus.domain.marketing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QStockMeltingMt is a Querydsl query type for StockMeltingMt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QStockMeltingMt extends EntityPathBase<StockMeltingMt> {

    private static final long serialVersionUID = 1526505691L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStockMeltingMt stockMeltingMt = new QStockMeltingMt("stockMeltingMt");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDt = createDateTime("createdDt", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final DateTimePath<java.util.Date> invDate = createDateTime("invDate", java.util.Date.class);

    public final StringPath invNo = createString("invNo");

    public final NumberPath<Integer> invSrNo = createNumber("invSrNo", Integer.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment location;

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public QStockMeltingMt(String variable) {
        this(StockMeltingMt.class, forVariable(variable), INITS);
    }

    public QStockMeltingMt(Path<? extends StockMeltingMt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStockMeltingMt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStockMeltingMt(PathMetadata<?> metadata, PathInits inits) {
        this(StockMeltingMt.class, metadata, inits);
    }

    public QStockMeltingMt(Class<? extends StockMeltingMt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.location = inits.isInitialized("location") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment(forProperty("location"), inits.get("location")) : null;
    }

}

