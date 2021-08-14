<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="option" value="StnBflMt" />

<c:if test="${success eq true}">
		<div class="alert alert-success">StnBflMt ${action} successfully!</div>
</c:if>

<div>
	<div id="toolbar">
		<a class="btn btn-info" style="font-size: 15px" type="button"
			href="/jewels/manufacturing/transactions/stnBflMt/add.html"><span
			class="glyphicon glyphicon-plus"></span>&nbsp;Add New</a>
	</div>
	<div>
		<table data-toggle="table" data-url="/jewels/manufacturing/transactions/stnBflMt/listing.html"
			data-toolbar="#toolbar" data-pagination="true"
			data-side-pagination="server"
			data-search="true"
			data-height="550" >
			<thead>
				<tr>
					<th data-field="invNo" data-align="left" data-sortable="true">Inv No</th>
					<th data-field="invDate" data-align="left" data-sortable="true">Inv Date</th>
					<th data-field="remark" data-align="left" data-sortable="true">Remark</th>
					<th data-field="action1" data-align="center">Edit</th>
					
				</tr>
			</thead>
		</table>
	</div>
</div>

<%@include file="/WEB-INF/jsp/common/modal.jsp"%>