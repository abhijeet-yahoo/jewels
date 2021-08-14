package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QHandlingMaster is a Querydsl query type for HandlingMaster
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QHandlingMaster extends EntityPathBase<HandlingMaster> {

    private static final long serialVersionUID = -1098684515L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHandlingMaster handlingMaster = new QHandlingMaster("handlingMaster");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDt = createDateTime("createdDt", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QMetal metal;

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final QParty party;

    public final NumberPath<Double> perCarate = createNumber("perCarate", Double.class);

    public final NumberPath<Double> percentage = createNumber("percentage", Double.class);

    public final QShape shape;

    public final QStoneType stoneType;

    public QHandlingMaster(String variable) {
        this(HandlingMaster.class, forVariable(variable), INITS);
    }

    public QHandlingMaster(Path<? extends HandlingMaster> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QHandlingMaster(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QHandlingMaster(PathMetadata<?> metadata, PathInits inits) {
        this(HandlingMaster.class, metadata, inits);
    }

    public QHandlingMaster(Class<? extends HandlingMaster> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.metal = inits.isInitialized("metal") ? new QMetal(forProperty("metal")) : null;
        this.party = inits.isInitialized("party") ? new QParty(forProperty("party"), inits.get("party")) : null;
        this.shape = inits.isInitialized("shape") ? new QShape(forProperty("shape")) : null;
        this.stoneType = inits.isInitialized("stoneType") ? new QStoneType(forProperty("stoneType")) : null;
    }

}

