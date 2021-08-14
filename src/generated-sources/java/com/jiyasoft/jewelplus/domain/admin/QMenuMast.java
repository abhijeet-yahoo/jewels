package com.jiyasoft.jewelplus.domain.admin;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QMenuMast is a Querydsl query type for MenuMast
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QMenuMast extends EntityPathBase<MenuMast> {

    private static final long serialVersionUID = -1992485508L;

    public static final QMenuMast menuMast = new QMenuMast("menuMast");

    public final BooleanPath deactive = createBoolean("deactive");

    public final BooleanPath hasChild = createBoolean("hasChild");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath menuHeading = createString("menuHeading");

    public final StringPath menuNm = createString("menuNm");

    public final NumberPath<Integer> parentId = createNumber("parentId", Integer.class);

    public final NumberPath<Integer> seqNo = createNumber("seqNo", Integer.class);

    public final StringPath url = createString("url");

    public QMenuMast(String variable) {
        super(MenuMast.class, forVariable(variable));
    }

    public QMenuMast(Path<? extends MenuMast> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMenuMast(PathMetadata<?> metadata) {
        super(MenuMast.class, metadata);
    }

}

