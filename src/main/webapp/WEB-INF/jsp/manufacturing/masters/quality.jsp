<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script src="/jewels/js/export/tableExport.min.js"></script>
<script src="/jewels/js/export/jspdf.min.js"></script>
<script src="/jewels/js/export/jspdf.plugin.autotable.js"></script>
<script src="/jewels/bootstrap-table-master/dist/extensions/export/bootstrap-table-export.js"></script>
 <script src="/jewels/bootstrap-table-master/dist/extensions/auto-refresh/bootstrap-table-auto-refresh.min.js"></script>

<div class="panel panel-primary" style="width: 100%">

	<c:set var="option" value="Quality" />

	<c:if test="${success eq true}">
		<div class="alert alert-success">Quality ${action} successfully!</div>
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
								<div id="toolbar" >
									<c:if test="${canAdd}">
										<a class="btn btn-info" style="font-size: 15px" type="button"
											href="/jewels/manufacturing/masters/quality/add.html"><span
											class="glyphicon glyphicon-plus"></span>&nbsp;Add Quality</a>
									</c:if>
									<c:if test="${!canAdd}">
										<a class="btn btn-info" style="font-size: 15px" type="button"
											onClick="javascript:displayMsg(event, this)"
											href="javascript:void(0)"><span
											class="glyphicon glyphicon-plus"></span>&nbsp;Add Quality</a>
									</c:if>
								 </div>
							
						<div>
						<table id="qualityId" data-toggle="table"
							
							data-toolbar="#toolbar"  data-pagination="true"
							data-side-pagination="server"
							data-page-list="[5, 10, 20, 50, 100, 200,1000]" data-search="true" data-show-export="true"
							data-striped="true" data-pagination="true" data-height="550">
							<thead>
								<tr class="btn-primary">
									<th data-field="party" data-align="left" data-sortable="true">Party</th>
									 <th data-field="shapeName" data-align="left" data-sortable="true">Shape</th>  
									 <th data-field="group" data-align="left" data-sortable="true">Group</th>
									<th data-field="name" data-align="left" data-sortable="true">Quality</th>
									<th data-field="code" data-align="left" data-sortable="true">Code</th>
									<th data-field="intQuality" data-align="left" data-sortable="true">Int Quality</th>
									<th data-field="description" data-align="left" data-sortable="true">Description</th>
									<!-- <th data-field="updatedBy" data-sortable="true">Updated By</th> -->
									<th data-field="deactive" data-sortable="true">Status</th>
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
				
				popQualityList('');
			});

	 
	
	
	 $('#shapeId').bootstrapTable({}).on(
				'click-row.bs.table',
				
				function(e, row, $element) {
					
					console.log("in onclikck function")
					
					var shapeId = jQuery.parseJSON(JSON.stringify(row)).id;
					
					popQualityList(shapeId)
								
					
				});
		
	
	
	function popQualityList(shapeId){
		$("#qualityId")
		.bootstrapTable(
				'refresh',
				{
					url : "/jewels/manufacturing/masters/quality/listing.html?shapeId="+shapeId
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

