package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QStoneTran is a Querydsl query type for StoneTran
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QStoneTran extends EntityPathBase<StoneTran> {

    private static final long serialVersionUID = 1308112464L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStoneTran stoneTran = new QStoneTran("stoneTran");

    public final NumberPath<Double> avgRate = createNumber("avgRate", Double.class);

    public final QBagMt bagMt;

    public final NumberPath<Double> bagQty = createNumber("bagQty", Double.class);

    public final NumberPath<Integer> bagSrNo = createNumber("bagSrNo", Integer.class);

    public final NumberPath<Double> balCarat = createNumber("balCarat", Double.class);

    public final NumberPath<Integer> balStone = createNumber("balStone", Integer.class);

    public final NumberPath<Double> carat = createNumber("carat", Double.class);

    public final BooleanPath centerStone = createBoolean("centerStone");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDt = createDateTime("createdDt", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment department;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment deptLocation;

    public final StringPath diaCateg = createString("diaCateg");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QEmployee employee;

    public final NumberPath<Double> factoryRate = createNumber("factoryRate", Double.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath inOutFld = createString("inOutFld");

    public final StringPath lotNo = createString("lotNo");

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final StringPath packetNo = createString("packetNo");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QLookUpMast partNm;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QParty party;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QQuality quality;

    public final NumberPath<Double> rate = createNumber("rate", Double.class);

    public final NumberPath<Integer> refTranId = createNumber("refTranId", Integer.class);

    public final StringPath remark = createString("remark");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QSetting setting;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QSettingType settingType;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QShape shape;

    public final StringPath sieve = createString("sieve");

    public final StringPath size = createString("size");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QSizeGroup sizeGroup;

    public final NumberPath<Integer> sordDtId = createNumber("sordDtId", Integer.class);

    public final NumberPath<Integer> sordMtId = createNumber("sordMtId", Integer.class);

    public final NumberPath<Integer> stnRecDtId = createNumber("stnRecDtId", Integer.class);

    public final NumberPath<Integer> stone = createNumber("stone", Integer.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QStoneType stoneType;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QSubShape subShape;

    public final DateTimePath<java.util.Date> tranDate = createDateTime("tranDate", java.util.Date.class);

    public final NumberPath<Integer> tranMtId = createNumber("tranMtId", Integer.class);

    public final NumberPath<Double> transferRate = createNumber("transferRate", Double.class);

    public final NumberPath<Integer> tranSrNo = createNumber("tranSrNo", Integer.class);

    public final StringPath tranType = createString("tranType");

    public QStoneTran(String variable) {
        this(StoneTran.class, forVariable(variable), INITS);
    }

    public QStoneTran(Path<? extends StoneTran> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStoneTran(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStoneTran(PathMetadata<?> metadata, PathInits inits) {
        this(StoneTran.class, metadata, inits);
    }

    public QStoneTran(Class<? extends StoneTran> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.bagMt = inits.isInitialized("bagMt") ? new QBagMt(forProperty("bagMt"), inits.get("bagMt")) : null;
        this.department = inits.isInitialized("department") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment(forProperty("department"), inits.get("department")) : null;
        this.deptLocation = inits.isInitialized("deptLocation") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment(forProperty("deptLocation"), inits.get("deptLocation")) : null;
        this.employee = inits.isInitialized("employee") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QEmployee(forProperty("employee"), inits.get("employee")) : null;
        this.partNm = inits.isInitialized("partNm") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QLookUpMast(forProperty("partNm")) : null;
        this.party = inits.isInitialized("party") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QParty(forProperty("party"), inits.get("party")) : null;
        this.quality = inits.isInitialized("quality") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QQuality(forProperty("quality"), inits.get("quality")) : null;
        this.setting = inits.isInitialized("setting") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QSetting(forProperty("setting")) : null;
        this.settingType = inits.isInitialized("settingType") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QSettingType(forProperty("settingType")) : null;
        this.shape = inits.isInitialized("shape") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QShape(forProperty("shape")) : null;
        this.sizeGroup = inits.isInitialized("sizeGroup") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QSizeGroup(forProperty("sizeGroup"), inits.get("sizeGroup")) : null;
        this.stoneType = inits.isInitialized("stoneType") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QStoneType(forProperty("stoneType")) : null;
        this.subShape = inits.isInitialized("subShape") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QSubShape(forProperty("subShape"), inits.get("subShape")) : null;
    }

}

