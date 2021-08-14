package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QDesignStone is a Querydsl query type for DesignStone
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QDesignStone extends EntityPathBase<DesignStone> {

    private static final long serialVersionUID = 1885466897L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDesignStone designStone = new QDesignStone("designStone");

    public final StringPath breadth = createString("breadth");

    public final NumberPath<Double> carat = createNumber("carat", Double.class);

    public final BooleanPath centerStone = createBoolean("centerStone");

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final StringPath createdBy = createString("createdBy");

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final QDesign design;

    public final StringPath diaCateg = createString("diaCateg");

    public final NumberPath<Double> handlingRate = createNumber("handlingRate", Double.class);

    public final NumberPath<Double> handlingValue = createNumber("handlingValue", Double.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Double> mcarat = createNumber("mcarat", Double.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final QLookUpMast partNm;

    public final QQuality quality;

    public final NumberPath<Double> setRate = createNumber("setRate", Double.class);

    public final QSetting setting;

    public final QSettingType settingType;

    public final NumberPath<Double> setValue = createNumber("setValue", Double.class);

    public final QShape shape;

    public final StringPath sieve = createString("sieve");

    public final StringPath size = createString("size");

    public final QSizeGroup sizeGroup;

    public final StringPath sizeGroupStr = createString("sizeGroupStr");

    public final NumberPath<Double> stnRate = createNumber("stnRate", Double.class);

    public final NumberPath<Integer> stone = createNumber("stone", Integer.class);

    public final QStoneType stoneType;

    public final NumberPath<Double> stoneValue = createNumber("stoneValue", Double.class);

    public final QSubShape subShape;

    public QDesignStone(String variable) {
        this(DesignStone.class, forVariable(variable), INITS);
    }

    public QDesignStone(Path<? extends DesignStone> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDesignStone(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDesignStone(PathMetadata<?> metadata, PathInits inits) {
        this(DesignStone.class, metadata, inits);
    }

    public QDesignStone(Class<? extends DesignStone> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.design = inits.isInitialized("design") ? new QDesign(forProperty("design"), inits.get("design")) : null;
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

