package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QMeltingRecDt is a Querydsl query type for MeltingRecDt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QMeltingRecDt extends EntityPathBase<MeltingRecDt> {

    private static final long serialVersionUID = 137478782L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMeltingRecDt meltingRecDt = new QMeltingRecDt("meltingRecDt");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor color;

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDt = createDateTime("createdDt", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment deptLocation;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QMeltingMt meltingMt;

    public final NumberPath<Double> metalRate = createNumber("metalRate", Double.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity purity;

    public final NumberPath<Double> purityConv = createNumber("purityConv", Double.class);

    public final NumberPath<Double> recCarat = createNumber("recCarat", Double.class);

    public final DateTimePath<java.util.Date> recDate = createDateTime("recDate", java.util.Date.class);

    public final NumberPath<Double> recExcpPureWt = createNumber("recExcpPureWt", Double.class);

    public final NumberPath<Double> recFreshMetalWt = createNumber("recFreshMetalWt", Double.class);

    public final NumberPath<Double> recNetWt = createNumber("recNetWt", Double.class);

    public final NumberPath<Integer> recStone = createNumber("recStone", Integer.class);

    public final NumberPath<Long> uniqueId = createNumber("uniqueId", Long.class);

    public QMeltingRecDt(String variable) {
        this(MeltingRecDt.class, forVariable(variable), INITS);
    }

    public QMeltingRecDt(Path<? extends MeltingRecDt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMeltingRecDt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMeltingRecDt(PathMetadata<?> metadata, PathInits inits) {
        this(MeltingRecDt.class, metadata, inits);
    }

    public QMeltingRecDt(Class<? extends MeltingRecDt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.color = inits.isInitialized("color") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor(forProperty("color")) : null;
        this.deptLocation = inits.isInitialized("deptLocation") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment(forProperty("deptLocation"), inits.get("deptLocation")) : null;
        this.meltingMt = inits.isInitialized("meltingMt") ? new QMeltingMt(forProperty("meltingMt")) : null;
        this.purity = inits.isInitialized("purity") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity(forProperty("purity"), inits.get("purity")) : null;
    }

}

