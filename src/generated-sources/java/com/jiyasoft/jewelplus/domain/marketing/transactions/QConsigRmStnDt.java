package com.jiyasoft.jewelplus.domain.marketing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QConsigRmStnDt is a Querydsl query type for ConsigRmStnDt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QConsigRmStnDt extends EntityPathBase<ConsigRmStnDt> {

    private static final long serialVersionUID = -2038691125L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QConsigRmStnDt consigRmStnDt = new QConsigRmStnDt("consigRmStnDt");

    public final NumberPath<Double> adjWt = createNumber("adjWt", Double.class);

    public final NumberPath<Double> bagQty = createNumber("bagQty", Double.class);

    public final NumberPath<Double> balCarat = createNumber("balCarat", Double.class);

    public final NumberPath<Integer> balStone = createNumber("balStone", Integer.class);

    public final NumberPath<Double> brkCarat = createNumber("brkCarat", Double.class);

    public final NumberPath<Integer> brkStone = createNumber("brkStone", Integer.class);

    public final NumberPath<Double> carat = createNumber("carat", Double.class);

    public final QConsigMt consigMt;

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDt = createDateTime("createdDt", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment department;

    public final NumberPath<Double> diffCarat = createNumber("diffCarat", Double.class);

    public final NumberPath<Double> fallCarat = createNumber("fallCarat", Double.class);

    public final NumberPath<Integer> fallStone = createNumber("fallStone", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final StringPath packetNo = createString("packetNo");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QQuality quality;

    public final StringPath remark = createString("remark");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QShape shape;

    public final StringPath sieve = createString("sieve");

    public final StringPath size = createString("size");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QSizeGroup sizeGroup;

    public final NumberPath<Integer> sordDtId = createNumber("sordDtId", Integer.class);

    public final NumberPath<Double> stnAmount = createNumber("stnAmount", Double.class);

    public final NumberPath<Double> stnRate = createNumber("stnRate", Double.class);

    public final NumberPath<Double> stnRate1 = createNumber("stnRate1", Double.class);

    public final NumberPath<Integer> stone = createNumber("stone", Integer.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QStoneType stoneType;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QSubShape subShape;

    public final NumberPath<Long> uniqueId = createNumber("uniqueId", Long.class);

    public QConsigRmStnDt(String variable) {
        this(ConsigRmStnDt.class, forVariable(variable), INITS);
    }

    public QConsigRmStnDt(Path<? extends ConsigRmStnDt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QConsigRmStnDt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QConsigRmStnDt(PathMetadata<?> metadata, PathInits inits) {
        this(ConsigRmStnDt.class, metadata, inits);
    }

    public QConsigRmStnDt(Class<? extends ConsigRmStnDt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.consigMt = inits.isInitialized("consigMt") ? new QConsigMt(forProperty("consigMt"), inits.get("consigMt")) : null;
        this.department = inits.isInitialized("department") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment(forProperty("department"), inits.get("department")) : null;
        this.quality = inits.isInitialized("quality") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QQuality(forProperty("quality"), inits.get("quality")) : null;
        this.shape = inits.isInitialized("shape") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QShape(forProperty("shape")) : null;
        this.sizeGroup = inits.isInitialized("sizeGroup") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QSizeGroup(forProperty("sizeGroup"), inits.get("sizeGroup")) : null;
        this.stoneType = inits.isInitialized("stoneType") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QStoneType(forProperty("stoneType")) : null;
        this.subShape = inits.isInitialized("subShape") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QSubShape(forProperty("subShape"), inits.get("subShape")) : null;
    }

}

