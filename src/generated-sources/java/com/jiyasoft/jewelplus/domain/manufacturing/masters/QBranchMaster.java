package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QBranchMaster is a Querydsl query type for BranchMaster
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QBranchMaster extends EntityPathBase<BranchMaster> {

    private static final long serialVersionUID = -334417894L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBranchMaster branchMaster = new QBranchMaster("branchMaster");

    public final StringPath address = createString("address");

    public final StringPath area = createString("area");

    public final StringPath city = createString("city");

    public final StringPath code = createString("code");

    public final StringPath contactPerson = createString("contactPerson");

    public final QCountryMaster country;

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final StringPath faxNo = createString("faxNo");

    public final StringPath gst = createString("gst");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final StringPath name = createString("name");

    public final StringPath officePhone = createString("officePhone");

    public final StringPath panNo = createString("panNo");

    public final QStateMaster stateMast;

    public final StringPath zip = createString("zip");

    public final StringPath zone = createString("zone");

    public QBranchMaster(String variable) {
        this(BranchMaster.class, forVariable(variable), INITS);
    }

    public QBranchMaster(Path<? extends BranchMaster> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QBranchMaster(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QBranchMaster(PathMetadata<?> metadata, PathInits inits) {
        this(BranchMaster.class, metadata, inits);
    }

    public QBranchMaster(Class<? extends BranchMaster> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.country = inits.isInitialized("country") ? new QCountryMaster(forProperty("country")) : null;
        this.stateMast = inits.isInitialized("stateMast") ? new QStateMaster(forProperty("stateMast"), inits.get("stateMast")) : null;
    }

}

