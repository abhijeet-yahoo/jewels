package com.jiyasoft.jewelplus.domain.manufacturing.masters.reports;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QReportFormat is a Querydsl query type for ReportFormat
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QReportFormat extends EntityPathBase<ReportFormat> {

    private static final long serialVersionUID = 885909904L;

    public static final QReportFormat reportFormat = new QReportFormat("reportFormat");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final BooleanPath deactive = createBoolean("deactive");

    public final DateTimePath<java.util.Date> deactiveDt = createDateTime("deactiveDt", java.util.Date.class);

    public final StringPath filterForm = createString("filterForm");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath modiBy = createString("modiBy");

    public final DateTimePath<java.util.Date> modiDate = createDateTime("modiDate", java.util.Date.class);

    public final StringPath procedureNm = createString("procedureNm");

    public final StringPath reportHeading = createString("reportHeading");

    public final StringPath reportNm = createString("reportNm");

    public QReportFormat(String variable) {
        super(ReportFormat.class, forVariable(variable));
    }

    public QReportFormat(Path<? extends ReportFormat> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReportFormat(PathMetadata<?> metadata) {
        super(ReportFormat.class, metadata);
    }

}

