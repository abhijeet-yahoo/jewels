package com.jiyasoft.jewelplus.domain.admin;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QEmailConfig is a Querydsl query type for EmailConfig
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QEmailConfig extends EntityPathBase<EmailConfig> {

    private static final long serialVersionUID = 1694211190L;

    public static final QEmailConfig emailConfig = new QEmailConfig("emailConfig");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDt = createDateTime("createdDt", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final StringPath fromEmailId = createString("fromEmailId");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath message = createString("message");

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDt = createDateTime("modiDt", java.util.Date.class);

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final StringPath subject = createString("subject");

    public final StringPath toEmailId = createString("toEmailId");

    public QEmailConfig(String variable) {
        super(EmailConfig.class, forVariable(variable));
    }

    public QEmailConfig(Path<? extends EmailConfig> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEmailConfig(PathMetadata<?> metadata) {
        super(EmailConfig.class, metadata);
    }

}

