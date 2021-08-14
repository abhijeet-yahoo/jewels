package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QKtConversionMt is a Querydsl query type for KtConversionMt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QKtConversionMt extends EntityPathBase<KtConversionMt> {

    private static final long serialVersionUID = 565547078L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QKtConversionMt ktConversionMt = new QKtConversionMt("ktConversionMt");

    public final NumberPath<Double> alloyNetWt = createNumber("alloyNetWt", Double.class);

    public final DateTimePath<java.util.Date> cDate = createDateTime("cDate", java.util.Date.class);

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDt = createDateTime("createdDt", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final StringPath finYear = createString("finYear");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath invNo = createString("invNo");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity issAlloy;

    public final NumberPath<Double> issAlloyWt = createNumber("issAlloyWt", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor issColor;

    public final NumberPath<Double> issMetalWt = createNumber("issMetalWt", Double.class);

    public final NumberPath<Double> issPureWt = createNumber("issPureWt", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity issPurity;

    public final NumberPath<Double> loss = createNumber("loss", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QMetal metal;

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final NumberPath<Double> pureNetWt = createNumber("pureNetWt", Double.class);

    public final NumberPath<Double> recWt = createNumber("recWt", Double.class);

    public final NumberPath<Double> reqAlloy = createNumber("reqAlloy", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor reqColor;

    public final NumberPath<Double> reqMetalWt = createNumber("reqMetalWt", Double.class);

    public final NumberPath<Double> reqPure = createNumber("reqPure", Double.class);

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity reqPurity;

    public final NumberPath<Double> reqPurityConv = createNumber("reqPurityConv", Double.class);

    public final NumberPath<Double> scrapWt = createNumber("scrapWt", Double.class);

    public final NumberPath<Integer> srNo = createNumber("srNo", Integer.class);

    public final NumberPath<Double> totIssWt = createNumber("totIssWt", Double.class);

    public final NumberPath<Double> uIssMetalWt = createNumber("uIssMetalWt", Double.class);

    public final NumberPath<Long> uniqueId = createNumber("uniqueId", Long.class);

    public QKtConversionMt(String variable) {
        this(KtConversionMt.class, forVariable(variable), INITS);
    }

    public QKtConversionMt(Path<? extends KtConversionMt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QKtConversionMt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QKtConversionMt(PathMetadata<?> metadata, PathInits inits) {
        this(KtConversionMt.class, metadata, inits);
    }

    public QKtConversionMt(Class<? extends KtConversionMt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.issAlloy = inits.isInitialized("issAlloy") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity(forProperty("issAlloy"), inits.get("issAlloy")) : null;
        this.issColor = inits.isInitialized("issColor") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor(forProperty("issColor")) : null;
        this.issPurity = inits.isInitialized("issPurity") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity(forProperty("issPurity"), inits.get("issPurity")) : null;
        this.metal = inits.isInitialized("metal") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QMetal(forProperty("metal")) : null;
        this.reqColor = inits.isInitialized("reqColor") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor(forProperty("reqColor")) : null;
        this.reqPurity = inits.isInitialized("reqPurity") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity(forProperty("reqPurity"), inits.get("reqPurity")) : null;
    }

}

