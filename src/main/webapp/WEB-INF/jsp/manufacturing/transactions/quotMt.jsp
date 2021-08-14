<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@include file="/WEB-INF/jsp/common/modal.jsp"%>

<c:set var="option" value="CostingMt" />

<c:if test="${success eq true}">
		<div class="alert alert-success">CostingMt ${action} successfully!</div>
</c:if>

<div>
	<div id="toolbar">
		<a class="btn btn-info" style="font-size: 15px" type="button"
			href="/jewels/manufacturing/transactions/quotMt/add.html"><span
			class="glyphicon glyphicon-plus"></span>&nbsp;New Quotation</a>
	</div>
	<div>
		<table data-toggle="table" data-url="/jewels/manufacturing/transactions/quotMt/listing.html"
			data-toolbar="#toolbar" data-pagination="true"
			data-side-pagination="server"
			data-search="true"  
			data-height="550" >
			<thead>
				<tr class="btn-primary">
					<th data-field="party" data-align="left" data-sortable="true">Party</th>
					<th data-field="invNo" data-align="left" data-sortable="true">Quot No</th>
					<th data-field="invDate" data-align="left" data-sortable="true">Quot Date</th>
					<th data-field="refNo" data-align="left" data-sortable="true">Ref No</th>
					<th data-field="masterQuot" data-align="left" data-sortable="true">Master Quot</th>
					<th data-field="action1" data-align="center">Edit</th>
					<th data-field="action2" data-align="center">Delete</th>
					<th data-field="action3" data-align="center">View</th>
				</tr>
			</thead>
		</table>
	</div>
</div>

