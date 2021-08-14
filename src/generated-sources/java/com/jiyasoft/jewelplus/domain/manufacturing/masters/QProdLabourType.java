package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QProdLabourType is a Querydsl query type for ProdLabourType
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QProdLabourType extends EntityPathBase<ProdLabourType> {

    private static final long serialVersionUID = -1907601114L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProdLabourType prodLabourType = new QProdLabourType("prodLabourType");

    public final QCategory category;

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDt = createDateTime("createdDt", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final QDepartment department;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final StringPath name = createString("name");

    public final BooleanPath prodLabTypeDefault = createBoolean("prodLabTypeDefault");

    public final NumberPath<Double> rate = createNumber("rate", Double.class);

    public QProdLabourType(String variable) {
        this(ProdLabourType.class, forVariable(variable), INITS);
    }

    public QProdLabourType(Path<? extends ProdLabourType> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QProdLabourType(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QProdLabourType(PathMetadata<?> metadata, PathInits inits) {
        this(ProdLabourType.class, metadata, inits);
    }

    public QProdLabourType(Class<? extends ProdLabourType> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new QCategory(forProperty("category")) : null;
        this.department = inits.isInitialized("department") ? new QDepartment(forProperty("department"), inits.get("department")) : null;
    }

}

