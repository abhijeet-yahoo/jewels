<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<c:set var="option" value="voucherTrfMt" />


<div>
	<div id="toolbar">
		<a class="btn btn-info" style="font-size: 15px" type="button"
			href="/jewels/manufacturing/transactions/voucherTrfMt/add.html"><span
			class="glyphicon glyphicon-plus"></span>&nbsp;Add Voucher</a>
	</div>
	<div>
		<table data-toggle="table" data-url="/jewels/manufacturing/transactions/voucherTrfMt/listing.html"
			data-toolbar="#toolbar" data-pagination="true"
			data-side-pagination="server"
			data-page-list="[5, 10, 20, 50, 100, 200]" data-search="true"  
			data-striped="true"  data-height="460" >
			<thead>
				<tr class="btn-primary">
					<th data-field="voucherNo" data-align="left" data-sortable="true">Voucher No</th>
					<th data-field="voucherDate" data-align="left" data-sortable="true">Voucher Date</th>
					<!-- <th data-field="bagNo" data-align="left" data-sortable="true">Bag No</th> -->
					<th data-field="action1" data-align="center">Edit</th>
					<th data-field="action2" data-align="center">Delete</th>
				</tr>
			</thead>
		</table>
	</div>
</div>





		