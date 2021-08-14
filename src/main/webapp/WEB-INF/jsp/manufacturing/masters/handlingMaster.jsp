<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<c:set var="option" value="HandlingMaster" />

<c:if test="${param.success eq true}">
			<div class="alert alert-success">Handling Rate added ${action}
				successfully!</div>
		</c:if>

<div>

	<h4 id="modelNameId" align="center" style="text-decoration: underline;"></h4>
	
	<div id="toolbar">
	<c:choose>
			<c:when test="${canAdd}">
		<a class="btn btn-info" style="font-size: 15px;" type="button"
			href="#"  onclick="popAdd()"><span
			class="glyphicon glyphicon-plus"></span>&nbsp;Add Handling Rate</a>
	       </c:when>
	       
	       <c:otherwise>
				<a class="btn btn-info" style="font-size: 15px" type="button"
					onClick="javascript:displayMsg(event, this)"
					href="javascript:void(0)"><span
					class="glyphicon glyphicon-plus"></span>&nbsp;Add Handling Rate</a>
			</c:otherwise>
		</c:choose>
	
	</div>
	<div>
		<table id="handlingRateTableId" data-toggle="table"
			data-toolbar="#toolbar" data-pagination="true"
			data-side-pagination="server"
			data-page-list="[5, 10, 20, 50, 100, 200]" data-search="true"  
			data-striped="true"  data-height="512" >
			<thead>
				<tr class="btn-primary">
					<th data-field="party" data-align="left" data-sortable="true">Name</th>
					<th data-field="metal" data-align="left" data-sortable="true">Metal</th>
					<th data-field="stoneType" data-align="left" data-sortable="true">Stone Type</th>
					<th data-field="shape" data-align="left" data-sortable="true">Shape</th>
					<th data-field="perCarate" data-align="left" data-sortable="true">Per Carat</th>
					<th data-field="percentage" data-sortable="true" data-align="left" >Percentage</th>
					<th data-field="action1" data-align="center">Edit</th>
					<th data-field="action2" data-align="center">Delete</th>
				</tr>
			</thead>
		</table>
	</div>
</div>

<%@include file="/WEB-INF/jsp/common/modal.jsp"%>


<script type="text/javascript">

$(document).ready(function(e){
	popHandlingRateListing();
});

function popHandlingRateListing(){
	$("#handlingRateTableId")
		.bootstrapTable(
			'refresh',{
				url : "/jewels/manufacturing/masters/handlingMaster/listing.html"
		});
}

function popAdd(){
	window.location.href="/jewels/manufacturing/masters/handlingMaster/add.html";
}



</script>

