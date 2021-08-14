package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QFindingRateMast is a Querydsl query type for FindingRateMast
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QFindingRateMast extends EntityPathBase<FindingRateMast> {

    private static final long serialVersionUID = 572263784L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFindingRateMast findingRateMast = new QFindingRateMast("findingRateMast");

    public final QComponent component;

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final QParty party;

    public final BooleanPath perGramRate = createBoolean("perGramRate");

    public final BooleanPath perPcRate = createBoolean("perPcRate");

    public final QPurity purity;

    public final NumberPath<Double> rate = createNumber("rate", Double.class);

    public QFindingRateMast(String variable) {
        this(FindingRateMast.class, forVariable(variable), INITS);
    }

    public QFindingRateMast(Path<? extends FindingRateMast> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QFindingRateMast(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QFindingRateMast(PathMetadata<?> metadata, PathInits inits) {
        this(FindingRateMast.class, metadata, inits);
    }

    public QFindingRateMast(Class<? extends FindingRateMast> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.component = inits.isInitialized("component") ? new QComponent(forProperty("component")) : null;
        this.party = inits.isInitialized("party") ? new QParty(forProperty("party"), inits.get("party")) : null;
        this.purity = inits.isInitialized("purity") ? new QPurity(forProperty("purity"), inits.get("purity")) : null;
    }

}

