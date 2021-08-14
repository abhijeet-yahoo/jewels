package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QCountryMaster is a Querydsl query type for CountryMaster
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCountryMaster extends EntityPathBase<CountryMaster> {

    private static final long serialVersionUID = -349479198L;

    public static final QCountryMaster countryMaster = new QCountryMaster("countryMaster");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final StringPath currencyNm = createString("currencyNm");

    public final StringPath currencySymbol = createString("currencySymbol");

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final StringPath name = createString("name");

    public QCountryMaster(String variable) {
        super(CountryMaster.class, forVariable(variable));
    }

    public QCountryMaster(Path<? extends CountryMaster> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCountryMaster(PathMetadata<?> metadata) {
        super(CountryMaster.class, metadata);
    }

}

