<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="option" value="StoneOutwardMt" />

<c:if test="${success eq true}">
		<div class="alert alert-success">Stone Outward ${action} successfully!</div>
</c:if>


<div>
	<div id="toolbar">
	<c:choose>
			<c:when test="${canAdd}">
		<a class="btn btn-info" style="font-size: 15px" type="button"
			href="/jewels/manufacturing/transactions/stoneOutwardMt/add.html"><span
			class="glyphicon glyphicon-plus"></span>&nbsp;Add</a>
			</c:when>
			<c:otherwise>
				<a class="btn btn-info" style="font-size: 15px" type="button"
					onClick="javascript:displayMsg(event, this)"
					href="javascript:void(0)"><span
					class="glyphicon glyphicon-plus"></span>&nbsp;Add</a>
		</c:otherwise>
		</c:choose>
	</div>
	<div>
		<table data-toggle="table" data-url="/jewels/manufacturing/transactions/stoneOutwardMt/listing.html"
			data-toolbar="#toolbar" data-pagination="true"
			data-side-pagination="server"
			 data-search="true"
			data-height="550" >
			<thead>
				<tr>
					<th data-field="invNo" data-align="left" data-sortable="true">Invoice No</th>
					<th data-field="invDate" data-align="left" data-sortable="true">Invoice Date</th>
					<th data-field="beNo" data-align="left" data-sortable="true">Be No</th>
					<th data-field="beDate" data-align="left" data-sortable="true">Be Date</th>
					<th data-field="party" data-align="left" data-sortable="true">For Client</th>
					<th data-field="inwardType" data-align="left" data-sortable="true">Inward Type</th>
					<th data-field="remark" data-align="left" data-sortable="true">Remark</th>
					<th data-field="action1" data-align="center">Edit</th>
					<th data-field="action2" data-align="center">Delete</th>
					<th data-field="action3" data-align="center">View</th>
				</tr>
			</thead>
		</table>
	</div>
</div>
<script type="text/javascript">

function deleteMt(mtId){
	$("#modalDialog").modal("hide");

	 $.ajax({
		url : "/jewels/manufacturing/transactions/stoneOutwardMt/delete/"+ mtId + ".html",
		type : 'GET',
		success : function(data) {

			
			if(data ===	"-1" ){
				displayMsg(event, this, 'Back Date Entry,You cannot delete this record ,Please Contact Administrator to Delete ');
			}else{
				
				window.location.reload(true);
			}

		}
	}); 
}

function deleteStoneMt(e,mtId){

		
		displayDlg(
				e,
				'javascript:deleteMt('+mtId+');',
				'Delete',
				'Do you want to Delete  ?',
				'Continue');
		
			
}
</script>


<%@include file="/WEB-INF/jsp/common/modal.jsp"%>

<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script src="/jewels/js/jquery/jquery-ui.min.js"></script>