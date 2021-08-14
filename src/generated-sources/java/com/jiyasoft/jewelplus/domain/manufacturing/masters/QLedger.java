package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QLedger is a Querydsl query type for Ledger
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QLedger extends EntityPathBase<Ledger> {

    private static final long serialVersionUID = -1731021985L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLedger ledger = new QLedger("ledger");

    public final StringPath additionalData = createString("additionalData");

    public final StringPath address = createString("address");

    public final StringPath area = createString("area");

    public final StringPath attachedFileNm1 = createString("attachedFileNm1");

    public final StringPath attachedFileNm2 = createString("attachedFileNm2");

    public final StringPath attachedFileNm3 = createString("attachedFileNm3");

    public final StringPath cgst = createString("cgst");

    public final StringPath city = createString("city");

    public final StringPath code = createString("code");

    public final StringPath contactPerson = createString("contactPerson");

    public final QCountryMaster country;

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final StringPath creditDays = createString("creditDays");

    public final NumberPath<Double> creditLimit = createNumber("creditLimit", Double.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final StringPath emailAddress = createString("emailAddress");

    public final StringPath fax = createString("fax");

    public final StringPath gst = createString("gst");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath igst = createString("igst");

    public final QLedgerGroup ledgerGroup;

    public final StringPath mailingName = createString("mailingName");

    public final StringPath mobile = createString("mobile");

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final StringPath name = createString("name");

    public final BooleanPath nonEditable = createBoolean("nonEditable");

    public final StringPath notes = createString("notes");

    public final StringPath officePhone = createString("officePhone");

    public final StringPath panNo = createString("panNo");

    public final StringPath partyType = createString("partyType");

    public final NumberPath<Integer> perDays = createNumber("perDays", Integer.class);

    public final NumberPath<Double> rateOfInterest = createNumber("rateOfInterest", Double.class);

    public final StringPath salesMan = createString("salesMan");

    public final NumberPath<Integer> srNo = createNumber("srNo", Integer.class);

    public final QStateMaster stateMast;

    public final StringPath tanNo = createString("tanNo");

    public final StringPath tranBooking = createString("tranBooking");

    public final StringPath tranDest = createString("tranDest");

    public final StringPath transporter = createString("transporter");

    public final QLookUpMast type;

    public final StringPath webSite = createString("webSite");

    public final StringPath zip = createString("zip");

    public final StringPath zone = createString("zone");

    public QLedger(String variable) {
        this(Ledger.class, forVariable(variable), INITS);
    }

    public QLedger(Path<? extends Ledger> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QLedger(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QLedger(PathMetadata<?> metadata, PathInits inits) {
        this(Ledger.class, metadata, inits);
    }

    public QLedger(Class<? extends Ledger> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.country = inits.isInitialized("country") ? new QCountryMaster(forProperty("country")) : null;
        this.ledgerGroup = inits.isInitialized("ledgerGroup") ? new QLedgerGroup(forProperty("ledgerGroup")) : null;
        this.stateMast = inits.isInitialized("stateMast") ? new QStateMaster(forProperty("stateMast"), inits.get("stateMast")) : null;
        this.type = inits.isInitialized("type") ? new QLookUpMast(forProperty("type")) : null;
    }

}

