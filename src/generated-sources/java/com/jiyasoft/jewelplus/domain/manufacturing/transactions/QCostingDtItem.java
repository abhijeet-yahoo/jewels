package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QCostingDtItem is a Querydsl query type for CostingDtItem
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCostingDtItem extends EntityPathBase<CostingDtItem> {

    private static final long serialVersionUID = -184676808L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCostingDtItem costingDtItem = new QCostingDtItem("costingDtItem");

    public final NumberPath<Double> actGrossWt = createNumber("actGrossWt", Double.class);

    public final NumberPath<Double> actNetWt = createNumber("actNetWt", Double.class);

    public final BooleanPath blackRhod = createBoolean("blackRhod");

    public final NumberPath<Double> clientPurityConv = createNumber("clientPurityConv", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor color;

    public final BooleanPath colorRhod = createBoolean("colorRhod");

    public final NumberPath<Double> compValue = createNumber("compValue", Double.class);

    public final QCostingMt costingMt;

    public final BooleanPath costTransfer = createBoolean("costTransfer");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment department;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDesign design;

    public final NumberPath<Double> discAmount = createNumber("discAmount", Double.class);

    public final NumberPath<Double> dispPercentDt = createNumber("dispPercentDt", Double.class);

    public final NumberPath<Double> finalPrice = createNumber("finalPrice", Double.class);

    public final NumberPath<Double> fob = createNumber("fob", Double.class);

    public final NumberPath<Double> grossWt = createNumber("grossWt", Double.class);

    public final NumberPath<Double> hdlgValue = createNumber("hdlgValue", Double.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath itemNo = createString("itemNo");

    public final NumberPath<Double> labValue = createNumber("labValue", Double.class);

    public final NumberPath<Double> lossPercDt = createNumber("lossPercDt", Double.class);

    public final NumberPath<Double> lossValue = createNumber("lossValue", Double.class);

    public final NumberPath<Double> lossWt = createNumber("lossWt", Double.class);

    public final NumberPath<Double> metalRate = createNumber("metalRate", Double.class);

    public final NumberPath<Double> metalValue = createNumber("metalValue", Double.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final NumberPath<Double> netWt = createNumber("netWt", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QOrderDt orderDt;

    public final NumberPath<Double> other = createNumber("other", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QParty party;

    public final NumberPath<Double> pcs = createNumber("pcs", Double.class);

    public final NumberPath<Double> perGmWt = createNumber("perGmWt", Double.class);

    public final NumberPath<Double> perPcDiscAmount = createNumber("perPcDiscAmount", Double.class);

    public final NumberPath<Double> perPcFinalPrice = createNumber("perPcFinalPrice", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity purity;

    public final NumberPath<Double> purityConv = createNumber("purityConv", Double.class);

    public final BooleanPath rLock = createBoolean("rLock");

    public final NumberPath<Integer> setNo = createNumber("setNo", Integer.class);

    public final NumberPath<Double> setValue = createNumber("setValue", Double.class);

    public final NumberPath<Double> stoneValue = createNumber("stoneValue", Double.class);

    public final NumberPath<Integer> trfInvMtId = createNumber("trfInvMtId", Integer.class);

    public final NumberPath<Long> uniqueId = createNumber("uniqueId", Long.class);

    public QCostingDtItem(String variable) {
        this(CostingDtItem.class, forVariable(variable), INITS);
    }

    public QCostingDtItem(Path<? extends CostingDtItem> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCostingDtItem(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCostingDtItem(PathMetadata<?> metadata, PathInits inits) {
        this(CostingDtItem.class, metadata, inits);
    }

    public QCostingDtItem(Class<? extends CostingDtItem> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.color = inits.isInitialized("color") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor(forProperty("color")) : null;
        this.costingMt = inits.isInitialized("costingMt") ? new QCostingMt(forProperty("costingMt"), inits.get("costingMt")) : null;
        this.department = inits.isInitialized("department") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment(forProperty("department"), inits.get("department")) : null;
        this.design = inits.isInitialized("design") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDesign(forProperty("design"), inits.get("design")) : null;
        this.orderDt = inits.isInitialized("orderDt") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QOrderDt(forProperty("orderDt"), inits.get("orderDt")) : null;
        this.party = inits.isInitialized("party") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QParty(forProperty("party"), inits.get("party")) : null;
        this.purity = inits.isInitialized("purity") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity(forProperty("purity"), inits.get("purity")) : null;
    }

}

