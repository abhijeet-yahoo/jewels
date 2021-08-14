<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="option" value="User" />

<div>
	<div id="toolbar">
		<c:choose>
			<c:when test="${canAdd}">
				<a class="btn btn-info" style="font-size: 15px" type="button"
					href="/jewels/admin/user/register.html"><span
					class="glyphicon glyphicon-plus"></span>&nbsp;Add User</a>
			</c:when>
			<c:otherwise>
				<a class="btn btn-info" style="font-size: 15px" type="button"
					onClick="javascript:displayMsg(event, this)"
					href="javascript:void(0)"><span
					class="glyphicon glyphicon-plus"></span>&nbsp;Add User</a>
			</c:otherwise>
		</c:choose>
	</div>
	<div>
		<table data-toggle="table"
			data-url="/jewels/admin/users/listing.html"
			data-toolbar="#toolbar" data-pagination="true"
			data-side-pagination="server"
			data-page-list="[5, 10, 20, 50, 100, 200]" data-search="true"
			data-striped="true"  data-height="520">
			<thead>
				<tr class="btn-primary">
					<!-- th data-field="id" data-align="right" data-sortable="true">ID</th -->
					<th data-field="name" data-align="left" data-sortable="true">Name</th>
					<th data-field="userRole" data-align="left" data-sortable="true">Role</th>
					<!-- <th data-field="password" data-sortable="true">Password</th> -->
					<!-- th data-field="updatedBy" data-sortable="true">Updated By</th>
					<th data-field="updatedDt" data-sortable="true">Updated Date</th -->
					<th data-field="deactive" data-sortable="true">Status</th>
					<th data-field="action1" data-sortable="true"></th>
					<!-- th data-field="deactiveDt" data-sortable="true">Deactive Date</th -->
					
				</tr>
			</thead>
		</table>
	</div>
</div>

<%@include file="/WEB-INF/jsp/common/modal.jsp"%>

<script type="text/javascript">



</script>
