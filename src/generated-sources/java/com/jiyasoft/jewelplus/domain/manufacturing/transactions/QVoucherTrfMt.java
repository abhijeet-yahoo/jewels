package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QVoucherTrfMt is a Querydsl query type for VoucherTrfMt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QVoucherTrfMt extends EntityPathBase<VoucherTrfMt> {

    private static final long serialVersionUID = -1420338623L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVoucherTrfMt voucherTrfMt = new QVoucherTrfMt("voucherTrfMt");

    public final QBagMt bagMt;

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDt = createDateTime("createdDt", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment deptFrom;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment deptTo;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final DateTimePath<java.util.Date> voucherdate = createDateTime("voucherdate", java.util.Date.class);

    public final StringPath voucherno = createString("voucherno");

    public QVoucherTrfMt(String variable) {
        this(VoucherTrfMt.class, forVariable(variable), INITS);
    }

    public QVoucherTrfMt(Path<? extends VoucherTrfMt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QVoucherTrfMt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QVoucherTrfMt(PathMetadata<?> metadata, PathInits inits) {
        this(VoucherTrfMt.class, metadata, inits);
    }

    public QVoucherTrfMt(Class<? extends VoucherTrfMt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.bagMt = inits.isInitialized("bagMt") ? new QBagMt(forProperty("bagMt"), inits.get("bagMt")) : null;
        this.deptFrom = inits.isInitialized("deptFrom") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment(forProperty("deptFrom"), inits.get("deptFrom")) : null;
        this.deptTo = inits.isInitialized("deptTo") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment(forProperty("deptTo"), inits.get("deptTo")) : null;
    }

}

