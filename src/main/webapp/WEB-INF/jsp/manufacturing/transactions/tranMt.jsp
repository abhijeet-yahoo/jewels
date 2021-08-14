<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<c:set var="option" value="TranMt" />

<div>
	<div id="toolbar">
		<a class="btn btn-info" style="font-size: 15px" type="button"
			href="<spring:url value='/manufacturing/transactions/tranMt/add.html' />"><span
			class="glyphicon glyphicon-plus"></span>&nbsp;Add TranMt</a>
	</div>
	<div>
		<table id="tableId" data-toggle="table"
			data-url="<spring:url value='/manufacturing/transactions/tranMt/listing.html' />"
			data-toolbar="#toolbar" data-pagination="true"
			data-side-pagination="server" data-search="true" data-striped="true"
			data-height="520" data-striped="true">
			<thead>
				<tr class="btn-primary">
					<th data-field="id" data-align="left" data-sortable="true">Id</th>
					<th data-field="deactive" data-sortable="true">Status</th>
					<th data-field="action1" data-align="center">Edit</th>
					<th data-field="action2" data-align="center">Deactivate</th>
				</tr>
			</thead>
		</table>
	</div>
</div>

<%@include file="/WEB-INF/jsp/common/modal.jsp"%>
