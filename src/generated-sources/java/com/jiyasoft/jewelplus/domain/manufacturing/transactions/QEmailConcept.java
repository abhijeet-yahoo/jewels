package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QEmailConcept is a Querydsl query type for EmailConcept
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QEmailConcept extends EntityPathBase<EmailConcept> {

    private static final long serialVersionUID = -136734196L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEmailConcept emailConcept = new QEmailConcept("emailConcept");

    public final StringPath cadImage = createString("cadImage");

    public final BooleanPath cancel = createBoolean("cancel");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QCategory category;

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final StringPath designImage = createString("designImage");

    public final BooleanPath done = createBoolean("done");

    public final DateTimePath<java.util.Date> eDate = createDateTime("eDate", java.util.Date.class);

    public final NumberPath<Integer> emailConIdMax = createNumber("emailConIdMax", Integer.class);

    public final StringPath emailImage = createString("emailImage");

    public final BooleanPath hold = createBoolean("hold");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final BooleanPath modify = createBoolean("modify");

    public final StringPath name = createString("name");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QParty party;

    public final StringPath remark = createString("remark");

    public final StringPath roughImage = createString("roughImage");

    public final NumberPath<Long> uniqueId = createNumber("uniqueId", Long.class);

    public final StringPath version = createString("version");

    public final BooleanPath versionFlg = createBoolean("versionFlg");

    public QEmailConcept(String variable) {
        this(EmailConcept.class, forVariable(variable), INITS);
    }

    public QEmailConcept(Path<? extends EmailConcept> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QEmailConcept(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QEmailConcept(PathMetadata<?> metadata, PathInits inits) {
        this(EmailConcept.class, metadata, inits);
    }

    public QEmailConcept(Class<? extends EmailConcept> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QCategory(forProperty("category")) : null;
        this.party = inits.isInitialized("party") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QParty(forProperty("party"), inits.get("party")) : null;
    }

}

