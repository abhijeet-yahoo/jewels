<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>




<div class="container">
	<div id="toolbar">
		<a class="btn btn-info" style="font-size: 15px" type="button"
			href="/jewels/manufacturing/transactions/metalPurchaseDt/add.html"><span
			class="glyphicon glyphicon-plus"></span>&nbsp;Add</a>
	</div>
	<div>
		<table data-toggle="table"
			data-url="/jewels/manufacturing/transactions/metalPurchaseDt/listing.html"
			data-toolbar="#toolbar" data-pagination="true"
			data-side-pagination="server"
			data-page-list="[5, 10, 20, 50, 100, 200]" 
			data-height="520" data-striped="true">
			<thead>
				<tr class="btn-warning">
					<th data-field="metal" data-sortable="true">Metal</th>
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