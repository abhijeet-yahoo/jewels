package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QClientWastage is a Querydsl query type for ClientWastage
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QClientWastage extends EntityPathBase<ClientWastage> {

    private static final long serialVersionUID = -1250183821L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QClientWastage clientWastage = new QClientWastage("clientWastage");

    public final QCategory category;

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDt = createDateTime("createdDt", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final QDesignGroup designGroup;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QMetal metal;

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final QParty party;

    public final QSubCategory subCategory;

    public final NumberPath<Double> wastagePerc = createNumber("wastagePerc", Double.class);

    public QClientWastage(String variable) {
        this(ClientWastage.class, forVariable(variable), INITS);
    }

    public QClientWastage(Path<? extends ClientWastage> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QClientWastage(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QClientWastage(PathMetadata<?> metadata, PathInits inits) {
        this(ClientWastage.class, metadata, inits);
    }

    public QClientWastage(Class<? extends ClientWastage> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new QCategory(forProperty("category")) : null;
        this.designGroup = inits.isInitialized("designGroup") ? new QDesignGroup(forProperty("designGroup")) : null;
        this.metal = inits.isInitialized("metal") ? new QMetal(forProperty("metal")) : null;
        this.party = inits.isInitialized("party") ? new QParty(forProperty("party"), inits.get("party")) : null;
        this.subCategory = inits.isInitialized("subCategory") ? new QSubCategory(forProperty("subCategory"), inits.get("subCategory")) : null;
    }

}

