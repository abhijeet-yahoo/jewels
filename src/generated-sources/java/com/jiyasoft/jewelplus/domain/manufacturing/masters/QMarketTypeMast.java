package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QMarketTypeMast is a Querydsl query type for MarketTypeMast
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QMarketTypeMast extends EntityPathBase<MarketTypeMast> {

    private static final long serialVersionUID = 1895860641L;

    public static final QMarketTypeMast marketTypeMast = new QMarketTypeMast("marketTypeMast");

    public final StringPath code = createString("code");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final StringPath name = createString("name");

    public QMarketTypeMast(String variable) {
        super(MarketTypeMast.class, forVariable(variable));
    }

    public QMarketTypeMast(Path<? extends MarketTypeMast> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMarketTypeMast(PathMetadata<?> metadata) {
        super(MarketTypeMast.class, metadata);
    }

}

