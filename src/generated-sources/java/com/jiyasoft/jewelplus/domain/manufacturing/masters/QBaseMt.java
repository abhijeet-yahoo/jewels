package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QBaseMt is a Querydsl query type for BaseMt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QBaseMt extends EntityPathBase<BaseMt> {

    private static final long serialVersionUID = -2020563378L;

    public static final QBaseMt baseMt = new QBaseMt("baseMt");

    public final NumberPath<Integer> baseNo = createNumber("baseNo", Integer.class);

    public final NumberPath<Double> baseWt = createNumber("baseWt", Double.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public QBaseMt(String variable) {
        super(BaseMt.class, forVariable(variable));
    }

    public QBaseMt(Path<? extends BaseMt> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBaseMt(PathMetadata<?> metadata) {
        super(BaseMt.class, metadata);
    }

}

