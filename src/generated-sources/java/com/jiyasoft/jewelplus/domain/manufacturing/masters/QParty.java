package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QParty is a Querydsl query type for Party
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QParty extends EntityPathBase<Party> {

    private static final long serialVersionUID = 1610317360L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QParty party = new QParty("party");

    public final StringPath address = createString("address");

    public final ListPath<Address, QAddress> addressList = this.<Address, QAddress>createList("addressList", Address.class, QAddress.class, PathInits.DIRECT2);

    public final StringPath area = createString("area");

    public final StringPath cgst = createString("cgst");

    public final StringPath city = createString("city");

    public final StringPath contactPerson = createString("contactPerson");

    public final QCountryMaster country;

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createDt = createDateTime("createDt", java.util.Date.class);

    public final StringPath creditDays = createString("creditDays");

    public final NumberPath<Double> creditLimit = createNumber("creditLimit", Double.class);

    public final QLookUpMast customerType;

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final BooleanPath defaultFlag = createBoolean("defaultFlag");

    public final StringPath diaRateType = createString("diaRateType");

    public final StringPath diaWtType = createString("diaWtType");

    public final NumberPath<Double> discount = createNumber("discount", Double.class);

    public final NumberPath<Double> discountPercent = createNumber("discountPercent", Double.class);

    public final StringPath email = createString("email");

    public final StringPath emailAddress = createString("emailAddress");

    public final QEmployee employee;

    public final BooleanPath exportClient = createBoolean("exportClient");

    public final StringPath fax = createString("fax");

    public final StringPath gst = createString("gst");

    public final StringPath hdlgRateType = createString("hdlgRateType");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath igst = createString("igst");

    public final StringPath labRateType = createString("labRateType");

    public final QLedgerGroup ledgerGroup;

    public final StringPath mailingName = createString("mailingName");

    public final NumberPath<Double> metalWtAddPerc = createNumber("metalWtAddPerc", Double.class);

    public final StringPath metalWtType = createString("metalWtType");

    public final StringPath mobile = createString("mobile");

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final StringPath name = createString("name");

    public final BooleanPath nonEditable = createBoolean("nonEditable");

    public final StringPath notes = createString("notes");

    public final StringPath officePhone = createString("officePhone");

    public final StringPath panNo = createString("panNo");

    public final StringPath parentParty = createString("parentParty");

    public final StringPath partyCode = createString("partyCode");

    public final QLookUpMast partyRegion;

    public final StringPath partyType = createString("partyType");

    public final NumberPath<Integer> perDays = createNumber("perDays", Integer.class);

    public final StringPath phone = createString("phone");

    public final NumberPath<Double> rateOfInterest = createNumber("rateOfInterest", Double.class);

    public final StringPath salesMan = createString("salesMan");

    public final StringPath setRateType = createString("setRateType");

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

    public QParty(String variable) {
        this(Party.class, forVariable(variable), INITS);
    }

    public QParty(Path<? extends Party> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QParty(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QParty(PathMetadata<?> metadata, PathInits inits) {
        this(Party.class, metadata, inits);
    }

    public QParty(Class<? extends Party> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.country = inits.isInitialized("country") ? new QCountryMaster(forProperty("country")) : null;
        this.customerType = inits.isInitialized("customerType") ? new QLookUpMast(forProperty("customerType")) : null;
        this.employee = inits.isInitialized("employee") ? new QEmployee(forProperty("employee"), inits.get("employee")) : null;
        this.ledgerGroup = inits.isInitialized("ledgerGroup") ? new QLedgerGroup(forProperty("ledgerGroup")) : null;
        this.partyRegion = inits.isInitialized("partyRegion") ? new QLookUpMast(forProperty("partyRegion")) : null;
        this.stateMast = inits.isInitialized("stateMast") ? new QStateMaster(forProperty("stateMast"), inits.get("stateMast")) : null;
        this.type = inits.isInitialized("type") ? new QLookUpMast(forProperty("type")) : null;
    }

}

