package com.jiyasoft.jewelplus.domain.manufacturing.masters.reports;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QDesignRpt is a Querydsl query type for DesignRpt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QDesignRpt extends EntityPathBase<DesignRpt> {

    private static final long serialVersionUID = 778020403L;

    public static final QDesignRpt designRpt = new QDesignRpt("designRpt");

    public final NumberPath<Double> carat = createNumber("carat", Double.class);

    public final NumberPath<Integer> categId = createNumber("categId", Integer.class);

    public final StringPath categNm = createString("categNm");

    public final NumberPath<Integer> collectionId = createNumber("collectionId", Integer.class);

    public final StringPath collectionNm = createString("collectionNm");

    public final NumberPath<Integer> conceptId = createNumber("conceptId", Integer.class);

    public final StringPath conceptNm = createString("conceptNm");

    public final StringPath createdBy = createString("createdBy");

    public final StringPath defaultImage = createString("defaultImage");

    public final DateTimePath<java.util.Date> designDt = createDateTime("designDt", java.util.Date.class);

    public final StringPath designGroupNm = createString("designGroupNm");

    public final NumberPath<Double> grossWt = createNumber("grossWt", Double.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> sCategId = createNumber("sCategId", Integer.class);

    public final StringPath scategNm = createString("scategNm");

    public final NumberPath<Double> silverWt = createNumber("silverWt", Double.class);

    public final NumberPath<Integer> stone = createNumber("stone", Integer.class);

    public final NumberPath<Integer> styleId = createNumber("styleId", Integer.class);

    public final StringPath styleNo = createString("styleNo");

    public final NumberPath<Integer> subConceptId = createNumber("subConceptId", Integer.class);

    public final StringPath subConceptNm = createString("subConceptNm");

    public final StringPath version = createString("version");

    public final NumberPath<Double> WaxWt = createNumber("WaxWt", Double.class);

    public QDesignRpt(String variable) {
        super(DesignRpt.class, forVariable(variable));
    }

    public QDesignRpt(Path<? extends DesignRpt> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDesignRpt(PathMetadata<?> metadata) {
        super(DesignRpt.class, metadata);
    }

}

