package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QBarcodeMt is a Querydsl query type for BarcodeMt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QBarcodeMt extends EntityPathBase<BarcodeMt> {

    private static final long serialVersionUID = 1278731111L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBarcodeMt barcodeMt = new QBarcodeMt("barcodeMt");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDt = createDateTime("createdDt", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment department;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final DateTimePath<java.util.Date> invDate = createDateTime("invDate", java.util.Date.class);

    public final StringPath invNo = createString("invNo");

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final NumberPath<Integer> srNo = createNumber("srNo", Integer.class);

    public QBarcodeMt(String variable) {
        this(BarcodeMt.class, forVariable(variable), INITS);
    }

    public QBarcodeMt(Path<? extends BarcodeMt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QBarcodeMt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QBarcodeMt(PathMetadata<?> metadata, PathInits inits) {
        this(BarcodeMt.class, metadata, inits);
    }

    public QBarcodeMt(Class<? extends BarcodeMt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.department = inits.isInitialized("department") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment(forProperty("department"), inits.get("department")) : null;
    }

}

