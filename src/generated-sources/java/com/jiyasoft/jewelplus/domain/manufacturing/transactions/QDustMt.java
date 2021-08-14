package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QDustMt is a Querydsl query type for DustMt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QDustMt extends EntityPathBase<DustMt> {

    private static final long serialVersionUID = 1901585113L;

    public static final QDustMt dustMt = new QDustMt("dustMt");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final DateTimePath<java.util.Date> fromPeriod = createDateTime("fromPeriod", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath invNo = createString("invNo");

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final NumberPath<Integer> srNo = createNumber("srNo", Integer.class);

    public final DateTimePath<java.util.Date> toPeriod = createDateTime("toPeriod", java.util.Date.class);

    public final NumberPath<Long> uniqueId = createNumber("uniqueId", Long.class);

    public QDustMt(String variable) {
        super(DustMt.class, forVariable(variable));
    }

    public QDustMt(Path<? extends DustMt> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDustMt(PathMetadata<?> metadata) {
        super(DustMt.class, metadata);
    }

}

