<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@include file="/WEB-INF/jsp/common/modal.jsp"%>

<c:set var="option" value="CostingMt" />

<c:if test="${success eq true}">
		<div class="alert alert-success">Costing ${action} successfully!</div>
</c:if>


<div>
	<div id="toolbar">
		<a class="btn btn-info" style="font-size: 15px" type="button"
			href="/jewels/manufacturing/transactions/costingMtNew/add.html"><span
			class="glyphicon glyphicon-plus"></span>&nbsp;Add Costing</a>
	</div>
	<div>
		<table data-toggle="table" data-url="/jewels/manufacturing/transactions/costingMt/listing.html?opt=1"
			data-toolbar="#toolbar" data-pagination="true"
			data-side-pagination="server"
			data-search="true"  
			data-height="550" >
			<thead>
				<tr>
					<th data-field="party" data-align="left" data-sortable="true">Party</th>
					<th data-field="invNo" data-align="left" data-sortable="true">InvNo</th>
					<th data-field="invDate" data-align="left" data-sortable="true">InvDate</th>
					<th data-field="expClose" data-sortable="true">Status</th>
					<th data-field="actionClose" data-sortable="true">OpenClose</th>
					<th data-field="action1" data-align="center">Edit</th>
					<th data-field="action2" data-align="center">Delete</th>
				</tr>
			</thead>
		</table>
	</div>
</div>


<script type="text/javascript">


	function doCostMtOpen(e,mtId){
		
		displayDlg(
				e,
				'javascript:openCloseCostMt('+mtId+');',
				'Delete',
				'Do you want to Open the Invoice  ?',
				'Continue');
		
	}


	function openCloseCostMt(id){
		
		$("#modalDialog").modal("hide");
		
		$.ajax({
			url : "/jewels/manufacturing/transactions/costingMt/exportCloseOpen/"+id+".html",
			type : 'GET',
			success : function(data){
				
				window.location.reload(true);
				
			}
			
			
		})
		
		
	}
	
	
	function deleteCosting(e,mtId){
		
		displayDlg(
				e,
				'javascript:deleteCostData('+mtId+');',
				'Delete',
				'Do you want to Delete the Invoice  ?',
				'Continue');
		
	}
	
	
	function deleteCostData(id){
		 $.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>' });
		
		$("#modalDialog").modal("hide");
		
		$.ajax({
			url : "/jewels/manufacturing/transactions/costingMtNew/delete/"+id+".html",
			type : 'GET',
			success : function(data){
				$.unblockUI();
				
				if(data.indexOf('Warn')!=-1){
					displayMsg(this, null, data);
				}else{

					window.location.reload(true);
				}
				
			}
			
			
		})
		
		
	}
	




</script>

<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="<spring:url value='/js/common/blockUserInterface.js' />"></script>

