package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QCostJobStnDt is a Querydsl query type for CostJobStnDt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCostJobStnDt extends EntityPathBase<CostJobStnDt> {

    private static final long serialVersionUID = -765394003L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCostJobStnDt costJobStnDt = new QCostJobStnDt("costJobStnDt");

    public final NumberPath<Integer> bagSrNo = createNumber("bagSrNo", Integer.class);

    public final NumberPath<Double> carat = createNumber("carat", Double.class);

    public final NumberPath<Double> caratRate = createNumber("caratRate", Double.class);

    public final BooleanPath centerStone = createBoolean("centerStone");

    public final QCostingJobDt costingJobDt;

    public final QCostingJobMt costingJobMt;

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Double> handlingRate = createNumber("handlingRate", Double.class);

    public final NumberPath<Double> handlingValue = createNumber("handlingValue", Double.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath itemNo = createString("itemNo");

    public final NumberPath<Double> manualCaratRate = createNumber("manualCaratRate", Double.class);

    public final NumberPath<Double> manualSetRate = createNumber("manualSetRate", Double.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QOrderDt orderDt;

    public final NumberPath<Double> perStoneWt = createNumber("perStoneWt", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QQuality quality;

    public final StringPath qualityNm = createString("qualityNm");

    public final BooleanPath rLock = createBoolean("rLock");

    public final NumberPath<Integer> seqNo = createNumber("seqNo", Integer.class);

    public final NumberPath<Double> setRate = createNumber("setRate", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QSetting setting;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QSettingType settingType;

    public final NumberPath<Double> setValue = createNumber("setValue", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QShape shape;

    public final StringPath shapeNm = createString("shapeNm");

    public final StringPath sieve = createString("sieve");

    public final StringPath size = createString("size");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QSizeGroup sizeGroup;

    public final StringPath sizeGroupNm = createString("sizeGroupNm");

    public final NumberPath<Double> stnRate = createNumber("stnRate", Double.class);

    public final NumberPath<Integer> stone = createNumber("stone", Integer.class);

    public final QStoneInwardDt stoneInwardDt;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QStoneType stoneType;

    public final NumberPath<Double> stoneValue = createNumber("stoneValue", Double.class);

    public final NumberPath<Double> tagWt = createNumber("tagWt", Double.class);

    public QCostJobStnDt(String variable) {
        this(CostJobStnDt.class, forVariable(variable), INITS);
    }

    public QCostJobStnDt(Path<? extends CostJobStnDt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCostJobStnDt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCostJobStnDt(PathMetadata<?> metadata, PathInits inits) {
        this(CostJobStnDt.class, metadata, inits);
    }

    public QCostJobStnDt(Class<? extends CostJobStnDt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.costingJobDt = inits.isInitialized("costingJobDt") ? new QCostingJobDt(forProperty("costingJobDt"), inits.get("costingJobDt")) : null;
        this.costingJobMt = inits.isInitialized("costingJobMt") ? new QCostingJobMt(forProperty("costingJobMt"), inits.get("costingJobMt")) : null;
        this.orderDt = inits.isInitialized("orderDt") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QOrderDt(forProperty("orderDt"), inits.get("orderDt")) : null;
        this.quality = inits.isInitialized("quality") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QQuality(forProperty("quality"), inits.get("quality")) : null;
        this.setting = inits.isInitialized("setting") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QSetting(forProperty("setting")) : null;
        this.settingType = inits.isInitialized("settingType") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QSettingType(forProperty("settingType")) : null;
        this.shape = inits.isInitialized("shape") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QShape(forProperty("shape")) : null;
        this.sizeGroup = inits.isInitialized("sizeGroup") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QSizeGroup(forProperty("sizeGroup"), inits.get("sizeGroup")) : null;
        this.stoneInwardDt = inits.isInitialized("stoneInwardDt") ? new QStoneInwardDt(forProperty("stoneInwardDt"), inits.get("stoneInwardDt")) : null;
        this.stoneType = inits.isInitialized("stoneType") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QStoneType(forProperty("stoneType")) : null;
    }

}

