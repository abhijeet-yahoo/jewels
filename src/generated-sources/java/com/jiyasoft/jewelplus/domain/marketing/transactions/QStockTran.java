package com.jiyasoft.jewelplus.domain.marketing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QStockTran is a Querydsl query type for StockTran
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QStockTran extends EntityPathBase<StockTran> {

    private static final long serialVersionUID = 780363545L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStockTran stockTran = new QStockTran("stockTran");

    public final StringPath barcode = createString("barcode");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final StringPath currStatus = createString("currStatus");

    public final BooleanPath currStk = createBoolean("currStk");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QEmployee employee;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final DateTimePath<java.util.Date> issueDate = createDateTime("issueDate", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment location;

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QParty party;

    public final NumberPath<Integer> refStkTranId = createNumber("refStkTranId", Integer.class);

    public final NumberPath<Integer> refTranId = createNumber("refTranId", Integer.class);

    public final StringPath remark = createString("remark");

    public final com.jiyasoft.jewelplus.domain.manufacturing.transactions.QStockMt stockMt;

    public final DateTimePath<java.util.Date> tranDate = createDateTime("tranDate", java.util.Date.class);

    public final StringPath tranType = createString("tranType");

    public QStockTran(String variable) {
        this(StockTran.class, forVariable(variable), INITS);
    }

    public QStockTran(Path<? extends StockTran> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStockTran(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStockTran(PathMetadata<?> metadata, PathInits inits) {
        this(StockTran.class, metadata, inits);
    }

    public QStockTran(Class<? extends StockTran> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.employee = inits.isInitialized("employee") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QEmployee(forProperty("employee"), inits.get("employee")) : null;
        this.location = inits.isInitialized("location") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment(forProperty("location"), inits.get("location")) : null;
        this.party = inits.isInitialized("party") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QParty(forProperty("party"), inits.get("party")) : null;
        this.stockMt = inits.isInitialized("stockMt") ? new com.jiyasoft.jewelplus.domain.manufacturing.transactions.QStockMt(forProperty("stockMt"), inits.get("stockMt")) : null;
    }

}

