<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="option" value="MakingMt" />

<c:if test="${param.success eq true}">
		<div class="alert alert-success">Making updated ${action} successfully!</div>
</c:if>


<div>
	<div id="toolbar">
		<a class="btn btn-info" style="font-size: 15px" type="button"
			href="/jewels/manufacturing/transactions/makingMt/add.html"><span
			class="glyphicon glyphicon-plus"></span>&nbsp;Add Making</a>
	</div>
	<div>
		<table data-toggle="table" data-url="/jewels/manufacturing/transactions/makingMt/listing.html"
			data-toolbar="#toolbar" data-pagination="true"
			data-side-pagination="server"
			data-page-list="[5, 10, 20, 50, 100, 200]" data-search="true"
			data-height="520" data-striped="true">
			<thead>
				<tr  class="btn-primary">
					<th data-field="invNo" data-align="left" data-sortable="true">Inv No</th>
					<th data-field="invDate" data-align="left" data-sortable="true">Inv Date</th>
					<th data-field="purity" data-align="left" data-sortable="true">Purity</th>
					<th data-field="color" data-align="left" data-sortable="true">Color</th>
					<th data-field="action1" data-align="center">Edit</th>
					<th data-field="action2" data-align="center">Delete</th>
				</tr>
			</thead>
		</table>
	</div>
</div>

<%@include file="/WEB-INF/jsp/common/modal.jsp"%>