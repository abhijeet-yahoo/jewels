package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QDesignMetal is a Querydsl query type for DesignMetal
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QDesignMetal extends EntityPathBase<DesignMetal> {

    private static final long serialVersionUID = 1879483315L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDesignMetal designMetal = new QDesignMetal("designMetal");

    public final QColor color;

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final StringPath createdBy = createString("createdBy");

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final QDesign design;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Double> lossPerc = createNumber("lossPerc", Double.class);

    public final BooleanPath mainMetal = createBoolean("mainMetal");

    public final NumberPath<Integer> metalPcs = createNumber("metalPcs", Integer.class);

    public final NumberPath<Double> metalRate = createNumber("metalRate", Double.class);

    public final NumberPath<Double> metalValue = createNumber("metalValue", Double.class);

    public final NumberPath<Double> metalWeight = createNumber("metalWeight", Double.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final QLookUpMast partNm;

    public final NumberPath<Double> perGramRate = createNumber("perGramRate", Double.class);

    public final NumberPath<Double> perPcMetalWeight = createNumber("perPcMetalWeight", Double.class);

    public final NumberPath<Double> perPcSilverWt = createNumber("perPcSilverWt", Double.class);

    public final NumberPath<Double> perPcWaxWt = createNumber("perPcWaxWt", Double.class);

    public final QPurity purity;

    public final NumberPath<Double> rptWt = createNumber("rptWt", Double.class);

    public final NumberPath<Double> silverWt = createNumber("silverWt", Double.class);

    public final NumberPath<Double> waxWt = createNumber("waxWt", Double.class);

    public QDesignMetal(String variable) {
        this(DesignMetal.class, forVariable(variable), INITS);
    }

    public QDesignMetal(Path<? extends DesignMetal> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDesignMetal(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDesignMetal(PathMetadata<?> metadata, PathInits inits) {
        this(DesignMetal.class, metadata, inits);
    }

    public QDesignMetal(Class<? extends DesignMetal> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.color = inits.isInitialized("color") ? new QColor(forProperty("color")) : null;
        this.design = inits.isInitialized("design") ? new QDesign(forProperty("design"), inits.get("design")) : null;
        this.partNm = inits.isInitialized("partNm") ? new QLookUpMast(forProperty("partNm")) : null;
        this.purity = inits.isInitialized("purity") ? new QPurity(forProperty("purity"), inits.get("purity")) : null;
    }

}

