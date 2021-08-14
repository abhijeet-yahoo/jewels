package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QBagMt is a Querydsl query type for BagMt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QBagMt extends EntityPathBase<BagMt> {

    private static final long serialVersionUID = -495303441L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBagMt bagMt = new QBagMt("bagMt");

    public final BooleanPath bagCloseFlg = createBoolean("bagCloseFlg");

    public final StringPath barcode = createString("barcode");

    public final NumberPath<Integer> barcodeSrNo = createNumber("barcodeSrNo", Integer.class);

    public final StringPath barcodeType = createString("barcodeType");

    public final BooleanPath costingFlg = createBoolean("costingFlg");

    public final NumberPath<Integer> costingMtId = createNumber("costingMtId", Integer.class);

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final StringPath huId = createString("huId");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath itemNo = createString("itemNo");

    public final DateTimePath<java.util.Date> itemNoDate = createDateTime("itemNoDate", java.util.Date.class);

    public final NumberPath<Integer> itemNoDeptId = createNumber("itemNoDeptId", Integer.class);

    public final BooleanPath jobWorkFlg = createBoolean("jobWorkFlg");

    public final StringPath mainParentBagNm = createString("mainParentBagNm");

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final StringPath name = createString("name");

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QOrderDt orderDt;

    public final com.jiyasoft.jewelplus.domain.manufacturing.masters.QOrderMt orderMt;

    public final StringPath parentBagNm = createString("parentBagNm");

    public final NumberPath<Double> qty = createNumber("qty", Double.class);

    public final BooleanPath recast = createBoolean("recast");

    public final NumberPath<Integer> seqNo = createNumber("seqNo", Integer.class);

    public final BooleanPath split = createBoolean("split");

    public final NumberPath<Integer> srNo = createNumber("srNo", Integer.class);

    public QBagMt(String variable) {
        this(BagMt.class, forVariable(variable), INITS);
    }

    public QBagMt(Path<? extends BagMt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QBagMt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QBagMt(PathMetadata<?> metadata, PathInits inits) {
        this(BagMt.class, metadata, inits);
    }

    public QBagMt(Class<? extends BagMt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.orderDt = inits.isInitialized("orderDt") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QOrderDt(forProperty("orderDt"), inits.get("orderDt")) : null;
        this.orderMt = inits.isInitialized("orderMt") ? new com.jiyasoft.jewelplus.domain.manufacturing.masters.QOrderMt(forProperty("orderMt"), inits.get("orderMt")) : null;
    }

}

