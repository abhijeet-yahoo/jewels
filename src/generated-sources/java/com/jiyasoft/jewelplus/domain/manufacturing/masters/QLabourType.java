package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QLabourType is a Querydsl query type for LabourType
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QLabourType extends EntityPathBase<LabourType> {

    private static final long serialVersionUID = 1632583023L;

    public static final QLabourType labourType = new QLabourType("labourType");

    public final StringPath code = createString("code");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final StringPath name = createString("name");

    public QLabourType(String variable) {
        super(LabourType.class, forVariable(variable));
    }

    public QLabourType(Path<? extends LabourType> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLabourType(PathMetadata<?> metadata) {
        super(LabourType.class, metadata);
    }

}

