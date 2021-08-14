package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QReadyBag is a Querydsl query type for ReadyBag
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QReadyBag extends EntityPathBase<ReadyBag> {

    private static final long serialVersionUID = -1152258491L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReadyBag readyBag = new QReadyBag("readyBag");

    public final NumberPath<Double> avgRate = createNumber("avgRate", Double.class);

    public final QBagMt bagMt;

    public final NumberPath<Double> bagPcs = createNumber("bagPcs", Double.class);

    public final NumberPath<Integer> bagSrNo = createNumber("bagSrNo", Integer.class);

    public final NumberPath<Double> carat = createNumber("carat", Double.class);

    public final BooleanPath centerStone = createBoolean("centerStone");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDt = createDateTime("createdDt", java.util.Date.class);

    public final BooleanPath currentStock = createBoolean("currentStock");

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment department;

    public final StringPath diaCateg = createString("diaCateg");

    public final NumberPath<Double> factoryRate = createNumber("factoryRate", Double.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final DateTimePath<java.util.Date> issDt = createDateTime("issDt", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment location;

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QLookUpMast partNm;

    public final BooleanPath pendApprovalFlg = createBoolean("pendApprovalFlg");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QQuality quality;

    public final NumberPath<Double> rate = createNumber("rate", Double.class);

    public final NumberPath<Double> retCarat = createNumber("retCarat", Double.class);

    public final NumberPath<Integer> retStone = createNumber("retStone", Integer.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QSetting setting;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QSettingType settingType;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QShape shape;

    public final StringPath sieve = createString("sieve");

    public final StringPath size = createString("size");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QSizeGroup sizeGroup;

    public final NumberPath<Integer> stone = createNumber("stone", Integer.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QStoneType stoneType;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QSubShape subShape;

    public final DateTimePath<java.util.Date> tranDate = createDateTime("tranDate", java.util.Date.class);

    public final QTranMt tranMt;

    public final NumberPath<Double> transferRate = createNumber("transferRate", Double.class);

    public QReadyBag(String variable) {
        this(ReadyBag.class, forVariable(variable), INITS);
    }

    public QReadyBag(Path<? extends ReadyBag> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QReadyBag(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QReadyBag(PathMetadata<?> metadata, PathInits inits) {
        this(ReadyBag.class, metadata, inits);
    }

    public QReadyBag(Class<? extends ReadyBag> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.bagMt = inits.isInitialized("bagMt") ? new QBagMt(forProperty("bagMt"), inits.get("bagMt")) : null;
        this.department = inits.isInitialized("department") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment(forProperty("department"), inits.get("department")) : null;
        this.location = inits.isInitialized("location") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment(forProperty("location"), inits.get("location")) : null;
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

