package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QDesignCostLabour is a Querydsl query type for DesignCostLabour
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QDesignCostLabour extends EntityPathBase<DesignCostLabour> {

    private static final long serialVersionUID = -1895307360L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDesignCostLabour designCostLabour = new QDesignCostLabour("designCostLabour");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final QDesign design;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Double> labourRate = createNumber("labourRate", Double.class);

    public final QLabourType labourType;

    public final NumberPath<Double> labourValue = createNumber("labourValue", Double.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public QDesignCostLabour(String variable) {
        this(DesignCostLabour.class, forVariable(variable), INITS);
    }

    public QDesignCostLabour(Path<? extends DesignCostLabour> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDesignCostLabour(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDesignCostLabour(PathMetadata<?> metadata, PathInits inits) {
        this(DesignCostLabour.class, metadata, inits);
    }

    public QDesignCostLabour(Class<? extends DesignCostLabour> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.design = inits.isInitialized("design") ? new QDesign(forProperty("design"), inits.get("design")) : null;
        this.labourType = inits.isInitialized("labourType") ? new QLabourType(forProperty("labourType")) : null;
    }

}

