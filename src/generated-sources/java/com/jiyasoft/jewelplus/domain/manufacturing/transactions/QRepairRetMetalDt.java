package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QRepairRetMetalDt is a Querydsl query type for RepairRetMetalDt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QRepairRetMetalDt extends EntityPathBase<RepairRetMetalDt> {

    private static final long serialVersionUID = -2054274973L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRepairRetMetalDt repairRetMetalDt = new QRepairRetMetalDt("repairRetMetalDt");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor color;

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final StringPath createdBy = createString("createdBy");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Double> lossPerc = createNumber("lossPerc", Double.class);

    public final BooleanPath mainMetal = createBoolean("mainMetal");

    public final NumberPath<Integer> metalPcs = createNumber("metalPcs", Integer.class);

    public final NumberPath<Double> metalRate = createNumber("metalRate", Double.class);

    public final NumberPath<Double> metalValue = createNumber("metalValue", Double.class);

    public final NumberPath<Double> metalWeight = createNumber("metalWeight", Double.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QLookUpMast partNm;

    public final NumberPath<Double> perGramRate = createNumber("perGramRate", Double.class);

    public final BooleanPath perPcRate = createBoolean("perPcRate");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity purity;

    public final NumberPath<Double> purityConv = createNumber("purityConv", Double.class);

    public final QRepairRetDt repairRetDt;

    public final QRepairRetMt repairRetMt;

    public final BooleanPath rLock = createBoolean("rLock");

    public QRepairRetMetalDt(String variable) {
        this(RepairRetMetalDt.class, forVariable(variable), INITS);
    }

    public QRepairRetMetalDt(Path<? extends RepairRetMetalDt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRepairRetMetalDt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRepairRetMetalDt(PathMetadata<?> metadata, PathInits inits) {
        this(RepairRetMetalDt.class, metadata, inits);
    }

    public QRepairRetMetalDt(Class<? extends RepairRetMetalDt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.color = inits.isInitialized("color") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor(forProperty("color")) : null;
        this.partNm = inits.isInitialized("partNm") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QLookUpMast(forProperty("partNm")) : null;
        this.purity = inits.isInitialized("purity") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity(forProperty("purity"), inits.get("purity")) : null;
        this.repairRetDt = inits.isInitialized("repairRetDt") ? new QRepairRetDt(forProperty("repairRetDt"), inits.get("repairRetDt")) : null;
        this.repairRetMt = inits.isInitialized("repairRetMt") ? new QRepairRetMt(forProperty("repairRetMt"), inits.get("repairRetMt")) : null;
    }

}

