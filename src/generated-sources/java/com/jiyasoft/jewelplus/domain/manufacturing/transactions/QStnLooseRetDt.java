package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QStnLooseRetDt is a Querydsl query type for StnLooseRetDt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QStnLooseRetDt extends EntityPathBase<StnLooseRetDt> {

    private static final long serialVersionUID = -2105960384L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStnLooseRetDt stnLooseRetDt = new QStnLooseRetDt("stnLooseRetDt");

    public final NumberPath<Double> amount = createNumber("amount", Double.class);

    public final NumberPath<Double> bagQty = createNumber("bagQty", Double.class);

    public final NumberPath<Double> balAmount = createNumber("balAmount", Double.class);

    public final NumberPath<Double> balCarat = createNumber("balCarat", Double.class);

    public final NumberPath<Integer> balStone = createNumber("balStone", Integer.class);

    public final NumberPath<Double> brkCarat = createNumber("brkCarat", Double.class);

    public final NumberPath<Integer> brkStone = createNumber("brkStone", Integer.class);

    public final NumberPath<Double> carat = createNumber("carat", Double.class);

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDt = createDateTime("createdDt", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment department;

    public final NumberPath<Double> diffCarat = createNumber("diffCarat", Double.class);

    public final NumberPath<Double> fallCarat = createNumber("fallCarat", Double.class);

    public final NumberPath<Integer> fallStone = createNumber("fallStone", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Double> lastUpdatedRate = createNumber("lastUpdatedRate", Double.class);

    public final NumberPath<Double> lossCarat = createNumber("lossCarat", Double.class);

    public final NumberPath<Integer> lossStone = createNumber("lossStone", Integer.class);

    public final StringPath lotNo = createString("lotNo");

    public final NumberPath<Integer> lotSrNo = createNumber("lotSrNo", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final StringPath packetNo = createString("packetNo");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QQuality quality;

    public final NumberPath<Double> rate = createNumber("rate", Double.class);

    public final NumberPath<Integer> refTranId = createNumber("refTranId", Integer.class);

    public final StringPath refTranType = createString("refTranType");

    public final StringPath remark = createString("remark");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QShape shape;

    public final StringPath sieve = createString("sieve");

    public final StringPath size = createString("size");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QSizeGroup sizeGroup;

    public final NumberPath<Integer> sordDtId = createNumber("sordDtId", Integer.class);

    public final QStnLooseRetMt stnLooseRetMt;

    public final NumberPath<Integer> stone = createNumber("stone", Integer.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QStoneType stoneType;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QSubShape subShape;

    public final NumberPath<Integer> tranSrNo = createNumber("tranSrNo", Integer.class);

    public final NumberPath<Long> uniqueId = createNumber("uniqueId", Long.class);

    public QStnLooseRetDt(String variable) {
        this(StnLooseRetDt.class, forVariable(variable), INITS);
    }

    public QStnLooseRetDt(Path<? extends StnLooseRetDt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStnLooseRetDt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStnLooseRetDt(PathMetadata<?> metadata, PathInits inits) {
        this(StnLooseRetDt.class, metadata, inits);
    }

    public QStnLooseRetDt(Class<? extends StnLooseRetDt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.department = inits.isInitialized("department") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment(forProperty("department"), inits.get("department")) : null;
        this.quality = inits.isInitialized("quality") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QQuality(forProperty("quality"), inits.get("quality")) : null;
        this.shape = inits.isInitialized("shape") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QShape(forProperty("shape")) : null;
        this.sizeGroup = inits.isInitialized("sizeGroup") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QSizeGroup(forProperty("sizeGroup"), inits.get("sizeGroup")) : null;
        this.stnLooseRetMt = inits.isInitialized("stnLooseRetMt") ? new QStnLooseRetMt(forProperty("stnLooseRetMt"), inits.get("stnLooseRetMt")) : null;
        this.stoneType = inits.isInitialized("stoneType") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QStoneType(forProperty("stoneType")) : null;
        this.subShape = inits.isInitialized("subShape") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QSubShape(forProperty("subShape"), inits.get("subShape")) : null;
    }

}

