package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QMeltingMt is a Querydsl query type for MeltingMt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QMeltingMt extends EntityPathBase<MeltingMt> {

    private static final long serialVersionUID = 704274729L;

    public static final QMeltingMt meltingMt = new QMeltingMt("meltingMt");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDt = createDateTime("createdDt", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final DateTimePath<java.util.Date> invDate = createDateTime("invDate", java.util.Date.class);

    public final StringPath invNo = createString("invNo");

    public final NumberPath<Double> loss = createNumber("loss", Double.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final StringPath remark = createString("remark");

    public final NumberPath<Integer> srNo = createNumber("srNo", Integer.class);

    public final NumberPath<Long> uniqueId = createNumber("uniqueId", Long.class);

    public QMeltingMt(String variable) {
        super(MeltingMt.class, forVariable(variable));
    }

    public QMeltingMt(Path<? extends MeltingMt> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMeltingMt(PathMetadata<?> metadata) {
        super(MeltingMt.class, metadata);
    }

}

