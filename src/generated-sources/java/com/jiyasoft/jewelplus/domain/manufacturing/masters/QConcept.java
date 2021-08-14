package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QConcept is a Querydsl query type for Concept
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QConcept extends EntityPathBase<Concept> {

    private static final long serialVersionUID = -1224264910L;

    public static final QConcept concept = new QConcept("concept");

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final StringPath createdBy = createString("createdBy");

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final StringPath name = createString("name");

    public final ListPath<SubConcept, QSubConcept> subConcept = this.<SubConcept, QSubConcept>createList("subConcept", SubConcept.class, QSubConcept.class, PathInits.DIRECT2);

    public QConcept(String variable) {
        super(Concept.class, forVariable(variable));
    }

    public QConcept(Path<? extends Concept> path) {
        super(path.getType(), path.getMetadata());
    }

    public QConcept(PathMetadata<?> metadata) {
        super(Concept.class, metadata);
    }

}

