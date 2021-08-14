package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QSettingCharge is a Querydsl query type for SettingCharge
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSettingCharge extends EntityPathBase<SettingCharge> {

    private static final long serialVersionUID = -471623058L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSettingCharge settingCharge = new QSettingCharge("settingCharge");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Double> fromWeight = createNumber("fromWeight", Double.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QMetal metal;

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final QParty party;

    public final BooleanPath qualityWiseRate = createBoolean("qualityWiseRate");

    public final NumberPath<Double> rate = createNumber("rate", Double.class);

    public final QSetting setting;

    public final QSettingType settingType;

    public final QShape shape;

    public final QStoneType stoneType;

    public final NumberPath<Double> toWeight = createNumber("toWeight", Double.class);

    public final NumberPath<Long> uniqueId = createNumber("uniqueId", Long.class);

    public QSettingCharge(String variable) {
        this(SettingCharge.class, forVariable(variable), INITS);
    }

    public QSettingCharge(Path<? extends SettingCharge> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSettingCharge(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSettingCharge(PathMetadata<?> metadata, PathInits inits) {
        this(SettingCharge.class, metadata, inits);
    }

    public QSettingCharge(Class<? extends SettingCharge> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.metal = inits.isInitialized("metal") ? new QMetal(forProperty("metal")) : null;
        this.party = inits.isInitialized("party") ? new QParty(forProperty("party"), inits.get("party")) : null;
        this.setting = inits.isInitialized("setting") ? new QSetting(forProperty("setting")) : null;
        this.settingType = inits.isInitialized("settingType") ? new QSettingType(forProperty("settingType")) : null;
        this.shape = inits.isInitialized("shape") ? new QShape(forProperty("shape")) : null;
        this.stoneType = inits.isInitialized("stoneType") ? new QStoneType(forProperty("stoneType")) : null;
    }

}

