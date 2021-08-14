package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QShape is a Querydsl query type for Shape
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QShape extends EntityPathBase<Shape> {

    private static final long serialVersionUID = 1613279979L;

    public static final QShape shape = new QShape("shape");

    public final StringPath code = createString("code");

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final StringPath createdBy = createString("createdBy");

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Double> lowerTolerance = createNumber("lowerTolerance", Double.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final StringPath name = createString("name");

    public final ListPath<Quality, QQuality> quality = this.<Quality, QQuality>createList("quality", Quality.class, QQuality.class, PathInits.DIRECT2);

    public final ListPath<SizeGroup, QSizeGroup> sizeGroup = this.<SizeGroup, QSizeGroup>createList("sizeGroup", SizeGroup.class, QSizeGroup.class, PathInits.DIRECT2);

    public final ListPath<StoneChart, QStoneChart> stoneChart = this.<StoneChart, QStoneChart>createList("stoneChart", StoneChart.class, QStoneChart.class, PathInits.DIRECT2);

    public final ListPath<SubShape, QSubShape> subShape = this.<SubShape, QSubShape>createList("subShape", SubShape.class, QSubShape.class, PathInits.DIRECT2);

    public final NumberPath<Double> upperTolerance = createNumber("upperTolerance", Double.class);

    public QShape(String variable) {
        super(Shape.class, forVariable(variable));
    }

    public QShape(Path<? extends Shape> path) {
        super(path.getType(), path.getMetadata());
    }

    public QShape(PathMetadata<?> metadata) {
        super(Shape.class, metadata);
    }

}

