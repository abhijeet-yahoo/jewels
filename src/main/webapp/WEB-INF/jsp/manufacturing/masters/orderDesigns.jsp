<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="container-fluid">
	<div id="toolbar">
		<a class="btn btn-info" style="font-size: 15px" type="button"
			href="javascript:popDesignAddList();"><span
			class="glyphicon glyphicon-plus"></span>&nbsp;Design</a>
		<a class="btn btn-warning" style="font-size: 15px" type="button"
			href="javascript:popDesignEditList();"><span
			class="glyphicon glyphicon-edit"></span>&nbsp;Design</a>
	</div>
	<div>
		<table id="oDtlsId" data-toggle="table"
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
					<th data-field="deactive" data-sortable="true">Status</th>
					<th data-field="action1" data-sortable="true">Action</th>
				</tr>
			</thead>
		</table>
	</div>
</div>

<script type="text/javascript">
	function editODesign(dId) {
		$.ajax({
			url : "/jewels/manufacturing/masters/order/design/edit/" + dId + ".html",
			type : 'GET',
			success : function(data) {
				$("#odRwId").html(data);
				$("form#design").attr('action', '/manufacturing/masters/order/design/add.html');
			}
		});
	}

	function popDesignAddList() {
		$("#oDtlsId").bootstrapTable('refresh', 
		{
			url : "/jewels/manufacturing/masters/design/listing.html?opt=2"
		});
	}

	function popDesignEditList() {
		alert('edit');
	}
</script>