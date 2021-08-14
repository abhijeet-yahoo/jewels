package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QLooseStkConversion is a Querydsl query type for LooseStkConversion
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QLooseStkConversion extends EntityPathBase<LooseStkConversion> {

    private static final long serialVersionUID = 1991129026L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLooseStkConversion looseStkConversion = new QLooseStkConversion("looseStkConversion");

    public final BooleanPath adjFlg = createBoolean("adjFlg");

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

    public final NumberPath<Integer> ordStnSrNo = createNumber("ordStnSrNo", Integer.class);

    public final StringPath packetNo = createString("packetNo");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QQuality quality;

    public final NumberPath<Double> rate = createNumber("rate", Double.class);

    public final StringPath remark = createString("remark");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QShape shape;

    public final StringPath sieve = createString("sieve");

    public final StringPath size = createString("size");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QSizeGroup sizeGroup;

    public final NumberPath<Integer> sordDtId = createNumber("sordDtId", Integer.class);

    public final QStnLooseDt stnLooseDt;

    public final QStnLooseMt stnLooseMt;

    public final NumberPath<Integer> stone = createNumber("stone", Integer.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QStoneType stoneType;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QSubShape subShape;

    public final NumberPath<Integer> tranSrNo = createNumber("tranSrNo", Integer.class);

    public final NumberPath<Long> uniqueId = createNumber("uniqueId", Long.class);

    public QLooseStkConversion(String variable) {
        this(LooseStkConversion.class, forVariable(variable), INITS);
    }

    public QLooseStkConversion(Path<? extends LooseStkConversion> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QLooseStkConversion(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QLooseStkConversion(PathMetadata<?> metadata, PathInits inits) {
        this(LooseStkConversion.class, metadata, inits);
    }

    public QLooseStkConversion(Class<? extends LooseStkConversion> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.department = inits.isInitialized("department") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment(forProperty("department"), inits.get("department")) : null;
        this.quality = inits.isInitialized("quality") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QQuality(forProperty("quality"), inits.get("quality")) : null;
        this.shape = inits.isInitialized("shape") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QShape(forProperty("shape")) : null;
        this.sizeGroup = inits.isInitialized("sizeGroup") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QSizeGroup(forProperty("sizeGroup"), inits.get("sizeGroup")) : null;
        this.stnLooseDt = inits.isInitialized("stnLooseDt") ? new QStnLooseDt(forProperty("stnLooseDt"), inits.get("stnLooseDt")) : null;
        this.stnLooseMt = inits.isInitialized("stnLooseMt") ? new QStnLooseMt(forProperty("stnLooseMt"), inits.get("stnLooseMt")) : null;
        this.stoneType = inits.isInitialized("stoneType") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QStoneType(forProperty("stoneType")) : null;
        this.subShape = inits.isInitialized("subShape") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QSubShape(forProperty("subShape"), inits.get("subShape")) : null;
    }

}

