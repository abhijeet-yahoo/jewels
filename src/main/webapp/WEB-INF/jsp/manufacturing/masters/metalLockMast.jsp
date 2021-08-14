<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/WEB-INF/jsp/common/modal.jsp"%>
<%@include file="/WEB-INF/jsp/common/modalMetalLockRates.jsp"%>



<div>

<div id="toolbar">
		<c:if test="${canAdd}">
			<a class="btn btn-info" style="font-size: 15px" type="button"
				onclick="javascript:popMetalLockRate()"><span
				class="glyphicon glyphicon-plus"></span>&nbsp;Add</a>
		</c:if>
		<c:if test="${!canAdd}">
			<a class="btn btn-info" style="font-size: 15px" type="button"
				onClick="javascript:displayMsg(event, this)"
				href="javascript:void(0)"><span class="glyphicon glyphicon-plus"></span>&nbsp;Add
				</a>
		</c:if>
	</div>

		
		
	</div>	
	
	<div>
		<table  data-toggle="table" data-url="/jewels/manufacturing/masters/metalLock/listing.html"
			data-toolbar="#toolbar" data-pagination="true"
			data-side-pagination="server"
			data-page-list="[5, 10, 20, 50, 100, 200]" data-search="true"  
			data-striped="true"  data-height="520" >
			<thead>
				<tr class="btn-primary">
					<th data-field="date" data-align="left" data-sortable="true">Date</th>
					<th data-field="gold" data-align="left" data-sortable="true">Gold</th>
					<th data-field="goldRate" data-align="left" data-sortable="true">Gold Rate</th>
					<th data-field="alloy" data-align="left" data-sortable="true">Alloy</th>
					<th data-field="alloyRate" data-sortable="true">Alloy Rate</th>
					<th data-field="silver" data-sortable="true">silver</th>
					<th data-field="silverRate" data-align="center">Silver Rate</th>
					<th data-field="steel" data-align="center">Steel</th>
					<th data-field="steelRate" data-align="center">Steel Rate</th>
					<th data-field="action1" data-align="center">Edit</th>
					<th data-field="action2" data-align="center">Delete</th>
					
				</tr>
			</thead>
		</table>
	</div>

</div>




	<script type="text/javascript">



function popMetalLockRate(){

	$('#metalLockRateModal').modal('show');
	popMetalLockRateTbl();
	
	setTimeout(function(){
		$('#metalLockRateTableDivId').bootstrapTable('resetView');
	}, 300);


}



	</script>





<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/common/common.js"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css"	rel="stylesheet" type="text/css" />

<link href="/jewels/css/common/common.css" rel="stylesheet" type="text/css" />
<script src="<spring:url value='/js/common/blockUserInterface.js' />"></script>

<script src="/jewels/js/common/design.js"></script>
