package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QPDCTranMt is a Querydsl query type for PDCTranMt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPDCTranMt extends EntityPathBase<PDCTranMt> {

    private static final long serialVersionUID = -742434527L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPDCTranMt pDCTranMt = new QPDCTranMt("pDCTranMt");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath currStk = createBoolean("currStk");

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment department;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment deptFrom;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment deptTo;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDesign design;

    public final NumberPath<Double> excessWt = createNumber("excessWt", Double.class);

    public final NumberPath<Double> extraLoss = createNumber("extraLoss", Double.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final DateTimePath<java.util.Date> issueDate = createDateTime("issueDate", java.util.Date.class);

    public final NumberPath<Double> issWt = createNumber("issWt", Double.class);

    public final NumberPath<Double> loss = createNumber("loss", Double.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final NumberPath<Double> pcs = createNumber("pcs", Double.class);

    public final NumberPath<Double> purityConv = createNumber("purityConv", Double.class);

    public final NumberPath<Double> recWt = createNumber("recWt", Double.class);

    public final NumberPath<Integer> refMtId = createNumber("refMtId", Integer.class);

    public final StringPath remark = createString("remark");

    public final NumberPath<Double> splitQty = createNumber("splitQty", Double.class);

    public final NumberPath<Double> splitWt = createNumber("splitWt", Double.class);

    public final TimePath<java.sql.Time> time = createTime("time", java.sql.Time.class);

    public QPDCTranMt(String variable) {
        this(PDCTranMt.class, forVariable(variable), INITS);
    }

    public QPDCTranMt(Path<? extends PDCTranMt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPDCTranMt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPDCTranMt(PathMetadata<?> metadata, PathInits inits) {
        this(PDCTranMt.class, metadata, inits);
    }

    public QPDCTranMt(Class<? extends PDCTranMt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.department = inits.isInitialized("department") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment(forProperty("department"), inits.get("department")) : null;
        this.deptFrom = inits.isInitialized("deptFrom") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment(forProperty("deptFrom"), inits.get("deptFrom")) : null;
        this.deptTo = inits.isInitialized("deptTo") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment(forProperty("deptTo"), inits.get("deptTo")) : null;
        this.design = inits.isInitialized("design") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDesign(forProperty("design"), inits.get("design")) : null;
    }

}

