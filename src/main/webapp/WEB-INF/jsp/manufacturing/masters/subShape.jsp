<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script src="/jewels/js/export/tableExport.min.js"></script>
<script src="/jewels/js/export/jspdf.min.js"></script>
<script src="/jewels/js/export/jspdf.plugin.autotable.js"></script>
<script src="/jewels/bootstrap-table-master/dist/extensions/export/bootstrap-table-export.js"></script>
 <script src="/jewels/bootstrap-table-master/dist/extensions/auto-refresh/bootstrap-table-auto-refresh.min.js"></script>

<div class="panel panel-primary" style="width: 100%">
	<c:set var="option" value="SubShape" />

	<c:if test="${success eq true}">
		<div class="alert alert-success">SubShape ${action}
			successfully!</div>
	</c:if>

	<div class="row">
		<div class="col-xs-13">
			
			<div class="col-xs-12">
				<div>
					<div id="toolbar">
						<c:if test="${canAdd}">
							<a class="btn btn-info" style="font-size: 15px" type="button"
								href="/jewels/manufacturing/masters/subShape/add.html"><span
								class="glyphicon glyphicon-plus"></span>&nbsp;Add SubShape</a>
						</c:if>
						<c:if test="${!canAdd}">
							<a class="btn btn-info" style="font-size: 15px" type="button"
								onClick="javascript:displayMsg(event, this)"
								href="javascript:void(0)"><span
								class="glyphicon glyphicon-plus"></span>&nbsp;Add SubShape</a>
						</c:if>
					</div>
					<div>
						<table id="subShapeId" data-toggle="table"
							data-url="/jewels/manufacturing/masters/subShape/listing.html"
							data-toolbar="#toolbar" data-pagination="true"
							data-side-pagination="server"
							data-page-list="[5, 10, 20, 50, 100, 200]" data-search="true" data-show-export="true"
							data-striped="true" data-pagination="true" data-height="550">
							<thead>
								<tr class="btn-primary">
									<th data-field="shapeName" data-align="left" data-sortable="true">Shape</th>
									<th data-field="name" data-align="left" data-sortable="true">Sub Shape</th>
									<th data-field="code" data-sortable="true">Code</th>
									<th data-field="deactive" data-sortable="true">Deactive</th>
									<th data-field="action1" data-align="center">Edit</th>
									<th data-field="action2" data-align="center">Delete</th>

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
					var shapeId = abcx.substring(window.location.href.indexOf('id') + 3);
					getChild(shapeId);
				}
			});

	function getChild(id) {
		$("#subShapeId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/masters/subShape/listing.html?id="
									+ id
						});
		$("#shapeId").bootstrapTable({
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