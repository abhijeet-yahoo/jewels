<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/common/modalSaleRmMetalDt.jsp"%>  

<div class="panel-body">

 		
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12">
					<div id="toolbar">
						<a class="btn btn-info" style="font-size: 15px" type="button"  id="salesRowMetalBtnId"
							onclick="javascript:popSaleRmMetalDtEntry();"> <span
							class="glyphicon glyphicon-plus"></span>&nbsp;Add 
						</a>
					</div>
					<div class="table-responsive">
						<table id="saleRmMetalTblId" data-toggle="table" data-height="400" data-search="true" data-toolbar="#toolbar"
								data-side-pagination="server" data-click-to-select="true"
								data-page-list="[5, 10, 20, 50, 100, 200]" 
								 data-pagination="true"	 >
							<thead>
								<tr class="btn-primary">
									
									<th data-field="action1" data-align="center">Edit</th>
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
							$('#saleRmMetalDtId').on('click', function() {
				popSaleRmMetalDt();
				
				});
		
				
			});
	
	function popSaleRmMetalDt() {
		$("#saleRmMetalTblId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/marketing/transactions/saleRmMetalDt/listing.html?mtId="+ mtid+"&disableFlg="+disableFlg,
						});
	}
	
	function popSaleRmMetalDtEntry(){
		
		$('#mySaleRmMetalDtModal').modal('show');
		$('#vSaleMtId').val(mtid);
	}
	

		function editSaleRmMetalDt(mtId) {
				
					$.ajax({
						url : "/jewels/marketing/transactions/saleRmMetalDt/edit/"+ mtId + ".html",
						type : 'GET',
						dataType:"JSON",
						success : function(data) {
						
							var validator = $( ".saleRmMetalDtForm" ).validate();
							validator.resetForm();
							
							$('#mySaleRmMetalDtModal').modal('show');

							$.each(data,function(k,v){
								$('#saleRmMetalDtModalDivId  #'+k).val(v);
								
							});
						}
					});	
				
			
				
	
	
	}  
	
	function deleteSaleRmMetalDt(e,dtId){
		
		displayDlg(
				e,
				'javascript:deleteSaleRmMetalDtData('+dtId+');',
				'Delete',
				'Do you want to Delete  ?',
				'Continue');
	} 

		function deleteSaleRmMetalDtData(dtId){

		$("#modalDialog").modal("hide");
		
		$.ajax({
			
			url:'/jewels/marketing/transactions/saleRmMetalDt/delete/'+dtId+'.html',
			data: 'GET',
			success : function(data){
				
								
				if(Number(data) == -1){
					displayMsg(event, this, 'You Can Not Delete This Record, Contact Administrator');
					
				}else{
					
					popSaleRmMetalDt();
					
					}
				
			}
			
		})
		
	}   




		/* Loose Metal Load Success Code */

		$('#saleRmMetalTblId').bootstrapTable({})
		.on(
				'load-success.bs.table',
				function(e, data) {

					var data = JSON.stringify($("#saleRmMetalTblId").bootstrapTable('getData'));
					
					var vTotStoneValue = 0.0;
					var vTotFob = 0.0;

					$.each(JSON.parse(data), function(idx, obj) {
						
						vTotStoneValue		+= Number(obj.amount);
						vTotFob		+= Number(obj.amount);
					});
					
					$('#totMetalValue').val((vTotStoneValue).toFixed(2));
					$('#totalFob').val((vTotFob).toFixed(2));

					
				});

		

</script>