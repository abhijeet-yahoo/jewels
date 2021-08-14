package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QCostingJobMt is a Querydsl query type for CostingJobMt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCostingJobMt extends EntityPathBase<CostingJobMt> {

    private static final long serialVersionUID = 969289007L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCostingJobMt costingJobMt = new QCostingJobMt("costingJobMt");

    public final NumberPath<Double> addPercent = createNumber("addPercent", Double.class);

    public final StringPath authSign = createString("authSign");

    public final StringPath bank = createString("bank");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QParty buyer;

    public final StringPath cif = createString("cif");

    public final StringPath countryOfFinalDestination = createString("countryOfFinalDestination");

    public final StringPath countryOfGoods = createString("countryOfGoods");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDt = createDateTime("createdDt", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final StringPath desCription = createString("desCription");

    public final NumberPath<Double> dispPercent = createNumber("dispPercent", Double.class);

    public final BooleanPath expClose = createBoolean("expClose");

    public final StringPath finalDestination = createString("finalDestination");

    public final StringPath finYear = createString("finYear");

    public final StringPath flightNo = createString("flightNo");

    public final NumberPath<Double> frieght = createNumber("frieght", Double.class);

    public final StringPath grNo = createString("grNo");

    public final StringPath hawb = createString("hawb");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final BooleanPath iN999 = createBoolean("iN999");

    public final NumberPath<Double> insuranceAmount = createNumber("insuranceAmount", Double.class);

    public final StringPath insuredBy = createString("insuredBy");

    public final DateTimePath<java.util.Date> invDate = createDateTime("invDate", java.util.Date.class);

    public final StringPath invNo = createString("invNo");

    public final StringPath InWords = createString("InWords");

    public final StringPath itcCode = createString("itcCode");

    public final NumberPath<Double> loanGold = createNumber("loanGold", Double.class);

    public final StringPath mawb = createString("mawb");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QMetal metal;

    public final NumberPath<Double> metalRate = createNumber("metalRate", Double.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final StringPath notes = createString("notes");

    public final StringPath otherRemark = createString("otherRemark");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QParty party;

    public final StringPath partyNm = createString("partyNm");

    public final StringPath payTerm = createString("payTerm");

    public final StringPath portOfDischarge = createString("portOfDischarge");

    public final StringPath portOfLoading = createString("portOfLoading");

    public final StringPath preCarriage = createString("preCarriage");

    public final StringPath remark = createString("remark");

    public final StringPath repairRemark = createString("repairRemark");

    public final StringPath sbNo = createString("sbNo");

    public final NumberPath<Double> silverRate = createNumber("silverRate", Double.class);

    public final StringPath tagColor = createString("tagColor");

    public final NumberPath<Double> tagPercent = createNumber("tagPercent", Double.class);

    public final NumberPath<Long> uniqueId = createNumber("uniqueId", Long.class);

    public final NumberPath<Double> vAdded = createNumber("vAdded", Double.class);

    public final NumberPath<Double> vAddGoldRate = createNumber("vAddGoldRate", Double.class);

    public final NumberPath<Double> vaddMtlRate = createNumber("vaddMtlRate", Double.class);

    public final NumberPath<Double> vAddSilverRate = createNumber("vAddSilverRate", Double.class);

    public final NumberPath<Double> vaddWastage = createNumber("vaddWastage", Double.class);

    public final NumberPath<Double> valueAdd = createNumber("valueAdd", Double.class);

    public QCostingJobMt(String variable) {
        this(CostingJobMt.class, forVariable(variable), INITS);
    }

    public QCostingJobMt(Path<? extends CostingJobMt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCostingJobMt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCostingJobMt(PathMetadata<?> metadata, PathInits inits) {
        this(CostingJobMt.class, metadata, inits);
    }

    public QCostingJobMt(Class<? extends CostingJobMt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.buyer = inits.isInitialized("buyer") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QParty(forProperty("buyer"), inits.get("buyer")) : null;
        this.metal = inits.isInitialized("metal") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QMetal(forProperty("metal")) : null;
        this.party = inits.isInitialized("party") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QParty(forProperty("party"), inits.get("party")) : null;
    }

}

