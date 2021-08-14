package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QOrderStnDt is a Querydsl query type for OrderStnDt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QOrderStnDt extends EntityPathBase<OrderStnDt> {

    private static final long serialVersionUID = -826936347L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderStnDt orderStnDt = new QOrderStnDt("orderStnDt");

    public final StringPath breadth = createString("breadth");

    public final NumberPath<Double> carat = createNumber("carat", Double.class);

    public final BooleanPath centerStone = createBoolean("centerStone");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final StringPath diaCateg = createString("diaCateg");

    public final NumberPath<Double> handlingRate = createNumber("handlingRate", Double.class);

    public final NumberPath<Double> handlingValue = createNumber("handlingValue", Double.class);

    public final BooleanPath hdlgPerCarat = createBoolean("hdlgPerCarat");

    public final BooleanPath hdlgPercentWise = createBoolean("hdlgPercentWise");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final QOrderDt orderDt;

    public final QOrderMt orderMt;

    public final QLookUpMast partNm;

    public final BooleanPath perPcsRateFlg = createBoolean("perPcsRateFlg");

    public final QQuality quality;

    public final BooleanPath rLock = createBoolean("rLock");

    public final NumberPath<Integer> seqNo = createNumber("seqNo", Integer.class);

    public final NumberPath<Double> setRate = createNumber("setRate", Double.class);

    public final QSetting setting;

    public final QSettingType settingType;

    public final NumberPath<Double> setValue = createNumber("setValue", Double.class);

    public final QShape shape;

    public final StringPath sieve = createString("sieve");

    public final StringPath size = createString("size");

    public final QSizeGroup sizeGroup;

    public final NumberPath<Integer> srNo = createNumber("srNo", Integer.class);

    public final NumberPath<Double> stnRate = createNumber("stnRate", Double.class);

    public final NumberPath<Integer> stone = createNumber("stone", Integer.class);

    public final QStoneType stoneType;

    public final NumberPath<Double> stoneValue = createNumber("stoneValue", Double.class);

    public final QSubShape subShape;

    public QOrderStnDt(String variable) {
        this(OrderStnDt.class, forVariable(variable), INITS);
    }

    public QOrderStnDt(Path<? extends OrderStnDt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrderStnDt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrderStnDt(PathMetadata<?> metadata, PathInits inits) {
        this(OrderStnDt.class, metadata, inits);
    }

    public QOrderStnDt(Class<? extends OrderStnDt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.orderDt = inits.isInitialized("orderDt") ? new QOrderDt(forProperty("orderDt"), inits.get("orderDt")) : null;
        this.orderMt = inits.isInitialized("orderMt") ? new QOrderMt(forProperty("orderMt"), inits.get("orderMt")) : null;
        this.partNm = inits.isInitialized("partNm") ? new QLookUpMast(forProperty("partNm")) : null;
        this.quality = inits.isInitialized("quality") ? new QQuality(forProperty("quality"), inits.get("quality")) : null;
        this.setting = inits.isInitialized("setting") ? new QSetting(forProperty("setting")) : null;
        this.settingType = inits.isInitialized("settingType") ? new QSettingType(forProperty("settingType")) : null;
        this.shape = inits.isInitialized("shape") ? new QShape(forProperty("shape")) : null;
        this.sizeGroup = inits.isInitialized("sizeGroup") ? new QSizeGroup(forProperty("sizeGroup"), inits.get("sizeGroup")) : null;
        this.stoneType = inits.isInitialized("stoneType") ? new QStoneType(forProperty("stoneType")) : null;
        this.subShape = inits.isInitialized("subShape") ? new QSubShape(forProperty("subShape"), inits.get("subShape")) : null;
    }

}

