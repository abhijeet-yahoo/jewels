package com.jiyasoft.jewelplus.domain.admin;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QIPAddressAccess is a Querydsl query type for IPAddressAccess
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QIPAddressAccess extends EntityPathBase<IPAddressAccess> {

    private static final long serialVersionUID = -1053407447L;

    public static final QIPAddressAccess iPAddressAccess = new QIPAddressAccess("iPAddressAccess");

    public final NumberPath<Integer> ipAddressAccessId = createNumber("ipAddressAccessId", Integer.class);

    public final StringPath staticIP = createString("staticIP");

    public QIPAddressAccess(String variable) {
        super(IPAddressAccess.class, forVariable(variable));
    }

    public QIPAddressAccess(Path<? extends IPAddressAccess> path) {
        super(path.getType(), path.getMetadata());
    }

    public QIPAddressAccess(PathMetadata<?> metadata) {
        super(IPAddressAccess.class, metadata);
    }

}

