package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QMakingMt is a Querydsl query type for MakingMt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QMakingMt extends EntityPathBase<MakingMt> {

    private static final long serialVersionUID = -240806638L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMakingMt makingMt = new QMakingMt("makingMt");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor color;

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDt = createDateTime("createdDt", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Double> freshIssWt = createNumber("freshIssWt", Double.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final DateTimePath<java.util.Date> invDate = createDateTime("invDate", java.util.Date.class);

    public final StringPath invNo = createString("invNo");

    public final NumberPath<Double> loss = createNumber("loss", Double.class);

    public final NumberPath<Double> metalRate = createNumber("metalRate", Double.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity purity;

    public final NumberPath<Double> purityConv = createNumber("purityConv", Double.class);

    public final StringPath remark = createString("remark");

    public final NumberPath<Double> returnMetal = createNumber("returnMetal", Double.class);

    public final NumberPath<Double> scrapWt = createNumber("scrapWt", Double.class);

    public final NumberPath<Long> srno = createNumber("srno", Long.class);

    public final NumberPath<Double> totIssWt = createNumber("totIssWt", Double.class);

    public final NumberPath<Long> uniqueId = createNumber("uniqueId", Long.class);

    public final NumberPath<Double> usedIssWt = createNumber("usedIssWt", Double.class);

    public QMakingMt(String variable) {
        this(MakingMt.class, forVariable(variable), INITS);
    }

    public QMakingMt(Path<? extends MakingMt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMakingMt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMakingMt(PathMetadata<?> metadata, PathInits inits) {
        this(MakingMt.class, metadata, inits);
    }

    public QMakingMt(Class<? extends MakingMt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.color = inits.isInitialized("color") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor(forProperty("color")) : null;
        this.purity = inits.isInitialized("purity") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity(forProperty("purity"), inits.get("purity")) : null;
    }

}

