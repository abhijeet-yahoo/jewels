<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script src="/jewels/js/export/tableExport.min.js"></script>
<script src="/jewels/js/export/jspdf.min.js"></script>
<script src="/jewels/js/export/jspdf.plugin.autotable.js"></script>
<script src="/jewels/bootstrap-table-master/dist/extensions/export/bootstrap-table-export.js"></script>
 <script src="/jewels/bootstrap-table-master/dist/extensions/auto-refresh/bootstrap-table-auto-refresh.min.js"></script>

<c:set var="option" value="Setting" />

<c:if test="${success eq true}">
	<div class="alert alert-success">Setting ${action} successfully!</div>
</c:if>


<div>
	<div id="toolbar">
	
		<c:choose>
			<c:when test="${canAdd}">
					<a class="btn btn-info" style="font-size: 15px" type="button"
			href="/jewels/manufacturing/masters/setting/add.html"><span
			class="glyphicon glyphicon-plus"></span>&nbsp;Add Setting</a>
			</c:when>
			<c:otherwise>
				<a class="btn btn-info" style="font-size: 15px" type="button"
					onClick="javascript:displayMsg(event, this)"
					href="javascript:void(0)"><span
					class="glyphicon glyphicon-plus"></span>&nbsp;Add Setting</a>
			</c:otherwise>
		</c:choose>
			
			
			
	</div>
	<!-- UPDATED -->
	<div>
		<table data-toggle="table"
			data-url="/jewels/manufacturing/masters/setting/listing.html?opt=1"
			data-toolbar="#toolbar" data-pagination="true"
			data-side-pagination="server"
			data-page-list="[5, 10, 20, 50, 100, 200]" data-search="true" data-show-export="true"
			data-striped="true" data-pagination="true" data-height="520">
			<thead>
				<tr  class="btn-primary">
					<th data-field="name" data-align="left" data-sortable="true">Name</th>
					<th data-field="code" data-align="left" data-sortable="true">Code</th>
					<th data-field="deactive" data-sortable="true">Deactive</th>
					<th data-field="action1" data-align="center">Edit</th>
					<th data-field="action2" data-align="center">Delete</th>
				</tr>
			</thead>
		</table>
	</div>
</div>

<%@include file="/WEB-INF/jsp/common/modal.jsp"%>
