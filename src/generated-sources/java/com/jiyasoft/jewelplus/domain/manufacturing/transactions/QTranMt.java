package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QTranMt is a Querydsl query type for TranMt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTranMt extends EntityPathBase<TranMt> {

    private static final long serialVersionUID = -1938628334L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTranMt tranMt = new QTranMt("tranMt");

    public final QBagMt bagMt;

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath currStk = createBoolean("currStk");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment department;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment deptFrom;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment deptTo;

    public final NumberPath<Double> excessWt = createNumber("excessWt", Double.class);

    public final NumberPath<Double> extraLoss = createNumber("extraLoss", Double.class);

    public final BooleanPath holdFlg = createBoolean("holdFlg");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final DateTimePath<java.util.Date> issueDate = createDateTime("issueDate", java.util.Date.class);

    public final NumberPath<Double> issWt = createNumber("issWt", Double.class);

    public final NumberPath<Double> loss = createNumber("loss", Double.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final StringPath noneAppRm = createString("noneAppRm");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QOrderDt orderDt;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QOrderMt orderMt;

    public final NumberPath<Double> pcs = createNumber("pcs", Double.class);

    public final BooleanPath pendApprovalFlg = createBoolean("pendApprovalFlg");

    public final NumberPath<Double> recWt = createNumber("recWt", Double.class);

    public final NumberPath<Integer> refMtId = createNumber("refMtId", Integer.class);

    public final StringPath remark = createString("remark");

    public final BooleanPath repFlg = createBoolean("repFlg");

    public final NumberPath<Double> splitQty = createNumber("splitQty", Double.class);

    public final NumberPath<Double> splitWt = createNumber("splitWt", Double.class);

    public final TimePath<java.sql.Time> time = createTime("time", java.sql.Time.class);

    public final NumberPath<Double> totCarat = createNumber("totCarat", Double.class);

    public final NumberPath<Integer> totStone = createNumber("totStone", Integer.class);

    public final DateTimePath<java.util.Date> trandate = createDateTime("trandate", java.util.Date.class);

    public QTranMt(String variable) {
        this(TranMt.class, forVariable(variable), INITS);
    }

    public QTranMt(Path<? extends TranMt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTranMt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTranMt(PathMetadata<?> metadata, PathInits inits) {
        this(TranMt.class, metadata, inits);
    }

    public QTranMt(Class<? extends TranMt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.bagMt = inits.isInitialized("bagMt") ? new QBagMt(forProperty("bagMt"), inits.get("bagMt")) : null;
        this.department = inits.isInitialized("department") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment(forProperty("department"), inits.get("department")) : null;
        this.deptFrom = inits.isInitialized("deptFrom") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment(forProperty("deptFrom"), inits.get("deptFrom")) : null;
        this.deptTo = inits.isInitialized("deptTo") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment(forProperty("deptTo"), inits.get("deptTo")) : null;
        this.orderDt = inits.isInitialized("orderDt") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QOrderDt(forProperty("orderDt"), inits.get("orderDt")) : null;
        this.orderMt = inits.isInitialized("orderMt") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QOrderMt(forProperty("orderMt"), inits.get("orderMt")) : null;
    }

}

