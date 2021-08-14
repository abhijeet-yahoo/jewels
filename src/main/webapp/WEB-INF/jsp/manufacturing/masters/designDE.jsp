<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- @ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" --%>

<c:set var="option" value="Design" />

<div class="container-fluid">
	<div id="toolbar">
		<a class="btn btn-info" style="font-size: 15px" type="button"
			href="/jewels/manufacturing/masters/design/add.html"><span
			class="glyphicon glyphicon-plus"></span>&nbsp;Add Design</a>
	</div>
	<div>
		<table data-toggle="table" data-url="/jewels/manufacturing/masters/design/listing.html"
			data-toolbar="#toolbar" data-pagination="true"
			data-side-pagination="server"
			data-page-list="[5, 10, 20, 50, 100, 200]" data-search="true"
			data-height="520">
			<thead>
				<tr>
					<th data-field="id" data-align="right" data-sortable="true">ID</th>
					<th data-field="group" data-align="left" data-sortable="true">Group</th>
					<th data-field="cDate" data-sortable="true">Create Date</th>
					<th data-field="designNo" data-sortable="true">Design No</th>
					<th data-field="styleNo" data-sortable="true">Style No</th>
					<th data-field="version" data-sortable="true">Version</th>
					<th data-field="designer" data-sortable="true">Designer</th>
					<th data-field="uploadImage" data-align="center">Upload Image</th>
					<th data-field="action1" data-align="center">Edit</th>
					<th data-field="action2" data-align="center">Delete</th>
				</tr>
			</thead>
		</table>
	</div>
</div>

<%@include file="/WEB-INF/jsp/common/modal.jsp"%>
