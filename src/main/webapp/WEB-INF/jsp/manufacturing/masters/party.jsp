<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@include file="/WEB-INF/jsp/common/modal.jsp"%>

<c:set var="option" value="Party" />

<div>
	<div id="toolbar">
		<c:choose>
			<c:when test="${canAdd}">
				<a class="btn btn-info" style="font-size: 15px" type="button"
					href="/jewels/manufacturing/masters/party/add.html"><span
					class="glyphicon glyphicon-plus"></span>&nbsp;Add Party</a>
			</c:when>
			<c:otherwise>
				<a class="btn btn-info" style="font-size: 15px" type="button"
					onClick="javascript:displayMsg(event, this)"
					href="javascript:void(0)"><span
					class="glyphicon glyphicon-plus"></span>&nbsp;Add Party</a>
			</c:otherwise>
		</c:choose>
		
					
					<a class="btn btn-warning" style="font-size: 15px" type="button"
									href="#" onclick="popPartyExcel()"><span
									class="glyphicon glyphicon-upload"></span>&nbsp;Upload Excel</a>
									
	</div>
	<div>
		<table data-toggle="table"
			data-url="/jewels/manufacturing/masters/party/listing.html"
			data-toolbar="#toolbar" data-pagination="true"
			data-side-pagination="server" data-show-columns="true"
			data-page-list="[5, 10, 20, 50, 100, 200]" data-search="true"
			data-striped="true" data-height="520">
			<thead>
				<tr class="btn-primary">
					<th data-field="name" data-align="left" data-sortable="true">Name</th>
					<th data-field="code" data-align="left" data-sortable="true">Code</th>
					<th data-field="email" data-sortable="true">Email</th>
					<th data-field="phone" data-sortable="true">Phone</th>
					<th data-field="deactive" data-sortable="true">Status</th>
					<th data-field="defaultFlag" data-sortable="true">Default</th>
					<th data-field="exportClient" data-sortable="true">Export Client</th>
					<th data-field="action1" data-align="center">Edit</th>
					<th data-field="action2" data-align="center">Deactivate</th>
				</tr>
			</thead>
		</table>
	</div>
</div>


<script type="text/javascript">

function popPartyExcel(){
	window.location.href="/jewels/manufacturing/masters/party/uploadExcel.html";
}

</script>


<script src="/jewels/js/lighter/jquery.lighter.js"
	type="text/javascript"></script>
<link href="/jewels/css/lighter/jquery.lighter.css" rel="stylesheet"
	type="text/css" />

<script src="/jewels/js/common/design.js"></script>
	
	

	
