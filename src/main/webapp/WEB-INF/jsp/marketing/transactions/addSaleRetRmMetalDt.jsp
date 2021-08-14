<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>


 <%@ include file="/WEB-INF/jsp/common/modalSaleRetRmMetalDt.jsp"%> 
  <%@ include file="/WEB-INF/jsp/common/modalSaleIssRmMetalDt.jsp"%> 
  

<div class="panel-body">

 	
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12">
					<div id="toolbar1">
						<a class="btn btn-info" style="font-size: 15px" type="button" id="saleRetMetalAddId"
							onclick="javascript:popSaleRetRmMetalDtEntry();"> <span
							class="glyphicon glyphicon-plus"></span>&nbsp;Add
						</a>
						<a class="btn btn-info" style="font-size: 15px" type="button" id="saleRetMetalPickupId"
							onclick="javascript:popSaleIssMetalDtPickup();"> <span
							class="glyphicon glyphicon-plus"></span>&nbsp;Pickup
						</a>
					</div>
					<div class="table-responsive">
						<table id="saleRetRmMetalTblId" data-toggle="table" data-height="400" data-search="true" data-toolbar="#toolbar1"
								data-side-pagination="server" data-click-to-select="true"
								data-page-list="[5, 10, 20, 50, 100, 200]" 
								 data-pagination="true"	 >
							<thead>
								<tr class="btn-primary">
									<th data-field="department" data-sortable="true">Location</th>
									<th data-field="metal" data-sortable="true">Metal</th>
									<th data-field="purity" data-align="left">Purity</th>
									<th data-field="color" data-sortable="true">Color</th>
									<th data-field="metalWt" data-sortable="true">Metal Wt</th>
									<th data-field="rate" data-sortable="true">Rate</th>
									<th data-field="amount" data-sortable="true">Amount</th>
									<th data-field="remark" data-sortable="true">Remark</th>
									<th data-field="action1" data-align="center">Edit</th>
									<th data-field="action2" data-align="center">Delete</th>
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
							$('#saleRetRmMetalDtId').on('click', function() {
				popSaleRetRmMetalDt();
				
				});
		
				
			});
	
	function popSaleRetRmMetalDt() {
		$("#saleRetRmMetalTblId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/marketing/transactions/saleRetRmMetalDt/listing.html?mtId="+mtid+"&disableFlg="+disableFlg, 															/* $('#saleRetMtId').val(), */
						});
	}
	
	function popSaleRetRmMetalDtEntry(){
		
		$('#mySaleRetRmMetalDtModal').modal('show');
		$('#vSaleRetMMtId').val(mtid);														/* val($('#saleRetMtId').val()); */
	}
	

	function editSaleRetRmMetalDt(e,mtId,reftranId) {

		if(reftranId === null){
				
					$.ajax({
						url : "/jewels/marketing/transactions/saleRetRmMetalDt/edit/"+ mtId + ".html",
						type : 'GET',
						dataType:"JSON",
						success : function(data) {
						
							var validator = $( ".saleRetRmMetalDtForm" ).validate();
							validator.resetForm();
							
							$('#mySaleRetRmMetalDtModal').modal('show');

							$.each(data,function(k,v){	
								$('#saleRetRmMetalDtModalDivId  #'+k).val(v);
								
							});
						}
					});	
			}else{
				alert('Pickup detail can not edit');
				}
			
				
	
	
	}
	
	function deleteSaleRetRmMetalDt(e,dtId){
		
		displayDlg(
				e,
				'javascript:deleteSaleRetRmMetalDtData('+dtId+');',
				'Delete',
				'Do you want to Delete  ?',
				'Continue');
	}

	function deleteSaleRetRmMetalDtData(dtId){

		$("#modalDialog").modal("hide");
		
		$.ajax({
			
			url:'/jewels/marketing/transactions/saleRetRmMetalDt/delete/'+dtId+'.html',
			data: 'GET',
			success : function(data){
								
				if(Number(data) == -1){
					displayMsg(event, this, 'You Can Not Delete This Record, Contact Administrator');
					
				}else{
					
					popSaleRetRmMetalDt();
					
					}
				
			}
			
		})
		
	}


	function popSaleIssMetalDtPickup(){

			$('#mySaleIssRmMetalDtModal').modal('show');
			 $('#mySaleIssRmMetalDtModal').on('shown.bs.modal', function () {
				 popSaleMtForMetalPickList();
		        });
		  
		}
	

</script>