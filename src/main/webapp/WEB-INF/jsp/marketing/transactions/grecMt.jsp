<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@include file="/WEB-INF/jsp/common/modal.jsp"%>

<c:set var="option" value="grecMt" />
<c:set var="optionText" value="Delete" />

<div>
	<div id="toolbar">
		<a class="btn btn-info" style="font-size: 15px" type="button"
			href="/jewels/marketing/transactions/grecMt/add.html"><span
			class="glyphicon glyphicon-plus"></span>&nbsp;Add</a>
			
	
	</div>
	<div class="table-responsive"> 
			<table id="grecXyz" data-toggle="table" data-url="/jewels/marketing/transactions/grecMt/listing.html"
			data-toolbar="#toolbar" data-pagination="true"
			data-side-pagination="server" data-search="true"  
		    data-height="550">
			<thead>
				<tr class="btn-primary">
					<th data-field="invNo" data-align="left" data-sortable="true">GRN No</th>
					<th data-field="invDate" data-sortable="true">GRN Date</th>					
					<th data-field="party" data-sortable="true">Client</th>\
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
		url : "/jewels/marketing/transactions/grecMt/delete/" + mtId + ".html",
		type : 'GET',
		success : function(data) {

			$.unblockUI();
			
			if(data === "1"){
				window.location.reload(true);
			}else{
				displayMsg(event, this, data);
			}
		}
	}); 
}

function deleteGrecMt(e,mtId){

		displayDlg(
				e,
				'javascript:deleteMt('+mtId+');',
				'Delete',
				'Do you want to Delete  ?',
				'Continue');
		
			
}
	

</script>


<script src="/jewels/js/common/order.js"></script>

<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="<spring:url value='/js/common/blockUserInterface.js' />"></script>

