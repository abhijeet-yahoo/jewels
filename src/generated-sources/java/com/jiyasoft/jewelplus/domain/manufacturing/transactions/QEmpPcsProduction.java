package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QEmpPcsProduction is a Querydsl query type for EmpPcsProduction
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QEmpPcsProduction extends EntityPathBase<EmpPcsProduction> {

    private static final long serialVersionUID = -492864335L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEmpPcsProduction empPcsProduction = new QEmpPcsProduction("empPcsProduction");

    public final QBagMt bagMt;

    public final NumberPath<Double> bagPcs = createNumber("bagPcs", Double.class);

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDt = createDateTime("createdDt", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment department;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QEmployee employee;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Double> issWt = createNumber("issWt", Double.class);

    public final NumberPath<Double> loss = createNumber("loss", Double.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QOrderDt orderDt;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QProdLabourType prodLabType;

    public final NumberPath<Double> prodPcs = createNumber("prodPcs", Double.class);

    public final NumberPath<Double> rate = createNumber("rate", Double.class);

    public final NumberPath<Double> recWt = createNumber("recWt", Double.class);

    public final TimePath<java.sql.Time> time = createTime("time", java.sql.Time.class);

    public final QTranMt tranMt;

    public QEmpPcsProduction(String variable) {
        this(EmpPcsProduction.class, forVariable(variable), INITS);
    }

    public QEmpPcsProduction(Path<? extends EmpPcsProduction> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QEmpPcsProduction(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QEmpPcsProduction(PathMetadata<?> metadata, PathInits inits) {
        this(EmpPcsProduction.class, metadata, inits);
    }

    public QEmpPcsProduction(Class<? extends EmpPcsProduction> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.bagMt = inits.isInitialized("bagMt") ? new QBagMt(forProperty("bagMt"), inits.get("bagMt")) : null;
        this.department = inits.isInitialized("department") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment(forProperty("department"), inits.get("department")) : null;
        this.employee = inits.isInitialized("employee") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QEmployee(forProperty("employee"), inits.get("employee")) : null;
        this.orderDt = inits.isInitialized("orderDt") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QOrderDt(forProperty("orderDt"), inits.get("orderDt")) : null;
        this.prodLabType = inits.isInitialized("prodLabType") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QProdLabourType(forProperty("prodLabType"), inits.get("prodLabType")) : null;
        this.tranMt = inits.isInitialized("tranMt") ? new QTranMt(forProperty("tranMt"), inits.get("tranMt")) : null;
    }

}

