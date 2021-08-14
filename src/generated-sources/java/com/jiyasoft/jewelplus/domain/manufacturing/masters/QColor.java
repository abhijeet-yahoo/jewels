package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QColor is a Querydsl query type for Color
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QColor extends EntityPathBase<Color> {

    private static final long serialVersionUID = 1598722733L;

    public static final QColor color = new QColor("color");

    public final StringPath colorTone = createString("colorTone");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final BooleanPath defColor = createBoolean("defColor");

    public final StringPath desc = createString("desc");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final StringPath name = createString("name");

    public final StringPath twoTone = createString("twoTone");

    public QColor(String variable) {
        super(Color.class, forVariable(variable));
    }

    public QColor(Path<? extends Color> path) {
        super(path.getType(), path.getMetadata());
    }

    public QColor(PathMetadata<?> metadata) {
        super(Color.class, metadata);
    }

}

