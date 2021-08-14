package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QQuotStnDt is a Querydsl query type for QuotStnDt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QQuotStnDt extends EntityPathBase<QuotStnDt> {

    private static final long serialVersionUID = -1768002764L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQuotStnDt quotStnDt = new QQuotStnDt("quotStnDt");

    public final NumberPath<Integer> bagSrNo = createNumber("bagSrNo", Integer.class);

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

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QLookUpMast partNm;

    public final BooleanPath perPcsRateFlg = createBoolean("perPcsRateFlg");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QQuality quality;

    public final QQuotDt quotDt;

    public final QQuotMt quotMt;

    public final BooleanPath rLock = createBoolean("rLock");

    public final NumberPath<Integer> seqNo = createNumber("seqNo", Integer.class);

    public final NumberPath<Double> setRate = createNumber("setRate", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QSetting setting;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QSettingType settingType;

    public final NumberPath<Double> setValue = createNumber("setValue", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QShape shape;

    public final StringPath sieve = createString("sieve");

    public final StringPath size = createString("size");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QSizeGroup sizeGroup;

    public final NumberPath<Double> stnRate = createNumber("stnRate", Double.class);

    public final NumberPath<Integer> stone = createNumber("stone", Integer.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QStoneType stoneType;

    public final NumberPath<Double> stoneValue = createNumber("stoneValue", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QSubShape subShape;

    public final NumberPath<Double> tagWt = createNumber("tagWt", Double.class);

    public QQuotStnDt(String variable) {
        this(QuotStnDt.class, forVariable(variable), INITS);
    }

    public QQuotStnDt(Path<? extends QuotStnDt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QQuotStnDt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QQuotStnDt(PathMetadata<?> metadata, PathInits inits) {
        this(QuotStnDt.class, metadata, inits);
    }

    public QQuotStnDt(Class<? extends QuotStnDt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.partNm = inits.isInitialized("partNm") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QLookUpMast(forProperty("partNm")) : null;
        this.quality = inits.isInitialized("quality") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QQuality(forProperty("quality"), inits.get("quality")) : null;
        this.quotDt = inits.isInitialized("quotDt") ? new QQuotDt(forProperty("quotDt"), inits.get("quotDt")) : null;
        this.quotMt = inits.isInitialized("quotMt") ? new QQuotMt(forProperty("quotMt"), inits.get("quotMt")) : null;
        this.setting = inits.isInitialized("setting") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QSetting(forProperty("setting")) : null;
        this.settingType = inits.isInitialized("settingType") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QSettingType(forProperty("settingType")) : null;
        this.shape = inits.isInitialized("shape") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QShape(forProperty("shape")) : null;
        this.sizeGroup = inits.isInitialized("sizeGroup") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QSizeGroup(forProperty("sizeGroup"), inits.get("sizeGroup")) : null;
        this.stoneType = inits.isInitialized("stoneType") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QStoneType(forProperty("stoneType")) : null;
        this.subShape = inits.isInitialized("subShape") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QSubShape(forProperty("subShape"), inits.get("subShape")) : null;
    }

}

