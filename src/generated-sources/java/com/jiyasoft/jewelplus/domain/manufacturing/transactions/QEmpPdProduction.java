package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QEmpPdProduction is a Querydsl query type for EmpPdProduction
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QEmpPdProduction extends EntityPathBase<EmpPdProduction> {

    private static final long serialVersionUID = 27501525L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEmpPdProduction empPdProduction = new QEmpPdProduction("empPdProduction");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDt = createDateTime("createdDt", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment department;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDesign design;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QEmployee employee;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Double> issWt = createNumber("issWt", Double.class);

    public final NumberPath<Double> loss = createNumber("loss", Double.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final NumberPath<Double> pcs = createNumber("pcs", Double.class);

    public final QPDCTranMt pdcTranMt;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QProdLabourType prodLabType;

    public final NumberPath<Double> prodPcs = createNumber("prodPcs", Double.class);

    public final NumberPath<Double> rate = createNumber("rate", Double.class);

    public final NumberPath<Double> recWt = createNumber("recWt", Double.class);

    public final TimePath<java.sql.Time> time = createTime("time", java.sql.Time.class);

    public QEmpPdProduction(String variable) {
        this(EmpPdProduction.class, forVariable(variable), INITS);
    }

    public QEmpPdProduction(Path<? extends EmpPdProduction> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QEmpPdProduction(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QEmpPdProduction(PathMetadata<?> metadata, PathInits inits) {
        this(EmpPdProduction.class, metadata, inits);
    }

    public QEmpPdProduction(Class<? extends EmpPdProduction> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.department = inits.isInitialized("department") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment(forProperty("department"), inits.get("department")) : null;
        this.design = inits.isInitialized("design") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDesign(forProperty("design"), inits.get("design")) : null;
        this.employee = inits.isInitialized("employee") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QEmployee(forProperty("employee"), inits.get("employee")) : null;
        this.pdcTranMt = inits.isInitialized("pdcTranMt") ? new QPDCTranMt(forProperty("pdcTranMt"), inits.get("pdcTranMt")) : null;
        this.prodLabType = inits.isInitialized("prodLabType") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QProdLabourType(forProperty("prodLabType"), inits.get("prodLabType")) : null;
    }

}

