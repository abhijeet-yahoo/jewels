package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QVAddStnAdj is a Querydsl query type for VAddStnAdj
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QVAddStnAdj extends EntityPathBase<VAddStnAdj> {

    private static final long serialVersionUID = 531035717L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVAddStnAdj vAddStnAdj = new QVAddStnAdj("vAddStnAdj");

    public final NumberPath<Double> adjustmentWt = createNumber("adjustmentWt", Double.class);

    public final NumberPath<Double> carat = createNumber("carat", Double.class);

    public final QCostingMt costingMt;

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDt = createDateTime("createdDt", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QQuality quality;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QShape shape;

    public final StringPath size = createString("size");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QSizeGroup sizeGroup;

    public final NumberPath<Double> stnRate = createNumber("stnRate", Double.class);

    public final NumberPath<Integer> stone = createNumber("stone", Integer.class);

    public final QStonePurchaseDt stonePurchaseDt;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QStoneType stoneType;

    public QVAddStnAdj(String variable) {
        this(VAddStnAdj.class, forVariable(variable), INITS);
    }

    public QVAddStnAdj(Path<? extends VAddStnAdj> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QVAddStnAdj(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QVAddStnAdj(PathMetadata<?> metadata, PathInits inits) {
        this(VAddStnAdj.class, metadata, inits);
    }

    public QVAddStnAdj(Class<? extends VAddStnAdj> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.costingMt = inits.isInitialized("costingMt") ? new QCostingMt(forProperty("costingMt"), inits.get("costingMt")) : null;
        this.quality = inits.isInitialized("quality") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QQuality(forProperty("quality"), inits.get("quality")) : null;
        this.shape = inits.isInitialized("shape") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QShape(forProperty("shape")) : null;
        this.sizeGroup = inits.isInitialized("sizeGroup") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QSizeGroup(forProperty("sizeGroup"), inits.get("sizeGroup")) : null;
        this.stonePurchaseDt = inits.isInitialized("stonePurchaseDt") ? new QStonePurchaseDt(forProperty("stonePurchaseDt"), inits.get("stonePurchaseDt")) : null;
        this.stoneType = inits.isInitialized("stoneType") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QStoneType(forProperty("stoneType")) : null;
    }

}

