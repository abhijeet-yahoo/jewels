package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QCostingMt is a Querydsl query type for CostingMt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCostingMt extends EntityPathBase<CostingMt> {

    private static final long serialVersionUID = -530224484L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCostingMt costingMt = new QCostingMt("costingMt");

    public final NumberPath<Double> addPercent = createNumber("addPercent", Double.class);

    public final NumberPath<Double> alloyRate = createNumber("alloyRate", Double.class);

    public final StringPath authSign = createString("authSign");

    public final StringPath bank = createString("bank");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QParty buyer;

    public final StringPath cif = createString("cif");

    public final StringPath countryOfFinalDestination = createString("countryOfFinalDestination");

    public final StringPath countryOfGoods = createString("countryOfGoods");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final StringPath description = createString("description");

    public final BooleanPath directParcel = createBoolean("directParcel");

    public final NumberPath<Double> disc = createNumber("disc", Double.class);

    public final NumberPath<Double> discountPerc = createNumber("discountPerc", Double.class);

    public final NumberPath<Double> dispPercent = createNumber("dispPercent", Double.class);

    public final NumberPath<Double> dutyPerc = createNumber("dutyPerc", Double.class);

    public final NumberPath<Double> exchangeRate = createNumber("exchangeRate", Double.class);

    public final BooleanPath expClose = createBoolean("expClose");

    public final StringPath finalDestination = createString("finalDestination");

    public final StringPath finYear = createString("finYear");

    public final StringPath flightNo = createString("flightNo");

    public final NumberPath<Double> frieght = createNumber("frieght", Double.class);

    public final StringPath grNo = createString("grNo");

    public final StringPath hawb = createString("hawb");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final BooleanPath in999 = createBoolean("in999");

    public final NumberPath<Double> insuranceAmount = createNumber("insuranceAmount", Double.class);

    public final StringPath insuredBy = createString("insuredBy");

    public final DateTimePath<java.util.Date> invDate = createDateTime("invDate", java.util.Date.class);

    public final StringPath invNo = createString("invNo");

    public final StringPath inWords = createString("inWords");

    public final StringPath itcCode = createString("itcCode");

    public final NumberPath<Double> loanGold = createNumber("loanGold", Double.class);

    public final NumberPath<Double> lossPercMt = createNumber("lossPercMt", Double.class);

    public final StringPath mawb = createString("mawb");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QMetal metal;

    public final NumberPath<Double> metalRate = createNumber("metalRate", Double.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final StringPath notes = createString("notes");

    public final StringPath otherRemark = createString("otherRemark");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QParty party;

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

    public final NumberPath<Double> vAddAlloyRate = createNumber("vAddAlloyRate", Double.class);

    public final NumberPath<Double> vAdded = createNumber("vAdded", Double.class);

    public final NumberPath<Double> vAddGoldRate = createNumber("vAddGoldRate", Double.class);

    public final BooleanPath vaddIn999 = createBoolean("vaddIn999");

    public final NumberPath<Double> vAddMtlRate = createNumber("vAddMtlRate", Double.class);

    public final NumberPath<Double> vAddSilverRate = createNumber("vAddSilverRate", Double.class);

    public final NumberPath<Double> vAddWastage = createNumber("vAddWastage", Double.class);

    public final NumberPath<Double> valueAdd = createNumber("valueAdd", Double.class);

    public QCostingMt(String variable) {
        this(CostingMt.class, forVariable(variable), INITS);
    }

    public QCostingMt(Path<? extends CostingMt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCostingMt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCostingMt(PathMetadata<?> metadata, PathInits inits) {
        this(CostingMt.class, metadata, inits);
    }

    public QCostingMt(Class<? extends CostingMt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.buyer = inits.isInitialized("buyer") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QParty(forProperty("buyer"), inits.get("buyer")) : null;
        this.metal = inits.isInitialized("metal") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QMetal(forProperty("metal")) : null;
        this.party = inits.isInitialized("party") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QParty(forProperty("party"), inits.get("party")) : null;
    }

}

