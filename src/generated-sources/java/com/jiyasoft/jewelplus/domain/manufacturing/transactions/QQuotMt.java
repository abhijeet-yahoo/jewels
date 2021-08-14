package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QQuotMt is a Querydsl query type for QuotMt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QQuotMt extends EntityPathBase<QuotMt> {

    private static final long serialVersionUID = -2021322384L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQuotMt quotMt = new QQuotMt("quotMt");

    public final NumberPath<Double> addPercent = createNumber("addPercent", Double.class);

    public final StringPath authSign = createString("authSign");

    public final StringPath bank = createString("bank");

    public final StringPath cif = createString("cif");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor color;

    public final StringPath countryOfFinalDestination = createString("countryOfFinalDestination");

    public final StringPath countryOfGoods = createString("countryOfGoods");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final StringPath description = createString("description");

    public final NumberPath<Double> discPercent = createNumber("discPercent", Double.class);

    public final NumberPath<Double> dispPercent = createNumber("dispPercent", Double.class);

    public final NumberPath<Double> exchangeRate = createNumber("exchangeRate", Double.class);

    public final BooleanPath expClose = createBoolean("expClose");

    public final StringPath finalDestination = createString("finalDestination");

    public final StringPath finYear = createString("finYear");

    public final StringPath flightNo = createString("flightNo");

    public final NumberPath<Double> frieght = createNumber("frieght", Double.class);

    public final StringPath grNo = createString("grNo");

    public final NumberPath<Double> handlingPerc = createNumber("handlingPerc", Double.class);

    public final StringPath hawb = createString("hawb");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final BooleanPath in999 = createBoolean("in999");

    public final NumberPath<Double> insuranceAmount = createNumber("insuranceAmount", Double.class);

    public final StringPath insuredBy = createString("insuredBy");

    public final DateTimePath<java.util.Date> invDate = createDateTime("invDate", java.util.Date.class);

    public final StringPath invNo = createString("invNo");

    public final StringPath itcCode = createString("itcCode");

    public final NumberPath<Double> loanGold = createNumber("loanGold", Double.class);

    public final BooleanPath masterFlg = createBoolean("masterFlg");

    public final StringPath mawb = createString("mawb");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QMetal metal;

    public final NumberPath<Double> metalRate = createNumber("metalRate", Double.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final StringPath notes = createString("notes");

    public final StringPath otherRemark = createString("otherRemark");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QParty party;

    public final StringPath partyNm = createString("partyNm");

    public final StringPath payTerm = createString("payTerm");

    public final StringPath portOfDischarge = createString("portOfDischarge");

    public final StringPath portOfLoading = createString("portOfLoading");

    public final StringPath preCarriage = createString("preCarriage");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity purity;

    public final StringPath refNo = createString("refNo");

    public final StringPath remark = createString("remark");

    public final StringPath repairRemark = createString("repairRemark");

    public final BooleanPath rLock = createBoolean("rLock");

    public final StringPath sbNo = createString("sbNo");

    public final NumberPath<Double> silverRate = createNumber("silverRate", Double.class);

    public final NumberPath<Long> srNo = createNumber("srNo", Long.class);

    public final StringPath tagColor = createString("tagColor");

    public final NumberPath<Double> tagPercent = createNumber("tagPercent", Double.class);

    public final NumberPath<Long> uniqueId = createNumber("uniqueId", Long.class);

    public final NumberPath<Double> vAddMtlRate = createNumber("vAddMtlRate", Double.class);

    public final NumberPath<Double> valueAdd = createNumber("valueAdd", Double.class);

    public QQuotMt(String variable) {
        this(QuotMt.class, forVariable(variable), INITS);
    }

    public QQuotMt(Path<? extends QuotMt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QQuotMt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QQuotMt(PathMetadata<?> metadata, PathInits inits) {
        this(QuotMt.class, metadata, inits);
    }

    public QQuotMt(Class<? extends QuotMt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.color = inits.isInitialized("color") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor(forProperty("color")) : null;
        this.metal = inits.isInitialized("metal") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QMetal(forProperty("metal")) : null;
        this.party = inits.isInitialized("party") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QParty(forProperty("party"), inits.get("party")) : null;
        this.purity = inits.isInitialized("purity") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity(forProperty("purity"), inits.get("purity")) : null;
    }

}

