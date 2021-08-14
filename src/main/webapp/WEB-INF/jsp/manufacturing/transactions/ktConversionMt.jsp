<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<c:set var="option" value="KtConversionMt" />

<c:if test="${success eq true}">
		<div class="alert alert-success">KtConversion ${action} successfully!</div>
</c:if>


<div>
	<div id="toolbar">
		<a class="btn btn-info" style="font-size: 15px" type="button"
			href="/jewels/manufacturing/transactions/ktConversionMt/add.html"><span
			class="glyphicon glyphicon-plus"></span>&nbsp;Add KtConversion</a>
	</div>
	<div>
		<table data-toggle="table" data-url="/jewels/manufacturing/transactions/ktConversionMt/listing.html"
			data-toolbar="#toolbar" data-pagination="true"
			data-side-pagination="server"
			data-search="true"
			data-height="550">
			<thead>
				<tr>
					<th data-field="invNo" data-align="left" data-sortable="true">Inv No</th>					
					<th data-field="date" data-align="left" data-sortable="true">Date</th>
					<th data-field="kt" data-align="left" data-sortable="true">KT</th>
					<th data-field="color" data-align="left" data-sortable="true">Color</th>
					<th data-field="reqMetal" data-align="left" data-sortable="true">Required Metal</th>
					<th data-field="totalIssueWt" data-align="left" data-sortable="true">Total Metal</th>
					<th data-field="recMetal" data-align="left" data-sortable="true">Received Metal</th>
					<th data-field="loss" data-align="left" data-sortable="true">Loss</th>
					<th data-field="scrapWt" data-align="left" data-sortable="true">Scrap Wt</th>
					<th data-field="action1" data-align="center">Edit</th>
					<th data-field="action2" data-align="center">Delete</th>
				</tr>
			</thead>
		</table>
	</div>
</div>

<%@include file="/WEB-INF/jsp/common/modal.jsp"%>





<script type="text/javascript" >



	function deleteKtConversionMt(e,MtId){
		
		displayDlg(
				e,
				'javascript:doDelteKtMt('+MtId+');',
				'Delete',
				'Do you want to Delete  ?',
				'Continue');
	
	}
	
	
	function doDelteKtMt(vMtId){
		
		$("#modalDialog").modal("hide");
		
		$.ajax({
			url:"/jewels/manufacturing/transactions/ktConversionMt/delete/"+vMtId+".html",
			type:"GET",
			success : function(data){
				
				if(data === 'true'){
					window.location.reload(true);
				}else{
					displayMsg(event, this, 'You cannot delete this record');
				}
				
			}
		});
		
	}


</script>










