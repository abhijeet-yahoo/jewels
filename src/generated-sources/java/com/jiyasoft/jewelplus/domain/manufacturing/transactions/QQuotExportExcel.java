package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QQuotExportExcel is a Querydsl query type for QuotExportExcel
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QQuotExportExcel extends EntityPathBase<QuotExportExcel> {

    private static final long serialVersionUID = 1238347194L;

    public static final QQuotExportExcel quotExportExcel = new QQuotExportExcel("quotExportExcel");

    public final StringPath barcode = createString("barcode");

    public final NumberPath<Double> carat = createNumber("carat", Double.class);

    public final NumberPath<Double> certify = createNumber("certify", Double.class);

    public final NumberPath<Double> cfpl = createNumber("cfpl", Double.class);

    public final StringPath colorNm = createString("colorNm");

    public final NumberPath<Double> compValue = createNumber("compValue", Double.class);

    public final NumberPath<Double> discAmount = createNumber("discAmount", Double.class);

    public final NumberPath<Double> finalPrice = createNumber("finalPrice", Double.class);

    public final NumberPath<Double> fob = createNumber("fob", Double.class);

    public final NumberPath<Double> grossWt = createNumber("grossWt", Double.class);

    public final NumberPath<Double> handlingRate = createNumber("handlingRate", Double.class);

    public final NumberPath<Double> handlingValue = createNumber("handlingValue", Double.class);

    public final NumberPath<Double> hdlgValue = createNumber("hdlgValue", Double.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final DateTimePath<java.util.Date> invDate = createDateTime("invDate", java.util.Date.class);

    public final StringPath invNo = createString("invNo");

    public final NumberPath<Double> labValue = createNumber("labValue", Double.class);

    public final NumberPath<Double> lossValue = createNumber("lossValue", Double.class);

    public final NumberPath<Double> metalValue = createNumber("metalValue", Double.class);

    public final NumberPath<Double> mil = createNumber("mil", Double.class);

    public final NumberPath<Double> netWt = createNumber("netWt", Double.class);

    public final NumberPath<Double> pcs = createNumber("pcs", Double.class);

    public final NumberPath<Double> perGramRate = createNumber("perGramRate", Double.class);

    public final NumberPath<Double> perPcFinalPrice = createNumber("perPcFinalPrice", Double.class);

    public final StringPath purityNm = createString("purityNm");

    public final StringPath qualityNm = createString("qualityNm");

    public final NumberPath<Double> rhd = createNumber("rhd", Double.class);

    public final NumberPath<Integer> serial = createNumber("serial", Integer.class);

    public final StringPath setNm = createString("setNm");

    public final NumberPath<Double> setRate = createNumber("setRate", Double.class);

    public final StringPath settypeNm = createString("settypeNm");

    public final NumberPath<Double> setVal = createNumber("setVal", Double.class);

    public final NumberPath<Double> setValue = createNumber("setValue", Double.class);

    public final StringPath shapeNm = createString("shapeNm");

    public final StringPath sieve = createString("sieve");

    public final StringPath size = createString("size");

    public final StringPath sizegroupNm = createString("sizegroupNm");

    public final NumberPath<Double> solder = createNumber("solder", Double.class);

    public final NumberPath<Double> stnRate = createNumber("stnRate", Double.class);

    public final NumberPath<Integer> stone = createNumber("stone", Integer.class);

    public final StringPath stonetypeNm = createString("stonetypeNm");

    public final NumberPath<Double> stoneVal = createNumber("stoneVal", Double.class);

    public final NumberPath<Double> stoneValue = createNumber("stoneValue", Double.class);

    public final StringPath styleNo = createString("styleNo");

    public final StringPath subShapeCode = createString("subShapeCode");

    public final NumberPath<Double> totCarat = createNumber("totCarat", Double.class);

    public final NumberPath<Integer> totStone = createNumber("totStone", Integer.class);

    public QQuotExportExcel(String variable) {
        super(QuotExportExcel.class, forVariable(variable));
    }

    public QQuotExportExcel(Path<? extends QuotExportExcel> path) {
        super(path.getType(), path.getMetadata());
    }

    public QQuotExportExcel(PathMetadata<?> metadata) {
        super(QuotExportExcel.class, metadata);
    }

}

