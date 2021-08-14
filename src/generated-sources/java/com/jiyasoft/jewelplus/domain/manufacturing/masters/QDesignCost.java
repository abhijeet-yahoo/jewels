package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QDesignCost is a Querydsl query type for DesignCost
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QDesignCost extends EntityPathBase<DesignCost> {

    private static final long serialVersionUID = -1602227807L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDesignCost designCost = new QDesignCost("designCost");

    public final NumberPath<Double> addedPerc = createNumber("addedPerc", Double.class);

    public final NumberPath<Double> compValue = createNumber("compValue", Double.class);

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final StringPath createdBy = createString("createdBy");

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final QDesign design;

    public final NumberPath<Double> dispPerc = createNumber("dispPerc", Double.class);

    public final NumberPath<Double> finalPrice = createNumber("finalPrice", Double.class);

    public final NumberPath<Double> fob = createNumber("fob", Double.class);

    public final NumberPath<Double> handlingPerc = createNumber("handlingPerc", Double.class);

    public final NumberPath<Double> handlingValue = createNumber("handlingValue", Double.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Double> labourValue = createNumber("labourValue", Double.class);

    public final NumberPath<Double> LossValue = createNumber("LossValue", Double.class);

    public final NumberPath<Double> lossWeight = createNumber("lossWeight", Double.class);

    public final NumberPath<Double> metalRate = createNumber("metalRate", Double.class);

    public final NumberPath<Double> metalValue = createNumber("metalValue", Double.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final NumberPath<Double> perGramRate = createNumber("perGramRate", Double.class);

    public final QPurity purity;

    public final NumberPath<Double> setValue = createNumber("setValue", Double.class);

    public final NumberPath<Double> stnValue = createNumber("stnValue", Double.class);

    public final NumberPath<Double> tagPerc = createNumber("tagPerc", Double.class);

    public QDesignCost(String variable) {
        this(DesignCost.class, forVariable(variable), INITS);
    }

    public QDesignCost(Path<? extends DesignCost> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDesignCost(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDesignCost(PathMetadata<?> metadata, PathInits inits) {
        this(DesignCost.class, metadata, inits);
    }

    public QDesignCost(Class<? extends DesignCost> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.design = inits.isInitialized("design") ? new QDesign(forProperty("design"), inits.get("design")) : null;
        this.purity = inits.isInitialized("purity") ? new QPurity(forProperty("purity"), inits.get("purity")) : null;
    }

}

