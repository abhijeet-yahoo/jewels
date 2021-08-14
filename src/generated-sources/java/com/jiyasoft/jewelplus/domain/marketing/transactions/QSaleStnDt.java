package com.jiyasoft.jewelplus.domain.marketing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QSaleStnDt is a Querydsl query type for SaleStnDt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSaleStnDt extends EntityPathBase<SaleStnDt> {

    private static final long serialVersionUID = -599763122L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSaleStnDt saleStnDt = new QSaleStnDt("saleStnDt");

    public final StringPath breadth = createString("breadth");

    public final NumberPath<Double> carat = createNumber("carat", Double.class);

    public final BooleanPath centerStone = createBoolean("centerStone");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

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

    public final BooleanPath rLock = createBoolean("rLock");

    public final QSaleDt saleDt;

    public final QSaleMt saleMt;

    public final NumberPath<Integer> seqNo = createNumber("seqNo", Integer.class);

    public final NumberPath<Double> setRate = createNumber("setRate", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QSetting setting;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QSettingType settingType;

    public final NumberPath<Double> setValue = createNumber("setValue", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QShape shape;

    public final StringPath sieve = createString("sieve");

    public final StringPath size = createString("size");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QSizeGroup sizeGroup;

    public final NumberPath<Integer> srNo = createNumber("srNo", Integer.class);

    public final NumberPath<Integer> stone = createNumber("stone", Integer.class);

    public final NumberPath<Double> stoneRate = createNumber("stoneRate", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QStoneType stoneType;

    public final NumberPath<Double> stoneValue = createNumber("stoneValue", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QSubShape subShape;

    public QSaleStnDt(String variable) {
        this(SaleStnDt.class, forVariable(variable), INITS);
    }

    public QSaleStnDt(Path<? extends SaleStnDt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSaleStnDt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSaleStnDt(PathMetadata<?> metadata, PathInits inits) {
        this(SaleStnDt.class, metadata, inits);
    }

    public QSaleStnDt(Class<? extends SaleStnDt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.partNm = inits.isInitialized("partNm") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QLookUpMast(forProperty("partNm")) : null;
        this.quality = inits.isInitialized("quality") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QQuality(forProperty("quality"), inits.get("quality")) : null;
        this.saleDt = inits.isInitialized("saleDt") ? new QSaleDt(forProperty("saleDt"), inits.get("saleDt")) : null;
        this.saleMt = inits.isInitialized("saleMt") ? new QSaleMt(forProperty("saleMt"), inits.get("saleMt")) : null;
        this.setting = inits.isInitialized("setting") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QSetting(forProperty("setting")) : null;
        this.settingType = inits.isInitialized("settingType") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QSettingType(forProperty("settingType")) : null;
        this.shape = inits.isInitialized("shape") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QShape(forProperty("shape")) : null;
        this.sizeGroup = inits.isInitialized("sizeGroup") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QSizeGroup(forProperty("sizeGroup"), inits.get("sizeGroup")) : null;
        this.stoneType = inits.isInitialized("stoneType") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QStoneType(forProperty("stoneType")) : null;
        this.subShape = inits.isInitialized("subShape") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QSubShape(forProperty("subShape"), inits.get("subShape")) : null;
    }

}

