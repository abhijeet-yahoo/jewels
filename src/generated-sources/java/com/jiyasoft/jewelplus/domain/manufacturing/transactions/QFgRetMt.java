package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QFgRetMt is a Querydsl query type for FgRetMt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QFgRetMt extends EntityPathBase<FgRetMt> {

    private static final long serialVersionUID = 162908935L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFgRetMt fgRetMt = new QFgRetMt("fgRetMt");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDt = createDateTime("createdDt", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment department;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment fromLocation;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final DateTimePath<java.util.Date> invDate = createDateTime("invDate", java.util.Date.class);

    public final StringPath invNo = createString("invNo");

    public final NumberPath<Integer> invSrNo = createNumber("invSrNo", Integer.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment location;

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment toLocation;

    public QFgRetMt(String variable) {
        this(FgRetMt.class, forVariable(variable), INITS);
    }

    public QFgRetMt(Path<? extends FgRetMt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QFgRetMt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QFgRetMt(PathMetadata<?> metadata, PathInits inits) {
        this(FgRetMt.class, metadata, inits);
    }

    public QFgRetMt(Class<? extends FgRetMt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.department = inits.isInitialized("department") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment(forProperty("department"), inits.get("department")) : null;
        this.fromLocation = inits.isInitialized("fromLocation") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment(forProperty("fromLocation"), inits.get("fromLocation")) : null;
        this.location = inits.isInitialized("location") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment(forProperty("location"), inits.get("location")) : null;
        this.toLocation = inits.isInitialized("toLocation") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment(forProperty("toLocation"), inits.get("toLocation")) : null;
    }

}

