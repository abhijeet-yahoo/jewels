package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QClientStyleLabDt is a Querydsl query type for ClientStyleLabDt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QClientStyleLabDt extends EntityPathBase<ClientStyleLabDt> {

    private static final long serialVersionUID = 1685761133L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QClientStyleLabDt clientStyleLabDt = new QClientStyleLabDt("clientStyleLabDt");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final QDesign design;

    public final NumberPath<Double> fromWeight = createNumber("fromWeight", Double.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QLabourType labourType;

    public final QMetal metal;

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final QParty party;

    public final BooleanPath perCaratRate = createBoolean("perCaratRate");

    public final BooleanPath percentage = createBoolean("percentage");

    public final BooleanPath perGramRate = createBoolean("perGramRate");

    public final BooleanPath perPcsRate = createBoolean("perPcsRate");

    public final QPurity purity;

    public final NumberPath<Double> rate = createNumber("rate", Double.class);

    public final NumberPath<Double> toWeight = createNumber("toWeight", Double.class);

    public QClientStyleLabDt(String variable) {
        this(ClientStyleLabDt.class, forVariable(variable), INITS);
    }

    public QClientStyleLabDt(Path<? extends ClientStyleLabDt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QClientStyleLabDt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QClientStyleLabDt(PathMetadata<?> metadata, PathInits inits) {
        this(ClientStyleLabDt.class, metadata, inits);
    }

    public QClientStyleLabDt(Class<? extends ClientStyleLabDt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.design = inits.isInitialized("design") ? new QDesign(forProperty("design"), inits.get("design")) : null;
        this.labourType = inits.isInitialized("labourType") ? new QLabourType(forProperty("labourType")) : null;
        this.metal = inits.isInitialized("metal") ? new QMetal(forProperty("metal")) : null;
        this.party = inits.isInitialized("party") ? new QParty(forProperty("party"), inits.get("party")) : null;
        this.purity = inits.isInitialized("purity") ? new QPurity(forProperty("purity"), inits.get("purity")) : null;
    }

}

