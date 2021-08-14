package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QHandlingMasterFl is a Querydsl query type for HandlingMasterFl
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QHandlingMasterFl extends EntityPathBase<HandlingMasterFl> {

    private static final long serialVersionUID = 726138179L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHandlingMasterFl handlingMasterFl = new QHandlingMasterFl("handlingMasterFl");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDt = createDateTime("createdDt", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Double> fromDiaRate = createNumber("fromDiaRate", Double.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final QParty party;

    public final NumberPath<Double> percentage = createNumber("percentage", Double.class);

    public final NumberPath<Double> rate = createNumber("rate", Double.class);

    public final NumberPath<Double> toDiaRate = createNumber("toDiaRate", Double.class);

    public QHandlingMasterFl(String variable) {
        this(HandlingMasterFl.class, forVariable(variable), INITS);
    }

    public QHandlingMasterFl(Path<? extends HandlingMasterFl> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QHandlingMasterFl(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QHandlingMasterFl(PathMetadata<?> metadata, PathInits inits) {
        this(HandlingMasterFl.class, metadata, inits);
    }

    public QHandlingMasterFl(Class<? extends HandlingMasterFl> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.party = inits.isInitialized("party") ? new QParty(forProperty("party"), inits.get("party")) : null;
    }

}

