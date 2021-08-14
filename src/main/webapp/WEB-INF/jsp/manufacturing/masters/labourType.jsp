<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="option" value="LabourType" />

<c:if test="${success eq true}">
	<div class="alert alert-success">LabourType ${action}
		successfully!</div>
</c:if>


<div>
	<div id="toolbar">
		
			
			<c:choose>
			<c:when test="${canAdd}">
				<a class="btn btn-info" style="font-size: 15px" type="button"
			href="/jewels/manufacturing/masters/labourType/add.html"><span
			class="glyphicon glyphicon-plus"></span>&nbsp;Add LabourType</a>
			</c:when>
			<c:otherwise>
				<a class="btn btn-info" style="font-size: 15px" type="button"
					onClick="javascript:displayMsg(event, this)"
					href="javascript:void(0)"><span
					class="glyphicon glyphicon-plus"></span>&nbsp;Add LabourType</a>
			</c:otherwise>
		</c:choose>
			
	</div>
	<div>
		<table data-toggle="table"
			data-url="/jewels/manufacturing/masters/labourType/listing.html"
			data-toolbar="#toolbar" data-pagination="true"
			data-side-pagination="server"
			data-page-list="[5, 10, 20, 50, 100, 200]" data-search="true"
			data-striped="true" data-pagination="true" data-height="520">
			<thead>
				<tr  class="btn-primary">
					<th data-field="name" data-align="left" data-sortable="true">Labour
						Type</th>
					<th data-field="code" data-align="left" data-sortable="true">Code</th>
					<th data-field="deactive" data-sortable="true">Deactive</th>
					<th data-field="action1" data-align="center">Edit</th>
					<th data-field="action2" data-align="center">Delete</th>
				</tr>
			</thead>
		</table>
	</div>
</div>

<%@include file="/WEB-INF/jsp/common/modal.jsp"%>
