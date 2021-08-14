package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QCollectionMaster is a Querydsl query type for CollectionMaster
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCollectionMaster extends EntityPathBase<CollectionMaster> {

    private static final long serialVersionUID = -963616490L;

    public static final QCollectionMaster collectionMaster = new QCollectionMaster("collectionMaster");

    public final StringPath collectionCode = createString("collectionCode");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDt = createDateTime("createdDt", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final StringPath name = createString("name");

    public QCollectionMaster(String variable) {
        super(CollectionMaster.class, forVariable(variable));
    }

    public QCollectionMaster(Path<? extends CollectionMaster> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCollectionMaster(PathMetadata<?> metadata) {
        super(CollectionMaster.class, metadata);
    }

}

