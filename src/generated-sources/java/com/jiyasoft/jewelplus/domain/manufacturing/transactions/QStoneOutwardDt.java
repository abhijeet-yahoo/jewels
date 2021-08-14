package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QStoneOutwardDt is a Querydsl query type for StoneOutwardDt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QStoneOutwardDt extends EntityPathBase<StoneOutwardDt> {

    private static final long serialVersionUID = -1349987083L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStoneOutwardDt stoneOutwardDt = new QStoneOutwardDt("stoneOutwardDt");

    public final NumberPath<Double> amount = createNumber("amount", Double.class);

    public final NumberPath<Double> bagQty = createNumber("bagQty", Double.class);

    public final NumberPath<Double> balCarat = createNumber("balCarat", Double.class);

    public final NumberPath<Integer> balStone = createNumber("balStone", Integer.class);

    public final NumberPath<Double> brkCarat = createNumber("brkCarat", Double.class);

    public final NumberPath<Integer> brkStone = createNumber("brkStone", Integer.class);

    public final NumberPath<Double> carat = createNumber("carat", Double.class);

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDt = createDateTime("createdDt", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment department;

    public final NumberPath<Double> diffCarat = createNumber("diffCarat", Double.class);

    public final NumberPath<Double> fallCarat = createNumber("fallCarat", Double.class);

    public final NumberPath<Integer> fallStone = createNumber("fallStone", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final NumberPath<Integer> ordStnSrNo = createNumber("ordStnSrNo", Integer.class);

    public final StringPath packetNo = createString("packetNo");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QQuality quality;

    public final NumberPath<Double> rate = createNumber("rate", Double.class);

    public final NumberPath<Double> rate1 = createNumber("rate1", Double.class);

    public final StringPath remark = createString("remark");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QShape shape;

    public final StringPath sieve = createString("sieve");

    public final StringPath size = createString("size");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QSizeGroup sizeGroup;

    public final StringPath sizeGroupStr = createString("sizeGroupStr");

    public final NumberPath<Integer> sordDtId = createNumber("sordDtId", Integer.class);

    public final NumberPath<Integer> stone = createNumber("stone", Integer.class);

    public final QStoneOutwardMt stoneOutwardMt;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QStoneType stoneType;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QSubShape subShape;

    public final NumberPath<Integer> tranSrNo = createNumber("tranSrNo", Integer.class);

    public final NumberPath<Long> uniqueId = createNumber("uniqueId", Long.class);

    public QStoneOutwardDt(String variable) {
        this(StoneOutwardDt.class, forVariable(variable), INITS);
    }

    public QStoneOutwardDt(Path<? extends StoneOutwardDt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStoneOutwardDt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStoneOutwardDt(PathMetadata<?> metadata, PathInits inits) {
        this(StoneOutwardDt.class, metadata, inits);
    }

    public QStoneOutwardDt(Class<? extends StoneOutwardDt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.department = inits.isInitialized("department") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment(forProperty("department"), inits.get("department")) : null;
        this.quality = inits.isInitialized("quality") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QQuality(forProperty("quality"), inits.get("quality")) : null;
        this.shape = inits.isInitialized("shape") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QShape(forProperty("shape")) : null;
        this.sizeGroup = inits.isInitialized("sizeGroup") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QSizeGroup(forProperty("sizeGroup"), inits.get("sizeGroup")) : null;
        this.stoneOutwardMt = inits.isInitialized("stoneOutwardMt") ? new QStoneOutwardMt(forProperty("stoneOutwardMt"), inits.get("stoneOutwardMt")) : null;
        this.stoneType = inits.isInitialized("stoneType") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QStoneType(forProperty("stoneType")) : null;
        this.subShape = inits.isInitialized("subShape") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QSubShape(forProperty("subShape"), inits.get("subShape")) : null;
    }

}

