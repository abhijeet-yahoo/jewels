<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modalSaleRetRmCompDt.jsp"%>
<%@ include file="/WEB-INF/jsp/common/modalSaleRowCompDtPickup.jsp"%>

<div class="panel-body">

 		
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12">
					<div id="toolbarCompRec">
					
						<a class="btn btn-info" style="font-size: 15px" type="button" id="saleRetCompAddId"
							onclick="javascript:popSaleRetRmCompDtEntry();"> <span
							class="glyphicon glyphicon-plus"></span>&nbsp;Add
						</a>
						
						<a class="btn btn-primary" style="font-size: 15px" type="button"  id="saleRetPickupRowCompBtnId"
							onclick="javascript:popPickupSaleIssComponent();"> <span
							class="glyphicon glyphicon-plus"></span>&nbsp;Pickup
						</a>
				
					</div>
					<div class="table-responsive">
						<table id="saleRetRmCompTblId" data-toggle="table" data-height="400" data-search="true" 
						data-toolbar="#toolbarCompRec" data-side-pagination="server" data-click-to-select="true"
								data-page-list="[5, 10, 20, 50, 100, 200]" 
								 data-pagination="true"	 >
							<thead>
								<tr class="btn-primary">
									<th data-field="department" data-sortable="true">Department</th>
									<th data-field="metal" data-sortable="true">Metal</th>
									<th data-field="component" data-sortable="true">Component</th>
									<th data-field="purity" data-align="left">Purity</th>
									<th data-field="color" data-sortable="true">Color</th>
									<th data-field="qty" data-sortable="true">Qty</th>
									<th data-field="metalWt" data-sortable="true">Metal Wt</th>
									<th data-field="rate" data-sortable="true">Rate</th>
									<th data-field="amount" data-sortable="true">Amount</th>
									<th data-field="action1" data-align="center">Edit</th>
									<th data-field="action2" data-align="center">Delete</th></tr>
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
							$('#saleRetRmCompDtId').on('click', function() {
						
							popSaleRetRmCompDt();
						
				});
		
				
			});
	
	function popSaleRetRmCompDt() {
		
		$("#saleRetRmCompTblId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/marketing/transactions/saleRetRmCompDt/listing.html?mtId="+$('#id').val()+"&disableFlg="+disableFlg,			/*  $('#saleRetMtId').val(), */
						});
	}
	
	function popSaleRetRmCompDtEntry(){
		
		$('#mySaleRetRmCompDtModal').modal('show');
		$('#vSaleRetRmCompMtId').val(mtid);											/* val($('#saleRetMtId').val()); */
	}
	

	function editSaleRetRmCompDt(e,mtId,reftranId) {

		console.log('e  '+e);
		console.log('mtId  '+mtId);
		console.log('reftranId  '+reftranId);
		
		if(reftranId === null){
					$.ajax({
						url : "/jewels/marketing/transactions/saleRetRmCompDt/edit/"+ mtId + ".html",
						type : 'GET',
						dataType:"JSON",
						success : function(data) {
						
							var validator = $( ".saleRetRmCompDtForm" ).validate();
							validator.resetForm();
							
							$('#mySaleRetRmCompDtModal').modal('show');

							$.each(data,function(k,v){
								
								$('#saleRetRmCompDtModalDivId  #'+k).val(v);
								
							});
						}
					});	
		}else{
			alert('Pickup detail can not edit');
			}	
	
	}
	
	function deleteSaleRetRmCompDt(e,dtId){
		
		displayDlg(
				e,
				'javascript:deleteSaleRetRmCompDtData('+dtId+');',
				'Delete',
				'Do you want to Delete  ?',
				'Continue');
	}

	function deleteSaleRetRmCompDtData(dtId){

		$("#modalDialog").modal("hide");
		
		$.ajax({
			
			url:'/jewels/marketing/transactions/saleRetRmCompDt/delete/'+dtId+'.html',
			data: 'GET',
			success : function(data){
				
								
				if(Number(data) == -1){
					displayMsg(event, this, 'You Can Not Delete This Record, Contact Administrator');
					
				}else{
					popSaleRetRmCompDt();
					}
			}
			
		})
		
	}

	function popPickupSaleIssComponent(){
		$('#myRowCompDtSaleIssModal').modal('show');

		popSaleMtRowCompPickList();
		}
	

</script>