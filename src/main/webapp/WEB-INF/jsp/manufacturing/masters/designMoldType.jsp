<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<c:set var="option" value="DesignMoldType" />

<c:if test="${success eq true}">
		<div class="alert alert-success">Design Mold Type ${action} successfully!</div>
</c:if>


<div>
	<div id="toolbar">
		<a class="btn btn-info" style="font-size: 15px" type="button"
			href="/jewels/manufacturing/masters/designMoldType/add.html"><span
			class="glyphicon glyphicon-plus"></span>&nbsp;Add DesignMoldType</a>
	</div>
	<div>
		<table data-toggle="table" data-url="/jewels/manufacturing/masters/designMoldType/listing.html"
			data-toolbar="#toolbar" data-pagination="true"
			data-side-pagination="server"
			data-page-list="[5, 10, 20, 50, 100, 200]" data-search="true"  
			data-striped="true"  data-height="520" >
			<thead>
				<tr class="btn-primary">
					<th data-field="name" data-align="left" data-sortable="true">Name</th>
					<th data-field="createdBy" data-align="left" data-sortable="true">Created By</th>
					<th data-field="createdDt" data-align="left" data-sortable="true">Created Date</th>
					<th data-field="deactive" data-sortable="true">Deactive</th>
					<th data-field="action1" data-align="center">Edit</th>
					<th data-field="action2" data-align="center">Delete</th>
				</tr>
			</thead>
		</table>
	</div>
</div>
