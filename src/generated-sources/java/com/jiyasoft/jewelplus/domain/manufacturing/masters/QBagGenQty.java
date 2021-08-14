package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QBagGenQty is a Querydsl query type for BagGenQty
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QBagGenQty extends EntityPathBase<BagGenQty> {

    private static final long serialVersionUID = 761109944L;

    public static final QBagGenQty bagGenQty = new QBagGenQty("bagGenQty");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Double> fromCtsWt = createNumber("fromCtsWt", Double.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final NumberPath<Double> qty = createNumber("qty", Double.class);

    public final NumberPath<Double> toCtsWt = createNumber("toCtsWt", Double.class);

    public QBagGenQty(String variable) {
        super(BagGenQty.class, forVariable(variable));
    }

    public QBagGenQty(Path<? extends BagGenQty> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBagGenQty(PathMetadata<?> metadata) {
        super(BagGenQty.class, metadata);
    }

}

