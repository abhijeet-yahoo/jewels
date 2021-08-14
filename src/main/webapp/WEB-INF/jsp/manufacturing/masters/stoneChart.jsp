<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script src="/jewels/js/export/tableExport.min.js"></script>
<script src="/jewels/js/export/jspdf.min.js"></script>
<script src="/jewels/js/export/jspdf.plugin.autotable.js"></script>
<script src="/jewels/bootstrap-table-master/dist/extensions/export/bootstrap-table-export.js"></script>
 <script src="/jewels/bootstrap-table-master/dist/extensions/auto-refresh/bootstrap-table-auto-refresh.min.js"></script>

<div class="panel panel-primary" style="width: 100%">
	<c:set var="option" value="Stone Chart" />

	<c:if test="${success eq true}">
		<div class="alert alert-success">Stone Chart ${action}
			successfully!</div>
	</c:if>

	<div class="row">
		<div class="col-md-12">
					<div class="col-xs-2">
									<div>
									<table id="shapeId" data-toggle="table" 
										data-url="/jewels/manufacturing/masters/shape/listing.html?opt=1"
										data-pagination="false" data-side-pagination="server"
										data-height="500" data-search="true" >
										<thead>
											<tr class="btn-primary">
												<th data-field="name">Shape</th>
											</tr>
										</thead>
									</table>
									</div>
						</div>
				
				<div class="col-xs-10">
				<div>
				
					<div id="toolbar">
							<c:if test="${canAdd}">
								<a class="btn btn-info" style="font-size: 15px" type="button"
									href="/jewels/manufacturing/masters/stoneChart/add.html"><span
									class="glyphicon glyphicon-plus"></span>&nbsp;Add Stone Chart</a>
							</c:if>
							<c:if test="${!canAdd}">
								<a class="btn btn-info" style="font-size: 15px" type="button"
									onClick="javascript:displayMsg(event, this)"
									href="javascript:void(0)"><span
									class="glyphicon glyphicon-plus"></span>&nbsp;Add Stone Chart</a>
							</c:if>
						</div>
					</div>
					
					<div>
						<table id="subConId" data-toggle="table"
							data-url="/jewels/manufacturing/masters/stoneChart/listing.html"
							data-toolbar="#toolbar" data-pagination="true"
							data-side-pagination="server"
							data-page-list="[10, 50, 100, 200,1000,2000]" data-search="true" data-show-export="true"
							data-striped="true" data-pagination="true" data-height="550">
							<thead>
								<tr class="btn-primary">
									<th data-field="shape" data-align="left" data-sortable="true">Shape</th> 
									<th data-field="size" data-align="left" data-sortable="true">Size</th>
									<th data-field="sieve" data-align="left" data-sortable="true">Sieve</th>
									<th data-field="sizeGroup" data-align="left"data-sortable="true">Size Group</th>
									<th data-field="perstonewt" data-align="left" data-sortable="true">PerStone Wt</th>
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
			
			popStoneChartList('');
			
		});



$('#shapeId').bootstrapTable({}).on(
		'click-row.bs.table',
		
		function(e, row, $element) {
						
			var shapeId = jQuery.parseJSON(JSON.stringify(row)).id;
			
			popStoneChartList(shapeId)
						
			
		});



function popStoneChartList(shapeId){
	$("#subConId")
	.bootstrapTable(
			'refresh',
			{
				url : "/jewels/manufacturing/masters/stoneChart/listing.html?shapeId="+shapeId
			});
	
}

	
	
	</script>
	<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

	<script src="/jewels/js/jquery/jquery-ui.min.js"></script>

	<script src="/jewels/js/common/common.js"></script>

	<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

	<link href="/jewels/css/lighter/jquery.lighter.css"	rel="stylesheet" type="text/css" />

	<link href="/jewels/css/common/common.css" rel="stylesheet" type="text/css" />
	
	<script src="/jewels/js/common/design.js"></script>
	
