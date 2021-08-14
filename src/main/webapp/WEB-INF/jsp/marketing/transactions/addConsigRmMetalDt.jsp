<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/common/modalConsigRmMetalDt.jsp"%>  

<div class="panel-body">

 		
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12">
					<div id="toolbar">
						<a class="btn btn-info" style="font-size: 15px" type="button" id="consigRowMetalBtnId"
							onclick="javascript:popConsigRmMetalDtEntry();"> <span
							class="glyphicon glyphicon-plus"></span>&nbsp;Add 
						</a>
						
						
					</div>
					<div class="table-responsive">
						<table id="consigRmMetalTblId" data-toggle="table" data-height="400" data-search="true" data-toolbar="#toolbar"
								data-side-pagination="server" data-click-to-select="true"
								data-page-list="[5, 10, 20, 50, 100, 200]" 
								 data-pagination="true"	 >
							<thead>
								<tr class="btn-primary">
									
									<!-- <th data-field="action1" data-align="center">Edit</th> -->
									<th data-field="action2" data-align="center">Delete</th>
									<th data-field="department" data-sortable="true">Location</th>
									<th data-field="metal" data-sortable="true">Metal</th>
									<th data-field="purity" data-align="left">Purity</th>
									<th data-field="color" data-sortable="true">Color</th>
									<th data-field="metalWt" data-sortable="true">Metal Wt</th>
									<th data-field="rate" data-sortable="true">Rate</th>
									<th data-field="amount" data-sortable="true">Amount</th>
									
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>

		
		</div>
		
	</div>

	
	<script type="text/javascript">
	$(document).ready(
			function(e) {
							$('#consigRmMetalDtId').on('click', function() {
				popConsigRmMetalDt();
				
				});
		
				
			});
	
	function popConsigRmMetalDt() {
		$("#consigRmMetalTblId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/marketing/transactions/consigRmMetalDt/listing.html?mtId="+ mtid+"&disableFlg="+disableFlg,
						});
	}
	
	function popConsigRmMetalDtEntry(){
		
		$('#myConsigRmMetalDtModal').modal('show');
		$('#vConsigMtId').val(mtid);
	}
	

		function editConsigRmMetalDt(mtId) {
				
					$.ajax({
						url : "/jewels/marketing/transactions/consigRmMetalDt/edit/"+ mtId + ".html",
						type : 'GET',
						dataType:"JSON",
						success : function(data) {
						
							var validator = $( ".consigRmMetalDtForm" ).validate();
							validator.resetForm();
							
							$('#myConsigRmMetalDtModal').modal('show');

							$.each(data,function(k,v){
								$('#consigRmMetalDtModalDivId  #'+k).val(v);
								
							});
						}
					});	
				
			
				
	
	
	}  
	
	function deleteConsigRmMetalDt(e,dtId){
		
		displayDlg(
				e,
				'javascript:deleteConsigRmMetalDtData('+dtId+');',
				'Delete',
				'Do you want to Delete  ?',
				'Continue');
	} 

		function deleteConsigRmMetalDtData(dtId){

		$("#modalDialog").modal("hide");
		
		$.ajax({
			
			url:'/jewels/marketing/transactions/consigRmMetalDt/delete/'+dtId+'.html',
			data: 'GET',
			success : function(data){
								
				if(data == "1"){
					popConsigRmMetalDt();
				}else{
					displayMsg(event, this, 'You Can Not Delete This Record, Contact Administrator');
					
					}
				
			}
			
		})
		
	}   


		
	

</script>