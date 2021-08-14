package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QCompTran is a Querydsl query type for CompTran
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCompTran extends EntityPathBase<CompTran> {

    private static final long serialVersionUID = -885931590L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCompTran compTran = new QCompTran("compTran");

    public final QBagMt bagMt;

    public final NumberPath<Double> balance = createNumber("balance", Double.class);

    public final NumberPath<Double> balancePcs = createNumber("balancePcs", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor color;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QComponent component;

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDt = createDateTime("createdDt", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment department;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment deptLocation;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath inOutFld = createString("inOutFld");

    public final NumberPath<Double> metalRate = createNumber("metalRate", Double.class);

    public final NumberPath<Double> metalWt = createNumber("metalWt", Double.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QLookUpMast partNm;

    public final NumberPath<Double> pcs = createNumber("pcs", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity purity;

    public final NumberPath<Double> purityConv = createNumber("purityConv", Double.class);

    public final NumberPath<Integer> refTranId = createNumber("refTranId", Integer.class);

    public final StringPath remark = createString("remark");

    public final DateTimePath<java.util.Date> trandate = createDateTime("trandate", java.util.Date.class);

    public final NumberPath<Integer> tranMtId = createNumber("tranMtId", Integer.class);

    public final StringPath tranType = createString("tranType");

    public QCompTran(String variable) {
        this(CompTran.class, forVariable(variable), INITS);
    }

    public QCompTran(Path<? extends CompTran> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCompTran(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCompTran(PathMetadata<?> metadata, PathInits inits) {
        this(CompTran.class, metadata, inits);
    }

    public QCompTran(Class<? extends CompTran> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.bagMt = inits.isInitialized("bagMt") ? new QBagMt(forProperty("bagMt"), inits.get("bagMt")) : null;
        this.color = inits.isInitialized("color") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor(forProperty("color")) : null;
        this.component = inits.isInitialized("component") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QComponent(forProperty("component")) : null;
        this.department = inits.isInitialized("department") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment(forProperty("department"), inits.get("department")) : null;
        this.deptLocation = inits.isInitialized("deptLocation") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment(forProperty("deptLocation"), inits.get("deptLocation")) : null;
        this.partNm = inits.isInitialized("partNm") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QLookUpMast(forProperty("partNm")) : null;
        this.purity = inits.isInitialized("purity") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity(forProperty("purity"), inits.get("purity")) : null;
    }

}

