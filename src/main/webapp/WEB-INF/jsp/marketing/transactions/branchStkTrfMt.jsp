<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div>

	<div id="toolbar">
	<c:choose>
			<c:when test="${canAdd}">
		<a class="btn btn-info" style="font-size: 15px" type="button"
			href="/jewels/marketing/transactions/branchStkTrfMt/add.html"><span
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
		<table data-toggle="table" data-url="/jewels/marketing/transactions/stkTrfMt/listing.html?tranType=BRANCHTRF"
			data-toolbar="#toolbar" data-pagination="true"
			data-side-pagination="server"
			data-search="true"
			data-height="550" >
			<thead>
				<tr>
					<th data-field="invNo" data-align="left" data-sortable="true">Invoice No</th>
					<th data-field="invDate" data-align="left" data-sortable="true">Invoice Date</th>
					<!-- <th data-field="party" data-align="left" data-sortable="true">Party</th> -->
					<th data-field="location" data-align="left" data-sortable="true">Location</th>
					<th data-field="action1" data-align="center">Edit</th>
					<th data-field="action2" data-align="center">Delete</th>
					
				</tr>
			</thead>
		</table>
	</div>
</div>
<script type="text/javascript">

function deleteMt(mtId){
		
		$("#modalDialog").modal("hide");

	 $.ajax({
		url : "/jewels/marketing/transactions/stkTrfMt/delete/" + mtId + ".html?tranType=BRANCHTRF",
		type : 'GET',
		success : function(data) {
			
			if(data === "1"){
				
				window.location.reload(true);
			}else{

				displayMsg(event, this, data);
			}

		}
	}); 
}

function deleteStkTrfMt(e,mtId){

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
