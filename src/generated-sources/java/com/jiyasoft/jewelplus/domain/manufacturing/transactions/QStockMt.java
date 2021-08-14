package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QStockMt is a Querydsl query type for StockMt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QStockMt extends EntityPathBase<StockMt> {

    private static final long serialVersionUID = -785552259L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStockMt stockMt = new QStockMt("stockMt");

    public final NumberPath<Integer> bagId = createNumber("bagId", Integer.class);

    public final StringPath barcode = createString("barcode");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QCategory category;

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath currStk = createBoolean("currStk");

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDesign design;

    public final NumberPath<Double> engraving = createNumber("engraving", Double.class);

    public final NumberPath<Double> factoryCost = createNumber("factoryCost", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment fromLocation;

    public final NumberPath<Double> grading = createNumber("grading", Double.class);

    public final NumberPath<Double> grossWt = createNumber("grossWt", Double.class);

    public final StringPath hallMarkId = createString("hallMarkId");

    public final NumberPath<Double> hallMarking = createNumber("hallMarking", Double.class);

    public final DateTimePath<java.util.Date> issueDate = createDateTime("issueDate", java.util.Date.class);

    public final NumberPath<Double> labourValue = createNumber("labourValue", Double.class);

    public final NumberPath<Double> lazerMarking = createNumber("lazerMarking", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment location;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QParty memoParty;

    public final NumberPath<Double> metalValue = createNumber("metalValue", Double.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final NumberPath<Double> mrp = createNumber("mrp", Double.class);

    public final NumberPath<Integer> mtId = createNumber("mtId", Integer.class);

    public final NumberPath<Double> netWt = createNumber("netWt", Double.class);

    public final NumberPath<Double> otherVlaue = createNumber("otherVlaue", Double.class);

    public final NumberPath<Double> otherWt = createNumber("otherWt", Double.class);

    public final NumberPath<Double> qty = createNumber("qty", Double.class);

    public final NumberPath<Integer> refMtId = createNumber("refMtId", Integer.class);

    public final NumberPath<Integer> refTranId = createNumber("refTranId", Integer.class);

    public final StringPath remark = createString("remark");

    public final NumberPath<Double> stoneValue = createNumber("stoneValue", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment toLocation;

    public final DateTimePath<java.util.Date> tranDate = createDateTime("tranDate", java.util.Date.class);

    public final StringPath tranType = createString("tranType");

    public QStockMt(String variable) {
        this(StockMt.class, forVariable(variable), INITS);
    }

    public QStockMt(Path<? extends StockMt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStockMt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStockMt(PathMetadata<?> metadata, PathInits inits) {
        this(StockMt.class, metadata, inits);
    }

    public QStockMt(Class<? extends StockMt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QCategory(forProperty("category")) : null;
        this.design = inits.isInitialized("design") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDesign(forProperty("design"), inits.get("design")) : null;
        this.fromLocation = inits.isInitialized("fromLocation") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment(forProperty("fromLocation"), inits.get("fromLocation")) : null;
        this.location = inits.isInitialized("location") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment(forProperty("location"), inits.get("location")) : null;
        this.memoParty = inits.isInitialized("memoParty") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QParty(forProperty("memoParty"), inits.get("memoParty")) : null;
        this.toLocation = inits.isInitialized("toLocation") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment(forProperty("toLocation"), inits.get("toLocation")) : null;
    }

}

