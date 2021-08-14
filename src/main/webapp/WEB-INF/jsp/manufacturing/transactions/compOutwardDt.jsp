<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%-- @ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" --%>

<c:set var="option" value="CompOutwardDt" />

<div class="container">
	<div id="toolbar">
		<a class="btn btn-info" style="font-size: 15px" type="button"
			href="/jewels/manufacturing/transactions/compOutwardDt/add.html"><span
			class="glyphicon glyphicon-plus"></span>&nbsp;Add CompOutwardDt</a>
	</div>
	<div>
		<table data-toggle="table"
			data-url="/jewels/manufacturing/transactions/compOutwardDt/listing.html"
			data-toolbar="#toolbar" data-pagination="true"
			data-side-pagination="server"
			data-page-list="[5, 10, 20, 50, 100, 200]" data-search="true"
			data-height="520" data-striped="true">
			<thead>
				<tr class="btn-primary">
					<th data-field="metal" data-sortable="true">Metal</th>
					<th data-field="component" data-sortable="true">Component</th>
					<th data-field="purity" data-align="left">Purity</th>
					<th data-field="color" data-sortable="true">Color</th>
					<th data-field="invWt" data-sortable="true">InvoiceWt</th>
					<th data-field="metalWt" data-sortable="true">MetalWt</th>
					<th data-field="rate" data-sortable="true">Rate</th>
					<th data-field="amount" data-sortable="true">Amount</th>
					<th data-field="deactive" data-sortable="true">Deactive</th>
					<th data-field="action1" data-align="center">Edit</th>
					<th data-field="action2" data-align="center">Delete</th>
				</tr>
			</thead>
		</table>
	</div>
</div>

<%@include file="/WEB-INF/jsp/common/modal.jsp"%>