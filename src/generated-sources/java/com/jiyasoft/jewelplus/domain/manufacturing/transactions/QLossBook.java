package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QLossBook is a Querydsl query type for LossBook
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QLossBook extends EntityPathBase<LossBook> {

    private static final long serialVersionUID = 2088466316L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLossBook lossBook = new QLossBook("lossBook");

    public final QBagMt bagMt;

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final NumberPath<Double> currentWt = createNumber("currentWt", Double.class);

    public final BooleanPath currStk = createBoolean("currStk");

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment department;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QEmployee employee;

    public final NumberPath<Double> extraWt = createNumber("extraWt", Double.class);

    public final NumberPath<Double> grossWt = createNumber("grossWt", Double.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Double> loss = createNumber("loss", Double.class);

    public final NumberPath<Double> lossPercOnIn = createNumber("lossPercOnIn", Double.class);

    public final NumberPath<Double> lossPercOnOut = createNumber("lossPercOnOut", Double.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QLookUpMast partNm;

    public final NumberPath<Double> partWt = createNumber("partWt", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QProdLabourType prodLabourType;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity purity;

    public final NumberPath<Double> purityConv = createNumber("purityConv", Double.class);

    public final NumberPath<Double> qty = createNumber("qty", Double.class);

    public final DateTimePath<java.util.Date> trandate = createDateTime("trandate", java.util.Date.class);

    public final QTranMt tranMt;

    public QLossBook(String variable) {
        this(LossBook.class, forVariable(variable), INITS);
    }

    public QLossBook(Path<? extends LossBook> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QLossBook(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QLossBook(PathMetadata<?> metadata, PathInits inits) {
        this(LossBook.class, metadata, inits);
    }

    public QLossBook(Class<? extends LossBook> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.bagMt = inits.isInitialized("bagMt") ? new QBagMt(forProperty("bagMt"), inits.get("bagMt")) : null;
        this.department = inits.isInitialized("department") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment(forProperty("department"), inits.get("department")) : null;
        this.employee = inits.isInitialized("employee") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QEmployee(forProperty("employee"), inits.get("employee")) : null;
        this.partNm = inits.isInitialized("partNm") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QLookUpMast(forProperty("partNm")) : null;
        this.prodLabourType = inits.isInitialized("prodLabourType") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QProdLabourType(forProperty("prodLabourType"), inits.get("prodLabourType")) : null;
        this.purity = inits.isInitialized("purity") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity(forProperty("purity"), inits.get("purity")) : null;
        this.tranMt = inits.isInitialized("tranMt") ? new QTranMt(forProperty("tranMt"), inits.get("tranMt")) : null;
    }

}

