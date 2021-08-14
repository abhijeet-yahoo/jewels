<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/common/modalSaleRmStnDt.jsp"%>  

<div class="panel-body">

 		
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12">
					<div id="toolbarStnIss">
						<a class="btn btn-info" style="font-size: 15px" type="button"  id="saleRowStnBtnId"
							onclick="javascript:popSaleRmStnDtEntry();"> <span
							class="glyphicon glyphicon-plus"></span>&nbsp;Add
						</a>
					</div>
					<div class="table-responsive">
						<table id="saleRmStnTblId" data-toggle="table" data-height="400" data-search="true" data-toolbar="#toolbarStnIss"
								data-side-pagination="server" data-click-to-select="true"
								data-page-list="[5, 10, 20, 50, 100, 200]" 
								 data-pagination="true"	 >
							<thead>
								<tr class="btn-primary">
								
								<!-- <th data-field="action1" data-align="center">Edit</th> -->
									<th data-field="action2" data-align="center">Delete</th>
									<th data-field="department" data-sortable="true">Location</th>
								<th data-field="stoneType" data-sortable="true">Stone Type</th>
									<th data-field="shape" data-align="left">Shape</th>
									<th data-field="subShape" data-align="left">SubShape</th>
									<th data-field="quality" data-sortable="true">Quality</th>
									<th data-field="stoneChart" data-sortable="true">Size</th>
									<th data-field="sieve" data-sortable="true">Sieve</th>
									<th data-field="sizeGroupStr" data-sortable="true">Size Group</th>
									<th data-field="stone" data-sortable="true">Stone</th>
									<th data-field="carat" data-sortable="true">Carat</th>
									<!-- <th data-field="diffCarat" data-sortable="true">Diff Carat</th> 
									<th data-field="balStone" data-sortable="true">BalStone</th>
									<th data-field="balCarat" data-sortable="true">BalCarat</th> -->
									<th data-field="rate" data-sortable="true">Rate</th>
									<th data-field="amount" data-sortable="true">Amount</th>
									<th data-field="packetNo" data-sortable="true">Packet No</th>
									<th data-field="remark" data-sortable="true">Remark</th>
									
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
							$('#saleStnRmDtId').on('click', function() {
				popSaleRmStnDt();
				
				});
		
				
			});
	
	function popSaleRmStnDt() {

	$("#saleRmStnTblId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/marketing/transactions/saleRmStnDt/listing.html?mtId="+mtid+"&disableFlg="+disableFlg,        /*  $('#saleMtId').val(), */
						});
	}
	
	function popSaleRmStnDtEntry(){
		
		$('#mySaleRmStnDtModal').modal('show');
		$('#vSaleRmStnMtId').val(mtid);																		/* val($('#saleMtId').val()); */
	}
	

	function editSaleRmStnDt(mtId) {
				
					$.ajax({
						url : "/jewels/marketing/transactions/saleRmStnDt/edit/"+ mtId + ".html",
						type : 'GET',
						dataType:"JSON",
						success : function(data) {
						
							var validator = $( ".saleRmStnDtForm" ).validate();
							validator.resetForm();
							
							$('#mySaleRmStnDtModal').modal('show');

							$.each(data,function(k,v){
								
								$('#saleRmStnDtModalDivId  #'+k).val(v);
								
								if(k === 'quality'){
									$('#shape\\.id').trigger('onchange');
									setTimeout(function() {

										$('#quality\\.id').val(v);
									}, 500);

								}
								
								if(k === 'size'){
									
									$('#shape\\.id').trigger('onchange');
									setTimeout(function() {

										$('#size').val(v);
									}, 500);

								}
								
							
								
							});
						}
					});	
	
	}
	
	function deleteSaleRmStnDt(e,dtId){
		
		displayDlg(
				e,
				'javascript:deleteSaleRmStnDtData('+dtId+');',
				'Delete',
				'Do you want to Delete  ?',
				'Continue');
	}

	function deleteSaleRmStnDtData(dtId){

		$("#modalDialog").modal("hide");
		
		$.ajax({
			
			url:'/jewels/marketing/transactions/saleRmStnDt/delete/'+dtId+'.html',
			data: 'GET',
			success : function(data){
				
								
				if(Number(data) == -1){
					displayMsg(event, this, 'You Can Not Delete This Record, Contact Administrator');
					
				}else{
					popSaleRmStnDt();
					}
			}
			
		})
		
	}




	/* Loose Stone Load Success Code */

	$('#saleRmStnTblId').bootstrapTable({})
	.on(
			'load-success.bs.table',
			function(e, data) {

				var data = JSON.stringify($("#saleRmStnTblId").bootstrapTable('getData'));
				
				var vTotStoneValue = 0.0;
				var vTotFob = 0.0;

				$.each(JSON.parse(data), function(idx, obj) {
					
					vTotStoneValue		+= Number(obj.amount);
					vTotFob		+= Number(obj.amount);
				});
				
				$('#totStoneValue').val((vTotStoneValue).toFixed(2));
				$('#totalFob').val((vTotFob).toFixed(2));

				
			});

</script>