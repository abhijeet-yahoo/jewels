package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPatternMast is a Querydsl query type for PatternMast
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPatternMast extends EntityPathBase<PatternMast> {

    private static final long serialVersionUID = 1129800303L;

    public static final QPatternMast patternMast = new QPatternMast("patternMast");

    public final StringPath code = createString("code");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDt = createDateTime("createdDt", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final StringPath name = createString("name");

    public QPatternMast(String variable) {
        super(PatternMast.class, forVariable(variable));
    }

    public QPatternMast(Path<? extends PatternMast> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPatternMast(PathMetadata<?> metadata) {
        super(PatternMast.class, metadata);
    }

}

