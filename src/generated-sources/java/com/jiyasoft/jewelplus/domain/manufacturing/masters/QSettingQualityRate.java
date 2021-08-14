package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QSettingQualityRate is a Querydsl query type for SettingQualityRate
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSettingQualityRate extends EntityPathBase<SettingQualityRate> {

    private static final long serialVersionUID = 1059862277L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSettingQualityRate settingQualityRate = new QSettingQualityRate("settingQualityRate");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final QQuality quality;

    public final NumberPath<Double> qualityRate = createNumber("qualityRate", Double.class);

    public final QSettingCharge settingCharge;

    public QSettingQualityRate(String variable) {
        this(SettingQualityRate.class, forVariable(variable), INITS);
    }

    public QSettingQualityRate(Path<? extends SettingQualityRate> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSettingQualityRate(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSettingQualityRate(PathMetadata<?> metadata, PathInits inits) {
        this(SettingQualityRate.class, metadata, inits);
    }

    public QSettingQualityRate(Class<? extends SettingQualityRate> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.quality = inits.isInitialized("quality") ? new QQuality(forProperty("quality"), inits.get("quality")) : null;
        this.settingCharge = inits.isInitialized("settingCharge") ? new QSettingCharge(forProperty("settingCharge"), inits.get("settingCharge")) : null;
    }

}

