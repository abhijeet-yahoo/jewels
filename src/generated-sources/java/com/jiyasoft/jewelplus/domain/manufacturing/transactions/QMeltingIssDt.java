package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QMeltingIssDt is a Querydsl query type for MeltingIssDt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QMeltingIssDt extends EntityPathBase<MeltingIssDt> {

    private static final long serialVersionUID = 129599543L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMeltingIssDt meltingIssDt = new QMeltingIssDt("meltingIssDt");

    public final QBagMt bagMt;

    public final StringPath barcode = createString("barcode");

    public final NumberPath<Double> carat = createNumber("carat", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor color;

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDt = createDateTime("createdDt", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment deptLocation;

    public final StringPath dtRemark = createString("dtRemark");

    public final NumberPath<Double> excpPureWt = createNumber("excpPureWt", Double.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final DateTimePath<java.util.Date> issDate = createDateTime("issDate", java.util.Date.class);

    public final NumberPath<Double> issFreshMetalWt = createNumber("issFreshMetalWt", Double.class);

    public final NumberPath<Double> loss = createNumber("loss", Double.class);

    public final QMeltingMt meltingMt;

    public final NumberPath<Double> metalRate = createNumber("metalRate", Double.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final NumberPath<Double> netWt = createNumber("netWt", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity purity;

    public final NumberPath<Double> purityConv = createNumber("purityConv", Double.class);

    public final NumberPath<Integer> refMtId = createNumber("refMtId", Integer.class);

    public final NumberPath<Integer> refTranMetalId = createNumber("refTranMetalId", Integer.class);

    public final NumberPath<Integer> stone = createNumber("stone", Integer.class);

    public final StringPath tranType = createString("tranType");

    public final NumberPath<Long> uniqueId = createNumber("uniqueId", Long.class);

    public QMeltingIssDt(String variable) {
        this(MeltingIssDt.class, forVariable(variable), INITS);
    }

    public QMeltingIssDt(Path<? extends MeltingIssDt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMeltingIssDt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMeltingIssDt(PathMetadata<?> metadata, PathInits inits) {
        this(MeltingIssDt.class, metadata, inits);
    }

    public QMeltingIssDt(Class<? extends MeltingIssDt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.bagMt = inits.isInitialized("bagMt") ? new QBagMt(forProperty("bagMt"), inits.get("bagMt")) : null;
        this.color = inits.isInitialized("color") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor(forProperty("color")) : null;
        this.deptLocation = inits.isInitialized("deptLocation") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment(forProperty("deptLocation"), inits.get("deptLocation")) : null;
        this.meltingMt = inits.isInitialized("meltingMt") ? new QMeltingMt(forProperty("meltingMt")) : null;
        this.purity = inits.isInitialized("purity") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity(forProperty("purity"), inits.get("purity")) : null;
    }

}

