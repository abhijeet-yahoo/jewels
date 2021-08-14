package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QInwardType is a Querydsl query type for InwardType
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QInwardType extends EntityPathBase<InwardType> {

    private static final long serialVersionUID = -1494603023L;

    public static final QInwardType inwardType = new QInwardType("inwardType");

    public final StringPath code = createString("code");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Double> handlingPercentage = createNumber("handlingPercentage", Double.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final StringPath name = createString("name");

    public QInwardType(String variable) {
        super(InwardType.class, forVariable(variable));
    }

    public QInwardType(Path<? extends InwardType> path) {
        super(path.getType(), path.getMetadata());
    }

    public QInwardType(PathMetadata<?> metadata) {
        super(InwardType.class, metadata);
    }

}

