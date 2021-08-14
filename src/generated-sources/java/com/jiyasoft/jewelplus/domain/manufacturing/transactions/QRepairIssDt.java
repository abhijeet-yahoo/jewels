package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QRepairIssDt is a Querydsl query type for RepairIssDt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QRepairIssDt extends EntityPathBase<RepairIssDt> {

    private static final long serialVersionUID = 502762636L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRepairIssDt repairIssDt = new QRepairIssDt("repairIssDt");

    public final NumberPath<Integer> bagId = createNumber("bagId", Integer.class);

    public final StringPath Barcode = createString("Barcode");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDt = createDateTime("createdDt", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final BooleanPath pendApprovalFlg = createBoolean("pendApprovalFlg");

    public final QRepairIssMt repairIssMt;

    public final NumberPath<Integer> tranMtId = createNumber("tranMtId", Integer.class);

    public QRepairIssDt(String variable) {
        this(RepairIssDt.class, forVariable(variable), INITS);
    }

    public QRepairIssDt(Path<? extends RepairIssDt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRepairIssDt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRepairIssDt(PathMetadata<?> metadata, PathInits inits) {
        this(RepairIssDt.class, metadata, inits);
    }

    public QRepairIssDt(Class<? extends RepairIssDt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.repairIssMt = inits.isInitialized("repairIssMt") ? new QRepairIssMt(forProperty("repairIssMt"), inits.get("repairIssMt")) : null;
    }

}

