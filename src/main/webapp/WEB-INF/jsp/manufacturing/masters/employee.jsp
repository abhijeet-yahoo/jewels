<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="panel panel-primary" style="width: 100%">
	<c:set var="option" value="Employee" />

	<c:if test="${success eq true}">
		<div class="alert alert-success">Employee ${action} successfully!
		</div>
	</c:if>

	<div class="row">
		<div class="col-xs-13">
			<!-- <div class="col-xs-2">
				<div>
					<table id="depId" data-toggle="table"
						data-url="/jewels/manufacturing/masters/department/listing.html?opt=2"
						data-pagination="false" data-side-pagination="server"
						data-height="500" data-search="true">
						<thead>
							<tr class="btn-primary">
								<th data-field="name">Department</th>
							</tr>
						</thead>
					</table>
				</div>
			</div> -->
			<div class="col-xs-12">
				<div>
					<div id="toolbar">
						<c:if test="${canAdd}">
							<a class="btn btn-info" style="font-size: 15px" type="button"
								href="/jewels/manufacturing/masters/employee/add.html"><span
								class="glyphicon glyphicon-plus"></span>&nbsp;Add Employee</a>
						</c:if>
						<c:if test="${!canAdd}">
							<a class="btn btn-info" style="font-size: 15px" type="button"
								onClick="javascript:displayMsg(event, this)"
								href="javascript:void(0)"><span
								class="glyphicon glyphicon-plus"></span>&nbsp;Add Employee</a>
						</c:if>
					</div>
					<div>
						<table id="empId" data-toggle="table"
							data-url="/jewels/manufacturing/masters/employee/listing.html"
							data-toolbar="#toolbar" data-pagination="true"
							data-side-pagination="server"
							data-page-list="[5, 10, 20, 50, 100, 200]" data-search="true"
							data-striped="true" data-pagination="true" data-height="550">
							<thead>
								<tr class="btn-primary">
									<th data-field="deptName" data-align="left" data-sortable="true">Department</th>
									<th data-field="name" data-align="left" data-sortable="true">Employee</th>
									<th data-field="code" data-align="left" data-sortable="true">Code</th>
									<th data-field="designation" data-align="left" data-sortable="true">Designation</th>
									<th data-field="updatedBy" data-sortable="true">Updated By</th>
								<!-- 	<th data-field="updatedDt" data-sortable="true">Updated Date</th> -->
									<th data-field="deactive" data-sortable="true">Status</th>
									<!-- <th data-field="deactiveDt" data-sortable="true">Deactive Date</th> -->
									<th data-field="action1" data-align="center">Edit</th>
									<th data-field="action2" data-align="center">Deactivate</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>

				<%@include file="/WEB-INF/jsp/common/modal.jsp"%>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(
			function(e) {

				if (window.location.href.indexOf('id') != -1) {

					var abcx = window.location.href;
					var metalId = abcx.substring(window.location.href
							.indexOf('id') + 3);
					getChild(metalId);
				}
			});

	function getChild(id) {
		$("#empId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/masters/employee/listing.html?id="
									+ id
						});
		$("#depId").bootstrapTable({
			pagingType : "bootstrapPager",
			pagerSettings : {
				textboxWidth : 70,
				firstIcon : "",
				previousIcon : "glyphicon glyphicon-arrow-left",
				nextIcon : "glyphicon glyphicon-arrow-right",
				lastIcon : "",
				searchOnEnter : true,
				language : "Page ~ of ~ pages"
			}
		});
	}
</script>