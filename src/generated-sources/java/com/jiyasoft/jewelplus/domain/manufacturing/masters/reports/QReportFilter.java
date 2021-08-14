package com.jiyasoft.jewelplus.domain.manufacturing.masters.reports;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QReportFilter is a Querydsl query type for ReportFilter
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QReportFilter extends EntityPathBase<ReportFilter> {

    private static final long serialVersionUID = 880196881L;

    public static final QReportFilter reportFilter = new QReportFilter("reportFilter");

    public final StringPath compulsoryFilter = createString("compulsoryFilter");

    public final StringPath filterForm = createString("filterForm");

    public final StringPath filterName = createString("filterName");

    public final StringPath groupList = createString("groupList");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath mandatoryFilter = createString("mandatoryFilter");

    public final StringPath name = createString("name");

    public final StringPath procedureNm = createString("procedureNm");

    public final StringPath xml = createString("xml");

    public QReportFilter(String variable) {
        super(ReportFilter.class, forVariable(variable));
    }

    public QReportFilter(Path<? extends ReportFilter> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReportFilter(PathMetadata<?> metadata) {
        super(ReportFilter.class, metadata);
    }

}

