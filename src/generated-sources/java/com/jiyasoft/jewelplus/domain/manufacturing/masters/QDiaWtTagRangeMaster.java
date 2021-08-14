package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QDiaWtTagRangeMaster is a Querydsl query type for DiaWtTagRangeMaster
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QDiaWtTagRangeMaster extends EntityPathBase<DiaWtTagRangeMaster> {

    private static final long serialVersionUID = -1184304984L;

    public static final QDiaWtTagRangeMaster diaWtTagRangeMaster = new QDiaWtTagRangeMaster("diaWtTagRangeMaster");

    public final NumberPath<Double> addedWt = createNumber("addedWt", Double.class);

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDt = createDateTime("createdDt", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Double> fromCts = createNumber("fromCts", Double.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final NumberPath<Double> toCts = createNumber("toCts", Double.class);

    public QDiaWtTagRangeMaster(String variable) {
        super(DiaWtTagRangeMaster.class, forVariable(variable));
    }

    public QDiaWtTagRangeMaster(Path<? extends DiaWtTagRangeMaster> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDiaWtTagRangeMaster(PathMetadata<?> metadata) {
        super(DiaWtTagRangeMaster.class, metadata);
    }

}

