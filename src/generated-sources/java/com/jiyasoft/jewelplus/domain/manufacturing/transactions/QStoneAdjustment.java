package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QStoneAdjustment is a Querydsl query type for StoneAdjustment
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QStoneAdjustment extends EntityPathBase<StoneAdjustment> {

    private static final long serialVersionUID = -1287520046L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStoneAdjustment stoneAdjustment = new QStoneAdjustment("stoneAdjustment");

    public final NumberPath<Double> BalCarat = createNumber("BalCarat", Double.class);

    public final NumberPath<Integer> balStone = createNumber("balStone", Integer.class);

    public final NumberPath<Double> carat = createNumber("carat", Double.class);

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDt = createDateTime("createdDt", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final StringPath entryType = createString("entryType");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final QStoneInwardDt stnInwardDt;

    public final NumberPath<Integer> stone = createNumber("stone", Integer.class);

    public final NumberPath<Long> uniqueId = createNumber("uniqueId", Long.class);

    public QStoneAdjustment(String variable) {
        this(StoneAdjustment.class, forVariable(variable), INITS);
    }

    public QStoneAdjustment(Path<? extends StoneAdjustment> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStoneAdjustment(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStoneAdjustment(PathMetadata<?> metadata, PathInits inits) {
        this(StoneAdjustment.class, metadata, inits);
    }

    public QStoneAdjustment(Class<? extends StoneAdjustment> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.stnInwardDt = inits.isInitialized("stnInwardDt") ? new QStoneInwardDt(forProperty("stnInwardDt"), inits.get("stnInwardDt")) : null;
    }

}

