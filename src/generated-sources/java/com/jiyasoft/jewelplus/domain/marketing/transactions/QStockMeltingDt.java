package com.jiyasoft.jewelplus.domain.marketing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QStockMeltingDt is a Querydsl query type for StockMeltingDt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QStockMeltingDt extends EntityPathBase<StockMeltingDt> {

    private static final long serialVersionUID = 1526505412L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStockMeltingDt stockMeltingDt = new QStockMeltingDt("stockMeltingDt");

    public final NumberPath<Integer> bagId = createNumber("bagId", Integer.class);

    public final StringPath Barcode = createString("Barcode");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDt = createDateTime("createdDt", java.util.Date.class);

    public final BooleanPath currStk = createBoolean("currStk");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final BooleanPath pendApprovalFlg = createBoolean("pendApprovalFlg");

    public final QStockMeltingMt stockMeltingMt;

    public final com.jiyasoft.jewelplus.domain.manufacturing.transactions.QStockMt stockMt;

    public QStockMeltingDt(String variable) {
        this(StockMeltingDt.class, forVariable(variable), INITS);
    }

    public QStockMeltingDt(Path<? extends StockMeltingDt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStockMeltingDt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStockMeltingDt(PathMetadata<?> metadata, PathInits inits) {
        this(StockMeltingDt.class, metadata, inits);
    }

    public QStockMeltingDt(Class<? extends StockMeltingDt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.stockMeltingMt = inits.isInitialized("stockMeltingMt") ? new QStockMeltingMt(forProperty("stockMeltingMt"), inits.get("stockMeltingMt")) : null;
        this.stockMt = inits.isInitialized("stockMt") ? new com.jiyasoft.jewelplus.domain.manufacturing.transactions.QStockMt(forProperty("stockMt"), inits.get("stockMt")) : null;
    }

}

