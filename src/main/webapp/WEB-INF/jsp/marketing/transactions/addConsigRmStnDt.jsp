<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/common/modalConsigRmStnDt.jsp"%>  

<div class="panel-body">

 		
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12">
					<div id="toolbarStnIss">
						<a class="btn btn-info" style="font-size: 15px" type="button" id="consigRowStnBtnId"
							onclick="javascript:popConsigRmStnDtEntry();"> <span
							class="glyphicon glyphicon-plus"></span>&nbsp;Add
						</a>
					</div>
					<div class="table-responsive">
						<table id="consigRmStnTblId" data-toggle="table" data-height="400" data-search="true" data-toolbar="#toolbarStnIss"
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
									<th data-field="rate1" data-sortable="true">Rate</th>
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
							$('#consigRmStnDtId').on('click', function() {
				popConsigRmStnDt();
				
				});
		
				
			});
	
	function popConsigRmStnDt() {
		$("#consigRmStnTblId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/marketing/transactions/consigRmStnDt/listing.html?mtId="+mtid+"&disableFlg="+disableFlg,        /*  $('#consigMtId').val(), */
						});
	}
	
	function popConsigRmStnDtEntry(){
		
		$('#myConsigRmStnDtModal').modal('show');
		$('#vConsigRmStnMtId').val(mtid);																		/* val($('#consigMtId').val()); */
	}
	

	function editConsigRmStnDt(mtId) {
				
					$.ajax({
						url : "/jewels/marketing/transactions/consigRmStnDt/edit/"+ mtId + ".html",
						type : 'GET',
						dataType:"JSON",
						success : function(data) {
						
							var validator = $( ".consigRmStnDtForm" ).validate();
							validator.resetForm();
							
							$('#myConsigRmStnDtModal').modal('show');

							$.each(data,function(k,v){
								
								$('#consigRmStnDtModalDivId  #'+k).val(v);
								
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
	
	function deleteConsigRmStnDt(e,dtId){
		
		displayDlg(
				e,
				'javascript:deleteConsigRmStnDtData('+dtId+');',
				'Delete',
				'Do you want to Delete  ?',
				'Continue');
	}

	function deleteConsigRmStnDtData(dtId){

		$("#modalDialog").modal("hide");
		
		$.ajax({
			
			url:'/jewels/marketing/transactions/consigRmStnDt/delete/'+dtId+'.html',
			data: 'GET',
			success : function(data){

				if(data == "1"){
					popConsigRmStnDt();
				}else{
					displayMsg(event, this, 'You Can Not Delete This Record, Contact Administrator');
					
					}
								
				
			}
			
		})
		
	}
	

</script>