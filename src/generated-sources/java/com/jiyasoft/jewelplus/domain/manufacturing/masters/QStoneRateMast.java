package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QStoneRateMast is a Querydsl query type for StoneRateMast
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QStoneRateMast extends EntityPathBase<StoneRateMast> {

    private static final long serialVersionUID = 493746372L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStoneRateMast stoneRateMast = new QStoneRateMast("stoneRateMast");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Double> fromWeight = createNumber("fromWeight", Double.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final QParty party;

    public final NumberPath<Double> perPcRate = createNumber("perPcRate", Double.class);

    public final QQuality quality;

    public final QShape shape;

    public final StringPath sieve = createString("sieve");

    public final StringPath size = createString("size");

    public final QSizeGroup sizeGroup;

    public final NumberPath<Double> stoneRate = createNumber("stoneRate", Double.class);

    public final QStoneType stoneType;

    public final NumberPath<Double> toWeight = createNumber("toWeight", Double.class);

    public QStoneRateMast(String variable) {
        this(StoneRateMast.class, forVariable(variable), INITS);
    }

    public QStoneRateMast(Path<? extends StoneRateMast> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStoneRateMast(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStoneRateMast(PathMetadata<?> metadata, PathInits inits) {
        this(StoneRateMast.class, metadata, inits);
    }

    public QStoneRateMast(Class<? extends StoneRateMast> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.party = inits.isInitialized("party") ? new QParty(forProperty("party"), inits.get("party")) : null;
        this.quality = inits.isInitialized("quality") ? new QQuality(forProperty("quality"), inits.get("quality")) : null;
        this.shape = inits.isInitialized("shape") ? new QShape(forProperty("shape")) : null;
        this.sizeGroup = inits.isInitialized("sizeGroup") ? new QSizeGroup(forProperty("sizeGroup"), inits.get("sizeGroup")) : null;
        this.stoneType = inits.isInitialized("stoneType") ? new QStoneType(forProperty("stoneType")) : null;
    }

}

