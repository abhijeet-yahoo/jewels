package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QLedgerGroup is a Querydsl query type for LedgerGroup
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QLedgerGroup extends EntityPathBase<LedgerGroup> {

    private static final long serialVersionUID = 874485344L;

    public static final QLedgerGroup ledgerGroup = new QLedgerGroup("ledgerGroup");

    public final NumberPath<Integer> compId = createNumber("compId", Integer.class);

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Integer> groupId = createNumber("groupId", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final BooleanPath isGroup = createBoolean("isGroup");

    public final StringPath ledgerGroupCode = createString("ledgerGroupCode");

    public final StringPath mainGroup = createString("mainGroup");

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final StringPath name = createString("name");

    public final BooleanPath nonEditable = createBoolean("nonEditable");

    public QLedgerGroup(String variable) {
        super(LedgerGroup.class, forVariable(variable));
    }

    public QLedgerGroup(Path<? extends LedgerGroup> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLedgerGroup(PathMetadata<?> metadata) {
        super(LedgerGroup.class, metadata);
    }

}

