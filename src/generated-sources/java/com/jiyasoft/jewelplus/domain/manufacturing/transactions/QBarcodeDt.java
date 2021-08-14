package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QBarcodeDt is a Querydsl query type for BarcodeDt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QBarcodeDt extends EntityPathBase<BarcodeDt> {

    private static final long serialVersionUID = 1278730832L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBarcodeDt barcodeDt = new QBarcodeDt("barcodeDt");

    public final QBagMt bagMt;

    public final StringPath barcode = createString("barcode");

    public final QBarcodeMt barcodeMt;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor color;

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDt = createDateTime("createdDt", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QDesign design;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity purity;

    public final NumberPath<Integer> refTranMtId = createNumber("refTranMtId", Integer.class);

    public QBarcodeDt(String variable) {
        this(BarcodeDt.class, forVariable(variable), INITS);
    }

    public QBarcodeDt(Path<? extends BarcodeDt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QBarcodeDt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QBarcodeDt(PathMetadata<?> metadata, PathInits inits) {
        this(BarcodeDt.class, metadata, inits);
    }

    public QBarcodeDt(Class<? extends BarcodeDt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.bagMt = inits.isInitialized("bagMt") ? new QBagMt(forProperty("bagMt"), inits.get("bagMt")) : null;
        this.barcodeMt = inits.isInitialized("barcodeMt") ? new QBarcodeMt(forProperty("barcodeMt"), inits.get("barcodeMt")) : null;
        this.color = inits.isInitialized("color") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor(forProperty("color")) : null;
        this.design = inits.isInitialized("design") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QDesign(forProperty("design"), inits.get("design")) : null;
        this.purity = inits.isInitialized("purity") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity(forProperty("purity"), inits.get("purity")) : null;
    }

}

