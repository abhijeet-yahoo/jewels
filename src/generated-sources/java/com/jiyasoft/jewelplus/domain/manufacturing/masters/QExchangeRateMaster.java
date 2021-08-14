package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QExchangeRateMaster is a Querydsl query type for ExchangeRateMaster
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QExchangeRateMaster extends EntityPathBase<ExchangeRateMaster> {

    private static final long serialVersionUID = -1621468453L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QExchangeRateMaster exchangeRateMaster = new QExchangeRateMaster("exchangeRateMaster");

    public final QCountryMaster countryMaster;

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Double> exChngeRate = createNumber("exChngeRate", Double.class);

    public final DateTimePath<java.util.Date> fromDate = createDateTime("fromDate", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final DateTimePath<java.util.Date> toDate = createDateTime("toDate", java.util.Date.class);

    public QExchangeRateMaster(String variable) {
        this(ExchangeRateMaster.class, forVariable(variable), INITS);
    }

    public QExchangeRateMaster(Path<? extends ExchangeRateMaster> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QExchangeRateMaster(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QExchangeRateMaster(PathMetadata<?> metadata, PathInits inits) {
        this(ExchangeRateMaster.class, metadata, inits);
    }

    public QExchangeRateMaster(Class<? extends ExchangeRateMaster> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.countryMaster = inits.isInitialized("countryMaster") ? new QCountryMaster(forProperty("countryMaster")) : null;
    }

}

