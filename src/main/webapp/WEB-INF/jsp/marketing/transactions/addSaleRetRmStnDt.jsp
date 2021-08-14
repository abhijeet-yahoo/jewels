<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modalSaleRetRmStnDt.jsp"%>
<%@ include file="/WEB-INF/jsp/common/modalSaleRowStnDtPickup.jsp"%>

<div class="panel-body">

 		
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12">
					<div id="toolbarStnRec">
						<a class="btn btn-info" style="font-size: 15px" type="button" id="saleRetStnAddId"
							onclick="javascript:popSaleRetRmStnDtEntry();"> <span
							class="glyphicon glyphicon-plus"></span>&nbsp;Add
						</a>
						
						<a class="btn btn-primary" style="font-size: 15px" type="button" id="saleRetPickupRowStnBtnId"
							onclick="javascript:popPickupSaleIssStone();"> <span
							class="glyphicon glyphicon-plus"></span>&nbsp;Pickup
						</a>
						
					</div>
					<div class="table-responsive">
						<table id="saleRetRmStnTblId" data-toggle="table" data-height="400" data-search="true" data-toolbar="#toolbarStnRec"
								data-side-pagination="server" data-click-to-select="true"
								data-page-list="[5, 10, 20, 50, 100, 200]" 
								 data-pagination="true"	 >
							<thead>
								<tr class="btn-primary">
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
									<th data-field="diffCarat" data-sortable="true">Diff Carat</th>
									<!-- <th data-field="balStone" data-sortable="true">BalStone</th>
									<th data-field="balCarat" data-sortable="true">BalCarat</th> -->
									<th data-field="rate" data-sortable="true">Rate</th>
									<th data-field="amount" data-sortable="true">Amount</th>
									<th data-field="packetNo" data-sortable="true">Packet No</th>
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
							$('#saleRetRmStnDtId').on('click', function() {
				popSaleRetRmStnDt();
				
				});
		
				
			});
	
	function popSaleRetRmStnDt() {
		$("#saleRetRmStnTblId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/marketing/transactions/saleRetRmStnDt/listing.html?mtId="+$('#id').val()+"&disableFlg="+disableFlg, 	 /* $('#saleRetMtId').val(), */
						});
	}
	
	function popSaleRetRmStnDtEntry(){
		
		$('#mySaleRetRmStnDtModal').modal('show');
		$('#vSaleRetRmStnMtId').val(mtid);				/* val($('#saleRetMtId').val()); */
	}
	

	function editSaleRetRmStnDt(e,mtId,reftranId) {

		if(reftranId === null){
					$.ajax({
						url : "/jewels/marketing/transactions/saleRetRmStnDt/edit/"+ mtId + ".html",
						type : 'GET',
						dataType:"JSON",
						success : function(data) {
						
							var validator = $( ".saleRetRmStnDtForm" ).validate();
							validator.resetForm();
							
							$('#mySaleRetRmStnDtModal').modal('show');

							$.each(data,function(k,v){
								
								$('#saleRetRmStnDtModalDivId  #'+k).val(v);
								
								if(k === 'quality'){
									$('#shape\\.id').trigger('onchange');
									setTimeout(function() {

										$('#quality\\.id').val(v);
									}, 600);

								}
								
								if(k === 'size'){
									
									$('#shape\\.id').trigger('onchange');
									setTimeout(function() {

										$('#size').val(v);
									}, 600);

								}
								
							
								
							});
						}
					});	
				
		}else{
			alert('Pickup detail can not edit');
			}
				
	
	
	}
	
	function deleteSaleRetRmStnDt(e,dtId){
		
		displayDlg(
				e,
				'javascript:deleteSaleRetRmStnDtData('+dtId+');',
				'Delete',
				'Do you want to Delete  ?',
				'Continue');
	}

	function deleteSaleRetRmStnDtData(dtId){

		$("#modalDialog").modal("hide");
		
		$.ajax({
			
			url:'/jewels/marketing/transactions/saleRetRmStnDt/delete/'+dtId+'.html',
			data: 'GET',
			success : function(data){

				if(data == "1"){
					popSaleRetRmStnDt();
					
				}else{
					
					displayMsg(event, this, data);
					
					}
				
			}
			
		})
		
	}


	function popPickupSaleIssStone(){

		$('#myRowStoneSaleMtModal').modal('show');

		popSaleMtStoneRowPickList();

		}
	

</script>