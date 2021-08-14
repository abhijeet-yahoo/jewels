package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QComponent is a Querydsl query type for Component
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QComponent extends EntityPathBase<Component> {

    private static final long serialVersionUID = -203699385L;

    public static final QComponent component = new QComponent("component");

    public final BooleanPath chargable = createBoolean("chargable");

    public final StringPath code = createString("code");

    public final NumberPath<Integer> compId = createNumber("compId", Integer.class);

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final StringPath name = createString("name");

    public final BooleanPath notMetalFlg = createBoolean("notMetalFlg");

    public final StringPath remark = createString("remark");

    public final NumberPath<Double> waxWt = createNumber("waxWt", Double.class);

    public QComponent(String variable) {
        super(Component.class, forVariable(variable));
    }

    public QComponent(Path<? extends Component> path) {
        super(path.getType(), path.getMetadata());
    }

    public QComponent(PathMetadata<?> metadata) {
        super(Component.class, metadata);
    }

}

