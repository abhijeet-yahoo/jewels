<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@include file="/WEB-INF/jsp/common/modal.jsp"%>

<c:set var="option" value="CostingMt" />

<c:if test="${success eq true}">
		<div class="alert alert-success">Costing ${action} successfully!</div>
</c:if>


<div>
	<div id="toolbar">
		<a class="btn btn-info" style="font-size: 15px" type="button"
			href="/jewels/manufacturing/transactions/costingMt/add.html"><span
			class="glyphicon glyphicon-plus"></span>&nbsp;Add Costing</a>
	</div>
	<div>
		<table data-toggle="table" data-url="/jewels/manufacturing/transactions/costingMt/listing.html?opt=0"
			data-toolbar="#toolbar" data-pagination="true"
			data-side-pagination="server"
			data-page-list="[5, 10, 20, 50, 100, 200]" data-search="true"  
			data-striped="true"  data-height="460" >
			<thead>
				<tr class="btn-primary">
					<th data-field="party" data-align="left" data-sortable="true">Party</th>
					<th data-field="invNo" data-align="left" data-sortable="true">InvNo</th>
					<th data-field="invDate" data-align="left" data-sortable="true">InvDate</th>
					<th data-field="metalRate" data-align="left" data-sortable="true">MetalRate</th>
					<th data-field="silverRate" data-align="left" data-sortable="true">SilverRate</th>
					<th data-field="alloyRate" data-align="left" data-sortable="true">AlloyRate</th>
					<th data-field="expClose" data-sortable="true">ExportCloseStatus</th>
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
	




</script>




