<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>


<c:set var="option" value="stnLooseMt" />

<c:if test="${success eq true}">
		<div class="alert alert-success">Loose Stone ${action} successfully!</div>
</c:if>


<div>
	<div id="toolbar">
	<c:choose>
			<c:when test="${canAdd}">
		<a class="btn btn-info" style="font-size: 15px" type="button"
			href="/jewels/manufacturing/transactions/stnLooseMt/add.html"><span
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
		<table data-toggle="table" data-url="/jewels/manufacturing/transactions/stnLooseMt/listing.html"
			data-toolbar="#toolbar" data-pagination="true"
			data-side-pagination="server"
			data-search="true"
			data-height="550" >
			<thead>
				<tr>
					<th data-field="invNo" data-align="left" data-sortable="true">Voucher No</th>
					<th data-field="invDate" data-align="left" data-sortable="true">Voucher Date</th>
					<th data-field="beNo" data-align="left" data-sortable="true">Reference No</th>
					<th data-field="beDate" data-align="left" data-sortable="true">Reference Date</th>
					<th data-field="supplier" data-align="left" data-sortable="true">Supplier</th>
					<th data-field="party" data-align="left" data-sortable="true">For Client</th>
					<th data-field="inwardType" data-align="left" data-sortable="true">Inward Type</th>
					<th data-field="remark" data-align="left" data-sortable="true">Remark</th>
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

	$.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>' });

	 $.ajax({
		url : "/jewels/manufacturing/transactions/stnLooseMt/delete/"+ mtId + ".html",
		type : 'GET',
		success : function(data) {
			
			$.unblockUI();
			
			if(data === "-1"){
				displayMsg(event, this, 'Back Date Entry,You cannot delete this record ,Please Contact Administrator to Delete ');
			}else if(data === "-2"){
				displayMsg(event, this, 'Can Not Delete, Conversion Done');
			}else if(data === "-3"){
				displayMsg(event, this, 'Can Not Delete, Record Adjusted');
			}
			else{
				
				window.location.reload(true);
			}

		}
	}); 
}

function deleteStnLooseMt(e,mtId){

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

<script src="<spring:url value='/js/common/blockUserInterface.js' />"></script>