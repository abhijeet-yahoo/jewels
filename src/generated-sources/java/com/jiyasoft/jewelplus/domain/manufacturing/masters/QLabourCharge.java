package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QLabourCharge is a Querydsl query type for LabourCharge
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QLabourCharge extends EntityPathBase<LabourCharge> {

    private static final long serialVersionUID = 746395561L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLabourCharge labourCharge = new QLabourCharge("labourCharge");

    public final QCategory category;

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final BooleanPath defLabour = createBoolean("defLabour");

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

    public QLabourCharge(String variable) {
        this(LabourCharge.class, forVariable(variable), INITS);
    }

    public QLabourCharge(Path<? extends LabourCharge> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QLabourCharge(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QLabourCharge(PathMetadata<?> metadata, PathInits inits) {
        this(LabourCharge.class, metadata, inits);
    }

    public QLabourCharge(Class<? extends LabourCharge> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new QCategory(forProperty("category")) : null;
        this.labourType = inits.isInitialized("labourType") ? new QLabourType(forProperty("labourType")) : null;
        this.metal = inits.isInitialized("metal") ? new QMetal(forProperty("metal")) : null;
        this.party = inits.isInitialized("party") ? new QParty(forProperty("party"), inits.get("party")) : null;
        this.purity = inits.isInitialized("purity") ? new QPurity(forProperty("purity"), inits.get("purity")) : null;
    }

}

