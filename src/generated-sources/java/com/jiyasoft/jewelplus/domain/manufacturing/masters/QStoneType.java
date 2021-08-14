package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QStoneType is a Querydsl query type for StoneType
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QStoneType extends EntityPathBase<StoneType> {

    private static final long serialVersionUID = -1521291383L;

    public static final QStoneType stoneType = new QStoneType("stoneType");

    public final StringPath code = createString("code");

    public final NumberPath<Integer> compId = createNumber("compId", Integer.class);

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Double> factoryRatePerc = createNumber("factoryRatePerc", Double.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final StringPath name = createString("name");

    public final NumberPath<Double> transferRatePerc = createNumber("transferRatePerc", Double.class);

    public QStoneType(String variable) {
        super(StoneType.class, forVariable(variable));
    }

    public QStoneType(Path<? extends StoneType> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStoneType(PathMetadata<?> metadata) {
        super(StoneType.class, metadata);
    }

}

