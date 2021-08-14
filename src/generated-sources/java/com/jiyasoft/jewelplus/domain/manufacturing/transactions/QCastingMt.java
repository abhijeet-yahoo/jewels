package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QCastingMt is a Querydsl query type for CastingMt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCastingMt extends EntityPathBase<CastingMt> {

    private static final long serialVersionUID = 840234602L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCastingMt castingMt = new QCastingMt("castingMt");

    public final NumberPath<Integer> alloyId = createNumber("alloyId", Integer.class);

    public final NumberPath<Double> alloyWt = createNumber("alloyWt", Double.class);

    public final NumberPath<Integer> baseNo = createNumber("baseNo", Integer.class);

    public final NumberPath<Double> baseWt = createNumber("baseWt", Double.class);

    public final NumberPath<Double> castingLoss = createNumber("castingLoss", Double.class);

    public final DateTimePath<java.util.Date> cDate = createDateTime("cDate", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor color;

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final NumberPath<Double> cuttingLoss = createNumber("cuttingLoss", Double.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment department;

    public final NumberPath<Double> fallenStnWt = createNumber("fallenStnWt", Double.class);

    public final NumberPath<Integer> fallenStone = createNumber("fallenStone", Integer.class);

    public final NumberPath<Double> freshIssWt = createNumber("freshIssWt", Double.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Double> metalRate = createNumber("metalRate", Double.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final NumberPath<Double> pcsWt = createNumber("pcsWt", Double.class);

    public final NumberPath<Double> pureWt = createNumber("pureWt", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity purity;

    public final NumberPath<Double> purityConv = createNumber("purityConv", Double.class);

    public final NumberPath<Double> reqWt = createNumber("reqWt", Double.class);

    public final NumberPath<Double> scrapWt = createNumber("scrapWt", Double.class);

    public final NumberPath<Double> stoneWt = createNumber("stoneWt", Double.class);

    public final NumberPath<Double> stubWt = createNumber("stubWt", Double.class);

    public final NumberPath<Double> totIssWt = createNumber("totIssWt", Double.class);

    public final StringPath treeNo = createString("treeNo");

    public final NumberPath<Double> treeWt = createNumber("treeWt", Double.class);

    public final NumberPath<Long> uniqueId = createNumber("uniqueId", Long.class);

    public final BooleanPath updateIss = createBoolean("updateIss");

    public final BooleanPath updateRec = createBoolean("updateRec");

    public final NumberPath<Double> usedIssWt = createNumber("usedIssWt", Double.class);

    public final NumberPath<Double> waxTreeWt = createNumber("waxTreeWt", Double.class);

    public final NumberPath<Double> waxWt = createNumber("waxWt", Double.class);

    public QCastingMt(String variable) {
        this(CastingMt.class, forVariable(variable), INITS);
    }

    public QCastingMt(Path<? extends CastingMt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCastingMt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCastingMt(PathMetadata<?> metadata, PathInits inits) {
        this(CastingMt.class, metadata, inits);
    }

    public QCastingMt(Class<? extends CastingMt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.color = inits.isInitialized("color") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor(forProperty("color")) : null;
        this.department = inits.isInitialized("department") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment(forProperty("department"), inits.get("department")) : null;
        this.purity = inits.isInitialized("purity") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity(forProperty("purity"), inits.get("purity")) : null;
    }

}

