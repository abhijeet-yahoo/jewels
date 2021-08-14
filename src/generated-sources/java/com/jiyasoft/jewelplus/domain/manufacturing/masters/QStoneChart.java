package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QStoneChart is a Querydsl query type for StoneChart
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QStoneChart extends EntityPathBase<StoneChart> {

    private static final long serialVersionUID = 68387183L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStoneChart stoneChart = new QStoneChart("stoneChart");

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final StringPath createdBy = createString("createdBy");

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final NumberPath<Double> perStoneWt = createNumber("perStoneWt", Double.class);

    public final BooleanPath pointerFlg = createBoolean("pointerFlg");

    public final QShape shape;

    public final StringPath sieve = createString("sieve");

    public final StringPath size = createString("size");

    public final QSizeGroup sizeGroup;

    public QStoneChart(String variable) {
        this(StoneChart.class, forVariable(variable), INITS);
    }

    public QStoneChart(Path<? extends StoneChart> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStoneChart(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStoneChart(PathMetadata<?> metadata, PathInits inits) {
        this(StoneChart.class, metadata, inits);
    }

    public QStoneChart(Class<? extends StoneChart> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.shape = inits.isInitialized("shape") ? new QShape(forProperty("shape")) : null;
        this.sizeGroup = inits.isInitialized("sizeGroup") ? new QSizeGroup(forProperty("sizeGroup"), inits.get("sizeGroup")) : null;
    }

}

