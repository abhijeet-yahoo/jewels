<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@include file="/WEB-INF/jsp/common/modal.jsp"%>

<c:set var="option" value="jobIssMt" />

<c:if test="${success eq true}">
		<div class="alert alert-success">Issue Challan ${action} successfully!</div>
</c:if>


<div>
	<div id="toolbar">
		<a class="btn btn-info" style="font-size: 15px" type="button"
			href="/jewels/manufacturing/transactions/jobIssMt/add.html"><span
			class="glyphicon glyphicon-plus"></span>&nbsp;New</a>
	</div>
	<div>
		<table data-toggle="table" data-url="/jewels/manufacturing/transactions/jobIssMt/listing.html"
			data-toolbar="#toolbar" data-pagination="true"
			data-side-pagination="server"
			data-page-list="[5, 10, 20, 50, 100, 200]" data-search="true"  
			data-striped="true"  data-height="460" >
			<thead>
				<tr class="btn-primary">
					<th data-field="vouType" data-align="left" data-sortable="true">Vou. Type</th>
					<th data-field="party" data-align="left" data-sortable="true">Party</th>
					<th data-field="invNo" data-align="left" data-sortable="true">Vou. No.</th>
					<th data-field="invDate" data-align="left" data-sortable="true">Date</th>
					<th data-field="metalRate" data-align="left" data-sortable="true">MetalRate</th>
					<th data-field="silverRate" data-align="left" data-sortable="true">SilverRate</th>
					<th data-field="alloyRate" data-align="left" data-sortable="true">AlloyRate</th>
					
					<th data-field="actionClose" data-sortable="true">OpenClose</th>
					<th data-field="action1" data-align="center">Edit</th>
					<th data-field="action2" data-align="center">Delete</th>
				</tr>
			</thead>
		</table>
	</div>
</div>


<script type="text/javascript">



	

	function deleteJobIss(e,mtId){
		
		displayDlg(
				e,
				'javascript:deleteJobData('+mtId+');',
				'Delete',
				'Do you want to Delete the Invoice  ?',
				'Continue');
		
	}
	
	
	function deleteJobData(id){
		 $.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>' });
		
		$("#modalDialog").modal("hide");
		
		$.ajax({
			url : "/jewels/manufacturing/transactions/jobIssMt/delete/"+id+".html",
			type : 'GET',
			success : function(data){
				$.unblockUI();
				
				if(data==='1'){
					window.location.reload(true);
				}else if(data==='-2'){
					displayMsg(this, null, "Can not delete backdated entry");
					}
				else{
					displayMsg(this, null, "Record present in "+data);
					
				}
				
			}
			
			
		})
		
		
	}



</script>

<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="<spring:url value='/js/common/blockUserInterface.js' />"></script>


