package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QLookUpMast is a Querydsl query type for LookUpMast
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QLookUpMast extends EntityPathBase<LookUpMast> {

    private static final long serialVersionUID = -1188503899L;

    public static final QLookUpMast lookUpMast = new QLookUpMast("lookUpMast");

    public final StringPath code = createString("code");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final StringPath fieldValue = createString("fieldValue");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final StringPath name = createString("name");

    public final BooleanPath nonEditable = createBoolean("nonEditable");

    public QLookUpMast(String variable) {
        super(LookUpMast.class, forVariable(variable));
    }

    public QLookUpMast(Path<? extends LookUpMast> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLookUpMast(PathMetadata<?> metadata) {
        super(LookUpMast.class, metadata);
    }

}

