<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- @ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" --%>

<div class="container">
	<div id="toolbar">
		<a class="btn btn-info" style="font-size: 15px" type="button"
			href="/jewels/manufacturing/masters/designStone/add.html"><span
			class="glyphicon glyphicon-plus"></span>&nbsp;Add Design Stonessssssss</a>
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
					<th data-field="name" data-align="left" data-sortable="true">Name</th>
					<th data-field="typeId" data-sortable="true">Type Id</th>
					<th data-field="code" data-sortable="true">Code</th>
					<th data-field="deactive" data-sortable="true">Deactive</th>
					<th data-field="action1" data-align="center">Edit</th>
					<th data-field="action2" data-align="center">Delete</th>
				</tr>
			</thead>
		</table>
	</div>
</div>
