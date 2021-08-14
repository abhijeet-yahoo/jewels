package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QProductTypeMast is a Querydsl query type for ProductTypeMast
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QProductTypeMast extends EntityPathBase<ProductTypeMast> {

    private static final long serialVersionUID = 1166802472L;

    public static final QProductTypeMast productTypeMast = new QProductTypeMast("productTypeMast");

    public final StringPath code = createString("code");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final StringPath name = createString("name");

    public QProductTypeMast(String variable) {
        super(ProductTypeMast.class, forVariable(variable));
    }

    public QProductTypeMast(Path<? extends ProductTypeMast> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProductTypeMast(PathMetadata<?> metadata) {
        super(ProductTypeMast.class, metadata);
    }

}

