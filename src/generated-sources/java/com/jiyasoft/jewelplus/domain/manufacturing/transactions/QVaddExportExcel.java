package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QVaddExportExcel is a Querydsl query type for VaddExportExcel
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QVaddExportExcel extends EntityPathBase<VaddExportExcel> {

    private static final long serialVersionUID = -1425448168L;

    public static final QVaddExportExcel vaddExportExcel = new QVaddExportExcel("vaddExportExcel");

    public final NumberPath<Double> carat = createNumber("carat", Double.class);

    public final StringPath categNm = createString("categNm");

    public final StringPath colorNm = createString("colorNm");

    public final StringPath diaCut = createString("diaCut");

    public final NumberPath<Double> finalPrcPerPcs = createNumber("finalPrcPerPcs", Double.class);

    public final NumberPath<Double> finalPrice = createNumber("finalPrice", Double.class);

    public final NumberPath<Double> fob = createNumber("fob", Double.class);

    public final NumberPath<Double> grossWt = createNumber("grossWt", Double.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath intQuality = createString("intQuality");

    public final DateTimePath<java.util.Date> invDate = createDateTime("invDate", java.util.Date.class);

    public final StringPath invNo = createString("invNo");

    public final NumberPath<Integer> itemSr = createNumber("itemSr", Integer.class);

    public final NumberPath<Double> lossValue = createNumber("lossValue", Double.class);

    public final NumberPath<Double> lossWt = createNumber("lossWt", Double.class);

    public final NumberPath<Double> metalValue = createNumber("metalValue", Double.class);

    public final NumberPath<Double> netWt = createNumber("netWt", Double.class);

    public final NumberPath<Double> pcs = createNumber("pcs", Double.class);

    public final NumberPath<Double> perGramRate = createNumber("perGramRate", Double.class);

    public final StringPath purityNm = createString("purityNm");

    public final StringPath qualityNm = createString("qualityNm");

    public final StringPath refNo = createString("refNo");

    public final NumberPath<Integer> serial = createNumber("serial", Integer.class);

    public final StringPath shapeNm = createString("shapeNm");

    public final StringPath size = createString("size");

    public final StringPath sizegroupNm = createString("sizegroupNm");

    public final NumberPath<Double> stnRate = createNumber("stnRate", Double.class);

    public final NumberPath<Integer> stone = createNumber("stone", Integer.class);

    public final NumberPath<Double> stoneValue = createNumber("stoneValue", Double.class);

    public final StringPath styleNo = createString("styleNo");

    public final NumberPath<Double> totCarat = createNumber("totCarat", Double.class);

    public final NumberPath<Double> totMetalValue = createNumber("totMetalValue", Double.class);

    public final NumberPath<Integer> totStone = createNumber("totStone", Integer.class);

    public final NumberPath<Double> totStoneValue = createNumber("totStoneValue", Double.class);

    public QVaddExportExcel(String variable) {
        super(VaddExportExcel.class, forVariable(variable));
    }

    public QVaddExportExcel(Path<? extends VaddExportExcel> path) {
        super(path.getType(), path.getMetadata());
    }

    public QVaddExportExcel(PathMetadata<?> metadata) {
        super(VaddExportExcel.class, metadata);
    }

}

