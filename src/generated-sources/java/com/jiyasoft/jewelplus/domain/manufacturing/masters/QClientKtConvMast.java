package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QClientKtConvMast is a Querydsl query type for ClientKtConvMast
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QClientKtConvMast extends EntityPathBase<ClientKtConvMast> {

    private static final long serialVersionUID = -1996177037L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QClientKtConvMast clientKtConvMast = new QClientKtConvMast("clientKtConvMast");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final QParty party;

    public final QPurity purity;

    public final NumberPath<Double> purityConv = createNumber("purityConv", Double.class);

    public QClientKtConvMast(String variable) {
        this(ClientKtConvMast.class, forVariable(variable), INITS);
    }

    public QClientKtConvMast(Path<? extends ClientKtConvMast> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QClientKtConvMast(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QClientKtConvMast(PathMetadata<?> metadata, PathInits inits) {
        this(ClientKtConvMast.class, metadata, inits);
    }

    public QClientKtConvMast(Class<? extends ClientKtConvMast> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.party = inits.isInitialized("party") ? new QParty(forProperty("party"), inits.get("party")) : null;
        this.purity = inits.isInitialized("purity") ? new QPurity(forProperty("purity"), inits.get("purity")) : null;
    }

}

