<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div>
	<div id="toolbar">
	<c:choose>
			<c:when test="${canAdd}">
		<a class="btn btn-info" style="font-size: 15px" type="button"
			href="/jewels/manufacturing/transactions/fgIssMt/add.html"><span
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
		<table data-toggle="table" data-url="/jewels/manufacturing/transactions/fgIssMt/listing.html"
			data-toolbar="#toolbar" data-pagination="true"
			data-side-pagination="server"
			data-search="true"
			data-height="550" >
			<thead>
				<tr>
					<th data-field="invNo" data-align="left" data-sortable="true">Voucher No</th>
					<th data-field="invDate" data-align="left" data-sortable="true">Voucher Date</th>
					<th data-field="deptNm" data-align="left" data-sortable="true">Location</th>
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
		url : "/jewels/manufacturing/transactions/fgStockIss/delete/"+ mtId + ".html",
		type : 'GET',
		success : function(data) {
			
			
			if(data === "-1"){
				displayMsg(event, this, 'Back Date Entry,You cannot delete this record ,Please Contact Administrator to Delete ');
			}else{
				
				window.location.reload(true);
			}

		}
	}); 
}

function deleteFgIss(e,mtId){

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