package com.jiyasoft.jewelplus.domain.manufacturing.transactions;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QGeneralSearch is a Querydsl query type for GeneralSearch
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QGeneralSearch extends EntityPathBase<GeneralSearch> {

    private static final long serialVersionUID = 983338448L;

    public static final QGeneralSearch generalSearch = new QGeneralSearch("generalSearch");

    public final BooleanPath bagCloseFlg = createBoolean("bagCloseFlg");

    public final NumberPath<Integer> bagId = createNumber("bagId", Integer.class);

    public final StringPath bagNm = createString("bagNm");

    public final NumberPath<Integer> bagPcs = createNumber("bagPcs", Integer.class);

    public final StringPath colorNm = createString("colorNm");

    public final NumberPath<Integer> colPcs = createNumber("colPcs", Integer.class);

    public final NumberPath<Double> colWt = createNumber("colWt", Double.class);

    public final NumberPath<Integer> czPcs = createNumber("czPcs", Integer.class);

    public final NumberPath<Double> czWt = createNumber("czWt", Double.class);

    public final DateTimePath<java.util.Date> date = createDateTime("date", java.util.Date.class);

    public final StringPath deptNm = createString("deptNm");

    public final NumberPath<Integer> diaPcs = createNumber("diaPcs", Integer.class);

    public final NumberPath<Double> diaWt = createNumber("diaWt", Double.class);

    public final StringPath dtItem = createString("dtItem");

    public final StringPath dtOrdRef = createString("dtOrdRef");

    public final StringPath dtRefNo = createString("dtRefNo");

    public final DateTimePath<java.util.Date> expDate = createDateTime("expDate", java.util.Date.class);

    public final StringPath expInv = createString("expInv");

    public final NumberPath<Double> finalPrice = createNumber("finalPrice", Double.class);

    public final NumberPath<Double> grossWt = createNumber("grossWt", Double.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath itemNo = createString("itemNo");

    public final StringPath orderNo = createString("orderNo");

    public final StringPath orderRefNo = createString("orderRefNo");

    public final StringPath orderType = createString("orderType");

    public final NumberPath<Integer> ordPcs = createNumber("ordPcs", Integer.class);

    public final StringPath partyNm = createString("partyNm");

    public final StringPath purityNm = createString("purityNm");

    public final StringPath sizeNm = createString("sizeNm");

    public final StringPath styleNo = createString("styleNo");

    public QGeneralSearch(String variable) {
        super(GeneralSearch.class, forVariable(variable));
    }

    public QGeneralSearch(Path<? extends GeneralSearch> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGeneralSearch(PathMetadata<?> metadata) {
        super(GeneralSearch.class, metadata);
    }

}

