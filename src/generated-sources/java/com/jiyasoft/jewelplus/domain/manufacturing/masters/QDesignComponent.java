package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QDesignComponent is a Querydsl query type for DesignComponent
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QDesignComponent extends EntityPathBase<DesignComponent> {

    private static final long serialVersionUID = -341712983L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDesignComponent designComponent = new QDesignComponent("designComponent");

    public final QColor color;

    public final QComponent component;

    public final NumberPath<Double> compRate = createNumber("compRate", Double.class);

    public final NumberPath<Double> compValue = createNumber("compValue", Double.class);

    public final NumberPath<Double> compWt = createNumber("compWt", Double.class);

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final StringPath createdBy = createString("createdBy");

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final QDesign design;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final QPurity purity;

    public final NumberPath<Double> quantity = createNumber("quantity", Double.class);

    public QDesignComponent(String variable) {
        this(DesignComponent.class, forVariable(variable), INITS);
    }

    public QDesignComponent(Path<? extends DesignComponent> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDesignComponent(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDesignComponent(PathMetadata<?> metadata, PathInits inits) {
        this(DesignComponent.class, metadata, inits);
    }

    public QDesignComponent(Class<? extends DesignComponent> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.color = inits.isInitialized("color") ? new QColor(forProperty("color")) : null;
        this.component = inits.isInitialized("component") ? new QComponent(forProperty("component")) : null;
        this.design = inits.isInitialized("design") ? new QDesign(forProperty("design"), inits.get("design")) : null;
        this.purity = inits.isInitialized("purity") ? new QPurity(forProperty("purity"), inits.get("purity")) : null;
    }

}

