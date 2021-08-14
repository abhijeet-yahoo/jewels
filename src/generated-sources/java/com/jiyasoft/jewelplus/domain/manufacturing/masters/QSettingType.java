package com.jiyasoft.jewelplus.domain.manufacturing.masters;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QSettingType is a Querydsl query type for SettingType
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSettingType extends EntityPathBase<SettingType> {

    private static final long serialVersionUID = -1617842828L;

    public static final QSettingType settingType = new QSettingType("settingType");

    public final StringPath code = createString("code");

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final StringPath createdBy = createString("createdBy");

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final StringPath name = createString("name");

    public QSettingType(String variable) {
        super(SettingType.class, forVariable(variable));
    }

    public QSettingType(Path<? extends SettingType> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSettingType(PathMetadata<?> metadata) {
        super(SettingType.class, metadata);
    }

}

