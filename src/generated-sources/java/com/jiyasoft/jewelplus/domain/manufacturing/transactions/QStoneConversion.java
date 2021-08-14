package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QStoneConversion is a Querydsl query type for StoneConversion
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QStoneConversion extends EntityPathBase<StoneConversion> {

    private static final long serialVersionUID = -1221371781L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStoneConversion stoneConversion = new QStoneConversion("stoneConversion");

    public final NumberPath<Double> carat = createNumber("carat", Double.class);

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDt = createDateTime("createdDt", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final DateTimePath<java.util.Date> invDate = createDateTime("invDate", java.util.Date.class);

    public final StringPath invNo = createString("invNo");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QQuality issQuality;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QShape issShape;

    public final StringPath issSize = createString("issSize");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QQuality quality;

    public final NumberPath<Integer> refTranId = createNumber("refTranId", Integer.class);

    public final StringPath refTranType = createString("refTranType");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QShape shape;

    public final StringPath sieve = createString("sieve");

    public final StringPath size = createString("size");

    public final NumberPath<Integer> srNo = createNumber("srNo", Integer.class);

    public final NumberPath<Integer> stone = createNumber("stone", Integer.class);

    public final NumberPath<Double> stoneRate = createNumber("stoneRate", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QStoneType stoneType;

    public QStoneConversion(String variable) {
        this(StoneConversion.class, forVariable(variable), INITS);
    }

    public QStoneConversion(Path<? extends StoneConversion> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStoneConversion(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStoneConversion(PathMetadata<?> metadata, PathInits inits) {
        this(StoneConversion.class, metadata, inits);
    }

    public QStoneConversion(Class<? extends StoneConversion> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.issQuality = inits.isInitialized("issQuality") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QQuality(forProperty("issQuality"), inits.get("issQuality")) : null;
        this.issShape = inits.isInitialized("issShape") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QShape(forProperty("issShape")) : null;
        this.quality = inits.isInitialized("quality") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QQuality(forProperty("quality"), inits.get("quality")) : null;
        this.shape = inits.isInitialized("shape") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QShape(forProperty("shape")) : null;
        this.stoneType = inits.isInitialized("stoneType") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QStoneType(forProperty("stoneType")) : null;
    }

}

