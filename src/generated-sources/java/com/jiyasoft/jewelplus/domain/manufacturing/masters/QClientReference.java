package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QClientReference is a Querydsl query type for ClientReference
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QClientReference extends EntityPathBase<ClientReference> {

    private static final long serialVersionUID = 926404234L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QClientReference clientReference = new QClientReference("clientReference");

    public final NumberPath<Double> caratWt = createNumber("caratWt", Double.class);

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDt = createDateTime("createdDt", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final QDesign design;

    public final NumberPath<Double> finishWt = createNumber("finishWt", Double.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final StringPath p = createString("p");

    public final QParty party;

    public final QPurity purity;

    public final StringPath pw = createString("pw");

    public final StringPath py = createString("py");

    public final StringPath tt = createString("tt");

    public final StringPath w = createString("w");

    public final StringPath wp = createString("wp");

    public final StringPath wy = createString("wy");

    public final StringPath y = createString("y");

    public final StringPath yp = createString("yp");

    public final StringPath yw = createString("yw");

    public QClientReference(String variable) {
        this(ClientReference.class, forVariable(variable), INITS);
    }

    public QClientReference(Path<? extends ClientReference> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QClientReference(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QClientReference(PathMetadata<?> metadata, PathInits inits) {
        this(ClientReference.class, metadata, inits);
    }

    public QClientReference(Class<? extends ClientReference> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.design = inits.isInitialized("design") ? new QDesign(forProperty("design"), inits.get("design")) : null;
        this.party = inits.isInitialized("party") ? new QParty(forProperty("party"), inits.get("party")) : null;
        this.purity = inits.isInitialized("purity") ? new QPurity(forProperty("purity"), inits.get("purity")) : null;
    }

}

