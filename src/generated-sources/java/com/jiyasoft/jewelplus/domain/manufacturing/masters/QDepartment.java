package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QDepartment is a Querydsl query type for Department
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QDepartment extends EntityPathBase<Department> {

    private static final long serialVersionUID = -724083128L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDepartment department = new QDepartment("department");

    public final BooleanPath allowZeroWt = createBoolean("allowZeroWt");

    public final BooleanPath barcodeFlg = createBoolean("barcodeFlg");

    public final QBranchMaster branchMaster;

    public final BooleanPath castBal = createBoolean("castBal");

    public final StringPath code = createString("code");

    public final BooleanPath componentStk = createBoolean("componentStk");

    public final BooleanPath costing = createBoolean("costing");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final BooleanPath diaIssueFlag = createBoolean("diaIssueFlag");

    public final NumberPath<Double> expectedRecovery = createNumber("expectedRecovery", Double.class);

    public final NumberPath<Double> extraWt = createNumber("extraWt", Double.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final BooleanPath inv = createBoolean("inv");

    public final BooleanPath looseMetalStk = createBoolean("looseMetalStk");

    public final BooleanPath lossBookDept = createBoolean("lossBookDept");

    public final NumberPath<Double> lossPerc = createNumber("lossPerc", Double.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final StringPath name = createString("name");

    public final BooleanPath nonEditable = createBoolean("nonEditable");

    public final BooleanPath pcsProd = createBoolean("pcsProd");

    public final StringPath process = createString("process");

    public final NumberPath<Integer> processSeq = createNumber("processSeq", Integer.class);

    public final BooleanPath stoneProd = createBoolean("stoneProd");

    public final BooleanPath stoneStk = createBoolean("stoneStk");

    public final BooleanPath vouTypeFlg = createBoolean("vouTypeFlg");

    public final BooleanPath wip = createBoolean("wip");

    public QDepartment(String variable) {
        this(Department.class, forVariable(variable), INITS);
    }

    public QDepartment(Path<? extends Department> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDepartment(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDepartment(PathMetadata<?> metadata, PathInits inits) {
        this(Department.class, metadata, inits);
    }

    public QDepartment(Class<? extends Department> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.branchMaster = inits.isInitialized("branchMaster") ? new QBranchMaster(forProperty("branchMaster"), inits.get("branchMaster")) : null;
    }

}

