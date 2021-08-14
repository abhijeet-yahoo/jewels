package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QDesign is a Querydsl query type for Design
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QDesign extends EntityPathBase<Design> {

    private static final long serialVersionUID = -1959606348L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDesign design = new QDesign("design");

    public final StringPath barCode = createString("barCode");

    public final BooleanPath bbc = createBoolean("bbc");

    public final QEmployee cadDesigner;

    public final QEmployee cadQc;

    public final QEmployee camQc;

    public final NumberPath<Double> camWt = createNumber("camWt", Double.class);

    public final BooleanPath cancel = createBoolean("cancel");

    public final NumberPath<Integer> castedPcs = createNumber("castedPcs", Integer.class);

    public final QCategory category;

    public final QCollectionMaster collectionMaster;

    public final QConcept concept;

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final StringPath createdBy = createString("createdBy");

    public final StringPath custStyleNo = createString("custStyleNo");

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final StringPath defaultImage = createString("defaultImage");

    public final NumberPath<Integer> defImgChk = createNumber("defImgChk", Integer.class);

    public final DateTimePath<java.util.Date> designDt = createDateTime("designDt", java.util.Date.class);

    public final QEmployee designerEmployee;

    public final QDesignGroup designGroup;

    public final StringPath designNo = createString("designNo");

    public final com.jiyasoft.jewelplus.domain.manufacturing.transactions.QEmailConcept emailConcept;

    public final BooleanPath exclusiveClient = createBoolean("exclusiveClient");

    public final NumberPath<Double> finishWt = createNumber("finishWt", Double.class);

    public final NumberPath<Double> grms = createNumber("grms", Double.class);

    public final NumberPath<Double> grossWt = createNumber("grossWt", Double.class);

    public final BooleanPath hold = createBoolean("hold");

    public final StringPath holdRemark = createString("holdRemark");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath image1 = createString("image1");

    public final StringPath image2 = createString("image2");

    public final StringPath image3 = createString("image3");

    public final StringPath imageCode = createString("imageCode");

    public final BooleanPath liquidMould = createBoolean("liquidMould");

    public final QLookMast lookMast;

    public final StringPath mainStyleNo = createString("mainStyleNo");

    public final QMarketTypeMast marketTypeMast;

    public final BooleanPath masterBal = createBoolean("masterBal");

    public final QEmployee merchandiser;

    public final QEmployee modelMakerEmployee;

    public final DateTimePath<java.util.Date> modelModiDt = createDateTime("modelModiDt", java.util.Date.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final BooleanPath msi = createBoolean("msi");

    public final BooleanPath mustHave = createBoolean("mustHave");

    public final StringPath oldStyleNo = createString("oldStyleNo");

    public final StringPath parentStyle = createString("parentStyle");

    public final QParty party;

    public final QPatternMast patternMast;

    public final QLookUpMast process;

    public final QProductSize productSize;

    public final QProductTypeMast productTypeMast;

    public final NumberPath<Integer> psizeId = createNumber("psizeId", Integer.class);

    public final QPurity purity;

    public final StringPath refNo = createString("refNo");

    public final StringPath remarks = createString("remarks");

    public final NumberPath<Double> rptWt = createNumber("rptWt", Double.class);

    public final BooleanPath rubberMould = createBoolean("rubberMould");

    public final NumberPath<Double> sampleWt = createNumber("sampleWt", Double.class);

    public final BooleanPath silverModel = createBoolean("silverModel");

    public final NumberPath<Double> silverWt = createNumber("silverWt", Double.class);

    public final QEmployee sizerEmployee;

    public final StringPath styleNo = createString("styleNo");

    public final QSubCategory subCategory;

    public final QSubConcept subConcept;

    public final NumberPath<Double> totCarat = createNumber("totCarat", Double.class);

    public final NumberPath<Integer> totStone = createNumber("totStone", Integer.class);

    public final NumberPath<Long> uniqueId = createNumber("uniqueId", Long.class);

    public final QParty vendor;

    public final StringPath vendorStyle = createString("vendorStyle");

    public final StringPath version = createString("version");

    public final NumberPath<Double> waxPre = createNumber("waxPre", Double.class);

    public final NumberPath<Double> waxTemp = createNumber("waxTemp", Double.class);

    public final NumberPath<Double> waxWt = createNumber("waxWt", Double.class);

    public QDesign(String variable) {
        this(Design.class, forVariable(variable), INITS);
    }

    public QDesign(Path<? extends Design> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDesign(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDesign(PathMetadata<?> metadata, PathInits inits) {
        this(Design.class, metadata, inits);
    }

    public QDesign(Class<? extends Design> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.cadDesigner = inits.isInitialized("cadDesigner") ? new QEmployee(forProperty("cadDesigner"), inits.get("cadDesigner")) : null;
        this.cadQc = inits.isInitialized("cadQc") ? new QEmployee(forProperty("cadQc"), inits.get("cadQc")) : null;
        this.camQc = inits.isInitialized("camQc") ? new QEmployee(forProperty("camQc"), inits.get("camQc")) : null;
        this.category = inits.isInitialized("category") ? new QCategory(forProperty("category")) : null;
        this.collectionMaster = inits.isInitialized("collectionMaster") ? new QCollectionMaster(forProperty("collectionMaster")) : null;
        this.concept = inits.isInitialized("concept") ? new QConcept(forProperty("concept")) : null;
        this.designerEmployee = inits.isInitialized("designerEmployee") ? new QEmployee(forProperty("designerEmployee"), inits.get("designerEmployee")) : null;
        this.designGroup = inits.isInitialized("designGroup") ? new QDesignGroup(forProperty("designGroup")) : null;
        this.emailConcept = inits.isInitialized("emailConcept") ? new com.jiyasoft.jewelplus.domain.manufacturing.transactions.QEmailConcept(forProperty("emailConcept"), inits.get("emailConcept")) : null;
        this.lookMast = inits.isInitialized("lookMast") ? new QLookMast(forProperty("lookMast")) : null;
        this.marketTypeMast = inits.isInitialized("marketTypeMast") ? new QMarketTypeMast(forProperty("marketTypeMast")) : null;
        this.merchandiser = inits.isInitialized("merchandiser") ? new QEmployee(forProperty("merchandiser"), inits.get("merchandiser")) : null;
        this.modelMakerEmployee = inits.isInitialized("modelMakerEmployee") ? new QEmployee(forProperty("modelMakerEmployee"), inits.get("modelMakerEmployee")) : null;
        this.party = inits.isInitialized("party") ? new QParty(forProperty("party"), inits.get("party")) : null;
        this.patternMast = inits.isInitialized("patternMast") ? new QPatternMast(forProperty("patternMast")) : null;
        this.process = inits.isInitialized("process") ? new QLookUpMast(forProperty("process")) : null;
        this.productSize = inits.isInitialized("productSize") ? new QProductSize(forProperty("productSize"), inits.get("productSize")) : null;
        this.productTypeMast = inits.isInitialized("productTypeMast") ? new QProductTypeMast(forProperty("productTypeMast")) : null;
        this.purity = inits.isInitialized("purity") ? new QPurity(forProperty("purity"), inits.get("purity")) : null;
        this.sizerEmployee = inits.isInitialized("sizerEmployee") ? new QEmployee(forProperty("sizerEmployee"), inits.get("sizerEmployee")) : null;
        this.subCategory = inits.isInitialized("subCategory") ? new QSubCategory(forProperty("subCategory"), inits.get("subCategory")) : null;
        this.subConcept = inits.isInitialized("subConcept") ? new QSubConcept(forProperty("subConcept"), inits.get("subConcept")) : null;
        this.vendor = inits.isInitialized("vendor") ? new QParty(forProperty("vendor"), inits.get("vendor")) : null;
    }

}

