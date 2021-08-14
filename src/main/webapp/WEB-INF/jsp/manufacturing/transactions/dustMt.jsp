<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="option" value="DustMt" />

<c:if test="${success eq true}">
		<div class="alert alert-success">DustMt ${action} successfully!</div>
</c:if>


<div>
	<div id="toolbar">
		<a class="btn btn-info" style="font-size: 15px" type="button"
			href="/jewels/manufacturing/transactions/dustMt/add.html"><span
			class="glyphicon glyphicon-plus"></span>&nbsp;Add DustMt</a>
	</div>
	<div>
		<table data-toggle="table" data-url="/jewels/manufacturing/transactions/dustMt/listing.html"
			data-toolbar="#toolbar" data-pagination="true"
			data-side-pagination="server"
			data-search="true"
			data-height="550">
			<thead>
				<tr>
					<th data-field="invNo" data-align="left" data-sortable="true"  >InvNo</th>
					<th data-field="fromPeriod" data-sortable="true">From Period</th>
					<th data-field="toPeriod" data-sortable="true">To Period</th>
					<th data-field="action1" data-align="center">Edit</th>
					<th data-field="action2" data-align="center">Delete</th>
				</tr>
			</thead>
		</table>
	</div>
</div>

<%@include file="/WEB-INF/jsp/common/modal.jsp"%>



<script type="text/javascript">



function deleteDustMt(e,dustMtId){
	
		
		displayDlg(
				e,
				'javascript:deleteDustMtData('+dustMtId+');',
				'Delete',
				'Do you want to Delete  ?',
				'Continue');
		
		
	}



function deleteDustMtData(dustMtPk){
	
	$("#modalDialog").modal("hide");
	
	$.ajax({
		
		url:'/jewels/manufacturing/transactions/dustMt/delete/'+dustMtPk+'.html',
		data: 'GET',
		success : function(data){
		
			if(data === '-1'){
				displayMsg(event, this,'Cannot Delete');
			}else{
				window.location.reload(true);
			}
			
		}
		
	})
	
}



</script>

