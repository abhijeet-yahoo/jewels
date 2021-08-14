package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QVouIssDt is a Querydsl query type for VouIssDt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QVouIssDt extends EntityPathBase<VouIssDt> {

    private static final long serialVersionUID = -1938334115L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVouIssDt vouIssDt = new QVouIssDt("vouIssDt");

    public final StringPath barcode = createString("barcode");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor color;

    public final NumberPath<Double> compValue = createNumber("compValue", Double.class);

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDesign design;

    public final StringPath destination = createString("destination");

    public final NumberPath<Double> discAmount = createNumber("discAmount", Double.class);

    public final NumberPath<Double> discPerc = createNumber("discPerc", Double.class);

    public final NumberPath<Double> finalPrice = createNumber("finalPrice", Double.class);

    public final NumberPath<Double> grosswt = createNumber("grosswt", Double.class);

    public final NumberPath<Double> handlingValue = createNumber("handlingValue", Double.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Double> labValue = createNumber("labValue", Double.class);

    public final NumberPath<Double> lossValue = createNumber("lossValue", Double.class);

    public final NumberPath<Double> lossWt = createNumber("lossWt", Double.class);

    public final NumberPath<Double> metalRate = createNumber("metalRate", Double.class);

    public final NumberPath<Double> metalValue = createNumber("metalValue", Double.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final NumberPath<Double> netAmount = createNumber("netAmount", Double.class);

    public final NumberPath<Double> netWt = createNumber("netWt", Double.class);

    public final StringPath orderRef = createString("orderRef");

    public final NumberPath<Double> other = createNumber("other", Double.class);

    public final NumberPath<Double> pcs = createNumber("pcs", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QProductSize productSize;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity purity;

    public final NumberPath<Double> purityConv = createNumber("purityConv", Double.class);

    public final StringPath refnO = createString("refnO");

    public final StringPath remark = createString("remark");

    public final BooleanPath rLock = createBoolean("rLock");

    public final NumberPath<Double> setValue = createNumber("setValue", Double.class);

    public final NumberPath<Integer> srNo = createNumber("srNo", Integer.class);

    public final NumberPath<Double> stoneValue = createNumber("stoneValue", Double.class);

    public final NumberPath<Double> tagPrice = createNumber("tagPrice", Double.class);

    public final QVouIssMt vouIssMt;

    public QVouIssDt(String variable) {
        this(VouIssDt.class, forVariable(variable), INITS);
    }

    public QVouIssDt(Path<? extends VouIssDt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QVouIssDt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QVouIssDt(PathMetadata<?> metadata, PathInits inits) {
        this(VouIssDt.class, metadata, inits);
    }

    public QVouIssDt(Class<? extends VouIssDt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.color = inits.isInitialized("color") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor(forProperty("color")) : null;
        this.design = inits.isInitialized("design") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDesign(forProperty("design"), inits.get("design")) : null;
        this.productSize = inits.isInitialized("productSize") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QProductSize(forProperty("productSize"), inits.get("productSize")) : null;
        this.purity = inits.isInitialized("purity") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity(forProperty("purity"), inits.get("purity")) : null;
        this.vouIssMt = inits.isInitialized("vouIssMt") ? new QVouIssMt(forProperty("vouIssMt"), inits.get("vouIssMt")) : null;
    }

}

