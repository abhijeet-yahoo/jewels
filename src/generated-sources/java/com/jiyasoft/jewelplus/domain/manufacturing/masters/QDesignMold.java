package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QDesignMold is a Querydsl query type for DesignMold
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QDesignMold extends EntityPathBase<DesignMold> {

    private static final long serialVersionUID = -1601930130L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDesignMold designMold = new QDesignMold("designMold");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final QDesign design;

    public final QDesignMoldType designMoldType;

    public final StringPath drawerNo = createString("drawerNo");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final NumberPath<Double> mouldQty = createNumber("mouldQty", Double.class);

    public final StringPath rackNo = createString("rackNo");

    public QDesignMold(String variable) {
        this(DesignMold.class, forVariable(variable), INITS);
    }

    public QDesignMold(Path<? extends DesignMold> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDesignMold(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDesignMold(PathMetadata<?> metadata, PathInits inits) {
        this(DesignMold.class, metadata, inits);
    }

    public QDesignMold(Class<? extends DesignMold> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.design = inits.isInitialized("design") ? new QDesign(forProperty("design"), inits.get("design")) : null;
        this.designMoldType = inits.isInitialized("designMoldType") ? new QDesignMoldType(forProperty("designMoldType")) : null;
    }

}

