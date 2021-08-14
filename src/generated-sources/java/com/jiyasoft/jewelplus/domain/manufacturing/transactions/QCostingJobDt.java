package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QCostingJobDt is a Querydsl query type for CostingJobDt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCostingJobDt extends EntityPathBase<CostingJobDt> {

    private static final long serialVersionUID = 969288728L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCostingJobDt costingJobDt = new QCostingJobDt("costingJobDt");

    public final QBagMt bagMt;

    public final StringPath bagNm = createString("bagNm");

    public final BooleanPath blackRhod = createBoolean("blackRhod");

    public final NumberPath<Double> clientPurityConv = createNumber("clientPurityConv", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor color;

    public final StringPath colorNm = createString("colorNm");

    public final BooleanPath colorRhod = createBoolean("colorRhod");

    public final NumberPath<Double> compValue = createNumber("compValue", Double.class);

    public final QCostingJobMt costingJobMt;

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment department;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDesign design;

    public final NumberPath<Double> finalPrice = createNumber("finalPrice", Double.class);

    public final NumberPath<Double> fob = createNumber("fob", Double.class);

    public final NumberPath<Double> grossWt = createNumber("grossWt", Double.class);

    public final NumberPath<Double> hdlgValue = createNumber("hdlgValue", Double.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath itemNo = createString("itemNo");

    public final NumberPath<Double> labValue = createNumber("labValue", Double.class);

    public final NumberPath<Double> lossValue = createNumber("lossValue", Double.class);

    public final NumberPath<Double> lossWt = createNumber("lossWt", Double.class);

    public final NumberPath<Double> metalRate = createNumber("metalRate", Double.class);

    public final NumberPath<Double> metalValue = createNumber("metalValue", Double.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final NumberPath<Double> netWt = createNumber("netWt", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QOrderDt orderDt;

    public final NumberPath<Double> other = createNumber("other", Double.class);

    public final NumberPath<Double> pcs = createNumber("pcs", Double.class);

    public final NumberPath<Double> perGmWt = createNumber("perGmWt", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity purity;

    public final NumberPath<Double> purityConv = createNumber("purityConv", Double.class);

    public final StringPath purityNm = createString("purityNm");

    public final BooleanPath rLock = createBoolean("rLock");

    public final NumberPath<Double> setValue = createNumber("setValue", Double.class);

    public final NumberPath<Double> stoneValue = createNumber("stoneValue", Double.class);

    public final StringPath styleNo = createString("styleNo");

    public final NumberPath<Long> uniqueId = createNumber("uniqueId", Long.class);

    public final StringPath version = createString("version");

    public QCostingJobDt(String variable) {
        this(CostingJobDt.class, forVariable(variable), INITS);
    }

    public QCostingJobDt(Path<? extends CostingJobDt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCostingJobDt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCostingJobDt(PathMetadata<?> metadata, PathInits inits) {
        this(CostingJobDt.class, metadata, inits);
    }

    public QCostingJobDt(Class<? extends CostingJobDt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.bagMt = inits.isInitialized("bagMt") ? new QBagMt(forProperty("bagMt"), inits.get("bagMt")) : null;
        this.color = inits.isInitialized("color") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor(forProperty("color")) : null;
        this.costingJobMt = inits.isInitialized("costingJobMt") ? new QCostingJobMt(forProperty("costingJobMt"), inits.get("costingJobMt")) : null;
        this.department = inits.isInitialized("department") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment(forProperty("department"), inits.get("department")) : null;
        this.design = inits.isInitialized("design") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDesign(forProperty("design"), inits.get("design")) : null;
        this.orderDt = inits.isInitialized("orderDt") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QOrderDt(forProperty("orderDt"), inits.get("orderDt")) : null;
        this.purity = inits.isInitialized("purity") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity(forProperty("purity"), inits.get("purity")) : null;
    }

}

