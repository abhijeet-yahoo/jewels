package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QVAddCompInv is a Querydsl query type for VAddCompInv
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QVAddCompInv extends EntityPathBase<VAddCompInv> {

    private static final long serialVersionUID = 2119353527L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVAddCompInv vAddCompInv = new QVAddCompInv("vAddCompInv");

    public final NumberPath<Double> adjBal = createNumber("adjBal", Double.class);

    public final BooleanPath adjusted = createBoolean("adjusted");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor color;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QComponent component;

    public final NumberPath<Double> compPcs = createNumber("compPcs", Double.class);

    public final NumberPath<Double> compWt = createNumber("compWt", Double.class);

    public final QCostingMt costingMt;

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity purity;

    public final NumberPath<Double> purityConv = createNumber("purityConv", Double.class);

    public QVAddCompInv(String variable) {
        this(VAddCompInv.class, forVariable(variable), INITS);
    }

    public QVAddCompInv(Path<? extends VAddCompInv> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QVAddCompInv(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QVAddCompInv(PathMetadata<?> metadata, PathInits inits) {
        this(VAddCompInv.class, metadata, inits);
    }

    public QVAddCompInv(Class<? extends VAddCompInv> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.color = inits.isInitialized("color") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor(forProperty("color")) : null;
        this.component = inits.isInitialized("component") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QComponent(forProperty("component")) : null;
        this.costingMt = inits.isInitialized("costingMt") ? new QCostingMt(forProperty("costingMt"), inits.get("costingMt")) : null;
        this.purity = inits.isInitialized("purity") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity(forProperty("purity"), inits.get("purity")) : null;
    }

}

