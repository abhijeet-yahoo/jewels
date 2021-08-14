package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QClientStamp is a Querydsl query type for ClientStamp
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QClientStamp extends EntityPathBase<ClientStamp> {

    private static final long serialVersionUID = -1680422270L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QClientStamp clientStamp = new QClientStamp("clientStamp");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDt = createDateTime("createdDt", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Double> fromCts = createNumber("fromCts", Double.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final QParty party;

    public final QPurity purity;

    public final StringPath stampNm = createString("stampNm");

    public final NumberPath<Double> toCts = createNumber("toCts", Double.class);

    public QClientStamp(String variable) {
        this(ClientStamp.class, forVariable(variable), INITS);
    }

    public QClientStamp(Path<? extends ClientStamp> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QClientStamp(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QClientStamp(PathMetadata<?> metadata, PathInits inits) {
        this(ClientStamp.class, metadata, inits);
    }

    public QClientStamp(Class<? extends ClientStamp> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.party = inits.isInitialized("party") ? new QParty(forProperty("party"), inits.get("party")) : null;
        this.purity = inits.isInitialized("purity") ? new QPurity(forProperty("purity"), inits.get("purity")) : null;
    }

}

