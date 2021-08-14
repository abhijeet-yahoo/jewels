<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="option" value="CastingMt" />

<c:if test="${success eq true}">
		<div class="alert alert-success">CastingMt ${action} successfully!</div>
</c:if>


<div>
	<div id="toolbar">
		<a class="btn btn-info" style="font-size: 15px" type="button"
			href="/jewels/manufacturing/transactions/castingMt/add.html"><span
			class="glyphicon glyphicon-plus"></span>&nbsp;New</a>
	</div>
	<div>
		<table data-toggle="table" data-url="/jewels/manufacturing/transactions/castingMt/listing.html"
			data-toolbar="#toolbar" data-pagination="true"
			data-side-pagination="server"
			data-page-list="[5, 10, 20, 50, 100, 200]" data-search="true"
			data-height="520" data-striped="true" data-striped="true">
			<thead>
				<tr class="btn-primary">					
					<th data-field="cDate" data-align="left" data-sortable="true">Date</th>
					<th data-field="treeNo" data-align="left" data-sortable="true">TreeNo</th>
					<th data-field="purity" data-align="left" data-sortable="true">Purity</th>
					<th data-field="color" data-align="left" data-sortable="true">Color</th>
					<th data-field="waxTreeWt" data-align="left" data-sortable="true">Wax Tree Wt</th>
					<th data-field="stoneWt" data-align="left" data-sortable="true">Stone Wt</th>
					<th data-field="reqWt" data-align="left" data-sortable="true">Req. Metal</th>
					<th data-field="issWt" data-align="left" data-sortable="true">Issue Metal</th>
					<th data-field="treeWt" data-align="left" data-sortable="true">Casted Tree Wt</th>
					<th data-field="castingLoss" data-align="left" data-sortable="true">Casting Loss</th>
					<th data-field="stubWt" data-align="left" data-sortable="true">Trunk & Spru Wt</th>
					<th data-field="pcsWt" data-align="left" data-sortable="true">Pcs & Comp Wt</th>
					<th data-field="cutLoss" data-align="left" data-sortable="true">Cut Loss</th>
					<th data-field="action1" data-align="center">Edit</th>
					<th data-field="action2" data-align="center">Delete</th>
				</tr>
			</thead>
		</table>
	</div>
</div>

<script type="text/javascript">


function deleteCasting(e,mtId){
	
	displayDlg(
			e,
			'javascript:deleteCastingData('+mtId+');',
			'Delete',
			'Do you want to Delete the Invoice  ?',
			'Continue');
	
}


function deleteCastingData(id){
	$("#modalDialog").modal("hide");
	$.ajax({
		url : "/jewels/manufacturing/transactions/castingMt/delete/"+id+".html",
		type : 'GET',
		success : function(data){
			
			if(data==1){
				window.location.reload(true);

			}else{
				displayMsg(this, null, data);
			
			}
			
		}
		
		
	})
	
	
}


</script>


<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script src="/jewels/js/jquery/jquery-ui.min.js"></script>


<%@include file="/WEB-INF/jsp/common/modal.jsp"%>