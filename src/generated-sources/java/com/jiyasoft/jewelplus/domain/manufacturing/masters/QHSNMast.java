package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QHSNMast is a Querydsl query type for HSNMast
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QHSNMast extends EntityPathBase<HSNMast> {

    private static final long serialVersionUID = -1913541854L;

    public static final QHSNMast hSNMast = new QHSNMast("hSNMast");

    public final NumberPath<Double> cGST = createNumber("cGST", Double.class);

    public final StringPath code = createString("code");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final StringPath desc = createString("desc");

    public final NumberPath<Integer> hsnNo = createNumber("hsnNo", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Double> iGST = createNumber("iGST", Double.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final NumberPath<Double> sGST = createNumber("sGST", Double.class);

    public QHSNMast(String variable) {
        super(HSNMast.class, forVariable(variable));
    }

    public QHSNMast(Path<? extends HSNMast> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHSNMast(PathMetadata<?> metadata) {
        super(HSNMast.class, metadata);
    }

}

