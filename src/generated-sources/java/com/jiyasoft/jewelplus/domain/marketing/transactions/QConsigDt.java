package com.jiyasoft.jewelplus.domain.marketing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QConsigDt is a Querydsl query type for ConsigDt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QConsigDt extends EntityPathBase<ConsigDt> {

    private static final long serialVersionUID = 940153415L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QConsigDt consigDt = new QConsigDt("consigDt");

    public final NumberPath<Double> adjQty = createNumber("adjQty", Double.class);

    public final StringPath barcode = createString("barcode");

    public final NumberPath<Double> compValue = createNumber("compValue", Double.class);

    public final QConsigMt consigMt;

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDesign design;

    public final StringPath destination = createString("destination");

    public final NumberPath<Double> discAmount = createNumber("discAmount", Double.class);

    public final NumberPath<Double> discPerc = createNumber("discPerc", Double.class);

    public final NumberPath<Double> engraving = createNumber("engraving", Double.class);

    public final NumberPath<Double> finalPrice = createNumber("finalPrice", Double.class);

    public final NumberPath<Double> fob = createNumber("fob", Double.class);

    public final NumberPath<Double> grading = createNumber("grading", Double.class);

    public final NumberPath<Double> grossWt = createNumber("grossWt", Double.class);

    public final NumberPath<Double> hallMarking = createNumber("hallMarking", Double.class);

    public final NumberPath<Double> handlingValue = createNumber("handlingValue", Double.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Double> labValue = createNumber("labValue", Double.class);

    public final NumberPath<Double> lazerMarking = createNumber("lazerMarking", Double.class);

    public final NumberPath<Double> lossValue = createNumber("lossValue", Double.class);

    public final NumberPath<Double> lossWt = createNumber("lossWt", Double.class);

    public final NumberPath<Double> metalValue = createNumber("metalValue", Double.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final NumberPath<Double> netAmount = createNumber("netAmount", Double.class);

    public final NumberPath<Double> netWt = createNumber("netWt", Double.class);

    public final StringPath orderRef = createString("orderRef");

    public final NumberPath<Double> other = createNumber("other", Double.class);

    public final NumberPath<Double> pcs = createNumber("pcs", Double.class);

    public final NumberPath<Integer> refTranDtId = createNumber("refTranDtId", Integer.class);

    public final StringPath remark = createString("remark");

    public final BooleanPath rLock = createBoolean("rLock");

    public final NumberPath<Double> setValue = createNumber("setValue", Double.class);

    public final NumberPath<Double> stoneValue = createNumber("stoneValue", Double.class);

    public final NumberPath<Double> tagPrice = createNumber("tagPrice", Double.class);

    public final StringPath tranType = createString("tranType");

    public QConsigDt(String variable) {
        this(ConsigDt.class, forVariable(variable), INITS);
    }

    public QConsigDt(Path<? extends ConsigDt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QConsigDt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QConsigDt(PathMetadata<?> metadata, PathInits inits) {
        this(ConsigDt.class, metadata, inits);
    }

    public QConsigDt(Class<? extends ConsigDt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.consigMt = inits.isInitialized("consigMt") ? new QConsigMt(forProperty("consigMt"), inits.get("consigMt")) : null;
        this.design = inits.isInitialized("design") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDesign(forProperty("design"), inits.get("design")) : null;
    }

}

