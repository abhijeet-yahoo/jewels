package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QTranDt is a Querydsl query type for TranDt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTranDt extends EntityPathBase<TranDt> {

    private static final long serialVersionUID = -1938628613L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTranDt tranDt = new QTranDt("tranDt");

    public final NumberPath<Double> avgRate = createNumber("avgRate", Double.class);

    public final QBagMt bagMt;

    public final NumberPath<Integer> bagSrNo = createNumber("bagSrNo", Integer.class);

    public final NumberPath<Double> carat = createNumber("carat", Double.class);

    public final BooleanPath centerStone = createBoolean("centerStone");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath currStk = createBoolean("currStk");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment department;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment deptFrom;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment deptTo;

    public final NumberPath<Double> factoryRate = createNumber("factoryRate", Double.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final DateTimePath<java.util.Date> issDate = createDateTime("issDate", java.util.Date.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QLookUpMast partNm;

    public final NumberPath<Double> pcs = createNumber("pcs", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QQuality quality;

    public final NumberPath<Double> rate = createNumber("rate", Double.class);

    public final NumberPath<Integer> refDtId = createNumber("refDtId", Integer.class);

    public final NumberPath<Integer> refTranDtId = createNumber("refTranDtId", Integer.class);

    public final StringPath refTranType = createString("refTranType");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QSetting setting;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QSettingType settingType;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QShape shape;

    public final StringPath sieve = createString("sieve");

    public final StringPath size = createString("size");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QSizeGroup sizeGroup;

    public final NumberPath<Double> splitCarat = createNumber("splitCarat", Double.class);

    public final NumberPath<Double> splitQty = createNumber("splitQty", Double.class);

    public final NumberPath<Integer> splitStone = createNumber("splitStone", Integer.class);

    public final NumberPath<Integer> stone = createNumber("stone", Integer.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QStoneType stoneType;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QSubShape subShape;

    public final TimePath<java.sql.Time> time = createTime("time", java.sql.Time.class);

    public final DateTimePath<java.util.Date> tranDate = createDateTime("tranDate", java.util.Date.class);

    public final QTranMt tranMt;

    public final NumberPath<Double> transferRate = createNumber("transferRate", Double.class);

    public QTranDt(String variable) {
        this(TranDt.class, forVariable(variable), INITS);
    }

    public QTranDt(Path<? extends TranDt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTranDt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTranDt(PathMetadata<?> metadata, PathInits inits) {
        this(TranDt.class, metadata, inits);
    }

    public QTranDt(Class<? extends TranDt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.bagMt = inits.isInitialized("bagMt") ? new QBagMt(forProperty("bagMt"), inits.get("bagMt")) : null;
        this.department = inits.isInitialized("department") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment(forProperty("department"), inits.get("department")) : null;
        this.deptFrom = inits.isInitialized("deptFrom") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment(forProperty("deptFrom"), inits.get("deptFrom")) : null;
        this.deptTo = inits.isInitialized("deptTo") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment(forProperty("deptTo"), inits.get("deptTo")) : null;
        this.partNm = inits.isInitialized("partNm") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QLookUpMast(forProperty("partNm")) : null;
        this.quality = inits.isInitialized("quality") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QQuality(forProperty("quality"), inits.get("quality")) : null;
        this.setting = inits.isInitialized("setting") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QSetting(forProperty("setting")) : null;
        this.settingType = inits.isInitialized("settingType") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QSettingType(forProperty("settingType")) : null;
        this.shape = inits.isInitialized("shape") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QShape(forProperty("shape")) : null;
        this.sizeGroup = inits.isInitialized("sizeGroup") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QSizeGroup(forProperty("sizeGroup"), inits.get("sizeGroup")) : null;
        this.stoneType = inits.isInitialized("stoneType") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QStoneType(forProperty("stoneType")) : null;
        this.subShape = inits.isInitialized("subShape") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QSubShape(forProperty("subShape"), inits.get("subShape")) : null;
        this.tranMt = inits.isInitialized("tranMt") ? new QTranMt(forProperty("tranMt"), inits.get("tranMt")) : null;
    }

}

