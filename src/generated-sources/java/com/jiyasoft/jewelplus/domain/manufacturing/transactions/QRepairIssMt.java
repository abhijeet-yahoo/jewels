package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QRepairIssMt is a Querydsl query type for RepairIssMt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QRepairIssMt extends EntityPathBase<RepairIssMt> {

    private static final long serialVersionUID = 502762915L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRepairIssMt repairIssMt = new QRepairIssMt("repairIssMt");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDt = createDateTime("createdDt", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment department;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final DateTimePath<java.util.Date> invDate = createDateTime("invDate", java.util.Date.class);

    public final StringPath invNo = createString("invNo");

    public final NumberPath<Integer> invSrNo = createNumber("invSrNo", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public QRepairIssMt(String variable) {
        this(RepairIssMt.class, forVariable(variable), INITS);
    }

    public QRepairIssMt(Path<? extends RepairIssMt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRepairIssMt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRepairIssMt(PathMetadata<?> metadata, PathInits inits) {
        this(RepairIssMt.class, metadata, inits);
    }

    public QRepairIssMt(Class<? extends RepairIssMt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.department = inits.isInitialized("department") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment(forProperty("department"), inits.get("department")) : null;
    }

}

