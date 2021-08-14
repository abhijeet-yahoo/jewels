package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QCompany is a Querydsl query type for Company
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCompany extends EntityPathBase<Company> {

    private static final long serialVersionUID = -1224805049L;

    public static final QCompany company = new QCompany("company");

    public final StringPath addressOne = createString("addressOne");

    public final StringPath addressThree = createString("addressThree");

    public final StringPath addressTwo = createString("addressTwo");

    public final StringPath bankAccountNo = createString("bankAccountNo");

    public final StringPath bankBranch = createString("bankBranch");

    public final StringPath bankNm = createString("bankNm");

    public final NumberPath<Integer> compId = createNumber("compId", Integer.class);

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final StringPath createdBy = createString("createdBy");

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final StringPath email = createString("email");

    public final DateTimePath<java.util.Date> endDate = createDateTime("endDate", java.util.Date.class);

    public final StringPath finYear = createString("finYear");

    public final StringPath gstn = createString("gstn");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath ifscCode = createString("ifscCode");

    public final BooleanPath inventory = createBoolean("inventory");

    public final BooleanPath isGroup = createBoolean("isGroup");

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final StringPath name = createString("name");

    public final StringPath panNo = createString("panNo");

    public final NumberPath<Integer> partentGroupId = createNumber("partentGroupId", Integer.class);

    public final StringPath phoneNo = createString("phoneNo");

    public final StringPath pincode = createString("pincode");

    public final BooleanPath rightClickFlg = createBoolean("rightClickFlg");

    public final StringPath schemaNm = createString("schemaNm");

    public final DateTimePath<java.util.Date> startDate = createDateTime("startDate", java.util.Date.class);

    public final StringPath state = createString("state");

    public final StringPath swiftCode = createString("swiftCode");

    public QCompany(String variable) {
        super(Company.class, forVariable(variable));
    }

    public QCompany(Path<? extends Company> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCompany(PathMetadata<?> metadata) {
        super(Company.class, metadata);
    }

}

