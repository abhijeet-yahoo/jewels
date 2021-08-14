package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QDustRecDt is a Querydsl query type for DustRecDt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QDustRecDt extends EntityPathBase<DustRecDt> {

    private static final long serialVersionUID = -492264754L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDustRecDt dustRecDt = new QDustRecDt("dustRecDt");

    public final NumberPath<Double> burnWt = createNumber("burnWt", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor color;

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment department;

    public final QDustMt dustMt;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QEmployee employee;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Double> metalWt = createNumber("metalWt", Double.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final StringPath other = createString("other");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity purity;

    public final NumberPath<Double> purityConv = createNumber("purityConv", Double.class);

    public final NumberPath<Long> uniqueId = createNumber("uniqueId", Long.class);

    public QDustRecDt(String variable) {
        this(DustRecDt.class, forVariable(variable), INITS);
    }

    public QDustRecDt(Path<? extends DustRecDt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDustRecDt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDustRecDt(PathMetadata<?> metadata, PathInits inits) {
        this(DustRecDt.class, metadata, inits);
    }

    public QDustRecDt(Class<? extends DustRecDt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.color = inits.isInitialized("color") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor(forProperty("color")) : null;
        this.department = inits.isInitialized("department") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment(forProperty("department"), inits.get("department")) : null;
        this.dustMt = inits.isInitialized("dustMt") ? new QDustMt(forProperty("dustMt")) : null;
        this.employee = inits.isInitialized("employee") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QEmployee(forProperty("employee"), inits.get("employee")) : null;
        this.purity = inits.isInitialized("purity") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity(forProperty("purity"), inits.get("purity")) : null;
    }

}

