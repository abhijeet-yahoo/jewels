<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="option" value="MeltingMt" />

<c:if test="${success eq true}">
		<div class="alert alert-success">Melting ${action} successfully!</div>
</c:if>


<div>
	<div id="toolbar">
<%-- 	<c:choose>
			<c:when test="${canAdd}">
		<a class="btn btn-info" style="font-size: 15px" type="button"
			href="/jewels/manufacturing/transactions/meltingMt/add.html"><span
			class="glyphicon glyphicon-plus"></span>&nbsp;Add</a>
			</c:when>
				<c:otherwise>
				<a class="btn btn-info" style="font-size: 15px" type="button"
					onClick="javascript:displayMsg(event, this)"
					href="javascript:void(0)"><span
					class="glyphicon glyphicon-plus"></span>&nbsp;Add</a>
		</c:otherwise>
		</c:choose> --%>
	</div>
	<div>
		<table data-toggle="table" data-url="/jewels/manufacturing/transactions/meltingMt/listing.html?opt=1"
			data-toolbar="#toolbar" data-pagination="true"
			data-side-pagination="server"
			data-page-list="[5, 10, 20, 50, 100, 200]" data-search="true"
			data-striped="true"  data-height="520" >
			<thead>
				<tr class="btn-primary">
					<th data-field="invNo" data-align="left" data-sortable="true"  >Invoice No</th>
					<th data-field="invDate" data-align="left" data-sortable="true">Invoice Date</th>
					<th data-field="loss" data-sortable="true">Loss</th>
					<th data-field="action1" data-align="center">Edit</th>
					<th data-field="action2" data-align="center">Delete</th>
					<th data-field="action3" data-align="center">View</th>
				</tr>
			</thead>
		</table>
	</div>
</div>

<%@include file="/WEB-INF/jsp/common/modal.jsp"%>
