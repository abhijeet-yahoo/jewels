<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="option" value="StoneInwardDt" />

<div class="container">
	<div id="toolbar">
		<a class="btn btn-info" style="font-size: 15px" type="button"
			href="/jewels/manufacturing/transactions/stoneInwardDt/add.html"><span
			class="glyphicon glyphicon-plus"></span>&nbsp;Add StoneInwardDt</a>
	</div>
	<div>
		<table data-toggle="table"
			data-url="/jewels/manufacturing/transactions/stoneInwardDt/listing.html"
			data-toolbar="#toolbar" data-pagination="true"
			data-side-pagination="server"
			data-page-list="[5, 10, 20, 50, 100, 200]" data-search="true"
			data-height="520" data-striped="true">
			<thead>
				<tr class="btn-primary">
					<th data-field="stoneType" data-sortable="true">StoneType</th>
					<th data-field="shape" data-align="left">Shape</th>
					<th data-field="subShape" data-align="left">SubShape</th>
					<th data-field="quality" data-sortable="true">Quality</th>
					<th data-field="stoneChart" data-sortable="true">Size</th>
					<th data-field="sieve" data-sortable="true">Sieve</th>
					<th data-field="sizeGroupStr" data-sortable="true">Size Group</th>
					<th data-field="stone" data-sortable="true">Stone</th>
					<th data-field="carat" data-sortable="true">Carat</th>
					<th data-field="diffCarat" data-sortable="true">Diff Carat</th>
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