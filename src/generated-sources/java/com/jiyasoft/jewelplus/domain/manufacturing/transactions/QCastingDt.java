package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QCastingDt is a Querydsl query type for CastingDt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCastingDt extends EntityPathBase<CastingDt> {

    private static final long serialVersionUID = 840234323L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCastingDt castingDt = new QCastingDt("castingDt");

    public final QBagMt bagMt;

    public final QCastingMt castingMt;

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment department;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment deptTo;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Double> metalRate = createNumber("metalRate", Double.class);

    public final NumberPath<Double> metalWt = createNumber("metalWt", Double.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QOrderDt orderDt;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QOrderMt orderMt;

    public final NumberPath<Double> qty = createNumber("qty", Double.class);

    public final NumberPath<Integer> refMtId = createNumber("refMtId", Integer.class);

    public final NumberPath<Integer> refTranMetalId = createNumber("refTranMetalId", Integer.class);

    public final BooleanPath transfer = createBoolean("transfer");

    public final DateTimePath<java.util.Date> transferDate = createDateTime("transferDate", java.util.Date.class);

    public final NumberPath<Integer> transferTranMtId = createNumber("transferTranMtId", Integer.class);

    public QCastingDt(String variable) {
        this(CastingDt.class, forVariable(variable), INITS);
    }

    public QCastingDt(Path<? extends CastingDt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCastingDt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCastingDt(PathMetadata<?> metadata, PathInits inits) {
        this(CastingDt.class, metadata, inits);
    }

    public QCastingDt(Class<? extends CastingDt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.bagMt = inits.isInitialized("bagMt") ? new QBagMt(forProperty("bagMt"), inits.get("bagMt")) : null;
        this.castingMt = inits.isInitialized("castingMt") ? new QCastingMt(forProperty("castingMt"), inits.get("castingMt")) : null;
        this.department = inits.isInitialized("department") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment(forProperty("department"), inits.get("department")) : null;
        this.deptTo = inits.isInitialized("deptTo") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment(forProperty("deptTo"), inits.get("deptTo")) : null;
        this.orderDt = inits.isInitialized("orderDt") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QOrderDt(forProperty("orderDt"), inits.get("orderDt")) : null;
        this.orderMt = inits.isInitialized("orderMt") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QOrderMt(forProperty("orderMt"), inits.get("orderMt")) : null;
    }

}

