package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QOrderType is a Querydsl query type for OrderType
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QOrderType extends EntityPathBase<OrderType> {

    private static final long serialVersionUID = 804643314L;

    public static final QOrderType orderType = new QOrderType("orderType");

    public final StringPath bagPrefix = createString("bagPrefix");

    public final StringPath code = createString("code");

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final StringPath createdBy = createString("createdBy");

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final StringPath name = createString("name");

    public final StringPath ordCode = createString("ordCode");

    public QOrderType(String variable) {
        super(OrderType.class, forVariable(variable));
    }

    public QOrderType(Path<? extends OrderType> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOrderType(PathMetadata<?> metadata) {
        super(OrderType.class, metadata);
    }

}

